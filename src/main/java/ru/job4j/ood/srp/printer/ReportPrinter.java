package ru.job4j.ood.srp.printer;

import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public interface ReportPrinter {
    String print(List<Employee> employees);
}
