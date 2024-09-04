package Simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class UpdateUserSImulation extends Simulation {

  val httpconfi = http.baseUrl(url ="https://reqres.in")
    .header(name ="Accept", value = "application/json")
    .header(name ="content-type" , value = "application/json")

  val scn = scenario(scenarioName = "Update User")
    .exec(http(requestName = "Update User request")
    .put("/api/users/2")
    .body(RawFileBody(filePath = "./src/test/resources/Bodies/updateUser.json")).asJson
  .check(status.in(expected = 200 to 201)))

  setUp(scn.inject(atOnceUsers(users = 1))).protocols(httpconfi)

}



