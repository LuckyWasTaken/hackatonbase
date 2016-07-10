package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/appliances")
public class HousingController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/shutdown", method = RequestMethod.GET)
    public String shutdown() {
        smart.shutdown();
        return ("Done!");
    }

    @RequestMapping(value = "/restore", method = RequestMethod.GET)
    public String restore() {
        smart.restore();
        return ("Done!");
    }
}

