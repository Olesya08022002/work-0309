package com.example.hello_spring.controller;

import com.example.hello_spring.DTO.Music;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class LikeController {
    @GetMapping("/setLike")
    public Boolean getAudio(@RequestParam(value = "name") String name, @RequestParam(value = "like") Boolean like) throws IOException {

        String path = new File(getClass().getClassLoader().getResource("static").getPath()).toString();

        InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);

        for(int i = 0; i < arrMusic.length; i++) {
            if (arrMusic[i].getName().equals(name)) {
                arrMusic[i].setLike(like);
            }
        }
        try {
            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(path + "\\data.json"), arrMusic);
        } catch (Exception e) {
            System.out.println("Вам не удалось загрузить " + name + " => " + e.getMessage());
            return false;
        }
        return true;
    }

    @GetMapping("/getMusicData")
    public Music[] getMusicData() throws IOException {
        InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);
        return arrMusic;
    }
}
