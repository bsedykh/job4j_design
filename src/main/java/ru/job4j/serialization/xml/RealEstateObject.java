package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "RealEstateObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealEstateObject {
    private String name;
    private double area;
    private Address address;
    private boolean hasElectricity;
    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public RealEstateObject() {
    }

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
