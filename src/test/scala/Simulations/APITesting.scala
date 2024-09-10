package Simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

class APITesting extends Simulation{

  val httpconf = http.baseUrl(url ="https://reqres.in/")
    .header(name ="Accept" , value ="application/json")


 //  Check correlation and extract data
    def GetAllUser() :ChainBuilder ={ {
      exec (http (requestName = "Get All users")
    .get ("/api/users?page=2")
    .check (jsonPath (path = "$.data[0].id").saveAs (key = "UserId") ) )
    }
    }
def GetSingleUser():ChainBuilder= { {
  exec (http (requestName = "Get Specific User")
  //public/v2/users/7396319
.get ("/api/users/${UserId}")
.check (jsonPath (path = "$.data.id").is (expected = "7") )
.check (jsonPath (path = "$.data.first_name").is (expected = "Michael") ) )
}
}
  val scn = scenario(scenarioName = "User Request")
    .exec(GetAllUser())
    .pause(3)
    .exec(GetSingleUser())

setUp(scn.inject(atOnceUsers(users = 1))).protocols(httpconf)
}
