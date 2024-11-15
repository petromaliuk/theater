package com.theater.controller;

import com.theater.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@AllArgsConstructor
public class IndexController {

    private PasswordEncoder pe;

    //private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/")
    public String mainPage (Model model, @AuthenticationPrincipal User user) {
        //List<Film> filmList = filmService.getAll()
        model.addAttribute("isDevMode", false);
        model.addAttribute("profile", user);
    return "index";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        //logger.info("Hello mapping was activated!");
        return "<html> <body> <h1> HELLO:"+ pe.encode("12") +"; </h1> </body> </html>";
    }
}
