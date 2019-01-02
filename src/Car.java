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

    private Position position;

    private Position direction;

    private Crossroad crossroad;

    public Car(Position position, Position direction, Crossroad crossroad) {

        super();
        this.position = position;
        this.direction = direction;
        this.crossroad = crossroad;

    }

    public Position getPosition() {

        return position;

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

            switch (position) {

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

    @Override
    public String toString() {

        return getName() + " going from " + position + " to " + direction;

    }

}
