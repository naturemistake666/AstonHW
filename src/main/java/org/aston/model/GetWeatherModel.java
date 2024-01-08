package org.aston.model;

import org.aston.Entity.SetUrl;
import org.aston.Entity.Weather;

import javax.servlet.ServletException;
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

public class GetWeatherModel extends HttpServlet {
    private SetUrl setUrl = new SetUrl();
    private Weather weather = new Weather();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            weather.setName("name");
            weather.setTemp_c("temp_c");
            weather.setLocalDateTime("localtime");

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
}
