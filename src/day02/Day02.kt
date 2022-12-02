package day02

import readInput

fun main() {

    fun calculateRoundScores(opponentPlay: Char, myPlay: Char): Pair<Int, Int> {
        var opponentScore = 0
        var myScore = 0
        opponentScore += when (opponentPlay) {
            'A' -> 1
            'B' -> 2
            'C' -> 3
            else -> throw java.lang.IllegalArgumentException("Opponent selection is not A, B, or C")
        }
        myScore += when (myPlay) {
            'X' -> 1
            'Y' -> 2
            'Z' -> 3
            else -> throw java.lang.IllegalArgumentException("My selection is not X, Y, or Z")
        }
        when (opponentScore) {
            1 -> {
                when (myScore) {
                    1 -> {  //draw - rock v rock
                        opponentScore += 3
                        myScore += 3
                    }
                    2 -> { // win for me - rock v paper
                        myScore += 6
                    }
                    3 -> { // loss for me - rock v scissors
                        opponentScore += 6
                    }
                    else -> throw Exception("myScore was not a value of 1, 2, or 3")
                }
            }
            2 -> {
                when (myScore) {
                    2 -> {  //draw - paper v paper
                        opponentScore += 3
                        myScore += 3
                    }
                    3 -> { // win for me - paper v scissors
                        myScore += 6
                    }
                    1 -> { // loss for me - rock v scissors
                        opponentScore += 6
                    }
                    else -> throw Exception("myScore was not a value of 1, 2, or 3")
                }
            }
            3 -> {
                when (myScore) {
                    3 -> {  //draw - scissors v scissors
                        opponentScore += 3
                        myScore += 3
                    }
                    1 -> { // win for me - scissors v rock
                        myScore += 6
                    }
                    2 -> { // loss for me - scissors v paper
                        opponentScore += 6
                    }
                    else -> throw Exception("myScore was not a value of 1, 2, or 3")
                }
            }
            else -> throw Exception("opponentScore was not a value of 1, 2, or 3")
        }
        return Pair(opponentScore, myScore)
    }

    fun part1(input: List<String>): Int {
        var totalScoreForOpponent = 0
        var totalScoreForMe = 0
        var scores : Pair<Int, Int>
        input.forEach {  strategy ->
            val opponentPlay = strategy[0]
            val myPlay = strategy[2]
            scores = calculateRoundScores(opponentPlay, myPlay)
            totalScoreForOpponent += scores.first
            totalScoreForMe += scores.second
        }
        return totalScoreForMe
    }

    fun getMyPlay(opponentPlay: Char, strategy: Char): Char {
        when (strategy) {
            'X' -> {  // lose
                return when (opponentPlay) {
                    'A' -> 'Z'
                    'B' -> 'X'
                    'C' -> 'Y'
                    else -> throw Exception("invalid opponent play")
                }
            }
            'Y' -> {  // draw
                return when (opponentPlay) {
                    'A' -> 'X'
                    'B' -> 'Y'
                    'C' -> 'Z'
                    else -> throw Exception("invalid opponent play")
                }
            }
            'Z' -> {  // win
                return when (opponentPlay) {
                    'A' -> 'Y'
                    'B' -> 'Z'
                    'C' -> 'X'
                    else -> throw Exception("invalid opponent play")
                }
            }
            else -> throw Exception("invalid strategy")
        }
    }

    fun part2(input: List<String>): Int {
        var totalScoreForOpponent = 0
        var totalScoreForMe = 0
        var scores : Pair<Int, Int>
        input.forEach {  strategy ->
            val opponentPlay = strategy[0]
            val outcome = strategy[2]
            val myPlay = getMyPlay(opponentPlay, outcome)
            scores = calculateRoundScores(opponentPlay, myPlay)
            totalScoreForOpponent += scores.first
            totalScoreForMe += scores.second
        }
        return totalScoreForMe
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("day02/Day02")
    println(part1(input))
    println(part2(input))
}
