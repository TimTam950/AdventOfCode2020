import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

class FerryModified(var waypointLocationEast: Int = 0, var waypointLocationNorth: Int = 0) {

    private var shipEastLocation: Int = 0
    private var shipNorthLocation: Int = 0

    fun performNavInstruction(navInstruction: String) {
        val action: String = navInstruction.substring(0, 1)
        val value: Int = navInstruction.substring(1).toInt()

        when(action) {
            "N" -> waypointLocationNorth += value
            "E" -> waypointLocationEast += value
            "S" -> waypointLocationNorth -= value
            "W" -> waypointLocationEast -= value
            "L" -> modifyWaypoint(action, value)
            "R" -> modifyWaypoint(action, value)
            "F" -> moveForward(value)
        }
    }

    private fun modifyWaypoint(direction: String, value: Int) {
        val currentEast = waypointLocationEast
        val currentNorth = waypointLocationNorth
        val modifiedValue = if (direction == "R" && value == 90) {
            270
        } else if (direction == "R" && value == 270) {
            90
        } else {
            value
        }

        when (modifiedValue) {
            0 -> {
                // do nothing
            }
            90 -> {
                waypointLocationEast = currentNorth * -1
                waypointLocationNorth = currentEast
            }
            180 -> {
                waypointLocationEast = currentEast * -1
                waypointLocationNorth = currentNorth * -1
            }
            270 -> {
                waypointLocationEast = currentNorth
                waypointLocationNorth = currentEast * -1
            }
        }
    }

    private fun moveForward(value: Int) {
        shipEastLocation += waypointLocationEast * value
        shipNorthLocation += waypointLocationNorth * value
    }

    fun printManhattanDistance() {
        val distance = shipEastLocation.absoluteValue + shipNorthLocation.absoluteValue
        println("Manhattan Distance Part 2: $distance")
    }
}