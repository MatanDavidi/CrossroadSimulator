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
 * Sides of the crossroad
 *
 * @author Matan Davidi
 * @version 29-dec-2018
 */
public enum Side {

    /**
     * The top side of the crossroad
     */
    Up,
    
    /**
     * The bottom side of the crossroad
     */
    Down,
    
    /**
     * The left side of the crossroad
     */
    Left,
    
    /**
     * The right side of the crossroad
     */
    Right;

    @Override
    public String toString() {

        switch (this) {

            case Up:
                return "up";

            case Down:
                return "down";

            case Left:
                return "left";

            default:
                return "right";

        }

    }
}
