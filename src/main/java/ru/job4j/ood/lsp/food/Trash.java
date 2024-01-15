package ru.job4j.ood.lsp.food;

public class Trash extends AbstractStore {
    private final DateProvider dateProvider;

    public Trash(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    protected boolean process(Food product) {
        return product.getExpiryPercentage(dateProvider.now()) == 100;
    }
}
