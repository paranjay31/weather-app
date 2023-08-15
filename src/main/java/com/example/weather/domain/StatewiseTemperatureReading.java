package com.example.weather.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "StatewiseTemperatureReading")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatewiseTemperatureReading {

    @MongoId
    private ObjectId id;

    private String state;

    private int temperature;
}
