package ru.job4j.ood.lsp.food;

public class Shop extends AbstractStore {
    private final DateProvider dateProvider;

    public Shop(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    protected boolean process(Food product) {
        var expiryPercentage = product.getExpiryPercentage(dateProvider.now());
        var result = expiryPercentage >= 25 && expiryPercentage < 100;
        if (result && expiryPercentage > 75) {
            product.setDiscount(20);
        }
        return result;
    }
}
