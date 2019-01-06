
import java.util.ArrayList;
import java.util.List;

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
 * The class Crossroad represents a crossroad that cars can get to and get out
 * of
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class Crossroad {

    /**
     * The four lights of the crossroad that tell the cars on a certain side to
     * stop or go
     */
    private TrafficLight trafficLight;

    /**
     * A list containing all the cars on the top side
     */
    private List<Car> upCars;

    /**
     * A list containing all the cars on the bottom side
     */
    private List<Car> downCars;

    /**
     * A list containing all the cars on the left side
     */
    private List<Car> leftCars;

    /**
     * A list containing all the cars on the right side
     */
    private List<Car> rightCars;

    /**
     * The thread that adds cars to the crossroad at random intervals
     */
    private AddCarsThread carsAdder;

    /**
     * A list containing the CrossroadListeners that are listening to this
     * specific instance of the class Crossroad
     */
    private List<CrossroadListener> listeners;

    /**
     * An empty object used for intrinsic locks
     */
    private final Object lock1;

    /**
     * An empty object used for intrinsic locks
     */
    private final Object lock2;

    /**
     * The number of cars that have gotten to the crossroad
     */
    private int addedCarsNumber;

    /**
     * The number of cars that have left the crossroad
     */
    private int passedCarsNumber;

    /**
     * Instances new objects of type Crossroad assigning default values to the
     * fields trafficLight, upcars, downCars, leftCars and rightCars
     */
    public Crossroad() {

        this(new TrafficLight(),
                new ArrayList<Car>(), new ArrayList<Car>(),
                new ArrayList<Car>(), new ArrayList<Car>());

    }

    /**
     * Instances new objects of type Crossroad allowing to specify a value for
     * the fields trafficLight, upCars, downCars, leftCars and rightCars
     *
     * @param trafficLight the four lights of the crossroad that tell the cars
     * on a certain side to stop or go
     * @param upCars a list containing all the cars on the top side
     * @param downCars a list containing all the cars on the bottom side
     * @param leftCars a list containing all the cars on the left side
     * @param rightCars a list containing all the cars on the right side
     */
    public Crossroad(TrafficLight trafficLight, List<Car> upCars, List<Car> downCars, List<Car> leftCars, List<Car> rightCars) {

        this.trafficLight = trafficLight;
        this.upCars = upCars;
        this.downCars = downCars;
        this.leftCars = leftCars;
        this.rightCars = rightCars;

        listeners = new ArrayList<>();

        lock1 = new Object();
        lock2 = new Object();

        addedCarsNumber = 0;

        carsAdder = new AddCarsThread(this);
        carsAdder.start();

    }

    /**
     * Gets the value of the field trafficLight
     *
     * @return the four lights of the crossroad that tell the cars on a certain
     * side to stop or go
     */
    public TrafficLight getTrafficLight() {

        return trafficLight;

    }

    /**
     * Gets the value of the field downCars
     *
     * @return a list containing all the cars on the bottom side
     */
    public List<Car> getDownCars() {

        return downCars;

    }

    /**
     * Gets the value of the field leftCars
     *
     * @return a list containing all the cars on the left side
     */
    public List<Car> getLeftCars() {

        return leftCars;

    }

    /**
     * Gets the value of the field rightCars
     *
     * @return a list containing all the cars on the right side
     */
    public List<Car> getRightCars() {

        return rightCars;

    }

    /**
     * Gets the value of the field upCars
     *
     * @return a list containing all the cars on the top side
     */
    public List<Car> getUpCars() {

        return upCars;

    }

    /**
     * Gets the value of the field addedCarsNumber
     *
     * @return the number of cars that have gotten to the crossroad
     */
    public int getAddedCarsNumber() {

        return addedCarsNumber;

    }

    /**
     * Gets the value of the field passedCarsNumber
     *
     * @return the number of cars that have left the crossroad
     */
    public int getPassedCarsNumber() {

        return passedCarsNumber;

    }

    /**
     * Sets the value of the field trafficLight
     *
     * @param trafficLight the four lights of the crossroad that tell the cars
     * on a certain side to stop or go
     */
    public void setTrafficLight(TrafficLight trafficLight) {

        this.trafficLight = trafficLight;

    }

    /**
     * Adds an instance of a class that implements the CrossroadListener
     * interface as a listener of this instance of the class Crossroad to
     * receive crossroad events from this instance of Crossroad. If listener is
     * null no exception is thrown and no action is performed
     *
     * @param listener the CrossroadListener
     */
    public void addCrossroadListener(CrossroadListener listener) {

        listeners.add(listener);

    }

    /**
     * Adds a car to the crossroad
     *
     * @param car the car to add
     * @return true (as specified by Collection.add(E))
     */
    public boolean addCar(Car car) {

        boolean re;

        synchronized (lock1) {

            switch (car.getLocation()) {

                case Down:
                    re = downCars.add(car);
                    break;

                case Left:
                    re = leftCars.add(car);
                    break;

                case Right:
                    re = rightCars.add(car);
                    break;

                default:
                    re = upCars.add(car);
                    break;

            }

            ++addedCarsNumber;

            for (int i = 0; i < listeners.size(); i++) {

                CrossroadListener listener = listeners.get(i);
                listener.carAdded(car);

            }

        }

        return re;

    }

    /**
     * Removes the first occurrence of the specified element from this
     * crossroad, if it is present (optional operation). If this list does not
     * contain the element, it is unchanged. More formally, removes the element
     * with the lowest index i such that (o==null ? get(i)==null :
     * o.equals(get(i))) (if such an element exists). Returns true if this list
     * contained the specified element (or equivalently, if this list changed as
     * a result of the call).
     *
     * @param car element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    public boolean passCar(Car car) {

        boolean re = false;

        for (int i = 0; i < listeners.size(); ++i) {

            CrossroadListener listener = listeners.get(i);
            listener.carPassing(car);

        }

        if (car.getCanPass()) {

            synchronized (lock2) {

                switch (car.getLocation()) {

                    case Down:
                        re = downCars.remove(car);
                        break;

                    case Left:
                        re = leftCars.remove(car);
                        break;

                    case Right:
                        re = rightCars.remove(car);
                        break;

                    default:
                        re = upCars.remove(car);
                        break;

                }

                ++passedCarsNumber;

                for (int i = 0; i < listeners.size(); i++) {

                    CrossroadListener listener = listeners.get(i);
                    listener.carPassed(car);

                }

            }

        }

        return re;

    }

}
