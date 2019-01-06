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
 * The listener interface for receiving crossroad events (car added, car passed,
 * car passing) on a component. (To track changes made on the crossroad.)
 * The class that is interested in processing a mouse event implements this
 * interface.
 * The listener object created from that class is then registered with a
 * crossroad using the crossroad's addCrossroadListener
 * A crossroad event is generated when a car is added, passing or after it has
 * passed.
 * When a crossroad event occurs, the relevant method in the listener object is
 * invoked, and the Car that caused the invocation is passed to it.
 *
 * @author Matan Davidi
 * @version ‎31-dec-2018
 */
public interface CrossroadListener {

    /**
     * Invoked whenever a car is added to the crossroad
     *
     * @param source the car that was added to the crossroad
     */
    public void carAdded(Car source);

    /**
     * Invoked whenever a car has passed the crossroad
     *
     * @param source the car that passed the crossroad
     */
    public void carPassed(Car source);

    /**
     * Invoked whenever a car is in the middle of passing through the
     * crossroad
     *
     * @param source the car that is passing the crossroad
     */
    public void carPassing(Car source);

}
