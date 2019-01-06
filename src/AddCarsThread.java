
import java.awt.Point;

/*
 * Copyright (C) 2018 Matan Davidi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * The class AddCarsThread is a subclass of Thread and is tasked with the
 * addition of cars to a crossroad at random intervals
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class AddCarsThread extends Thread {

    /**
     * The crossroad in which to add the cars
     */
    private Crossroad crossroad;

    /**
     * The minimum time to wait before adding a car
     */
    private final long minInterval;

    /**
     * The maximum time to wait before adding a car
     */
    private final long maxInterval;

    /**
     * Instances new objects of type AddCarsThread with a default values
     * assigned to the fields minInterval and maxInterval
     *
     * @param crossroad the crossroad in which to add the cars
     */
    public AddCarsThread(Crossroad crossroad) {

        this(crossroad, 1000, 10000);

    }

    /**
     * Instances new objects of type AddCarsThread allowing to specify a value
     * to set to the fields crossroad, minInterval and maxInterval
     *
     * @param crossroad the crossroad in which to add the cars
     * @param minInterval the minimum time to wait before adding a car
     * @param maxInterval the maximum time to wait before adding a car
     */
    public AddCarsThread(Crossroad crossroad, long minInterval, long maxInterval) {

        this.crossroad = crossroad;
        this.minInterval = minInterval;
        this.maxInterval = maxInterval;

    }

    @Override
    public void run() {

        while (crossroad.getDownCars().size() + crossroad.getLeftCars().size() + crossroad.getRightCars().size() + crossroad.getUpCars().size() < 15) {

            try {

                sleep((long) Math.ceil(Math.random() * maxInterval + minInterval));

            } catch (InterruptedException ex) {
            }

            Side currentLocation;
            Side direction;

            do {

                currentLocation = SideClass.getRandomSide();
                direction = SideClass.getRandomSide();

            } while (currentLocation == direction);

            Point newPosition = new Point();

            crossroad.addCar(new Car(newPosition, currentLocation, direction, crossroad));

        }

    }

}
