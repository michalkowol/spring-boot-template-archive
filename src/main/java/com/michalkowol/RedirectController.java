package com.michalkowol;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
class RedirectController {

    @RequestMapping("/redirect")
    String redirectToHealth() {
        return "redirect:/health";
    }
}
