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
 * @version 31-dic-2018
 *
 */
public class CrossroadSimulator {

    /**
     * Main method used to start and test the program's
     *
     * @param args Gli argomenti passati dalla linea di comando.
     * @throws java.lang.InterruptedException if the sleep actions are
     * interrupted by the user
     */
    public static void main(String[] args) throws InterruptedException {

        final Crossroad c = new Crossroad();

        while (true) {

            Thread.sleep(5000);

            c.getTrafficLight().setLeftLit(false);
            c.getTrafficLight().setDownLit(true);

            Thread.sleep(2500);

            c.getTrafficLight().setDownLit(false);
            c.getTrafficLight().setRightLit(true);

            Thread.sleep(10000);

            c.getTrafficLight().setRightLit(false);
            c.getTrafficLight().setUpLit(true);

            Thread.sleep(1000);

            c.getTrafficLight().setUpLit(false);
            c.getTrafficLight().setLeftLit(true);

        }
    }

}
