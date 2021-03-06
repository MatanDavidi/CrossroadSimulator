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
 * The class SideClass contains useful methods for the enumeration Side
 *
 * @author Matan Davidi
 * @version 29-dec-2018
 */
public class SideClass {

    /**
     * Chooses one of the four sides of a crossroad
     * @return a side chosen randomly between Up, Down, Left and Right
     */
    public static Side getRandomSide() {

        Side re;

        switch ((int) (Math.random() * 4)) {

            case 0:
                re = Side.Up;
                break;

            case 1:
                re = Side.Down;
                break;

            case 2:
                re = Side.Left;
                break;

            default:
                re = Side.Right;
                break;

        }

        return re;

    }

}
