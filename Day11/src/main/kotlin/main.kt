fun main(args: Array<String>) {
    var input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n").toMutableList()

    var finalResult = mutableListOf<String>()

    while(true){
        val newSeats = mutableListOf<String>()
        for(rowIndex in 0..input[0].length - 1) {
            var newRow = ""
            for(seatIndex in 0..input.size - 1){
                val updatedSeat = checkSeat(input, seatIndex to rowIndex)
                newRow = newRow.plus(updatedSeat)
            }
            newSeats.add(newRow)
        }
        if ((newSeats - input).isEmpty()) {
            finalResult = newSeats
            break
        }
        input = newSeats

    }
    println(finalResult.map { it.filter { status -> status.toString() == "#" } }.map { it.length }.sum())

}

// first value in currentSeat is position in row, second is position in column
fun checkSeat(seats: List<String>, currentSeat: Pair<Int, Int>): String {
    val indexedLengthOfRow = seats[0].length - 1
    val indexedLengthOfColumn = seats.size - 1

    val statusOfCurrentSeat = seats[currentSeat.second][currentSeat.first].toString()

    val adjacentSeats = getAdjacentSeats(currentSeat, indexedLengthOfRow, indexedLengthOfColumn)

    return when(statusOfCurrentSeat) {
        "L" -> {
            val unoccupiedSeats = adjacentSeats.map { seats[it.second][it.first].toString() }
                .none { it == "#" }
            return if (unoccupiedSeats) "#" else statusOfCurrentSeat
        }
        "#" -> {
            val occupiedSeatCount = adjacentSeats.map { seats[it.second][it.first].toString() }
                .filter { it == "#" }
                .size

            return if(occupiedSeatCount >= 4) return "L" else statusOfCurrentSeat
        }
        else -> statusOfCurrentSeat
    }
}

private fun getAdjacentSeats(currentSeat: Pair<Int, Int>, indexedLengthOfRow: Int, indexedLengthOfColumn: Int) = when {
        currentSeat == Pair(0, 0) -> listOf(0 to 1, 1 to 0, 1 to 1)

        currentSeat == Pair(0, indexedLengthOfRow) ->
            listOf(0 to indexedLengthOfRow - 1, 1 to indexedLengthOfRow, 1 to indexedLengthOfRow - 1)

        currentSeat == Pair(indexedLengthOfColumn, 0) ->
            listOf(indexedLengthOfColumn - 1 to 0, indexedLengthOfColumn - 1 to 1, indexedLengthOfColumn to 1)

        currentSeat == Pair(indexedLengthOfColumn, indexedLengthOfRow) ->
            listOf(
                indexedLengthOfColumn - 1 to indexedLengthOfRow, indexedLengthOfColumn - 1 to indexedLengthOfRow - 1,
                indexedLengthOfColumn to indexedLengthOfRow - 1
            )

        currentSeat.first == 0 ->
            listOf(
                currentSeat.first to currentSeat.second - 1,
                currentSeat.first + 1 to currentSeat.second - 1,
                currentSeat.first + 1 to currentSeat.second,
                currentSeat.first + 1 to currentSeat.second + 1,
                currentSeat.first to currentSeat.second + 1
            )

        currentSeat.first == indexedLengthOfRow ->
            listOf(
                currentSeat.first to currentSeat.second - 1,
                currentSeat.first - 1 to currentSeat.second - 1,
                currentSeat.first - 1 to currentSeat.second,
                currentSeat.first - 1 to currentSeat.second + 1,
                currentSeat.first to currentSeat.second + 1
            )

        currentSeat.second == 0 ->
            listOf(
                currentSeat.first - 1 to currentSeat.second,
                currentSeat.first - 1 to currentSeat.second + 1,
                currentSeat.first to currentSeat.second + 1,
                currentSeat.first + 1 to currentSeat.second + 1,
                currentSeat.first + 1 to currentSeat.second
            )

        currentSeat.second == indexedLengthOfColumn ->
            listOf(
                currentSeat.first - 1 to currentSeat.second,
                currentSeat.first - 1 to currentSeat.second - 1,
                currentSeat.first to currentSeat.second - 1,
                currentSeat.first + 1 to currentSeat.second - 1,
                currentSeat.first + 1 to currentSeat.second
            )

        else -> listOf(
            currentSeat.first - 1 to currentSeat.second - 1,
            currentSeat.first to currentSeat.second - 1,
            currentSeat.first + 1 to currentSeat.second - 1,
            currentSeat.first - 1 to currentSeat.second,
            currentSeat.first + 1 to currentSeat.second,
            currentSeat.first - 1 to currentSeat.second + 1,
            currentSeat.first to currentSeat.second + 1,
            currentSeat.first + 1 to currentSeat.second + 1
        )
    }
