package com.example.weather.service;

import com.example.weather.domain.StatewiseTemperatureReading;
import com.example.weather.model.StatewiseTemperatures;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeatherService {
    Flux<StatewiseTemperatureReading> weatherDetails(String type);
    StatewiseTemperatures weather(StatewiseTemperatureReading reading);
    Mono<StatewiseTemperatureReading> weatherDetailsByState(String state);
    Mono<ServerResponse> saveNewState(StatewiseTemperatures statewiseTemperatures);
    Mono<ServerResponse> deleteByState(String state);
    Mono<ServerResponse> updateTemperatureByState(StatewiseTemperatures statewiseTemperatures);
}
