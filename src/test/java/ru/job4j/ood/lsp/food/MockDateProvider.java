package ru.job4j.ood.lsp.food;

import java.time.LocalDate;

public class MockDateProvider implements DateProvider {
    private LocalDate date = LocalDate.now();

    @Override
    public LocalDate now() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
