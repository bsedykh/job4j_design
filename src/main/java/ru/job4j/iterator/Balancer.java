package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        var nodesIterator = nodes.iterator();
        while (source.hasNext()) {
            if (!nodesIterator.hasNext()) {
                nodesIterator = nodes.iterator();
            }
            nodesIterator.next().add(source.next());
        }
    }
}
