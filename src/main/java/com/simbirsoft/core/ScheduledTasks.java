package com.simbirsoft.core;
import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    
    @Autowired
    private SmarthomeService smart;
    
    @Scheduled(fixedRate = 5000)
    public void mainAction() {
        boolean is_fire = (Double.parseDouble(smart.getStatusSensor("ROOM_TEMPERATURE"))>100)
                &&(Double.parseDouble(smart.getStatusSensor("SMOKE"))>0);
        boolean is_flood = (Double.parseDouble(smart.getStatusSensor("WATER_LEAK"))>0);
        
        double distance = Double.parseDouble(smart.getStatusSensor("DISTANCE"));
        if (Double.parseDouble(smart.getStatusSensor("ELECTRICITY")) >= 12) {
            smart.restore();
            if (is_flood||is_fire)
            {
                smart.callExternalService("CALL_OWNER");
                smart.shutdown();
                if (is_fire) {
                    smart.callExternalService("FIRE_DEPARTMENT");
            
                }
                if (is_flood)
                {
                    smart.callExternalService("PLUMBER");
                }
            }
            else
            {
                if ((Double.parseDouble(smart.getStatusSensor("NOISE")) < 50)&&(Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 50))
                    smart.switchDevice("LOUDSPEAKERS", true);
                else
                    smart.switchDevice("LOUDSPEAKERS",false);

                Double smoke = Double.parseDouble(smart.getStatusSensor("SMOKE"));
                Double gaz_leak = Double.parseDouble(smart.getStatusSensor("GAZ_LEAK"));

                if (distance < 50)
                    smart.switchDevice("TV", true);
                else
                    smart.switchDevice("TV",false);

                if (distance > 500)
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
        else {
            smart.shutdown();
        }
    }
    
}
