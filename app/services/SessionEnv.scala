package services

import com.mohiva.play.silhouette.impl.authenticators.SessionAuthenticator
import com.mohiva.play.silhouette.api.{ Environment }

/**
  * Created by diogo on 07.07.16.
  * TODO: https://github.com/KadekM/play-slick-silhouette-auth-api/tree/persistence-h2
  */
trait SessionEnv extends Environment {
  type I = user
  type A = SessionAuthenticator
}
