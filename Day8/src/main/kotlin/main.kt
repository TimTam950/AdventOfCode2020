import com.sun.media.sound.InvalidFormatException

fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")

    println("part1 ${returnAccBeforeDupOp(input)}")

    // literal (metaphorical) garbage
    val newInput = mutableListOf<List<String>>()
    for(i in 0..input.size - 1) {
        val copiedInput = input.toMutableList()
        val modifiedInput = mutableListOf<String>()
        var valHasBeenSwitched = false
        for(j in 0..input.size - 1) {
            if(!valHasBeenSwitched) {
                if("nop" in copiedInput[i]) copiedInput[i] = copiedInput[i].replace("nop", "jmp")
                else if("jmp" in copiedInput[i]) copiedInput[i] = copiedInput[i].replace("jmp", "nop")
                valHasBeenSwitched = true
            }
            modifiedInput.add(copiedInput[j])
        }
        newInput.add(modifiedInput)
    }

    for(x in newInput) {
        returnAccBeforeDupOp(x)
    }
}

fun returnAccBeforeDupOp(ops: List<String>): Int {
    var acc = 0
    val performedOps = mutableListOf<Int>()
    var opIndex = 0

    while(true) {
        val instruction = convertOpToPair(ops[opIndex])

        // check condition for part 1
        if (performedOps.contains(opIndex)) {
            break
        }

        performedOps.add(opIndex)

        when(instruction.first) {
            "nop" -> {
                opIndex++
            }
            "acc" -> {
                acc += instruction.second
                opIndex++
            }
            "jmp" -> {
                opIndex += instruction.second
            }
        }
        // check condition for part 2
        if(opIndex >= ops.size && performedOps.contains(ops.size - 1)) {
            println("answer acc $acc")
            break
        }
    }
    return acc
}

fun convertOpToPair(op: String): Pair<String, Int> {
    val splitOp = op.split(" ")

    val argument = when(splitOp[1][0]) {
        '+' -> splitOp[1].substring(1).toInt()
        '-' -> splitOp[1].substring(1).toInt() * -1
        else -> throw InvalidFormatException("Invalid format on argument")
    }

    return Pair(splitOp[0], argument)
}