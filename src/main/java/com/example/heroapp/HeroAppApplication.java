package com.example.heroapp;

import com.example.heroapp.ui.MainFxApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeroAppApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(HeroAppApplication.class, args);

        MainFxApp.launch(MainFxApp.class, args);
    }
}
