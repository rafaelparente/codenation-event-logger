package com.rafaelparente.eventlogger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

@Controller
public class MainController {

    @RequestMapping("/")
    @ApiIgnore
    public RedirectView redirectBase (RedirectAttributes attributes) {
        return new RedirectView("/swagger-ui.html");
    }

}
