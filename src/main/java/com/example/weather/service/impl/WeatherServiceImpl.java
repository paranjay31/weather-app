package com.example.weather.service.impl;

import com.example.weather.domain.StatewiseTemperatureReading;
import com.example.weather.model.StatewiseTemperatures;
import com.example.weather.repository.WeatherRepository;
import com.example.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository repository;

    @Override
    public Flux<StatewiseTemperatureReading> weatherDetails(String type) {
        log.info("Inside service: weatherDetails");
        return repository.findAll();
    }

    @Override
    public StatewiseTemperatures weather(StatewiseTemperatureReading reading) {
        log.info("Inside service: weather");
        return new StatewiseTemperatures(reading.getId(), reading.getState(), reading.getTemperature());
    }

    @Override
    public Mono<StatewiseTemperatureReading> weatherDetailsByState(String state) {
        log.info("Inside service: weatherDetailsByState");
        return repository.findByState(state);
    }

    @Override
    public Mono<ServerResponse> saveNewState(StatewiseTemperatures statewiseTemperatures) {
        log.info("Inside service: saveNewState");
        return repository.save(StatewiseTemperatureReading.builder()
                        .state(statewiseTemperatures.state())
                        .temperature(statewiseTemperatures.temperature())
                        .build())
                .flatMap(reading -> ServerResponse
                        .noContent()
                        .build());

    }

    @Override
    public Mono<ServerResponse> deleteByState(String state) {
        return repository.deleteByState(state)
                .flatMap(reading -> ServerResponse
                        .noContent()
                        .build());
    }

    @Override
    public Mono<ServerResponse> updateTemperatureByState(StatewiseTemperatures statewiseTemperatures) {
        log.info("Inside service: updateTemperatureByState");
        return repository.findByState(statewiseTemperatures.state())
                .flatMap(reading -> repository.save(StatewiseTemperatureReading.builder()
                                .id(reading.getId())
                                .state(reading.getState())
                                .temperature(statewiseTemperatures.temperature())
                                .build())
                        .flatMap(nextReading -> ServerResponse.noContent().build()));
    }
}
