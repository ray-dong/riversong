package net.so.riversong.example

import java.util.HashMap
import net.so.riversong.monitors.TransactionRateMonitor
import com.espertech.esper.client.{EPServiceProviderManager, Configuration}
import scala.collection.JavaConversions._

/**
 * TODO: manager that supports a dashboard and a dynamic way to modify alerts
 * TODO: remote events
 * TODO: dashboard of events
 * TODO: content of dashboard
 * TODO: correlation of transaction across instances, with different keys. ipaddr, jsessionid, woa session
 * TODO: latency, between time of booking and time of cancellation?
 * TODO: add Qos montiors, that are key => error rate per user?
 * //Manager.main(null)
 */
object Manager {

  def main(args: Array[String]) {
    val config = new Configuration();
    val eventType = new HashMap[String, AnyRef]()
    eventType.put("id", classOf[String])
    eventType.put("url", classOf[String])
    config.addEventType("Requests", eventType)                  ;
    val epService = EPServiceProviderManager.getDefaultProvider(config);

    TransactionRateMonitor.start()
    
    for (p <- Range(1,100)) {
      epService.getEPRuntime.sendEvent(Map("url" -> "/"), "Requests");
      Thread.sleep(10)
    }

  }
}