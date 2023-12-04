package ru.job4j.serialization.json;

public class Address {
    private final String region;
    private final String city;
    private final String street;
    private final String number;

    public Address(String region, String city, String street, String number) {
        this.region = region;
        this.city = city;
        this.street = street;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{"
                + "region='" + region + '\''
                + ", city='" + city + '\''
                + ", street='" + street + '\''
                + ", number='" + number + '\''
                + '}';
    }
}
