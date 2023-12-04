package ru.job4j.serialization.json;

import com.google.gson.GsonBuilder;

public class RealEstateSerialization {
    public static void main(String[] args) {
        var building = new RealEstateObject("Здание", 200.0,
                new Address("Калужская обл", "Калуга", "Ленина", "2"),
                true, new String[]{"Объект культурного наследия", "Требуется капитальный ремонт"});
        var gsonBuilder = new GsonBuilder().create();
        var buildingJson = gsonBuilder.toJson(building);
        System.out.println(buildingJson);
        System.out.println(gsonBuilder.fromJson(buildingJson, RealEstateObject.class));
    }
}
