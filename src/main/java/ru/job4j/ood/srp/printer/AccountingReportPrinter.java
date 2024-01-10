package ru.job4j.ood.srp.printer;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;
import java.util.List;

public class AccountingReportPrinter implements ReportPrinter {
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter converter;
    private final Currency currency;

    public AccountingReportPrinter(DateTimeParser<Calendar> dateTimeParser,
                                   CurrencyConverter converter, Currency currency) {
        this.dateTimeParser = dateTimeParser;
        this.converter = converter;
        this.currency = currency;
    }

    @Override
    public String print(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(converter.convert(Currency.RUB, employee.getSalary(), currency))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
