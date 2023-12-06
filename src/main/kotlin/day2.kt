package day2

import java.io.File


fun exercise1 ()
{
    val filename = "./inputs/day2ex1.txt"
    val cubes = mapOf<String, Int>("red" to 12, "green" to 13, "blue" to 14)
    var total: Int = 0
    for ((id, line) in File(filename).readLines().withIndex())
    {
        var possible = true
        val picks = line.split(":")[1].split(",", ";")
        for (pick in picks)
        {
            val num_color = pick.trimStart().split(" ")
            if (num_color[0].toInt() > cubes[num_color[1]]!!)
            {
                possible = false
                break
            }
        }
        if (possible) total += id + 1
    }
    println("Total ${total}")
}


fun exercise2 ()
{
    val filename = "./inputs/day2ex2.txt"
    var total: Int = 0
    for ((id, line) in File(filename).readLines().withIndex())
    {
        var mins = mutableMapOf<String, Int>("red" to 0, "green" to 0, "blue" to 0)
        val picks = line.split(":")[1].split(",", ";")
        for (pick in picks)
        {
            val num_color = pick.trimStart().split(" ")
            val num = num_color[0].toInt()
            val color = num_color[1]
            if (num > mins[color]!!) mins[color] = num
        }
        val power = mins["red"]!! * mins["green"]!! * mins["blue"]!!
        total += power
    }
    println("Total ${total}")
}