package com.simbirsoft.services;

import com.simbirsoft.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.util.LinkedMultiValueMap;

import java.net.URI;

@Service
public class SmarthomeService {

    private static final String baseUrl = "http://smarthome.simbirsoft:8080/";
    private static final String microserviceId = "f9c2064d47a671a3d0c6dec0eec6a9b9";
    private boolean offAllDevices = false; 

    public boolean switchDevice(String device, boolean status)
    {
        if(!(offAllDevices & status))
        this.setValue(device, status);

        return true;
    }
    
    
    public void restore() {
        offAllDevices = false;
    }
    
    public void shutdown(){
        this.switchDevice("HOOVER", false);
        this.switchDevice("VENTILATION", false);
        this.switchDevice("FRIDGE", false);
        this.switchDevice("TV", false);
        this.switchDevice("MICROWAVE", false);
        this.switchDevice("LOUDSPEAKERS", false);
        offAllDevices = true;
    }
    public String getStatusDevice(String device)
    {
        return this.getValue("devices", device);
    }

    public String getStatusSensor(String sensor)
    {
        return this.getValue("sensors", sensor);
    }

    public String getStatusExternalService(String service)
    {
        return this.getValue("services", service);
    }

    public boolean callExternalService(String service)
    {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("services/" + service)
                .build()
                .toUri();

        new RestTemplate().postForObject(targetUri, microserviceId, String.class);

        return true;
    }

    private String getValue(String type, String method)
    {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(type + "/" + method)
                //.queryParam("textLine", "My Param")
                .build()
                .toUri();

        return new RestTemplate().getForObject(targetUri, String.class);
    }

    private boolean setValue(String method, boolean on)
    {
        String state = (on) ? "ON" : "OFF";

        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("devices/" + method)
                .queryParam("microserviceId", microserviceId)
                .queryParam("state", state)
                .build()
                .toUri();

        new RestTemplate().put(targetUri, String.class);

        return true;
    }
}
