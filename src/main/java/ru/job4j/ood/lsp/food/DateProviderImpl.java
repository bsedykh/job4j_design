package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class DateProviderImpl implements DateProvider {
    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
