package com.simbirsoft.controllers;

import com.simbirsoft.services.SmarthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by telik on 10.07.2016.
 */
@RequestMapping(value = "/hv")
public class HooverController {
    @Autowired
    private SmarthomeService smart;

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String getHooverStatus() {

        return smart.getStatusDevice("HOOVER");
    }

    @RequestMapping(value = "/on", method = RequestMethod.GET)
    public boolean getHooverOn() {

        return smart.switchDevice("HOOVER", true);
    }

    @RequestMapping(value = "/off", method = RequestMethod.GET)
    public boolean getHooverOff() {

        return smart.switchDevice("HOOVER", false);
    }
}
