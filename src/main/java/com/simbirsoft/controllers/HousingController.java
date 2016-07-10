package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/housing")
public class HousingController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/offall", method = RequestMethod.GET)
    public String getVentilationStatus() {
        smart.switchDevice("HOOVER", false);
        smart.switchDevice("VENTILATION", false);
        smart.switchDevice("FRIDGE", false);
        smart.switchDevice("TV", false);
        smart.switchDevice("MICROWAVE", false);
        smart.switchDevice("LOUDSPEAKERS", false);
        return ("Done!");
    }

    
}

