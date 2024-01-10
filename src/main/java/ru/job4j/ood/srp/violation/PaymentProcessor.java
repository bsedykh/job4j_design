package ru.job4j.ood.srp.violation;

public class PaymentProcessor {
    public void processPayment(Payment payment) {
        sendReceipt();
        updateHistory();
        sendNotification();
    }

    private void sendReceipt() {
    }

    private void updateHistory() {
    }

    private void sendNotification() {
    }
}
