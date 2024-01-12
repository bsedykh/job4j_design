package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingImplTest {
    @Test
    public void whenSufficientEmptySpacesOfEachSizeThenTrue() {
        Parking parking = new ParkingImpl(1, 1);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck(5);
        assertThat(parking.add(passengerCar)).isTrue();
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.getCars()).containsExactly(passengerCar, truck);
    }

    @Test
    public void whenPlaceTruckToSufficientOneSizedPlacesThenTrue() {
        Parking parking = new ParkingImpl(5, 0);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck(5);
        assertThat(parking.add(passengerCar)).isTrue();
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.getCars()).containsExactly(passengerCar, truck);
    }

    @Test
    public void whenPlacePassengerCarToOccupiedOneSizedPlaceThenFalse() {
        Parking parking = new ParkingImpl(1, 0);
        Car passengerCar = new PassengerCar();
        Car anotherPassengerCar = new PassengerCar();
        assertThat(parking.add(passengerCar)).isTrue();
        assertThat(parking.add(anotherPassengerCar)).isFalse();
        assertThat(parking.getCars()).containsExactly(passengerCar);
    }

    @Test
    public void whenPlacePassengerCarToMultiSizedPlaceThenFalse() {
        Parking parking = new ParkingImpl(0, 1);
        Car passengerCar = new PassengerCar();
        assertThat(parking.add(passengerCar)).isFalse();
        assertThat(parking.getCars()).isEmpty();
    }

    @Test
    public void whenPlaceTruckToOccupiedMultiSizedPlaceThenFalse() {
        Parking parking = new ParkingImpl(0, 1);
        Car truck = new Truck(5);
        Car anotherTruck = new Truck(5);
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(anotherTruck)).isFalse();
        assertThat(parking.getCars()).containsExactly(truck);
    }

    @Test
    public void whenPlaceTruckToInsufficientOneSizedPlacesThenFalse() {
        Parking parking = new ParkingImpl(4, 0);
        Car truck = new Truck(5);
        assertThat(parking.add(truck)).isFalse();
        assertThat(parking.getCars()).isEmpty();
    }

    @Test
    public void whenRemoveCars() {
        Parking parking = new ParkingImpl(1, 1);
        Car passengerCar = new PassengerCar();
        Car truck = new Truck(5);
        parking.add(passengerCar);
        parking.add(truck);
        parking.remove(passengerCar);
        assertThat(parking.getCars()).containsExactly(truck);
        parking.remove(truck);
        assertThat(parking.getCars()).isEmpty();
    }
}
