package day01

import data.Elf
import readInput

fun main() {
    fun getElvesFromInput(input: List<String>): ArrayList<Elf> {
        val elfList = ArrayList<Elf>()
        var tempElf: Elf? = null
        input.forEach { calorieValue ->
            if (calorieValue.isNotEmpty()) {
                if (tempElf == null) {
                    tempElf = Elf(calorieValue.toInt())
                    elfList.add(tempElf!!)
                } else {
                    tempElf!!.addCaloriesCarried(calorieValue.toInt())
                }
            } else {
                tempElf = null
            }
        }
        return elfList
    }

    fun part1(input: List<String>): Int {
        val elfList = getElvesFromInput(input)
        var mostCaloriesCarried = 0
        elfList.forEach { elf ->
            if (elf.getTotalCalories() > mostCaloriesCarried) {
                mostCaloriesCarried = elf.getTotalCalories()
            }
        }
        return mostCaloriesCarried
    }

    fun part2(input: List<String>): Int {
        val elfList = getElvesFromInput(input)
        var topElf = 0
        var secondElf = 0
        var thirdElf = 0
        elfList.forEach { elf ->
            val calories = elf.getTotalCalories()
            if (calories > topElf) {
                thirdElf = secondElf
                secondElf = topElf
                topElf = calories
            } else if (calories > secondElf) {
                thirdElf = secondElf
                secondElf = calories
            } else if (calories > thirdElf) {
                thirdElf = calories
            }
        }
        return topElf + secondElf + thirdElf
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("day01/Day01")
    println(part1(input))
    println(part2(input))
}
