package ru.job4j.ood.srp.printer;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class XMLReportPrinterTest {
    @Test
    public void whenXMLGenerated() throws JAXBException {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Employee anotherWorker = new Employee("Vasiliy", now, now, 200);
        store.add(anotherWorker);
        ReportPrinter printer = new XMLReportPrinter();
        Report engine = new ReportEngine(store, printer);
        String expected = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """
                .formatted(
                        worker.getName(),
                        dateTimeParser.parse(worker.getHired()),
                        dateTimeParser.parse(worker.getFired()),
                        worker.getSalary(),
                        anotherWorker.getName(),
                        dateTimeParser.parse(anotherWorker.getHired()),
                        dateTimeParser.parse(anotherWorker.getFired()),
                        anotherWorker.getSalary()
                );
        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}
