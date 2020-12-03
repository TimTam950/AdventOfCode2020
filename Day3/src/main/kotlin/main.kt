fun main(args: Array<String>) {
    val inputStrings = object {}.javaClass.getResourceAsStream("input.txt").bufferedReader().readLines()

    println(countTrees(3, 1, inputStrings))
}

fun countTrees(xDelta: Int, yDelta: Int, input: List<String>): Int {
    var trees = 0
    var x = 0
    var y = 0
    while (y < input.size) {
        if (input[y][x].toString() == "#") trees++

        x = (x + xDelta) % input[0].length
        y += yDelta
    }
    return trees
}