package com.example.hello_spring.controller;

import com.example.hello_spring.DTO.Music;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@RestController
public class LikeController {
    @GetMapping("/setLike")
    public Boolean getAudio(@RequestParam(value = "name") String name,
                            @RequestParam(value = "like") Boolean like) throws IOException {

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

    public static String[] addX(int n, String arr[], String x) {
        String newarr[] = new String[n + 1];

        for (int i = 0; i < n; i++) {
            newarr[i] = arr[i];
        }
        newarr[n] = x;
        return newarr;
    }

    @RequestMapping(value="/setReport", method= RequestMethod.GET)
    public ResponseEntity<?> setReport(@RequestParam("name") String name,
                                       @RequestParam("singer") String singer,
                                       @RequestParam("album") String album) throws IOException {
        String path = new File(getClass().getClassLoader().getResource("static").getPath()).toString();

        InputStream is = LikeController.class.getResourceAsStream("/static/parental_advisory.json");
        ObjectMapper objectMapper = new ObjectMapper();
        String[] arrMusic = objectMapper.readValue(is, String[].class);

        boolean contains = Arrays.stream(arrMusic)
                .anyMatch(i -> i.contains(name));

        if (contains) {
            return ResponseEntity.ok("ОШИБКА отправки жалобы! Жалоба УЖЕ существует");
        }

        arrMusic = addX(arrMusic.length, arrMusic, name);

        ObjectWriter writer = objectMapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(path + "\\parental_advisory.json"), arrMusic);

        return ResponseEntity.ok("Жалоба успешно отправлена!");
    }
}
