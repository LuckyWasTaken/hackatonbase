package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tv")
public class TvController {

    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getTvStatus() {

        return smart.getTVStatus();
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public boolean getTvOn() {

        return smart.switchTV(true);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public boolean getTvOff() {

        return smart.switchTV(false);
    }
}
