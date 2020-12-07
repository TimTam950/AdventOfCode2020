fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")

    input.map { getRowAndColumn(it) }
        .map { getSeatId(it) }
        .also { println("part1 ${it.maxOrNull()}") }
        .sorted()
        .findMissingValue()
        .also { println("part2 ${it.first()}") }
}

fun getRowAndColumn(input: String): Pair<Int, Int> {
    var rowStart = 0
    var rowEnd = 127
    var rowMid: Int = (rowStart + rowEnd) / 2
    for(character in input.subSequence(0, 6)) {
        if (character == 'F') {
            rowEnd = rowMid
        }
        else if (character == 'B') {
            rowStart = rowMid + 1
        }
        rowMid = (rowStart + rowEnd) / 2
    }
    val row = if (input[6] == 'F') rowStart else rowEnd

    var columnStart = 0
    var columnEnd = 7
    var columnMid: Int = (columnStart + columnEnd) / 2
    for(character in input.subSequence(7, 9)) {
        if (character == 'L') {
            columnEnd = columnMid
        }
        else if (character == 'R') {
            columnStart = columnMid + 1
        }
        columnMid = (columnStart + columnEnd) / 2
    }

    val column = if (input[9] == 'L') columnStart else columnEnd
    return Pair(row, column)
}

fun getSeatId(seat: Pair<Int, Int>) = seat.first * 8 + seat.second

fun List<Int>.findMissingValue(): List<Int> {
    val fullList = (this[0]..this[this.size - 1]).toList()
    return fullList - this
}