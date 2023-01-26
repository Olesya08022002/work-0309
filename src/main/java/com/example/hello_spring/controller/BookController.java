package com.example.hello_spring.controller;
import com.example.hello_spring.DTO.Music;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    public static final String path = "/src/test.txt";

    public static String readFile() throws IOException {
        String filePath = new File("").getAbsolutePath();
        BufferedReader br = new BufferedReader(new FileReader(filePath + path));
        return "str";
    }

    @RequestMapping("/add")
    public String book(@RequestParam(value = "author") String author, @RequestParam(value = "ISBN") String ISBN) {
        try {
            Files.write(Paths.get("myfile.txt"),
                    ("\n" + ISBN + ":" + author).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) { }
        return "list";
    }

    @RequestMapping("/")
    public String getMusic (Model model) throws IOException {

        InputStream is = LikeController.class.getResourceAsStream("/static/data.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Music[] arrMusic = objectMapper.readValue(is, Music[].class);

        List<Music> result = Arrays.stream(arrMusic)
                .filter(i -> i.getLike())
                .collect(Collectors.toList());

        model.addAttribute("musics", result);
        return "music";
    }

    @RequestMapping("/index")
    public String index () {
        return "index";
    }
}
