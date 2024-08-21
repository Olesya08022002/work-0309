package com.example.hello_spring.model;

public class Player {

        private Long id;

        private String name;
        private String position;

        private Integer age;

        public Player() {
        }

        public Long getId() {
            return id;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
}
