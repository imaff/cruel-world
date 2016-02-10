package com.chat.cruel.controllers;

import com.chat.cruel.beans.Message;
import com.chat.cruel.beans.Room;
import com.chat.cruel.beans.UserBeanSession;
import com.chat.cruel.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/secured")
public class SecuredController {

    @Autowired
    private IRoomService roomService;

    @RequestMapping(path = "/messages", method = RequestMethod.POST)
    public String messages(Model model, @RequestParam("room") String roomid) {
        List<Message> messagesByRoom = roomService.getMessagesByRoom(Long.valueOf(roomid));
        model.addAttribute("messages", messagesByRoom);
        return "messages";
    }

    @RequestMapping(path = "/send/message", method = RequestMethod.POST)
    public String send(Model model, @RequestParam("message") String message, @RequestParam("roomid") String roomid) {
        UserBeanSession principal = (UserBeanSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("messages", Collections.singletonList(roomService.sendMessage(new Message(principal.getLogin(), message, Long.valueOf(roomid)))));
        return "messages";
    }

    @RequestMapping(path = "/create/room", method = RequestMethod.POST)
    public
    @ResponseBody
    Room newRoom(Model model) {
        return roomService.createNewRoom();
    }

    @RequestMapping(path = "/search/room", method = RequestMethod.POST)
    public
    @ResponseBody
    Room searchRoom(Model model, @RequestParam("room") String roomid) {
        return roomService.getRoomById(Long.valueOf(roomid));
    }

}
