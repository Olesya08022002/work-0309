package com.example.hello_spring.controller;
import com.example.hello_spring.DTO.Music;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@RestController
public class LikeController {
    @CrossOrigin("*")
    @PostMapping("/setLike")
    public Map<String, Boolean> setLike(@RequestBody Music[] updatedArrMusic) throws IOException {

        String path = new File(getClass().getClassLoader().getResource("static").getPath()).toString();
        File file = new File(path + "/data.json");

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        try (FileOutputStream os = new FileOutputStream(file)) {
            writer.writeValue(os, updatedArrMusic);
        }


        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);

        return response;

       /* InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);

        for(int i = 0; i < arrMusic.length; i++) {
            if (arrMusic[i].getName().equals(name)) {
                arrMusic[i].setLike(like);
                break;
            }
        }

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(path + "/data.json"), arrMusic);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", true);
        return response; */
    }

        @CrossOrigin("*")
        @PostMapping(value="/setReport")
        public ResponseEntity<?> setReport(@RequestParam("name") String name,
                                           @RequestParam("singer") String singer,
                                           @RequestParam("album") String album) throws IOException {
            String path = new File(getClass().getClassLoader().getResource("static").getPath()).toString();

            InputStream is = LikeController.class.getResourceAsStream("/static/parental_advisory.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Music> listMusic = new ArrayList<>(Arrays.asList(objectMapper.readValue(is, Music[].class)));

            boolean contains = listMusic.stream().anyMatch(i -> (i.getName().contains(name) &&
                    i.getSinger().contains(singer)) && i.getAlbum().contains(album));


            /*for(Music item : listMusic){
                if((item.getName().equals(name)) && (item.getAlbum().equals(album)) && (item.getSinger().equals(singer))){
                    contains = true;
                    break;
                }
            }*/

            if (contains) {
                return ResponseEntity.ok("ОШИБКА отправки жалобы! Жалоба УЖЕ существует");
            }
            Music musicItem = new Music(album, name, singer);

            listMusic.add(musicItem);

            ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(path + "\\parental_advisory.json"), listMusic);

            return ResponseEntity.ok("Жалоба успешно отправлена!");
        }
    }