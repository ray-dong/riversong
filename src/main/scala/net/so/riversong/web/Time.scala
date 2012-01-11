package net.so.riversong.web

import unfiltered.request._
import unfiltered.response._

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Date
import unfiltered.filter.Plan

object Time extends Plan {

  val logger = LoggerFactory.getLogger(getClass().getName())

  def intent = {
    case req @ GET(Path("/time")) =>
      logger.debug("GET /time")
      //todo: wrong typee of req?
        view(new java.util.Date().toString)
  }



  def view(time: String) = {
    Html(
      <html><body>
        The current time is: { time }
      </body></html>
    )
  }


}