package net.so.riversong.monitors;

import com.espertech.esper.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//todo: fix logging?
//todo: scalatra and jetty

public class TransactionRateMonitor {


    public static void start()
    {
        final Logger log = LoggerFactory.getLogger(TransactionRateMonitor.class);

        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epService.getEPAdministrator();

        //wtf does this pattern reqd? to ensure if there are no events it gets fired anyway?
//        EPStatement pattern = admin.createPattern("every timer:at(*, *, *, *, *, */1)");

//        epService.getEPAdministrator().getConfiguration().addVariable("var_output_limit", long.class, "5");

        final EPStatement view = admin.createEPL("select rate(3) as size from Requests.win:time(1 sec) output snapshot every 3 seconds");
        final EPStatement tps = admin.createEPL("insert into TicksPerSecond select count(*) as size from Requests.win:time_batch(1 second)");

        tps.addListener(new UpdateListener()
        {
            public void update(EventBean[] newEvents, EventBean[] oldEvents)
            {
                EventBean event = newEvents[0];
                long count = (Long) event.get("size");

                System.out.println("update Info, transaction rate in the last 1 secs is " + count);
            }
        });
    }
}
