package parcel

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseURL("http://localhost")
		.inferHtmlResources()
		.acceptHeader("*/*")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-GB,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
		"Access-Control-Request-Headers" -> "content-type",
		"Access-Control-Request-Method" -> "POST",
		"Origin" -> "http://192.168.56.101")

	val headers_4 = Map(
		"Accept" -> "application/json, text/plain, */*",
		"Content-Type" -> "application/json;charset=utf-8",
		"Origin" -> "http://192.168.56.101")

    val uri1 = "http://192.168.56.100/js"
    val uri2 = "192.168.56.101"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get("/images/icon_pakete.png")
			.resources(http("request_1")
			.get(uri1 + "/app.js"),
            http("request_2")
			.get(uri1 + "/parcel-price.component.js"))
			.check(status.is(404)))
		.pause(16)
		.exec(http("request_3")
			.options("http://" + uri2 + ":8443/parcel/size")
			.headers(headers_3)
			.resources(http("request_4")
			.post("http://" + uri2 + ":8443/parcel/size")
			.headers(headers_4)
			.body(RawFileBody("RecordedSimulation_0004_request.txt"))))

	setUp(scn.inject(atOnceUsers(100))).protocols(httpProtocol)
}
