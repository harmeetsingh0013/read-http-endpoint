package com.knoldus

import scala.collection.mutable
import scala.concurrent.Future

/**
  * Created by harmeet on 8/6/17.
  */
class Database {

  private val database: mutable.Map[String, Vector[GithubRepo]] = new mutable.HashMap[String, Vector[GithubRepo]]

  def findGithubRepo(value: String): Future[Vector[GithubRepo]] = {
    database.get(value) match  {
      case Some(values) => Future.successful(values)
      case None => Future.successful(Vector.empty[GithubRepo])
    }
  }

  def insertOrUpdateRecord(key: String, values: Vector[GithubRepo]) = {
    database.put(key, values)
  }
}
