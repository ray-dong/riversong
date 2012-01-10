package net.so.riversong.monitors;

import com.espertech.esper.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionRateMonitor {


    public static void start()
    {
        final Logger log = LoggerFactory.getLogger(TransactionRateMonitor.class);

        EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider();

        EPAdministrator admin = epService.getEPAdministrator();

        //wtf does this pattern reqd? to ensure if there are no events it gets fired anyway?
        EPStatement pattern = admin.createPattern("every timer:at(*, *, *, *, *, */1)");

        epService.getEPAdministrator().getConfiguration().addVariable("var_output_limit", long.class, "10");

        final EPStatement view = admin.createEPL("select count(*) as size from Responses (code!=2??).win:time(10 sec)");

        select rate(5) from MarketDataEvent output snapshot every 1 sec

        pattern.addListener(new TransactionRateMonitor()
        {
            public void update(EventBean[] newEvents, EventBean[] oldEvents)
            {
                long count = (Long) view.iterator().next().get("size");

                log.info(".update Info, error rate in the last 1 minutes is " + count);
            }
        });
    }
}
