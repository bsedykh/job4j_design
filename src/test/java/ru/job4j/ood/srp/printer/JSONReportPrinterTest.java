package ru.job4j.ood.srp.printer;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.report.Report;
import ru.job4j.ood.srp.report.ReportEngine;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class JSONReportPrinterTest {
    @Test
    public void whenJSONGenerated() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportPrinter printer = new JSONReportPrinter(dateTimeParser);
        Report engine = new ReportEngine(store, printer);
        String expected = """
                [
                  {
                    "name": "%s",
                    "hired": "%s",
                    "fired": "%s",
                    "salary": %s
                  }
                ]"""
                .formatted(worker.getName(),
                        dateTimeParser.parse(worker.getHired()),
                        dateTimeParser.parse(worker.getFired()),
                        worker.getSalary());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected);
    }
}
