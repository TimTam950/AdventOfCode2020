fun main(args: Array<String>) {
    //val instructions = listOf("F10", "N3", "F7", "R90", "F11")
    val instructions = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")

    val ferry = Ferry(0)
    for(instruction in instructions) {
        ferry.performNavInstruction(instruction)
    }
    ferry.printManhattanDistance()

    val ferry2 = FerryModified(waypointLocationEast = 10, waypointLocationNorth = 1)
    for(instruction in instructions) {
        ferry2.performNavInstruction(instruction)
    }
    ferry2.printManhattanDistance()
}