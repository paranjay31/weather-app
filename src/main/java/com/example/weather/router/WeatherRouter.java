package com.example.weather.router;

import com.example.weather.handler.WeatherHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class WeatherRouter {

    private static final String WEATHER_DATA_URL = "/weather-data";
    @Bean
    public RouterFunction<ServerResponse> route(WeatherHandler weatherHandler) {
        return RouterFunctions
                .route(GET(WEATHER_DATA_URL), weatherHandler::weatherUpdate)
                .andRoute(GET("/weather-data/{state}"), weatherHandler::weatherUpdateByState)
                .andRoute(POST(WEATHER_DATA_URL), weatherHandler::saveNewState)
                .andRoute(DELETE("/weather-data/{state}"), weatherHandler::deleteByState)
                .andRoute(PUT(WEATHER_DATA_URL), weatherHandler::updateTemperatureByState);
    }
}
