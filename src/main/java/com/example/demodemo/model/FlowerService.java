package com.example.demodemo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerService {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/flowers";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "A@annatupota2004";

    public static void saveFlower(Flower flower) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO flowers (flower_name, color, sort, live, red_book) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flower.getFlowerName());
                statement.setString(2, flower.getColor());
                statement.setString(3, flower.getSort());
                statement.setBoolean(4, flower.isLive());
                statement.setBoolean(5, flower.isRedBook());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Flower> getAllFlowers() {
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM flowers";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Flower flower = new Flower();
                    flower.setId(resultSet.getInt("id"));
                    flower.setFlowerName(resultSet.getString("flower_name"));
                    flower.setColor(resultSet.getString("color"));
                    flower.setSort(resultSet.getString("sort"));
                    flower.setLive(resultSet.getBoolean("live"));
                    flower.setRedBook(resultSet.getBoolean("red_book"));
                    flowers.add(flower);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flowers;
    }

    public static void updateFlower(Flower flower) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE flowers SET flower_name = ?, color = ?, sort = ?, live = ?, red_book = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, flower.getFlowerName());
                statement.setString(2, flower.getColor());
                statement.setString(3, flower.getSort());
                statement.setBoolean(4, flower.isLive());
                statement.setBoolean(5, flower.isRedBook());
                statement.setInt(6, flower.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFlower(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM flowers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}