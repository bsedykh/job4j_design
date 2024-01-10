package ru.job4j.ood.srp.violation;

public interface ReportGenerator {
    Report generate();

    String toHTML(Report report);

    String toXML(Report report);

    String toJSON(Report report);
}
