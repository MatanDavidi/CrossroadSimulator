
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
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class AddCarsThread extends Thread {

    private Crossroad crossroad;

    private final long minInterval;

    private final long maxInterval;

    public AddCarsThread(Crossroad crossroad) {

        this(crossroad, 1000, 10000);

    }

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

            Position currentLocation;
            Position direction;

            do {

                currentLocation = PositionClass.getRandomPosition();
                direction = PositionClass.getRandomPosition();

            } while (currentLocation == direction);

            Point newPosition = new Point();

            crossroad.addCar(new Car(newPosition, currentLocation, direction, crossroad));

        }

    }

}
