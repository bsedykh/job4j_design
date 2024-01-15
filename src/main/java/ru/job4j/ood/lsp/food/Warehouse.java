package ru.job4j.ood.lsp.food;

public class Warehouse extends AbstractStore {
    private final DateProvider dateProvider;

    public Warehouse(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    protected boolean process(Food product) {
        return product.getExpiryPercentage(dateProvider.now()) < 25;
    }
}
