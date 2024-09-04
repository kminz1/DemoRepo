package Simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestAPISImulation extends Simulation{

  // http config
  val httpconfi = http.baseUrl(url ="https://reqres.in")
     .header(name ="Accept", value = "application/json")
     .header(name ="content-type" , value = "application/json")


   // scenario
 val scn = scenario(scenarioName = "Get User")
    .exec(http(requestName = "get user request")
    .get("/api/users/2")
    .check(status is 200))

  //setup
  setUp(scn.inject(atOnceUsers( users =100))).protocols(httpconfi)

}
