
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
 *
 * @author Matan Davidi
 * @version 31-dic-2018
 *
 */
public class CrossroadPanel extends JPanel implements CrossroadListener, MouseListener {

    private Crossroad crossroad;

    private final int RADIUS;

    private Point leftLight;

    private Point upLight;

    private Point rightLight;

    private Point downLight;

    private final int MARGIN;

    public final long FRAMES_TIMEOUT;

    public CrossroadPanel() {

        MARGIN = 5;
        RADIUS = 10;
        FRAMES_TIMEOUT = 100;

        crossroad = new Crossroad();

        crossroad.addCrossroadListener(this);
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

    private void updateLightPositions() {

        leftLight = new Point(getWidth() / 2 - RADIUS * 3, getHeight() / 2 - RADIUS);
        upLight = new Point(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS * 3);
        rightLight = new Point(getWidth() / 2 + RADIUS, getHeight() / 2 - RADIUS);
        downLight = new Point(getWidth() / 2 - RADIUS, getHeight() / 2 + RADIUS);

    }

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

    private void paintCars(Graphics g) {

        List<Car> leftCars = crossroad.getLeftCars();
        List<Car> downCars = crossroad.getDownCars();
        List<Car> upCars = crossroad.getUpCars();
        List<Car> rightCars = crossroad.getRightCars();

        //Left cars
        for (int i = 0; i < leftCars.size(); i++) {

            Car car = leftCars.get(i);
            String id = getIdString(car);

            g.setColor(Color.YELLOW);
            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Bottom cars
        for (int i = 0; i < downCars.size(); i++) {

            Car car = downCars.get(i);
            String id = getIdString(car);

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Right cars
        for (int i = 0; i < rightCars.size(); i++) {

            Car car = rightCars.get(i);
            String id = getIdString(car);

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

        //Top cars
        for (int i = 0; i < upCars.size(); i++) {

            Car car = upCars.get(i);
            String id = getIdString(car);

            g.setColor(Color.YELLOW);

            g.fillOval(car.position.x, car.position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, car.position.x + RADIUS / 2, car.position.y + RADIUS);

        }

    }

    private String getIdString(Car car) {

        return Integer.toString((int) car.getId() - 19);

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

    @Override
    public void carAdded(Car source) {

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

        System.out.println("The car " + source + " just arrived at the crossroad");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");

    }

    public void animatePassingCar(Car source) {

        if (source.getCanPass()) {

            //Move car to center
            switch (source.getLocation()) {

                case Down:

                    while (source.position.y + (source.getDirection().equals(Position.Right) ? RADIUS * 2 : 0) > getHeight() / 2) {

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
                    while (source.position.x + (source.getDirection().equals(Position.Down) ? RADIUS * 2 : 0) < getWidth() / 2) {

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
                    while (source.position.x + (source.getDirection().equals(Position.Down) ? RADIUS * 2 : 0) > getWidth() / 2) {

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
                    while (source.position.y + (source.getDirection().equals(Position.Right) ? RADIUS * 2 : 0) < getHeight() / 2) {

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
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        Point mousePoint = e.getPoint();

        Point upLight = new Point(this.upLight);
        upLight.x += RADIUS;
        upLight.y += RADIUS;

        Point rightLight = new Point(this.rightLight);
        rightLight.x += RADIUS;
        rightLight.y += RADIUS;

        Point downLight = new Point(this.downLight);
        downLight.x += RADIUS;
        downLight.y += RADIUS;

        Point leftLight = new Point(this.leftLight);
        leftLight.x += RADIUS;
        leftLight.y += RADIUS;

        if (mousePoint.distance(upLight) <= RADIUS) {

            upLightClicked(e);

        } else if (mousePoint.distance(rightLight) <= RADIUS) {

            rightLightClicked(e);

        } else if (mousePoint.distance(downLight) <= RADIUS) {

            downLightClicked(e);

        } else if (mousePoint.distance(leftLight) <= RADIUS) {

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

    private void upLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setUpLit(!tl.getUpLit());

        for (Iterator<Car> it = crossroad.getUpCars().iterator(); it.hasNext();) {

            Car upCar = it.next();
            upCar.updateTrafficLight(tl);

        }

        repaint(upLight.x, upLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void rightLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setRightLit(!tl.getRightLit());

        for (Iterator<Car> it = crossroad.getRightCars().iterator(); it.hasNext();) {

            Car rightCar = it.next();
            rightCar.updateTrafficLight(tl);

        }

        repaint(rightLight.x, rightLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void downLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setDownLit(!tl.getDownLit());

        for (Iterator<Car> it = crossroad.getDownCars().iterator(); it.hasNext();) {

            Car downCar = it.next();
            downCar.updateTrafficLight(tl);

        }

        repaint(downLight.x, downLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void leftLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setLeftLit(!tl.getLeftLit());

        for (Iterator<Car> it = crossroad.getLeftCars().iterator(); it.hasNext();) {

            Car leftCar = it.next();
            leftCar.updateTrafficLight(tl);

        }

        repaint(leftLight.x, leftLight.y, RADIUS * 2, RADIUS * 2);

    }

}
