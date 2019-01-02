
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

    public CrossroadPanel() {

        crossroad = new Crossroad();
        RADIUS = 10;

        crossroad.addCrossroadListener(this);
        addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.clearRect(0, 0, getWidth(), getHeight());

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

        crossroad.addCrossroadListener(this);

        List<Car> leftCars = crossroad.getLeftCars();
        List<Car> downCars = crossroad.getDownCars();
        List<Car> upCars = crossroad.getUpCars();
        List<Car> rightCars = crossroad.getRightCars();

        int startX = leftLight.x - RADIUS * 2;
        int startY = leftLight.y + RADIUS * 2;

        //Left cars
        for (int i = 0; i < leftCars.size(); i++) {

            String id = Integer.toString((int) leftCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(startX - i * RADIUS * 2, leftLight.y, RADIUS * 2, RADIUS * 2);

            g.setColor(Color.BLACK);

            g.drawString(id, startX - i * RADIUS * 3, leftLight.y + RADIUS);

        }

        //Bottom cars
        for (int i = 0; i < downCars.size(); i++) {

            String id = Integer.toString((int) downCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(downLight.x, startY + i * RADIUS * 2, RADIUS * 2, RADIUS * 2);

            g.setColor(Color.BLACK);

            g.drawString(id, downLight.x + RADIUS, startY + i * RADIUS * 3);

        }

        startX = rightLight.x + RADIUS * 2;

        //Right cars
        for (int i = 0; i < rightCars.size(); i++) {

            String id = Integer.toString((int) rightCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(startX + i * RADIUS * 2, rightLight.y, RADIUS * 2, RADIUS * 2);

            g.setColor(Color.BLACK);

            g.drawString(id, downLight.x + RADIUS, startY + i * RADIUS * 3);

        }

        startY = upLight.y - RADIUS * 2;

        //Bottom cars
        for (int i = 0; i < upCars.size(); i++) {

            String id = Integer.toString((int) upCars.get(i).getId());

            g.setColor(Color.YELLOW);

            g.fillOval(upLight.x, startY - i * RADIUS * 2, RADIUS * 2, RADIUS * 2);

            g.setColor(Color.BLACK);

            g.drawString(id, downLight.x + RADIUS, startY + i * RADIUS * 3);

        }

    }

    @Override
    public void carPassed(Car source) {

//        switch (source.getPosition()) {
//
//            case Down:
//                repaint(downLight.x, downLight.y + RADIUS * 2, RADIUS * 6, getHeight() / 2 - RADIUS * 5);
//                break;
//
//            case Left:
//                repaint(leftLight.x - RADIUS * 2, leftLight.y, getWidth() / 2 - RADIUS * 5, RADIUS * 6);
//                break;
//
//            case Right:
//                repaint(rightLight.x + RADIUS * 2, rightLight.y, getWidth() / 2 - RADIUS * 5, RADIUS * 6);
//                break;
//
//            case Up:
//                repaint(upLight.x, upLight.y - RADIUS * 2, RADIUS * 6, getHeight() / 2 - RADIUS * 5);
//                break;
//
//        }
        repaint();

        System.out.println("The car " + source + " has passed");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right.");

    }

    @Override
    public void carAdded(Car source) {

        switch (source.getPosition()) {

            case Down:
                repaint(downLight.x, downLight.y + RADIUS * 2, RADIUS * 6, getHeight() / 2 - RADIUS * 5);
                break;

            case Left:
                repaint(leftLight.x - RADIUS * 2, leftLight.y, getWidth() / 2 - RADIUS * 5, RADIUS * 6);
                break;

            case Right:
                repaint(rightLight.x + RADIUS * 2, rightLight.y, getWidth() / 2 - RADIUS * 5, RADIUS * 6);
                break;

            case Up:
                repaint(upLight.x, upLight.y - RADIUS * 2, RADIUS * 6, getHeight() / 2 - RADIUS * 5);
                break;

        }

        System.out.println("The car " + source + " just arrived at the crossroad");
        System.out.println("There are now " + crossroad.getDownCars().size() + " on the bottom.");
        System.out.println("There are now " + crossroad.getLeftCars().size() + " to the left.");
        System.out.println("There are now " + crossroad.getUpCars().size() + " on the top.");
        System.out.println("There are now " + crossroad.getRightCars().size() + " to the right the top.");

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
        repaint(upLight.x, upLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void rightLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setRightLit(!tl.getRightLit());
        repaint(rightLight.x, rightLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void downLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setDownLit(!tl.getDownLit());
        repaint(downLight.x, downLight.y, RADIUS * 2, RADIUS * 2);

    }

    private void leftLightClicked(MouseEvent e) {

        TrafficLight tl = crossroad.getTrafficLight();
        crossroad.getTrafficLight().setLeftLit(!tl.getLeftLit());
        repaint(leftLight.x, leftLight.y, RADIUS * 2, RADIUS * 2);

    }

}
