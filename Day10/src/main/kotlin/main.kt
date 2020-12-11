val cache = mutableMapOf<Int, Long>()

fun main(args: Array<String>) {

    var input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n").map { it.toInt() }.toMutableList()
    // part 1
//    val diffs = mutableMapOf<Int, Int>()
//
//    input.sort()
//
//    input.add(0, 0)
//    input.add(input[input.size - 1] + 3)
//
//    for(i in 0..(input.size - 2)) {
//        val difference = input[1 + i] - input[i]
//        if(diffs.containsKey(difference)) {
//            diffs[difference] = diffs[difference]!!.plus(1)
//        } else {
//            diffs[difference] = 1
//        }
//    }
//    println(diffs)
    input.sort()
    println(traverseNodes(0, input))
}

//fun traverseNodes(n: Int, sortedList: List<Int>): Long{
//    if (cache.containsKey(n)) {
//        return cache[n]!!
//    }
//
//    var count = 0L
//    var children = sortedList.filter { it >= (n + 1) && it <= (n + 3) }
//
//    if(children.isEmpty()) {
//        cache[n] = 1L
//    }
//    for(child in children) {
//        count += traverseNodes(child, sortedList)
//    }
//    cache[n] = count
//
//    return count
//}

fun partTwo()