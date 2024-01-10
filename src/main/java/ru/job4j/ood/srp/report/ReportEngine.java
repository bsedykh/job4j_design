package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.printer.ReportPrinter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private final Store store;
    private final ReportPrinter printer;

    public ReportEngine(Store store, ReportPrinter printer) {
        this.store = store;
        this.printer = printer;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return printer.print(store.findBy(filter));
    }
}
