package net.so.riversong.example

import com.espertech.esper.client.{EventBean, UpdateListener, EPServiceProviderManager, Configuration}
import java.util.HashMap
import scala.collection.JavaConversions._

object ExternalAPIClient {

  def main(args: Array[String]) {
    val config = new Configuration();
    val eventType = new HashMap[String, AnyRef]()
    eventType.put("api", classOf[String])
    eventType.put("msg", classOf[String])
    eventType.put("id", classOf[String])
    eventType.put("extref", classOf[String])
    eventType.put("ts", classOf[Long])
    eventType.put("price", classOf[Double])
    config.addEventType("ExternalCallEvent", eventType)

    val epService = EPServiceProviderManager.getDefaultProvider(config);
    val expression = "select count(id) from ExternalCallEvent.win:time(3 sec)";
    val statement = epService.getEPAdministrator.createEPL(expression);
    statement.addListener(new UpdateListener {
      def update(newEvents: Array[EventBean], oldEvents: Array[EventBean]) {
        val event = newEvents(0)
        //does the event.get expr need to match an expression in the epl?
        println("sum=" + event.get("count(id)"))
        println("sum=" + event.get("count(id)"))
      }
    })

    epService.getEPRuntime.sendEvent(Map("api" -> "amadeus", "id" -> "1", "extref"->"1", "msg" -> "flight"), "ExternalCallEvent")
    epService.getEPRuntime.sendEvent(Map("api" -> "amadeus", "id" -> "2", "extref"->"2","msg" -> "flight"), "ExternalCallEvent")
    epService.getEPRuntime.sendEvent(Map("api" -> "amadeus", "id" -> "3", "extref"->"3","msg" -> "flight"), "ExternalCallEvent")
    epService.getEPRuntime.sendEvent(Map("api" -> "rex", "id" -> "3", "extref"->"4","msg" -> "flight"), "ExternalCallEvent")
    epService.getEPRuntime.sendEvent(Map("api" -> "amadeus", "id" -> "1", "extref"->"1","msg" -> "flight", "status" -> 200), "ExternalCallEvent")
    epService.getEPRuntime.sendEvent(Map("api" -> "amadeus", "id" -> "2", "extref"->"2","msg" -> "flight", "status" -> 502), "ExternalCallEvent")
    //es 3 is in progress
    //
  }


}