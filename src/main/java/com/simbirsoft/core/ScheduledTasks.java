package com.simbirsoft.core;
import com.simbirsoft.services.SmarthomeService;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private SmarthomeService smart;
    
    @Scheduled(fixedRate = 5000)
    public void mainAction() {
        if(Double.parseDouble(smart.getStatusSensor("DISTANCE"))<50)
          smart.switchDevice("TV", true);
       else
         smart.switchDevice("TV",false);
    }

    @Scheduled(fixedRate = 5000)
    public void checkSmoke() {

        Double smoke = Double.parseDouble(smart.getStatusSensor("SMOKE"));

        if (smoke > 1) {
            smart.switchDevice("VENTILATION", true);

        } else {
            smart.switchDevice("VENTILATION",false);
        }

        //if (smoke > 5) {
            // alert
        //}
    }
}
