package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/vent")
public class VentilationController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getVentilationStatus() {

        return smart.getStatusDevice("VENTILATION");
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public boolean getVentilationOn() {

        return smart.switchDevice("VENTILATION", true);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public boolean getVentilationOff() {

        return smart.switchDevice("VENTILATION", false);
    }
}
