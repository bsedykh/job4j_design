package ru.job4j.ood.srp.printer;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class HRReportPrinter implements ReportPrinter {
    @Override
    public String print(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        employees.stream()
                .sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary()))
                .forEach(employee -> text.append(employee.getName()).append(" ")
                        .append(employee.getSalary())
                        .append(System.lineSeparator()));
        return text.toString();
    }
}
