
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

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
 * Contains an instance of Crossroad and prints the traffic lights and cars that
 * come and go to and from the crossroad. The user can interact with the traffic
 * lights by clicking on them which will make them change from green to red and
 * from red to green, while the cars will adapt to the traffic lights
 *
 * @author Matan Davidi
 * @version 31-dic-2018
 *
 */
public class CrossroadPanel extends JPanel implements CrossroadListener, MouseListener {

    /**
     * The crossroad to represent graphically
     */
    private Crossroad crossroad;

    /**
     * The length, in pixels, of the radius of every circle that is printed
     */
    private final int RADIUS;

    /**
     * The length, in pixels, of the margin between the lights and the road
     */
    private final int MARGIN;

    /**
     * The time to wait between one frame of the cars' animations and the other
     */
    public final long FRAMES_TIMEOUT;

    /**
     * The top left coordinate of the circle that represents the left traffic
     * light
     */
    private Point leftLight;

    /**
     * The top left coordinate of the circle that represents the top traffic
     * light
     */
    private Point upLight;

    /**
     * The top left coordinate of the circle that represents the right traffic
     * light
     */
    private Point rightLight;

    /**
     * The top left coordinate of the circle that represents the bottom traffic
     * light
     */
    private Point downLight;

    private int id;

    /**
     * Instances new objects of type CrossroadPanel assigning default values to
     * the fields RADIUS, MARGIN; FRAME_TIMEOUT and crossroad
     */
    public CrossroadPanel() {

        this(10, 5, 100, new Crossroad());

    }

    /**
     * Instances new objects of type CrossroadPanel allowing to specify a value
     * to assign to the fields RADIUS, MARGIN, FRAMES_TIMEOUT and crossroad
     *
     * @param RADIUS the length, in pixels, of the radius of every circle that
     * is printed
     * @param MARGIN the length, in pixels, of the margin between the lights and
     * the road
     * @param FRAMES_TIMEOUT the time to wait between one frame of the cars'
     * animations and the other
     * @param crossroad the crossroad to represent graphically
     */
    public CrossroadPanel(int RADIUS, int MARGIN, long FRAMES_TIMEOUT, Crossroad crossroad) {

        this.RADIUS = RADIUS;
        this.MARGIN = MARGIN;
        this.FRAMES_TIMEOUT = FRAMES_TIMEOUT;
        id = 0;

        this.crossroad = crossroad;

        this.crossroad.addCrossroadListener(this);
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        updateLightPositions();

        //Road
        paintRoad(g);

        //Lights
        paintLights(g);

        //Ca(me)r(a)s
        paintCars(g);

    }

    /**
     * Calculates the coordinates to assign to the fields leftLight, upLight,
     * rightLight and downLight, so the traffic lights are always in the middle
     * of the panel
     */
    private void updateLightPositions() {

        leftLight = new Point(getWidth() / 2 - RADIUS * 3, getHeight() / 2 - RADIUS);
        upLight = new Point(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS * 3);
        rightLight = new Point(getWidth() / 2 + RADIUS, getHeight() / 2 - RADIUS);
        downLight = new Point(getWidth() / 2 - RADIUS, getHeight() / 2 + RADIUS);

    }

    /**
     * Paints the traffic lights in the middle of the panel
     *
     * @param g the Graphics object to protect
     */
    private void paintLights(Graphics g) {

        //Down
        if (crossroad.getTrafficLight().getDownLit()) {

            g.setColor(Color.RED);

        } else {

            g.setColor(Color.GREEN);

        }

        g.fillOval(downLight.x, downLight.y, RADIUS * 2, RADIUS * 2);

        //Right
        if (crossroad.getTrafficLight().getRightLit()) {

            g.setColor(Color.RED);

        } else {

            g.setColor(Color.GREEN);

        }

        g.fillOval(rightLight.x, rightLight.y, RADIUS * 2, RADIUS * 2);

        //Up
        if (crossroad.getTrafficLight().getUpLit()) {

            g.setColor(Color.RED);

        } else {

            g.setColor(Color.GREEN);

        }

        g.fillOval(upLight.x, upLight.y, RADIUS * 2, RADIUS * 2);

        //Left
        if (crossroad.getTrafficLight().getLeftLit()) {

            g.setColor(Color.RED);

        } else {

            g.setColor(Color.GREEN);

        }

        g.fillOval(leftLight.x, leftLight.y, RADIUS * 2, RADIUS * 2);

    }

    /**
     * Paints the cars that are coming or going to or from the crossroad
     *
     * @param g the Graphics object to protect
     */
    private void paintCars(Graphics g) {

        List<Car> leftCars = crossroad.getLeftCars();
        List<Car> downCars = crossroad.getDownCars();
        List<Car> upCars = crossroad.getUpCars();
        List<Car> rightCars = crossroad.getRightCars();

        //Left cars
        for (int i = 0; i < leftCars.size(); ++i) {

            Car car = leftCars.get(i);
            String id = getIdString();

            g.setColor(Color.YELLOW);
            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Bottom cars
        for (int i = 0; i < downCars.size(); ++i) {

            Car car = downCars.get(i);
            String id = getIdString();

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Right cars
        for (int i = 0; i < rightCars.size(); ++i) {

            Car car = rightCars.get(i);
            String id = getIdString();

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Top cars
        for (int i = 0; i < upCars.size(); ++i) {

            Car car = upCars.get(i);
            String id = getIdString();

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

    }

    private void paintRoad(Graphics g) {

        //Top
        g.drawRect(upLight.x - RADIUS, 0, RADIUS * 4, upLight.y - MARGIN);
        g.drawLine(upLight.x + RADIUS, 0, upLight.x + RADIUS, upLight.y - MARGIN);

        //Left
        g.drawRect(0, leftLight.y - RADIUS, leftLight.x - MARGIN, RADIUS * 4);
        g.drawLine(0, leftLight.y + RADIUS, leftLight.x - MARGIN, rightLight.y + RADIUS);

        //Bottom
        g.drawRect(downLight.x - RADIUS, downLight.y + RADIUS * 2 + MARGIN, RADIUS * 4, getHeight() - (downLight.y + RADIUS * 2 + MARGIN));
        g.drawLine(downLight.x + RADIUS, getHeight(), downLight.x + RADIUS, downLight.y + RADIUS * 2 + MARGIN);

        //Right
        g.drawRect(rightLight.x + RADIUS * 2 + MARGIN, rightLight.y - RADIUS, getWidth() - (rightLight.x + RADIUS * 2 + MARGIN), RADIUS * 4);
        g.drawLine(getWidth(), rightLight.y + RADIUS, rightLight.x + RADIUS * 2 + MARGIN, rightLight.y + RADIUS);

    }

    /**
     * Gets the id of a car, taken from the field id, and converts it to a
     * String
     *
     * @return a string containing the id of the car
     */
    private String getIdString() {

        return Integer.toString(id);

    }

    @Override
    public void carAdded(Car source) {

        ++id;

        animateAddedCar(source);
        source.start();
        System.out.println("The car " + source + " just arrived at the crossroad");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");
        System.out.println("Since the beginning of the program's execution, " + crossroad.getAddedCarsNumber() + " car" + (crossroad.getAddedCarsNumber() == 1 ? " was" : "s were") + " added");

    }

    @Override
    public void carPassed(Car source) {

        System.out.println("The car " + source + " has passed");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");
        System.out.println("Since the beginning of the program's execution, " + crossroad.getAddedCarsNumber() + " car" + (crossroad.getPassedCarsNumber() == 1 ? " was" : "s have") + " passed");

    }

    @Override
    public void carPassing(Car source) {

        animatePassingCar(source);

    }

    /**
     * Animates the adding of the car by showing the car going from outside the
     * panel to the end of the line on one of the sides dictated by the value of
     * the car's location field
     *
     * @param source the car to animate
     */
    public void animateAddedCar(Car source) {

        final TrafficLight trafficLight = crossroad.getTrafficLight();

        switch (source.getLocation()) {

            case Down:
                source.position.x = downLight.x + RADIUS;
                source.position.y = getHeight();

                while (source.position.y - RADIUS > downLight.y + RADIUS * (2 + (crossroad.getDownCars().size() - 1) * 2) + MARGIN) {

                    animateCarMovingUp(source);

                }

                if (trafficLight.getDownLit()) {

                    repaintCar(source);
                    source.position.y = downLight.y + RADIUS * (2 + (crossroad.getDownCars().size() - 1) * 2) + MARGIN;
                    repaintCar(source);

                }
                break;

            case Up:
                source.position.x = upLight.x - RADIUS;
                source.position.y = 0;

                while (source.position.y + RADIUS < upLight.y - RADIUS * (2 + (crossroad.getUpCars().size() - 1) * 2) + MARGIN) {

                    animateCarMovingDown(source);

                }

                if (trafficLight.getUpLit()) {

                    repaintCar(source);
                    source.position.y = upLight.y - RADIUS * (2 + (crossroad.getUpCars().size() - 1) * 2) - MARGIN;
                    repaintCar(source);

                }
                break;

            case Left:
                source.position.x = 0;
                source.position.y = leftLight.y - RADIUS;

                while (source.position.x + RADIUS < leftLight.x - (RADIUS * (2 + (crossroad.getLeftCars().size() - 1) * 2) + MARGIN)) {

                    animateCarMovingRight(source);

                }
                if (trafficLight.getLeftLit()) {

                    repaintCar(source);
                    source.position.x = leftLight.x - RADIUS * (2 + (crossroad.getLeftCars().size() - 1) * 2) - MARGIN;
                    repaintCar(source);

                }
                break;

            case Right:
                source.position.x = getWidth();
                source.position.y = rightLight.y + RADIUS;

                while (source.position.x - RADIUS > rightLight.x + RADIUS * (2 + (crossroad.getRightCars().size() - 1) * 2) + MARGIN) {

                    animateCarMovingLeft(source);

                }
                if (trafficLight.getRightLit()) {

                    repaintCar(source);
                    source.position.x = rightLight.x + RADIUS * (2 + (crossroad.getRightCars().size() - 1) * 2) + MARGIN;
                    repaintCar(source);

                }
                break;

        }

    }

    /**
     * Animates the passage of the car by showing the car going from traffic
     * light to outside the panel from one of the sides dictated by the value of
     * the car's destination field
     *
     * @param source the car to animate
     */
    public void animatePassingCar(Car source) {

        if (source.getCanPass()) {

            //Move car to center
            switch (source.getLocation()) {

                case Down:

                    while (source.position.y + (source.getDirection().equals(Side.Right) ? RADIUS * 2 : 0) > getHeight() / 2) {

                        if (source.position.y >= downLight.y + RADIUS * 2 + MARGIN) {

                            if (source.position.y == downLight.y + RADIUS * 2 + MARGIN) {

                                source.isAtLight = true;

                            }
                            source.setCanPass(!crossroad.getTrafficLight().getDownLit());

                        } else if (!source.getCanPass()) {

                            source.setCanPass(true);

                        }

                        if (source.getCanPass()) {

                            animateCarMovingUp(source);

                        } else if (!source.isAtLight) {

                            moveCarToLight(source);

                        }

                    }
                    break;

                case Left:
                    while (source.position.x + (source.getDirection().equals(Side.Down) ? RADIUS * 2 : 0) < getWidth() / 2) {

                        if (source.position.x + RADIUS * 2 <= leftLight.x - MARGIN) {

                            source.setCanPass(!crossroad.getTrafficLight().getLeftLit());

                        } else if (!source.getCanPass()) {

                            source.setCanPass(true);

                        }

                        if (source.getCanPass()) {

                            animateCarMovingRight(source);

                        } else {

                            moveCarToLight(source);

                        }

                    }
                    break;

                case Right:
                    while (source.position.x + (source.getDirection().equals(Side.Down) ? RADIUS * 2 : 0) > getWidth() / 2) {

                        if (source.position.x >= rightLight.x + RADIUS * 2 + MARGIN) {

                            source.setCanPass(!crossroad.getTrafficLight().getRightLit());

                        } else if (!source.getCanPass()) {

                            source.setCanPass(true);

                        }

                        if (source.getCanPass()) {

                            animateCarMovingLeft(source);

                        } else {

                            moveCarToLight(source);

                        }

                    }
                    break;

                case Up:
                    while (source.position.y + (source.getDirection().equals(Side.Right) ? RADIUS * 2 : 0) < getHeight() / 2) {

                        if (source.position.y + RADIUS * 2 <= upLight.y - MARGIN) {

                            source.setCanPass(!crossroad.getTrafficLight().getUpLit());

                        } else if (!source.getCanPass()) {

                            source.setCanPass(true);

                        }

                        if (source.getCanPass()) {

                            animateCarMovingDown(source);

                        } else {

                            moveCarToLight(source);

                        }

                    }
                    break;

            }

        }

        if (source.getCanPass()) {

            //Move car to destination
            switch (source.getDirection()) {

                case Down:
                    while (source.position.y < getHeight()) {

                        animateCarMovingDown(source);

                    }

                    if (source.position.y >= getHeight()) {

                        source.setHasPassed(true);

                    }
                    break;

                case Left:
                    while (source.position.x + RADIUS * 2 > 0) {

                        animateCarMovingLeft(source);

                    }

                    if (source.position.x + RADIUS * 2 <= 0) {

                        source.setHasPassed(true);

                    }
                    break;

                case Right:
                    while (source.position.x < getWidth()) {

                        animateCarMovingRight(source);

                    }

                    if (source.position.x >= getWidth()) {

                        source.setHasPassed(true);

                    }
                    break;

                case Up:
                    while (source.position.y + RADIUS * 2 > 0) {

                        animateCarMovingUp(source);

                    }

                    if (source.position.y + RADIUS * 2 <= 0) {

                        source.setHasPassed(true);

                    }
                    break;

            }

        }

    }

    /**
     * Moves the car a half radius to the right and repaints it
     *
     * @param source the car to move
     */
    private void animateCarMovingRight(Car source) {

        source.position.x += RADIUS / 2;
        repaint(source.position.x - RADIUS / 2, source.position.y, RADIUS * 2 + RADIUS / 2, RADIUS * 2);
        try {
            Thread.sleep(FRAMES_TIMEOUT);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * Moves the car a half radius down and repaints it
     *
     * @param source the car to move
     */
    private void animateCarMovingDown(Car source) {

        source.position.y += RADIUS / 2;
        repaint(source.position.x, source.position.y - RADIUS / 2, RADIUS * 2, RADIUS * 2 + RADIUS / 2);
        try {
            Thread.sleep(FRAMES_TIMEOUT);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * Moves the car a half radius up and repaints it
     *
     * @param source the car to move
     */
    private void animateCarMovingUp(Car source) {

        source.position.y -= RADIUS / 2;
        repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2 + RADIUS / 2);
        try {
            Thread.sleep(FRAMES_TIMEOUT);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * Moves the car a half radius to the left and repaints it
     *
     * @param source the car to move
     */
    private void animateCarMovingLeft(Car source) {

        source.position.x -= RADIUS / 2;
        repaint(source.position.x, source.position.y, RADIUS * 2 + RADIUS / 2, RADIUS * 2);
        try {
            Thread.sleep(FRAMES_TIMEOUT);
        } catch (InterruptedException ex) {
        }

    }

    /**
     * Calls the repaint method with a clipping area that corresponds to the
     * car's surface
     *
     * @param source the car to repaint
     */
    private void repaintCar(Car source) {

        repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point mousePoint = e.getPoint();

        Point upCenter = new Point(this.upLight);
        upCenter.x += RADIUS;
        upCenter.y += RADIUS;

        Point rightCenter = new Point(this.rightLight);
        rightCenter.x += RADIUS;
        rightCenter.y += RADIUS;

        Point downCenter = new Point(this.downLight);
        downCenter.x += RADIUS;
        downCenter.y += RADIUS;

        Point leftCenter = new Point(this.leftLight);
        leftCenter.x += RADIUS;
        leftCenter.y += RADIUS;

        if (mousePoint.distance(upCenter) <= RADIUS) {

            upLightClicked(e);

        } else if (mousePoint.distance(rightCenter) <= RADIUS) {

            rightLightClicked(e);

        } else if (mousePoint.distance(downCenter) <= RADIUS) {

            downLightClicked(e);

        } else if (mousePoint.distance(leftCenter) <= RADIUS) {

            leftLightClicked(e);

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * the top light
     *
     * @param e the event of the mouse
     */
    private void upLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setUpLit(!tl.getUpLit());

        for (Iterator<Car> it = crossroad.getUpCars().iterator(); it.hasNext();) {

            Car upCar = it.next();
            upCar.updateTrafficLight(tl);

        }

        repaint(upLight.x, upLight.y, RADIUS * 2, RADIUS * 2);

    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * the right light
     *
     * @param e the event of the mouse
     */
    private void rightLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setRightLit(!tl.getRightLit());

        for (Iterator<Car> it = crossroad.getRightCars().iterator(); it.hasNext();) {

            Car rightCar = it.next();
            rightCar.updateTrafficLight(tl);

        }

        repaint(rightLight.x, rightLight.y, RADIUS * 2, RADIUS * 2);

    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * the bottom light
     *
     * @param e the event of the mouse
     */
    private void downLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setDownLit(!tl.getDownLit());

        for (Iterator<Car> it = crossroad.getDownCars().iterator(); it.hasNext();) {

            Car downCar = it.next();
            downCar.updateTrafficLight(tl);

        }

        repaint(downLight.x, downLight.y, RADIUS * 2, RADIUS * 2);

    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * the left light
     *
     * @param e the event of the mouse
     */
    private void leftLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setLeftLit(!tl.getLeftLit());

        for (Iterator<Car> it = crossroad.getLeftCars().iterator(); it.hasNext();) {

            Car leftCar = it.next();
            leftCar.updateTrafficLight(tl);

        }

        repaint(leftLight.x, leftLight.y, RADIUS * 2, RADIUS * 2);

    }

    /**
     * Moves and animates a car moving forward towards the traffic light keeping
     * an eventual line that may have formed previously
     *
     * @param car the car to move
     */
    private synchronized void moveCarToLight(Car car) {

        int passedCarsNum = 0;

        switch (car.getLocation()) {

            case Down:
                List<Car> downCars = crossroad.getDownCars();
                for (int i = 0; i < downCars.indexOf(car); ++i) {

                    Car currentCar = downCars.get(i);
                    if (currentCar.getCanPass()) {
                        ++passedCarsNum;
                    }

                }
                while (car.position.y > downLight.y + RADIUS * (2 + (downCars.indexOf(car) - passedCarsNum) * 2) + MARGIN) {

                    animateCarMovingUp(car);

                }
                break;

            case Left:
                List<Car> leftCars = crossroad.getLeftCars();
                for (int i = 0; i < leftCars.indexOf(car); ++i) {

                    Car currentCar = leftCars.get(i);
                    if (currentCar.getCanPass()) {
                        ++passedCarsNum;
                    }

                }
                while (car.position.x + RADIUS * 2 < leftLight.x - MARGIN - RADIUS * ((leftCars.indexOf(car) - passedCarsNum) * 2)) {

                    animateCarMovingRight(car);

                }
                break;

            case Right:
                List<Car> rightCars = crossroad.getRightCars();
                for (int i = 0; i < rightCars.indexOf(car); ++i) {
                    Car currentCar = rightCars.get(i);

                    if (currentCar.getCanPass()) {
                        ++passedCarsNum;
                    }

                }
                while (car.position.x > rightLight.x + RADIUS * (2 + (rightCars.indexOf(car) - passedCarsNum) * 2) + MARGIN) {

                    animateCarMovingLeft(car);

                }
                break;

            case Up:
                List<Car> upCars = crossroad.getUpCars();
                for (int i = 0; i < upCars.indexOf(car); ++i) {
                    Car currentCar = upCars.get(i);

                    if (currentCar.getCanPass()) {
                        ++passedCarsNum;
                    }

                }
                while (car.position.y + RADIUS * 2 < upLight.y - MARGIN - RADIUS * ((upCars.indexOf(car) - passedCarsNum) * 2)) {

                    animateCarMovingDown(car);

                }
                break;

        }
        car.isAtLight = true;

    }

}
