package ru.job4j.ood.dip.violation;

class IntelCPU {
}

class Motherboard {
    private final IntelCPU cpu = new IntelCPU();
}
