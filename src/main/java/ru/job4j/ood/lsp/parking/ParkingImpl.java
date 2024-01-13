package ru.job4j.ood.lsp.parking;

import java.util.*;

public class ParkingImpl implements Parking {
    private static class OccupiedSpaces {
        boolean multiSized;
        int[] indexes;
    }

    private final boolean[] oneSizedSpaces;
    private final boolean[] multiSizedSpaces;
    private final Map<Car, OccupiedSpaces> cars = new LinkedHashMap<>();

    public ParkingImpl(int oneSizedSpaces, int multiSizedSpaces) {
        this.oneSizedSpaces = new boolean[oneSizedSpaces];
        this.multiSizedSpaces = new boolean[multiSizedSpaces];
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.getSize() > 1) {
            for (int i = 0; i < multiSizedSpaces.length; i++) {
                if (!multiSizedSpaces[i]) {
                    multiSizedSpaces[i] = true;
                    var occupiedSpaces = new OccupiedSpaces();
                    occupiedSpaces.multiSized = true;
                    occupiedSpaces.indexes = new int[1];
                    occupiedSpaces.indexes[0] = i;
                    cars.put(car, occupiedSpaces);
                    result = true;
                    break;
                }
            }
        }
        if (!result) {
            int freePlaces = 0;
            for (int i = 0; i < oneSizedSpaces.length; i++) {
                if (oneSizedSpaces[i]) {
                    freePlaces = 0;
                } else {
                    freePlaces++;
                }
                if (freePlaces == car.getSize()) {
                    var occupiedSpaces = new OccupiedSpaces();
                    occupiedSpaces.indexes = new int[freePlaces];
                    occupiedSpaces.multiSized = false;
                    int k = 0;
                    for (int j = i - freePlaces + 1; j <= i; j++) {
                        oneSizedSpaces[j] = true;
                        occupiedSpaces.indexes[k++] = j;
                    }
                    cars.put(car, occupiedSpaces);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public boolean remove(Car car) {
        boolean result = false;
        var occupiedSpaces = cars.remove(car);
        if (occupiedSpaces != null) {
            var spaces = occupiedSpaces.multiSized ? multiSizedSpaces : oneSizedSpaces;
            for (var index : occupiedSpaces.indexes) {
                spaces[index] = false;
            }
            result = true;
        }
        return result;
    }

    @Override
    public List<Car> getCars() {
        return List.copyOf(cars.keySet());
    }
}
