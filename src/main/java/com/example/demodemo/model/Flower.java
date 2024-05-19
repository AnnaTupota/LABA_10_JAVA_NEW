package com.example.demodemo.model;

public class Flower {
    private int id; // ID теперь типа int
    private String flowerName;
    private String sort;
    private String color;
    private boolean live; // Live теперь типа boolean
    private boolean redBook; // RedBook теперь типа boolean

    // Конструктор без параметров
    public Flower() {
    }

    // Конструктор со всеми параметрами
    public Flower(int id, String flowerName, String sort, String color, boolean live, boolean redBook) {
        this.id = id;
        this.flowerName = flowerName;
        this.sort = sort;
        this.color = color;
        this.live = live;
        this.redBook = redBook;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isRedBook() {
        return redBook;
    }

    public void setRedBook(boolean redBook) {
        this.redBook = redBook;
    }

    // Переопределение метода toString()
    @Override
    public String toString() {
        return "Flower{" +
                "id=" + id +
                ", flowerName='" + flowerName + '\'' +
                ", sort='" + sort + '\'' +
                ", color='" + color + '\'' +
                ", live=" + live +
                ", redBook=" + redBook +
                '}';
    }
}