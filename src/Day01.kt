import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        var currentMax = 0
        var localSum = 0

        for (value in input) {
            if (value.isEmpty()) {
                currentMax = max(currentMax, localSum)
                localSum = 0
            } else {
                localSum += value.toInt()
            }
        }

        return currentMax
    }

    fun part2(input: List<String>): Int = buildList {
        var localSum = 0

        for (value in input) {
            if (value.isEmpty()) {
                add(localSum)
                localSum = 0
            } else {
                localSum += value.toInt()
            }
        }
    }.sorted()
        .takeLast(3)
        .sum()

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
