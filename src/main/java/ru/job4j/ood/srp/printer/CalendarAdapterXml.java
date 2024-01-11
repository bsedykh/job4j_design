package ru.job4j.ood.srp.printer;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;

import javax.naming.OperationNotSupportedException;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Calendar;

public class CalendarAdapterXml extends XmlAdapter<String, Calendar> {
    private final DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();

    @Override
    public Calendar unmarshal(String s) throws Exception {
        throw new OperationNotSupportedException();
    }

    @Override
    public String marshal(Calendar calendar) {
        return dateTimeParser.parse(calendar);
    }
}
