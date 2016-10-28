package models.daos

import javax.inject._
import models.JobPosition
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.db.NamedDatabase

import slick.driver.JdbcProfile
import com.github.tototoshi.slick.PostgresJodaSupport._
//import slick.driver.PostgresDriver.api._
import org.joda.time.DateTime


import scala.concurrent.Future

/**
  * Created by diogo on 19.07.16.
  * Doubts about DAOs? Check the most basic example ever: https://github.com/playframework/play-slick/blob/master/samples/basic/app/dao/DogDAO.scala
  * @NamedDatabase("slick")
  */

class JobPositionDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val JobPositions = TableQuery[JobPositionsTable]

  def all(): Future[Seq[JobPosition]] = db.run(JobPositions.result)

  def findById(id: Long): Future[JobPosition] = db.run(JobPositions.filter(_.id === id ).result.head)

  def insert(jobPosition: JobPosition): Future[Long] = {
    //db.run(Companies += company).map { _ => () } // note: this returns Unit/None
    //http://stackoverflow.com/questions/21894377/returning-autoinc-id-after-insert-in-slick-2-0
    db.run( (JobPositions returning JobPositions.map(_.id) ) += jobPosition )
  }

  def update(id: Long, job: JobPosition) = {
    db.run(JobPositions.filter(_.id === id).update(job))
  }

  def delete(id: Long) = db.run(JobPositions.filter(_.id === id).delete)

  private class JobPositionsTable(tag: Tag) extends Table[JobPosition](tag, "jobPosition") {
    /*
    id: Option[Long],
                  title: String,
                  description: Option[String],
                  company_id: Long,
                  tags: Option[String],
                  date_created: DateTime,
                  date_closed: DateTime
     */
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def title = column[String]("title")
    def description = column[Option[String]]("description")
    def company_id = column[Long]("company_id")
    def tags = column[Option[String]]("tags")
    def date_created = column[DateTime]("date_created")
    def date_closed = column[DateTime]("date_closed")

    def * = (id, title, description, company_id, tags,date_created, date_closed) <> (JobPosition.tupled, JobPosition.unapply _)
  }

}
