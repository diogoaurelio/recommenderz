package models.daos


import models.Company
import javax.inject._
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
class CompanyDAO @Inject()( protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  private val Companies = TableQuery[CompaniesTable]

  def all(): Future[Seq[Company]] = db.run(Companies.result)

  def findById(id: String): Future[Option[Company]] = db.run(Companies.filter(_.id === id ).result.headOption)

  def insert(company: Company): Future[String] = {
    //db.run(Companies += company).map { _ => () } // note this returns Unit/None
    //http://stackoverflow.com/questions/21894377/returning-autoinc-id-after-insert-in-slick-2-0
    db.run( (Companies returning Companies.map(_.id) ) += company )
  }

  private class CompaniesTable(tag: Tag) extends Table[Company](tag, "company") {
    // TODO: change back to long
    def id = column[String]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def description = column[String]("description")
    def logo = column[String]("logo")

    def * = (id, name, description, logo) <> (Company.tupled, Company.unapply _)
  }

}
