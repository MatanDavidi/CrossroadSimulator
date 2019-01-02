
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

    public CrossroadPanel() {
        
        MARGIN = 5;

        crossroad = new Crossroad();
        RADIUS = 10;

        crossroad.addCrossroadListener(this);
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        updateLightPositions();

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

        int startX = leftLight.x - RADIUS * 2;
        int startY = downLight.y + RADIUS * 2;

        //Left cars
        for (int i = 0; i < leftCars.size(); i++) {

            int x = startX - i * RADIUS * 2 - MARGIN;
            String id = Integer.toString((int) leftCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(leftCars.get(i).position.x, leftCars.get(i).position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, leftCars.get(i).position.x + RADIUS, leftCars.get(i).position.y + RADIUS);

        }

        //Bottom cars
        for (int i = 0; i < downCars.size(); i++) {

            int y = startY + i * RADIUS * 2 + MARGIN;
            String id = Integer.toString((int) downCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(downCars.get(i).position.x, downCars.get(i).position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, downCars.get(i).position.x + RADIUS, downCars.get(i).position.y + RADIUS);

        }

        startX = rightLight.x + RADIUS * 2;

        //Right cars
        for (int i = 0; i < rightCars.size(); i++) {

            int x = startX + i * RADIUS * 2 + MARGIN;
            String id = Integer.toString((int) rightCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(rightCars.get(i).position.x, rightCars.get(i).position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, rightCars.get(i).position.x + RADIUS / 2, rightCars.get(i).position.y + RADIUS * 2);

        }

        startY = upLight.y - RADIUS * 2;

        //Top cars
        for (int i = 0; i < upCars.size(); i++) {

            int y = startY - i * RADIUS * 2 - MARGIN;
            String id = Integer.toString((int) upCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(upCars.get(i).position.x, upCars.get(i).position.y, RADIUS * 2, RADIUS * 2);
            g.setColor(Color.BLACK);
            g.drawString(id, upCars.get(i).position.x + RADIUS, upCars.get(i).position.y + RADIUS);

        }

    }

    @Override
    public void carPassed(Car source) {

        System.out.println("The car " + source + " has passed");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");

    }

    @Override
    public void carAdded(Car source) {

        switch (source.getLocation()) {

            case Down:
                source.position.x = downLight.x + RADIUS;
                source.position.y = getHeight();

                while (source.position.y > downLight.y + RADIUS * (2 + (crossroad.getDownCars().size() - 1) * 2) + MARGIN) {

                    source.position.y -= RADIUS / 2;
                    repaint(source.position.x, source.position.y + RADIUS / 2, RADIUS * 2, RADIUS * 2 + RADIUS / 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }

                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                source.position.y = downLight.y + RADIUS * (2 + (crossroad.getDownCars().size() - 1) * 2) + MARGIN;
                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                break;

            case Up:
                source.position.x = upLight.x - RADIUS;
                source.position.y = 0;

                while (source.position.y < upLight.y - RADIUS * (2 + (crossroad.getUpCars().size() - 1) * 2) - MARGIN) {

                    source.position.y += RADIUS / 2;
                    repaint(source.position.x, source.position.y - RADIUS / 2, RADIUS * 2, RADIUS * 2 + RADIUS / 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }

                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                source.position.y = upLight.y - RADIUS * (2 + (crossroad.getUpCars().size() - 1) * 2) - MARGIN;
                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                break;

            case Left:
                source.position.x = 0;
                source.position.y = leftLight.y - RADIUS;

                while (source.position.x < leftLight.x - RADIUS * (2 + (crossroad.getLeftCars().size() - 1) * 2) - MARGIN) {

                    source.position.x += RADIUS / 2;
                    repaint(source.position.x - RADIUS / 2, source.position.y, RADIUS * 2 + RADIUS / 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                source.position.x = leftLight.x - RADIUS * (2 + (crossroad.getLeftCars().size() - 1) * 2) - MARGIN;
                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                break;

            case Right:
                source.position.x = getWidth();
                source.position.y = rightLight.y + RADIUS;

                while (source.position.x > rightLight.x + RADIUS * (2 + (crossroad.getRightCars().size() - 1) * 2) - MARGIN) {

                    source.position.x -= RADIUS / 2;
                    repaint(source.position.x + RADIUS / 2, source.position.y, RADIUS * 2 + RADIUS / 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }

                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                source.position.x = rightLight.x + RADIUS * (2 + (crossroad.getRightCars().size() - 1) * 2) + MARGIN;
                repaint(source.position.x, source.position.y, RADIUS * 2, RADIUS * 2);
                break;

        }

        System.out.println("The car " + source + " just arrived at the crossroad");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");

    }

    @Override
    public void carPassing(Car source) {

        switch (source.getLocation()) {

            case Down:
                while (source.position.y + RADIUS * 2 > getHeight() / 2) {

                    source.position.y -= RADIUS / 2;
                    repaint(source.position.x, source.position.y + RADIUS / 2, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Left:
                while (source.position.x + RADIUS < getWidth() / 2) {

                    source.position.x += RADIUS / 2;
                    repaint(source.position.x - RADIUS / 2, source.position.y, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Right:
                while (source.position.x + RADIUS > getWidth() / 2) {

                    source.position.x -= RADIUS / 2;
                    repaint(source.position.x + RADIUS / 2, source.position.y, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Up:
                while (source.position.y + RADIUS * 2 < getHeight() / 2) {

                    source.position.y += RADIUS / 2;
                    repaint(source.position.x, source.position.y - RADIUS / 2, RADIUS * 2, RADIUS + RADIUS / 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

        }

        switch (source.getDirection()) {

            case Down:
                while (source.position.y < getHeight()) {

                    source.position.y += RADIUS / 2;
                    repaint(source.position.x, source.position.y - RADIUS / 2, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Left:
                while (source.position.x + RADIUS * 2 > 0) {

                    source.position.x -= RADIUS / 2;
                    repaint(source.position.x + RADIUS / 2, source.position.y, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Right:
                while (source.position.x < getWidth()) {

                    source.position.x += RADIUS / 2;
                    repaint(source.position.x - RADIUS / 2, source.position.y, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

            case Up:
                while (source.position.y + RADIUS * 2 > 0) {

                    source.position.y -= RADIUS / 2;
                    repaint(source.position.x, source.position.y + RADIUS / 2, RADIUS * 2, RADIUS * 2);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                    }

                }
                break;

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
