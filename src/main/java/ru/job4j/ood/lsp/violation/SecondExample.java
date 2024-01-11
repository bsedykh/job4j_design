package ru.job4j.ood.lsp.violation;

class BankCard {
    private int balance;

    public void setBalance(int newValue) {
        balance = newValue;
    }
}

class DebitCard extends BankCard {
    @Override
    public void setBalance(int newValue) {
        if (newValue < 0) {
            throw new IllegalArgumentException("Overdraft is not supported for a debit card");
        }
        super.setBalance(newValue);
    }
}
