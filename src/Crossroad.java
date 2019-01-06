
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
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class Crossroad {

    private TrafficLight trafficLight;

    private List<Car> upCars;

    private List<Car> downCars;

    private List<Car> leftCars;

    private List<Car> rightCars;

    private AddCarsThread carsAdder;

    private List<CrossroadListener> listeners;

    private final Object lock1;

    private final Object lock2;

    private int carsNumber;

    public Crossroad() {

        this(new TrafficLight(),
                new ArrayList<Car>(), new ArrayList<Car>(),
                new ArrayList<Car>(), new ArrayList<Car>());

    }

    public Crossroad(TrafficLight trafficLight, List<Car> upCars, List<Car> downCars, List<Car> leftCars, List<Car> rightCars) {

        this.trafficLight = trafficLight;
        this.upCars = upCars;
        this.downCars = downCars;
        this.leftCars = leftCars;
        this.rightCars = rightCars;

        listeners = new ArrayList<>();

        lock1 = new Object();
        lock2 = new Object();

        carsNumber = 0;

        carsAdder = new AddCarsThread(this);
        carsAdder.start();

    }

    public TrafficLight getTrafficLight() {

        return trafficLight;

    }

    public List<Car> getDownCars() {

        return downCars;

    }

    public List<Car> getLeftCars() {

        return leftCars;

    }

    public List<Car> getRightCars() {

        return rightCars;

    }

    public List<Car> getUpCars() {

        return upCars;

    }

    public int getCarsNumber() {

        return carsNumber;

    }

    public void setTrafficLight(TrafficLight trafficLight) {

        this.trafficLight = trafficLight;

    }

    public void addCrossroadListener(CrossroadListener listener) {

        listeners.add(listener);

    }

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

            car.start();

            for (int i = 0; i < listeners.size(); i++) {

                CrossroadListener listener = listeners.get(i);
                listener.carAdded(car);

            }

        }

        return re;

    }

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

                for (int i = 0; i < listeners.size(); i++) {

                    CrossroadListener listener = listeners.get(i);
                    listener.carPassed(car);

                }

            }

        }

        return re;

    }

}
