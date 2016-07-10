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
            smart.callExternalService("FIRE_DEPARTMENT");

        } else {
            smart.switchDevice("VENTILATION",false);
        }
        
        if (Double.parseDouble(smart.getStatusSensor("DISTANCE")) < 20000)
            smart.switchDevice("FRIDGE", true);
        else
            smart.switchDevice("FRIDGE", false);
        
        if(detectFire()||detectflood())
            smart.switchDevice("MICROWAVE", false);
        else
            smart.switchDevice("MICROWAVE", true);
    }
     
    public boolean detectFire(){
        return (Double.parseDouble(smart.getStatusSensor("ROOM_TEMPERATURE"))>100)
                &&(Double.parseDouble(smart.getStatusSensor("SMOKE"))>0);
                
    }
    
    public boolean  detectflood(){
        return (Double.parseDouble(smart.getStatusSensor("WATER_LEAK"))>100);
    }
}
