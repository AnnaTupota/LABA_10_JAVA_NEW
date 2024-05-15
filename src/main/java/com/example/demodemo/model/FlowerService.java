package com.example.demodemo.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.getType;

public class FlowerService {
    private static final String FILE_PATH = "src\\main\\webapp\\flowers.json";

    public static void saveFlower(Flower flower) {
        List<Flower> flowers = getAllFlowers();
        flowers.add(flower);
        writeToJsonFile(flowers);
    }

    public static List<Flower> getAllFlowers() {
        String json = readFromJsonFile();
        Type listType = new TypeToken<ArrayList<Flower>>(){}.getType();
        return new Gson().fromJson(json, listType);
    }

    private static String readFromJsonFile() {
        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    private static void writeToJsonFile(List<Flower> flowers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String json = new Gson().toJson(flowers);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}