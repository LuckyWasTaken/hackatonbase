package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/microwave")
public class MicrowaveController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getFridgeStatus() {
        return smart.getStatusDevice("MICROWAVE");
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public boolean getFridgeOn() {
        return smart.switchDevice("MICROWAVE", true);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public boolean getFridgeOff() {
        return smart.switchDevice("MICROWAVE", false);
    }
}
