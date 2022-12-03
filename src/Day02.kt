import java.lang.IllegalArgumentException

fun main() {

    fun part1(input: List<String>): Int = input
        .toRounds()
        .sumOf { round ->
            val outcomeComponent = round.toOutcome().score
            val handComponent = round.second.score

            outcomeComponent + handComponent
        }

    fun part2(input: List<String>): Int = input
        .toScenarios()
        .sumOf { scenario ->
            val outcomeComponent = scenario.second.score
            val handComponent = scenario.toHand().score

            outcomeComponent + handComponent
        }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

private typealias Opponent = Hand
private typealias Yours = Hand
private typealias Round = Pair<Opponent, Yours>
private typealias Scenario = Pair<Opponent, Outcome>

private fun List<String>.toRounds(): List<Round> =
    map { it.first().toHand() to it.last().toHand() }

private fun List<String>.toScenarios(): List<Scenario> =
    map { it.first().toHand() to it.last().toOutcome() }

private fun Char.toHand(): Hand = when (this) {
    'X', 'A' -> Hand.Rock
    'Y', 'B' -> Hand.Paper
    'Z', 'C' -> Hand.Scissors
    else -> throw IllegalArgumentException("Invalid hand with value $this")
}

private fun Char.toOutcome(): Outcome = when (this) {
    'X' -> Outcome.Loss
    'Y' -> Outcome.Draw
    'Z' -> Outcome.Win
    else -> throw IllegalArgumentException("Invalid outcome with value $this")
}

private fun Scenario.toHand(): Yours = when (first) {
    Hand.Paper -> when (second) {
        Outcome.Draw -> Hand.Paper
        Outcome.Loss -> Hand.Rock
        Outcome.Win -> Hand.Scissors
    }

    Hand.Rock -> when (second) {
        Outcome.Draw -> Hand.Rock
        Outcome.Loss -> Hand.Scissors
        Outcome.Win -> Hand.Paper
    }

    Hand.Scissors -> when (second) {
        Outcome.Draw -> Hand.Scissors
        Outcome.Loss -> Hand.Paper
        Outcome.Win -> Hand.Rock
    }
}

private fun Round.toOutcome(): Outcome = when (first) {
    Hand.Paper -> when (second) {
        Hand.Paper -> Outcome.Draw
        Hand.Rock -> Outcome.Loss
        Hand.Scissors -> Outcome.Win
    }

    Hand.Rock -> when (second) {
        Hand.Paper -> Outcome.Win
        Hand.Rock -> Outcome.Draw
        Hand.Scissors -> Outcome.Loss
    }

    Hand.Scissors -> when (second) {
        Hand.Paper -> Outcome.Loss
        Hand.Rock -> Outcome.Win
        Hand.Scissors -> Outcome.Draw
    }
}

enum class Hand(val score: Int) {
    Rock(1),
    Paper(2),
    Scissors(3),
}

enum class Outcome(val score: Int) {
    Loss(0),
    Draw(3),
    Win(6),
}