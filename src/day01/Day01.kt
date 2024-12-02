package day01

import println
import readInput
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val listA: MutableList<Int> = mutableListOf()
        val listB: MutableList<Int> = mutableListOf()
        input.forEach {
            listA.add(it.split(" ").first().toInt())
            listB.add(it.split(" ").last().toInt())
        }

        listA.sort()
        listB.sort()

        var distance = 0

        listA.forEachIndexed { index, i ->
            distance += (i - listB[index]).absoluteValue
        }

        return distance
    }

    fun part2(input: List<String>): Int {
        val listA: MutableList<Int> = mutableListOf()
        val listB: MutableList<Int> = mutableListOf()
        input.forEach {
            listA.add(it.split(" ").first().toInt())
            listB.add(it.split(" ").last().toInt())
        }

        var accumulator = 0

        listA.forEach { a ->
            var counter = 0
            listB.forEach { b ->
                if (a == b) {
                    counter++
                }
            }
            accumulator += a * counter
        }

        return accumulator
    }

    val input = readInput("day01/Day01_test")
    part1(input).println()
    part2(input).println()
}