package com.knoldus

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import com.knoldus.SearchData.Response

import scala.concurrent.Future
import scala.concurrent.duration.DurationDouble

/**
  * Created by harmeet on 8/6/17.
  */
object Launcher extends App {

  val system = ActorSystem("demo")
  implicit val timeout = Timeout(5 seconds)
  implicit val ex = scala.concurrent.ExecutionContext.Implicits.global

  val db = new Database
  val serRef = system.actorOf(SearchData.props(database = db))

  def request: Future[Vector[GithubRepo]] = {
    db.findGithubRepo("java").flatMap {
      case (repo +: repos) => Future.successful(repo +: repos)
      case repos => (serRef ? "java").mapTo[Response].map(_.repos)
    }
  }

  request.foreach(values => println("######## "+values))
}
