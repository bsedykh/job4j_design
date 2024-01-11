package ru.job4j.ood.srp.printer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.job4j.ood.srp.formatter.DateTimeParser;

import java.lang.reflect.Type;
import java.util.Calendar;

public class CalendarSerializer implements JsonSerializer<Calendar> {
    private final DateTimeParser<Calendar> dateTimeParser;

    public CalendarSerializer(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public JsonElement serialize(Calendar src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateTimeParser.parse(src));
    }
}
