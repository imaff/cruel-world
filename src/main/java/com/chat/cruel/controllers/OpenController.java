package com.chat.cruel.controllers;

import com.chat.cruel.beans.UserBeanAuth;
import com.chat.cruel.beans.UserBeanSession;
import com.chat.cruel.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Component
@Controller
public class OpenController {
    @Autowired
    private IUserService userService;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String hello(Model model, Authentication authentication) throws IOException {
        if (authentication != null) {
            model.addAttribute("user", authentication.getPrincipal());
        }
        model.addAttribute("authorised", authentication != null);
        model.addAttribute("roomid", 0);
        return "index";
    }

    @RequestMapping(path = "/about", method = RequestMethod.GET)
    public String about() throws IOException {
        return "about";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> login(@RequestParam("login") String login,
                              @RequestParam("password") String password,
                              Authentication authentication) {
        if (null == authentication) {
            UserBeanSession user = userService.login(new UserBeanAuth(login, password));
            if (null == user) {
                return Collections.singletonMap("error", "User not found or Password incorrect");
            }
            authenticat(user);
            return Collections.singletonMap("state", "ok");
        } else {
            return Collections.singletonMap("error", "You are already logged in!");
        }
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, String> registration(@RequestParam("login") String login,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("passrply") String passrply,
                                     Authentication authentication) {
        if (StringUtils.hasText(login) && StringUtils.hasText(email) && StringUtils.hasText(password) && StringUtils.hasText(passrply) && null == authentication) {
            UserBeanSession registration = userService.registration(new UserBeanAuth(login, email, password));
            if (null == registration) {
                return Collections.singletonMap("error", "User with this credential already exist!");
            }
            authenticat(registration);
            return Collections.singletonMap("state", "ok");
        } else {
            return Collections.singletonMap("error", "Error validate fields");
        }
    }

    @RequestMapping(path = "/quit", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Boolean> logout() {
        SecurityContextHolder.clearContext();
        return Collections.singletonMap("state", true);
    }

    private void authenticat(UserBeanSession user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, "N/A", AuthorityUtils.commaSeparatedStringToAuthorityList("USER")));
    }
}
