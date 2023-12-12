package com.anowlbear

val numbers = mapOf(
    "one" to '1',
    "two" to '2',
    "three" to '3',
    "four" to '4',
    "five" to '5',
    "six" to '6',
    "seven" to '7',
    "eight" to '8',
    "nine" to '9'
)

fun main() {
    val input = object {}.javaClass.getResourceAsStream("/day1.txt")?.bufferedReader()?.readLines()
    val result = input?.sumOf { line -> getFirstLastSum(line) }
    println(result)
}

fun getFirstLastSum(line: String): Int {
    val digits = getAsDigits(line)
    return (digits.first().toString() + digits.last().toString()).toInt()
}

fun getAsDigits(input: String): List<Char> {
    if (input.isEmpty()) return emptyList()
    else {
        val digit = parseNextDigit(input)
        val nextInput = input.drop(1)
        return when (digit) {
            null -> getAsDigits(nextInput)
            else -> listOf(digit) + getAsDigits(nextInput)
        }
    }
}

fun parseNextDigit(input: String): Char? {
    if (input[0] in '1'..'9')
        return input[0]
    else {
        val keyIterator = numbers.keys.iterator()
        while (keyIterator.hasNext()) {
            val key = keyIterator.next()
            if (input.startsWith(key)) {
                return numbers[key]
            }
        }
    }

    return null
}