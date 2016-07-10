package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/fridge")
public class FridgeController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getFridgeStatus() {
        return smart.getStatusDevice("FRIDGE");
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public boolean getFridgeOn() {
        return smart.switchDevice("FRIDGE", true);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public boolean getFridgeOff() {
        return smart.switchDevice("FRIDGE", false);
    }
}
