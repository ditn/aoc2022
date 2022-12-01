fun main() {

    fun part1(input: List<String>): Int = input
        .chunkToCalorieTotal()
        .max()

    fun part2(input: List<String>): Int = input
        .chunkToCalorieTotal()
        .sorted()
        .takeLast(3)
        .sum()

    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun List<String>.chunkToCalorieTotal() = chunkedBy(String::isEmpty)
    .map { it.map(String::toInt).sum() }
