package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class RealEstateSerialization {
    public static void main(String[] args) throws Exception {
        var building = new RealEstateObject("Здание", 200.0,
                new Address("Калужская обл", "Калуга", "Ленина", "2"),
                true, new String[]{"Объект культурного наследия", "Требуется капитальный ремонт"});
        JAXBContext context = JAXBContext.newInstance(RealEstateObject.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(building, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            var result = (RealEstateObject) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
