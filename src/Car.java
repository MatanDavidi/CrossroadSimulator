
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
 * La classe Car è
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class Car extends Thread {
    
    public Point position;
    
    private Position location;

    private Position direction;

    private Crossroad crossroad;

    public Car(Position location, Position direction, Crossroad crossroad) {

        super();
        this.location = location;
        this.direction = direction;
        this.crossroad = crossroad;

    }

    public Position getLocation() {

        return location;

    }

    public Position getDirection() {

        return direction;

    }

    @Override
    public void run() {

        boolean hasPassed = false;
        
        while (!hasPassed) {

            TrafficLight lights = crossroad.getTrafficLight();

            boolean canPass;

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
                hasPassed = true;

            }

        }

    }
    
    public void updateTrafficLight(TrafficLight trafficLight) {
        
        crossroad.setTrafficLight(trafficLight);
        
    }

    @Override
    public String toString() {

        return getName() + " going from " + location + " to " + direction;

    }

}
