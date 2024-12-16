package day06

import println
import readInput

fun main() {
    fun part1(text: List<String>): Int {
        val map = text.map { it.toCharArray() }
        var guardPosX = text.first { it.contains('^') }.indexOf('^')
        var guardPosY = text.indexOfFirst { it.contains('^') }
        var guardDir = 'U'

        while (true) {
            map[guardPosY][guardPosX] = 'X'
            when {
                guardDir == 'L' && guardPosX == 0 -> break
                guardDir == 'R' && guardPosX == map[0].size - 1 -> break
                guardDir == 'U' && guardPosY == 0 -> break
                guardDir == 'D' && guardPosY == map.size - 1 -> break
            }
            guardDir = when (guardDir) {
                'L' -> if (text[guardPosY][guardPosX - 1] != '#') {
                    guardPosX--; guardDir
                } else 'U'

                'R' -> if (text[guardPosY][guardPosX + 1] != '#') {
                    guardPosX++; guardDir
                } else 'D'

                'U' -> if (text[guardPosY - 1][guardPosX] != '#') {
                    guardPosY--; guardDir
                } else 'R'

                'D' -> if (text[guardPosY + 1][guardPosX] != '#') {
                    guardPosY++; guardDir
                } else 'L'

                else -> break
            }
            //printMap(map)
        }
        return map.sumOf { row -> row.count { it == 'X' } }
    }

    fun part2(input: List<String>): Int {
        var count = 0
        for (i in input.indices) {
            for (j in input[0].indices) {
                val map = input.map { it.toCharArray() }
                if (map[i][j] == '#' || map[i][j] == '^') continue
                map[i][j] = '#'

                //println("$i $j")
                //printMap(map)

                var guardPosX = input.first { it.contains('^') }.indexOf('^')
                var guardPosY = input.indexOfFirst { it.contains('^') }
                var guardDir = 'U'
                val visited = mutableMapOf<Pair<Int, Int>, MutableList<Char>>()
                var isLoop = false

                while (true) {
                    map[guardPosY][guardPosX] = 'X'
                    if (visited[guardPosY to guardPosX]?.contains(guardDir) == true) {
                        isLoop = true
                        break
                    }
                    visited.getOrPut(guardPosY to guardPosX) { mutableListOf() }.add(guardDir)
                    when {
                        guardDir == 'L' && guardPosX == 0 -> break
                        guardDir == 'R' && guardPosX == map[0].size - 1 -> break
                        guardDir == 'U' && guardPosY == 0 -> break
                        guardDir == 'D' && guardPosY == map.size - 1 -> break
                    }
                    guardDir = when (guardDir) {
                        'L' -> if (map[guardPosY][guardPosX - 1] != '#') {
                            guardPosX--; guardDir
                        } else 'U'

                        'R' -> if (map[guardPosY][guardPosX + 1] != '#') {
                            guardPosX++; guardDir
                        } else 'D'

                        'U' -> if (map[guardPosY - 1][guardPosX] != '#') {
                            guardPosY--; guardDir
                        } else 'R'

                        'D' -> if (map[guardPosY + 1][guardPosX] != '#') {
                            guardPosY++; guardDir
                        } else 'L'

                        else -> break
                    }
                    //printMap(map)
                }
                //println(isLoop)
                if (isLoop) {
                    count++
                    //printMap(map)
                }
            }
        }
        //println(count)

        return count
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("/day06/Day06")
    part1(input).println()
    part2(input).println()
}
