package net.so.riversong


import com.espertech.esper.client.{Configuration, EventBean, UpdateListener, EPServiceProviderManager}
import java.util.HashMap
import scala.collection.JavaConversions._

object Sandbox {
  def main(args: Array[String]) {
    val config = new Configuration();
    val eventType = new HashMap[String, AnyRef]()
    eventType.put("itemName", classOf[String])
    eventType.put("price", classOf[Double])
    config.addEventType("OrderEvent", eventType)

    val epService = EPServiceProviderManager.getDefaultProvider(config);
    val expression = "select avg(price) from OrderEvent.win:time(3 sec)";
    val statement = epService.getEPAdministrator.createEPL(expression);
    statement.addListener(new UpdateListener {
      def update(newEvents: Array[EventBean], oldEvents: Array[EventBean]) {
        val event = newEvents(0)
        println("avg=" + event.get("avg(price)"))
      }
    })

    epService.getEPRuntime.sendEvent(Map("itemName" -> "shirt", "price" -> 74.5), "OrderEvent")
    epService.getEPRuntime.sendEvent(Map("itemName" -> "shirt", "price" -> 60.5), "OrderEvent")
  }

}