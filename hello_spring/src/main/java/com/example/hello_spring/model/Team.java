package com.example.hello_spring.model;

import java.nio.file.Path;

public class Team {
    private String name;
    private Path logo;

    public Team() {}
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getLogo() {
        return this.logo;
    }

    public void setLogo(Path newPath) {
        this.logo = newPath;
    }
}
