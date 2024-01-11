package ru.job4j.ood.srp.printer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.Calendar;
import java.util.List;

public class JSONReportPrinter implements ReportPrinter {
    private final Gson gson;

    public JSONReportPrinter(DateTimeParser<Calendar> dateTimeParser) {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeHierarchyAdapter(Calendar.class, new CalendarSerializer(dateTimeParser))
                .create();
    }

    @Override
    public String print(List<Employee> employees) {
        return gson.toJson(employees);
    }
}
