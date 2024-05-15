package com.example.demodemo.model;

public class Flower {
    private String id;
    private String flowerName;
    private String sort;
    private String color;
    private String live;
    private String redBook;

    // Конструктор без параметров
    public Flower() {
    }

    // Конструктор со всеми параметрами
    public Flower(String id, String flowerName, String sort, String color, String live, String redBook) {
        this.id = id;
        this.flowerName = flowerName;
        this.sort = sort;
        this.color = color;
        this.live = live;
        this.redBook = redBook;
    }

    // Геттеры и сеттеры
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlowerName() {
        return flowerName;
    }

    public void setFlowerName(String flowerName) {
        this.flowerName = flowerName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLive() {
        return live;
    }

    public void setLive(String live) {
        this.live = live;
    }

    public String getRedBook() {
        return redBook;
    }

    public void setRedBook(String redBook) {
        this.redBook = redBook;
    }

    // Переопределение метода toString()
    @Override
    public String toString() {
        return "Flower{" +
                "id='" + id + '\'' +
                ", flowerName='" + flowerName + '\'' +
                ", sort='" + sort + '\'' +
                ", color='" + color + '\'' +
                ", live='" + live + '\'' +
                ", redBook='" + redBook + '\'' +
                '}';
    }
}