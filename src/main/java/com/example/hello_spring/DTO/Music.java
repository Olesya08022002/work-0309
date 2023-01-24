package com.example.hello_spring.DTO;

public class Music {
    Integer id;
    String album;
    String name;
    String singer;
    Boolean like;

    public Music() {
    }

    public Music(Integer id, String album, String name, String singer, Boolean like) {
        this.id = id;
        this.album = album;
        this.name = name;
        this.singer = singer;
        this.like = like;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }
}
