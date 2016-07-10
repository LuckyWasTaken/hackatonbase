package com.simbirsoft.controllers;

import com.simbirsoft.entity.User;
import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public String getRoomTemp() {

        return smart.getRoomTemp();
    }
}
