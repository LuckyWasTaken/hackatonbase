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
    public void reportCurrentTime() {
        
        
       if(Double.parseDouble(smart.getDist())<50)
          smart.switchTV(true);
       else
          smart.switchTV(false); 
    }
}
