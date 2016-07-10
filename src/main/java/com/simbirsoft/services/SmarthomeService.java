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

    public String getRoomTemp()
    {
        return this.getValue(Wrapp("ROOM_TEMPERATURE","sensors"));
    }

    public String getDist()
    {
        return this.getValue(Wrapp("DISTANCE","sensors"));
    }
    
    public String getSmoke()
    {
        return this.getValue(Wrapp("SMOKE","sensors"));
    }
    
    public String getNoise()
    {
        return this.getValue(Wrapp("NOISE","sensors"));
    }
    
    public boolean switchTV(boolean on)
    {
        this.setValue("TV", on);
        return true;
    }

    public String getTVStatus()
    {
        return this.getValue(Wrapp("TV","devices"));
    }

    private String getValue(String method)
    {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path(method)
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
    public String Wrapp(String method,String wrapper)
    {
        return wrapper+"/"+method;
    }
}
