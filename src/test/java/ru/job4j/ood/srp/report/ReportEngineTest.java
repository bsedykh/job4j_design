package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.printer.*;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemoryStore;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {
    private final Store store = new MemoryStore();
    private final DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
    private final Calendar now = Calendar.getInstance();
    private final Employee worker = new Employee("Ivan", now, now, 100);

    @BeforeEach
    public void init() {
        store.add(worker);
    }

    @Test
    public void whenOldGenerated() {
        ReportPrinter printer = new OldReportPrinter(dateTimeParser);
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(dateTimeParser.parse(worker.getHired())).append(" ")
                .append(dateTimeParser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenAccountingGenerated() {
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        ReportPrinter printer = new AccountingReportPrinter(dateTimeParser, converter, Currency.USD);
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(dateTimeParser.parse(worker.getHired())).append(" ")
                .append(dateTimeParser.parse(worker.getFired())).append(" ")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD))
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenITGenerated() {
        ReportPrinter printer = new ITReportPrinter(dateTimeParser);
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(dateTimeParser.parse(worker.getHired())).append(";")
                .append(dateTimeParser.parse(worker.getFired())).append(";")
                .append(worker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }

    @Test
    public void whenHRGenerated() {
        Employee anotherWorker = new Employee("Vasiliy", now, now, 50);
        store.add(anotherWorker);
        ReportPrinter printer = new HRReportPrinter();
        Report engine = new ReportEngine(store, printer);
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator())
                .append(anotherWorker.getName()).append(" ")
                .append(anotherWorker.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
