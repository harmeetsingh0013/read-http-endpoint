package com.knoldus

import argonaut.Argonaut


/**
  * Created by harmeet on 8/6/17.
  */
case class Owner(
                  avatarUrl: String
                )

object Owner {
  implicit val decoder = Argonaut.casecodec1(Owner.apply, Owner.unapply)("avatar_url")
}

case class GithubRepo(
                       id: Long,
                       name: String,
                       full_name: String,
                       owner: Owner,
                       url: String,
                       languages_url: String,
                       ssh_url: String,
                       clone_url: String,
                       homepage: String,
                       watchers_count: Long,
                       watchers: Long,
                       forks: Long
                     )

object GithubRepo {
  implicit val decoder = Argonaut.casecodec12(GithubRepo.apply, GithubRepo.unapply)("id", "name", "full_name", "owner",
    "url", "languages_url", "ssh_url", "clone_url", "homepage", "watchers_count", "watchers", "forks")
}

case class JsonResponse(
                         total_count: Long,
                         items: Vector[GithubRepo]
                       )

object JsonResponse {

  implicit val decoder = Argonaut.casecodec2(JsonResponse.apply, JsonResponse.unapply)("total_count", "items")
}