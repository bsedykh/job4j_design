package ru.job4j.ood.srp.printer;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class XMLReportPrinter implements ReportPrinter {
    private final Marshaller marshaller;

    public XMLReportPrinter() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    @Override
    public String print(List<Employee> employees) {
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }
}
