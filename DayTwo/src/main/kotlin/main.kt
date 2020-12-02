fun main(args: Array<String>) {
    val inputStrings = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")

    // part 1 example: 1-3 b: bbbag, determine whether letter b occurs in string 1-3 times
    val count = inputStrings.asSequence().filter { it != "" }
        .map { it.split(" ") }
        .map { Triple(convertStringToRange(it[0]), it[1].first(), it[2]) }
        .filter { (range, character, input) -> input.count { inputChar -> inputChar == character } in range }
        .count()

    println(count)
}

fun convertStringToRange(stringValue: String, separator: String = "-"): IntRange {
    val values = stringValue.split(separator)
    return IntRange(values[0].toInt(), values[1].toInt())
}