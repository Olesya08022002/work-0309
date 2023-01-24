package com.example.hello_spring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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
            Files.write(Paths.get("myfile.txt"), ("\n" + ISBN + ":" + author).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) { }
        return "list";
    }

    @RequestMapping("/lst")
    public ModelAndView list () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @RequestMapping("/index")
    public String index () {
        return "index";
    }
}
