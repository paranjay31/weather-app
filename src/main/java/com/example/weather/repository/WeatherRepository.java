package com.example.weather.repository;

import com.example.weather.domain.StatewiseTemperatureReading;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface WeatherRepository extends ReactiveMongoRepository<StatewiseTemperatureReading, ObjectId> {

    Mono<StatewiseTemperatureReading> findByState(String state);
    Mono<StatewiseTemperatureReading> deleteByState(String state);

}
