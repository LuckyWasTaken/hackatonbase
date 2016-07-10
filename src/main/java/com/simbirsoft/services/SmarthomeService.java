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
        return this.getValue("sensors/ROOM_TEMPERATURE");
    }

    public boolean switchTV(boolean on)
    {
        this.setValue("TV", on);
        return true;
    }

    public String getTVStatus()
    {
        return this.getValue("devices/TV");
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

}
