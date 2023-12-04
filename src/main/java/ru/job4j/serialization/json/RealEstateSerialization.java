package ru.job4j.serialization.json;

import org.json.JSONObject;

public class RealEstateSerialization {
    public static void main(String[] args) {
        var building = new RealEstateObject("Здание", 200.0,
                new Address("Калужская обл", "Калуга", "Ленина", "2"),
                true, new String[]{"Объект культурного наследия", "Требуется капитальный ремонт"});
        System.out.println(new JSONObject(building));
    }
}
