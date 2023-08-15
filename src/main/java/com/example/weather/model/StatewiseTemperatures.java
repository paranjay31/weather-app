package com.example.weather.model;

import org.bson.types.ObjectId;

public record StatewiseTemperatures(ObjectId id, String state, int temperature) {
}
