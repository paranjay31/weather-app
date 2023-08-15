package com.example.weather.handler;

import com.example.weather.model.StatewiseTemperatures;
import com.example.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class WeatherHandler {
    private final WeatherService service;
    private static final String STATE = "state";

    public Mono<ServerResponse> weatherUpdate(ServerRequest request) {
        log.info("Inside handler method: weatherUpdate");
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.weatherDetails("How is the weather?")
                        .map(service::weather), StatewiseTemperatures.class);
    }

    public Mono<ServerResponse> weatherUpdateByState(ServerRequest request) {
        log.info("Inside handler method: weatherUpdateByState");
        String state = request.pathVariable(STATE);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.weatherDetailsByState(state)
                        .map(service::weather), StatewiseTemperatures.class);
    }

    public Mono<ServerResponse> saveNewState(ServerRequest request) {
        log.info("Inside handler method: saveNewState");
        Mono<StatewiseTemperatures> requestBodyMono = request.bodyToMono(StatewiseTemperatures.class);
        return requestBodyMono.flatMap(service::saveNewState);
    }

    public Mono<ServerResponse> deleteByState(ServerRequest request) {
        log.info("Inside handler method: deleteByState");
        String state = request.pathVariable(STATE);
        return service.deleteByState(state);
    }

    public Mono<ServerResponse> updateTemperatureByState(ServerRequest request) {
        log.info("Inside handler method: updateTemperatureByState");
        Mono<StatewiseTemperatures> requestBodyMono = request.bodyToMono(StatewiseTemperatures.class);
        return requestBodyMono.flatMap(service::updateTemperatureByState);
    }
}
