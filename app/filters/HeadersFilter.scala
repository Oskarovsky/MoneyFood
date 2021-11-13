package filters

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, Materializer}
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import play.api.http.HeaderNames._
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._



/**
 * The HeadersFilter.noCache filter adds all the headers to a response, which are required to disable caching in browsers.
 * PRAGMA, CACHE_CONTROL, and EXPIRES are constants provided by play.api.http.HeaderNames.
 * */

object HeadersFilter {

  implicit val sys: ActorSystem = ActorSystem("MyTest")
  implicit val mat: Materializer = ActorMaterializer()

  val noCache: Filter = Filter {
    (nextFilter, rh) =>
      nextFilter(rh) map {
        result: Result => addNoCacheHeaders(result)
      }
  }

  private def addNoCacheHeaders(result: Result): Result = {
    result.withHeaders(
      PRAGMA -> "no-cache",
      CACHE_CONTROL -> "no-cache, no-store, must-revalidate, max-age=0",
      EXPIRES -> serverTime)
  }

  private def serverTime = {
    val now = new DateTime()
    val dateFormat = DateTimeFormat.forPattern(
      "EEE, dd MMM yyyy HH:mm:ss z")
    dateFormat.print(now)
  }
}
