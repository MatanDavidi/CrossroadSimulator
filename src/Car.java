
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
 * The class Car represents a car that is approaching a crossroad with the
 * intention of crossing it to get to a different side
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
 public class Car extends Thread {

    /**
     * The coordinates in which to draw the car
     */
    public Point position;

    /**
     * The starting point where a car comes into the crossroad
     */
    private Side location;

    /**
     * The side where a car exits the crossroad
     */
    private Side direction;

    /**
     * The crossroad where the car is located
     */
    private Crossroad crossroad;

    /**
     * A boolean value that specifies if the car can pass the traffic light
     * (true) or not (false)
     */
    private boolean canPass;

    /**
     * A boolean value that specifies if the car has already passed the
     * crossroad
     */
    private boolean hasPassed;

    /**
     * A boolean value that specifies whether or not the car's position
     * correspond's to one of the lights'
     */
    public boolean isAtLight;

    /**
     * Instances new objects of type Car, allowing to specify a value to assign
     * to the fields position, location, direction and crossroad
     *
     * @param position the coordinates in which to draw the car
     * @param location the starting point where a car comes into the crossroad
     * @param direction the side where a car exits the crossroad
     * @param crossroad the crossroad where the car is located
     */
    public Car(Point position, Side location, Side direction, Crossroad crossroad) {

        super();
        this.position = position;
        setLocation(location);
        setDirection(direction);
        this.crossroad = crossroad;

    }

    /**
     * Gets the value of the field location
     *
     * @return the starting point where a car comes into the crossroad
     */
    public Side getLocation() {

        return location;

    }

    /**
     * Gets the value of the field direction
     *
     * @return the side where a car exits the crossroad
     */
    public Side getDirection() {

        return direction;

    }

    /**
     * Gets the value of the field hasPassed
     *
     * @return a boolean value that specifies if the car has already passed the
     * crossroad
     */
    public boolean getHasPassed() {

        return hasPassed;

    }

    /**
     * Gets the value of the field canPass
     *
     * @return a boolean value that specifies if the car can pass the traffic
     * light (true) or not (false)
     */
    public boolean getCanPass() {

        return canPass;

    }

    /**
     * Sets the value of the field location
     *
     * @param location the starting point where a car comes into the crossroad
     */
    private void setLocation(Side location) {

        if (location != direction) {

            this.location = location;

        }

    }

    /**
     * Sets the value of the field direction
     *
     * @param direction the side where a car exits the crossroad
     */
    private void setDirection(Side direction) {

        if (direction != location) {

            this.direction = direction;

        }

    }

    /**
     * Sets the value of the field canPass
     *
     * @param canPass a boolean value that specifies if the car can pass the
     * traffic light (true) or not (false)
     */
    public void setCanPass(boolean canPass) {

        if (canPass != this.canPass) {

            this.canPass = canPass;

        }

    }

    /**
     * Sets the value of the field hasPassed
     *
     * @param hasPassed a boolean value that specifies if the car has already
     * passed the crossroad
     */
    public void setHasPassed(boolean hasPassed) {

        if (hasPassed != this.hasPassed) {

            this.hasPassed = hasPassed;

        }

    }

    @Override
    public void run() {

        while (!hasPassed) {

            TrafficLight lights = crossroad.getTrafficLight();
            switch (location) {

                case Down:
                    canPass = !(lights.getDownLit());
                    break;

                case Left:
                    canPass = !(lights.getLeftLit());
                    break;

                case Right:
                    canPass = !(lights.getRightLit());
                    break;

                default:
                    canPass = !(lights.getUpLit());
                    break;

            }

            if (canPass) {

                crossroad.passCar(this);

            }

        }

    }

    /**
     * Allows to update the crossroad's field trafficLight
     *
     * @param trafficLight the crossroad's lights
     */
    public void updateTrafficLight(TrafficLight trafficLight) {

        crossroad.setTrafficLight(trafficLight);

    }

    @Override
    public String toString() {

        return getName() + " going from " + location + " to " + direction;

    }

}
