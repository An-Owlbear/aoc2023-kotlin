package com.anowlbear

fun main() {
    val input = object {}.javaClass.getResourceAsStream("/day1.txt")?.bufferedReader()?.readLines()
    val result = input?.sumOf { line -> getFirstLastSum(line) }
    println(result)
}

fun getFirstLastSum(line: String): Int {
    val pattern = Regex("[0-9]")
    val numbers = pattern.findAll(line)
    return (numbers.first().value + numbers.last().value).toInt()
}