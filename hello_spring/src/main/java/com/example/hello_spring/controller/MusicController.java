package com.example.hello_spring.controller;
import com.example.hello_spring.DTO.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MusicController {

    @CrossOrigin("*")
    @RequestMapping("/")
    @ResponseBody
    public String getMusic (Model model) throws IOException {

        InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);

        List<Music> result = Arrays.stream(arrMusic)
                .collect(Collectors.toList());

        /*
               List<Music> result = Arrays.stream(arrMusic)
                .filter(i -> i.getLike())
                .collect(Collectors.toList());
        */

        String jsonResponse = objectMapper.writeValueAsString(result);

        return jsonResponse;
    }

    @CrossOrigin("*")
    @RequestMapping("/getLikedMusic")
    @ResponseBody
    public String getLikedMusic (Model model) throws IOException {

        InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);

        List<Music> result = Arrays.stream(arrMusic)
                .filter(i -> i.getLike())
                .collect(Collectors.toList());



        String jsonResponse = objectMapper.writeValueAsString(result);

        return jsonResponse;
    }

    @RequestMapping("/index")
    public String index () {
        return "index";
    }
}