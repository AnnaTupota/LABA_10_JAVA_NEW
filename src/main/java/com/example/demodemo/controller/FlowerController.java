package com.example.demodemo.controller;

import com.example.demodemo.model.Flower;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.apache.logging.log4j.Logger;


@WebServlet("/flowers")
public class FlowerController extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/flowers";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "A@annatupota2004";
    private static final Logger LOGGER = Logger.getLogger(FlowerController.class.getName());

    // Обработка POST запроса для создания нового цветка
    // Метод doPost для создания нового цветка
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO flowers (flower_name, color, sort, live, red_book) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, request.getParameter("flower_name"));
                statement.setString(2, request.getParameter("color"));
                statement.setString(3, request.getParameter("sort"));
                statement.setBoolean(4, Boolean.parseBoolean(request.getParameter("live")));
                statement.setBoolean(5, Boolean.parseBoolean(request.getParameter("red_book")));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при добавлении цветка", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при добавлении цветка");
        }
    }


    // Обработка GET запроса для получения всех цветков
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Flower> flowers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM flowers";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Flower flower = new Flower();
                    flower.setId(Integer.parseInt(resultSet.getString("id")));
                    flower.setFlowerName(resultSet.getString("flower_name"));
                    flower.setColor(resultSet.getString("color"));
                    flower.setSort(resultSet.getString("sort"));
                    flower.setLive(Boolean.parseBoolean(resultSet.getString("live")));
                    flower.setRedBook(Boolean.parseBoolean(resultSet.getString("red_book")));
                    flowers.add(flower);
                }
            }
            String flowersJson = new Gson().toJson(flowers);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(flowersJson);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при получении списка цветов", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении списка цветов");
        }
    }


    // Обработка PUT запроса для обновления существующего цветка
    // Метод doDelete для удаления цветка
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM flowers WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(id));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при удалении цветка с ID: " + id, e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при удалении цветка");
        }
    }

    // Метод doPut для обновления существующего цветка
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE flowers SET flower_name = ?, color = ?, sort = ?, live = ?, red_book = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, request.getParameter("flower_name"));
                statement.setString(2, request.getParameter("color"));
                statement.setString(3, request.getParameter("sort"));
                statement.setBoolean(4, Boolean.parseBoolean(request.getParameter("live")));
                statement.setBoolean(5, Boolean.parseBoolean(request.getParameter("red_book")));
                statement.setInt(6, Integer.parseInt(request.getParameter("id")));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при обновлении цветка с ID: " + request.getParameter("id"), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при обновлении цветка");

// Вспомогательные методы для работы с базой данных
// ...
        }
    }
}