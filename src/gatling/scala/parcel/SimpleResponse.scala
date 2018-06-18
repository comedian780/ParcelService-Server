package parcelService

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class SimpleResponse extends Simulation {

	val httpProtocol = http
		.baseURL("http://www.allgaeu-parcel-service.de:8443")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")

	val headers_5 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Access-Control-Request-Headers" -> "content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "http://localhost")

	val headers_6 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json;charset=utf-8",
		"Origin" -> "http://localhost")

    val uri1 = "http://asset.allgaeu-parcel-service.de/js"
    val uri2 = "http://www.allgaeu-parcel-service.de:8443/parcel/size"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get(uri1 + "/parcel-address.component.js")
			.resources(http("request_1")
			.get(uri1 + "/parcel-option.component.js"),
            http("request_2")
			.get(uri1 + "/app.js"),
            http("request_3")
			.get(uri1 + "/parcel-price.component.js"),
            http("request_4")
			.get(uri1 + "/parcel-size.component.js")))
		.pause(6)
		.exec(http("request_5")
			.options("/parcel/size")
			.headers(headers_5)
			.resources(http("request_6")
			.post("/parcel/size")
			.headers(headers_6)
			.body(RawFileBody("SimpleResponse.txt"))))

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
