package com.knoldus

import akka.actor.{Actor, ActorLogging, Props}
import com.knoldus.SearchData.Response
import org.http4s.argonaut._
import org.http4s.client.blaze.PooledHttp1Client

/**
  * Created by harmeet on 8/6/17.
  */
class SearchData(database: Database) extends Actor with ActorLogging {

  val httpClient = PooledHttp1Client()

  override def receive: Receive = {
    case value: String => {
      val request = httpClient.expect[JsonResponse](s"https://api.github.com/search/repositories" +
        s"?q=topic:${value}&sort=stars&order=desc")(jsonOf[JsonResponse])
      request.map { resp =>
        database.insertOrUpdateRecord(value, resp.items)
        sender() ! Response(resp.items)
      }.run
      httpClient.shutdown
    }
  }
}

object SearchData {

  case class Response(repos: Vector[GithubRepo])

  def props(database: Database) = Props(classOf[SearchData], database)
}
