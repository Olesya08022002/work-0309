package com.example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class BookController {

    @RequestMapping("/add")
    public String book(@RequestParam(value = "author") String author, @RequestParam(value = "ISBN") String ISBN) {
        try {
            Files.write(Paths.get("myfile.txt"), ("\n" + ISBN + ":" + author).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

        }
        return "list";
    }

    @RequestMapping("/lst")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("list");
        return modelAndView;
    }


}
