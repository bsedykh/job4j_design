package ru.job4j.ood.ocp.violation;

import java.util.List;

public interface Store {
    List<Item> findByName(String name);

    List<Item> findById(String id);
}
