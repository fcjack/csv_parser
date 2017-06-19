package com.warwick.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jackson on 17/06/17.
 */
@Controller
public class IndexController {

    private Logger logger = Logger.getLogger(getClass());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "uploadStatus", method = RequestMethod.GET)
    public String uploadStatus() {
        return "status";
    }
}
