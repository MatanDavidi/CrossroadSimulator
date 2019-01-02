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
 * The class TrafficLight simulates the real-life traffic light of a crossroad,
 * with four lights that can be red or green to signal whether or not cars are
 * are allowed to pass
 *
 * @author Matan Davidi
 * @version 29-dic-2018
 *
 */
public class TrafficLight {

    /**
     * Simulation of the upper light. If the value is set to true the light is
     * on and the cars stop; if the value is set to false the light is off and
     * the cars go
     */
    private boolean upLit;

    /**
     * Simulation of the lower light. If the value is set to true the light is
     * on and the cars stop; if the value is set to false the light is off and
     * the cars go
     */
    private boolean downLit;

    /**
     * Simulation of the left light. If the value is set to true the light is on
     * and the cars stop; if the value is set to false the light is off and the
     * cars go
     */
    private boolean leftLit;

    /**
     * Simulation of the right light. If the value is set to true the light is
     * on and the cars stop; if the value is set to false the light is off and
     * the cars go
     */
    private boolean rightLit;

    /**
     * Istantiates a new object of type TrafficLight with every light on (all
     * boolean fields are set to the value true)
     */
    public TrafficLight() {

        this(true, true, true, true);

    }

    /**
     * Istantiates a new object of type TrafficLight
     *
     * @param upLit the value to assign to the field 'upLit'
     * @param downLit the value to assign to the field 'downLit'
     * @param leftLit the value to assign to the field 'leftLit'
     * @param rightLit the value to assign to the field 'rightLit'
     */
    public TrafficLight(boolean upLit, boolean downLit, boolean leftLit, boolean rightLit) {

        this.upLit = upLit;
        this.downLit = downLit;
        this.leftLit = leftLit;
        this.rightLit = rightLit;

    }

    /**
     * Returns the value of the field upLit
     *
     * @return the value of the field upLit
     */
    public boolean getUpLit() {

        return upLit;

    }

    /**
     * Returns the value of the field downLit
     *
     * @return the value of the field downLit
     */
    public boolean getDownLit() {

        return downLit;

    }

    /**
     * Returns the value of the field leftLit
     *
     * @return the value of the field leftLit
     */
    public boolean getLeftLit() {

        return leftLit;

    }

    /**
     * Returns the value of the field rightLit
     *
     * @return the value of the field rightLit
     */
    public boolean getRightLit() {

        return rightLit;

    }

    /**
     * Sets the value of the field downLit
     * @param downLit the value to set to the field downLit
     */
    public void setDownLit(boolean downLit) {
        
        this.downLit = downLit;
        System.out.println("Turning " + (downLit? "on": "off") + " down");
        
    }

    /**
     * Sets the value of the field leftLit
     * @param leftLit the value to set to the field leftLit
     */
    public void setLeftLit(boolean leftLit) {
        
        this.leftLit = leftLit;
        System.out.println("Turning " + (leftLit? "on": "off") + " left");
        
    }

    /**
     * Sets the value of the field rightLit
     * @param rightLit the value to set to the field rightLit
     */
    public void setRightLit(boolean rightLit) {
        
        this.rightLit = rightLit;
        System.out.println("Turning " + (rightLit? "on": "off") + " right");
        
    }
    
    /**
     * Sets the value of the field upLit
     * @param upLit the value to set to the field upLit
     */
    public void setUpLit(boolean upLit) {
        
        this.upLit = upLit;
        System.out.println("Turning " + (upLit? "on": "off") + " up");
        
    }
    

}
