package com.example.demodemo;



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
import java.util.Random;

@WebServlet("/flowers_table")
public class flowersServlet extends HttpServlet {

    @Override
    protected  void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("flowers.jsp");
        requestDispatcher.forward(req, resp);

        //req.getRequestDispatcher("/flowers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        Random rand = new Random();
        String randValue = Integer.toString(rand.nextInt(1000));

        JSONObject json = new JSONObject();
        json.put("id", randValue);
        json.put("flower_name", req.getParameter("flower_name"));
        json.put("sort", req.getParameter("sort"));
        json.put("color", req.getParameter("color"));
        json.put("live", req.getParameter("live"));
        json.put("red_book", req.getParameter("red_book"));


        //System.out.println(json);

        String path = "flowers.json";

        ServletContext context = getServletContext();

        InputStream inputStream = context.getResourceAsStream(path);

        if (inputStream != null) {
            System.out.println("Hey!");

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder jsonString = new StringBuilder();

            while (true) {
                String text = bufferedReader.readLine();
                if (text == null)
                    break;
                jsonString.append(text);
            }

            System.out.println(jsonString);

            JSONArray array = new JSONArray(jsonString.toString());

            array.put(json);

            System.out.println(array);

            String fullPath;
            try {
                fullPath = new File(flowersServlet.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getParentFile().getParent();
                fullPath += File.separator + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "flowers.json";
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            try(FileWriter writer = new FileWriter(fullPath)){
                writer.write(array.toString(4));
                writer.flush();
                writer.close();
            } catch (IOException ex){
                System.out.println("full Error!!!");
                ex.printStackTrace();
            }

            doGet(req, resp);
        }

    }
}