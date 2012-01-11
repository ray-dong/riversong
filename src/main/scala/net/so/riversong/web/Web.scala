package net.so.riversong.web

import io.Source
import java.util.logging.Logger
import unfiltered.filter._
import unfiltered.jetty._
import unfiltered.request.{Params, Path}
import unfiltered.response.{ResponseString, TextXmlContent}

object Web {
  val logger = Logger.getLogger(getClass().getName())

  def main(args: Array[String]) {


//    val plan = unfiltered.filter.Planify {
//
//      case req@Path(path) => {
//
//        req match {
//          case Params(params) => logger.info("params: " + params.map(p => {
//            (p._1, p._2(0))
//          }))
//          case _ => Unit
//
//        }
//        logger.info("Retrieving file: " + path)
//
//        //          TextXmlContent ~> ResponseString(Source
//        //            .fromInputStream(getClass().getResourceAsStream(path + ".xml")).getLines().mkString)
//        //        }
//
//      }

      unfiltered.jetty.Http.local(9206)
          .filter(Time)
        .run { s =>
        logger.info("starting unfiltered app at localhost on port %s"
          .format(s.port))
        unfiltered.util.Browser.open(
          "http://127.0.0.1:%d/time".format(s.port))
      }

    }

  }
