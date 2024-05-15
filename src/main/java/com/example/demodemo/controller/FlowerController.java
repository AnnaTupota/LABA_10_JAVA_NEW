package com.example.demodemo.controller;
import com.example.demodemo.model.Flower;
import com.example.demodemo.model.FlowerService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;
    @WebServlet("/flowers")
    public class FlowerController extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Flower flower = new Flower();
            flower.setFlowerName(request.getParameter("flower_name"));
            flower.setColor(request.getParameter("color"));
            flower.setSort(request.getParameter("sort"));
            flower.setId(request.getParameter("id"));
            flower.setLive(request.getParameter("live"));
            flower.setRedBook(request.getParameter("red_book"));

            FlowerService.saveFlower(flower);

            List<Flower> flowers = FlowerService.getAllFlowers();
            String flowersJson = new Gson().toJson(flowers);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(flowersJson);
        }
    }

