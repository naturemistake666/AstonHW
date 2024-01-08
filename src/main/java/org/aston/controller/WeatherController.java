package org.aston.controller;

import org.aston.Entity.SetUrl;
import org.aston.Entity.Weather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;


@WebServlet("/weather")
public class WeatherController extends HttpServlet {
    private SetUrl setUrl = new SetUrl();
    private Weather weather = new Weather();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        setUrl.setName("London");
        setUrl.setToken("25e7b61ae79b471d844222502221507");

        URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + setUrl.getToken() + "&q=" + setUrl.getName() + "&aqi=no");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        connection.setRequestProperty("Authorization", "Bearer " + setUrl.getToken());


        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            resp.setContentType("application/json");
            weather.setName(req.getParameter("{\"location\":\"name\")}"));
            weather.setTemp_c(req.getParameter("temp_c"));
            weather.setLocalDateTime(req.getParameter("localtime"));

            resp.setContentType("text/html");
            PrintWriter printWriter = resp.getWriter();
            printWriter.write("name: " + weather.getName());
            printWriter.write("localtime: " + weather.getLocalDateTime());
            printWriter.write("temp_c: " + weather.getTemp_c());
            printWriter.close();

        } else {
            resp.setContentType("text/html");
            resp.getWriter().write("Ошибка при получении данных. Код ответа: " + responseCode);
        }
    }

    /**private Weather weather = new Weather();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
            printWriter.write("Max Weather: " +  weather.getMaxTemp());
            printWriter.write(" ");
            printWriter.write("Min Weather: " + weather.getMaxTemp());
            printWriter.close();
        }**/

    }

