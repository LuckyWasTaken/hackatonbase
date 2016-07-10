package com.simbirsoft.services;

import com.simbirsoft.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SmarthomeService {

    private static final String baseUrl = "http://smarthome.simbirsoft:8080/";

    public String getRoomTemp()
    {
        return this.getValue("");
    }

    private String getValue(String method)
    {
        URI targetUri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("sensors/ROOM_TEMPERATURE")
                //.queryParam("textLine", "My Param")
                .build()
                .toUri();

        return new RestTemplate().getForObject(targetUri, String.class);
    }

}
