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
    
    @Scheduled(fixedRate = 10000)
    public void mainAction() {

        Double smoke = Double.parseDouble(smart.getStatusSensor("SMOKE"));
        Double gaz_leak = Double.parseDouble(smart.getStatusSensor("GAZ_LEAK"));

        if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 50)
            smart.switchDevice("TV", true);
        else
            smart.switchDevice("TV",false);

        if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 50)
            smart.switchDevice("HOOVER", true);
        else
            smart.switchDevice("HOOVER", false);

        if (smoke > 0 || gaz_leak > 0) {
            smart.switchDevice("VENTILATION", true);
        } else {
            smart.switchDevice("VENTILATION",false);
        }

    }
}
