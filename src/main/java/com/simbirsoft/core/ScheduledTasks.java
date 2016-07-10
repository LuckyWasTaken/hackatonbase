package com.simbirsoft.core;
import com.simbirsoft.services.SmarthomeService;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    
    @Autowired
    private SmarthomeService smart;
    
    @Scheduled(fixedRate = 5000)
    public void mainAction() {
        
        if (Double.parseDouble(smart.getStatusSensor("ELECTRICITY")) >= 12) {
            smart.restore();
            //Loudspeakers Controls
            if (Double.parseDouble(smart.getStatusSensor("NOISE")) < 50)
                smart.switchDevice("LOUDSPEAKERS", true);
            else
                smart.switchDevice("LOUDSPEAKERS",false);

            Double smoke = Double.parseDouble(smart.getStatusSensor("SMOKE"));
            Double gaz_leak = Double.parseDouble(smart.getStatusSensor("GAZ_LEAK"));

            if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 50)
                smart.switchDevice("TV", true);
            else
                smart.switchDevice("TV",false);

            if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) > 500)
                smart.switchDevice("HOOVER", true);
            else
                smart.switchDevice("HOOVER", false);

            if (smoke > 0 || gaz_leak > 0) {
                smart.switchDevice("VENTILATION", true);

            } else {
                smart.switchDevice("VENTILATION",false);
            }
        
            if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 20000)
                smart.switchDevice("FRIDGE", true);
            else
                smart.switchDevice("FRIDGE", false);

        }
        else {
            smart.shutdown();
        }
    }
    @Scheduled(fixedRate = 120000) 
    public void actionOnFire(){

        boolean is_fire = (Double.parseDouble(smart.getStatusSensor("ROOM_TEMPERATURE"))>100)
                &&(Double.parseDouble(smart.getStatusSensor("SMOKE"))>0);

        if (is_fire) {
            smart.callExternalService("FIRE_DEPARTMENT");
            smart.switchDevice("VENTILATION", true);
            smart.switchDevice("MICROWAVE", false);
            smart.switchDevice("TV",false);
            smart.switchDevice("HOOVER", false);
        }

        
    }

    @Scheduled(fixedRate = 120000) 
    public void actionOnFlood(){
        boolean is_flood = (Double.parseDouble(smart.getStatusSensor("WATER_LEAK"))>0);
        if (is_flood)
        {
            smart.callExternalService("PLUMBER");
            smart.switchDevice("MICROWAVE", false);
            smart.switchDevice("TV",false);
            smart.switchDevice("HOOVER", false);
        }
        

    }
}
