package ru.job4j.serialization.json;

import java.util.Arrays;

public class RealEstateObject {
    private final String name;
    private final double area;
    private final Address address;
    private final boolean hasElectricity;
    private final String[] statuses;

    public RealEstateObject(String name, double area, Address address,
                            boolean hasElectricity, String[] statuses) {
        this.name = name;
        this.area = area;
        this.address = address;
        this.hasElectricity = hasElectricity;
        this.statuses = statuses;
    }

    @Override
    public String toString() {
        return "RealEstateObject{"
                + "name='" + name + '\''
                + ", area=" + area
                + ", address=" + address
                + ", hasElectricity=" + hasElectricity
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }
}
