package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {
    private String region;
    private String city;
    private String street;
    private String number;

    public Address() {
    }

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
