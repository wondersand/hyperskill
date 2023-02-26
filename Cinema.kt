package cinema

var CINEMA_SIZE = 0
var SOLD_TICKETS = 0
var FACT_INCOME = 0

fun createCinema() : MutableList<MutableList<String>> {
    println("Enter the number of rows:")
    val r = readln().toInt()

    println("Enter the number of seats in each row:")
    val s = readln().toInt()

    CINEMA_SIZE = r * s

    return MutableList(r){MutableList(s){"S"}}

}

fun showSeats(list: MutableList<MutableList<String>>) {
    println("Cinema:")
    for (i in 1..list[0].size) print(if (i == list[0].size) " $i\n" else " $i")
    for (i in 1..list.size) println("$i ${list[i - 1].joinToString(" ")}")
}

fun checkPrice(list: MutableList<MutableList<String>>, orderRow: Int): Int {
    return if (CINEMA_SIZE > 60 && orderRow - 1 < list.size / 2 || CINEMA_SIZE < 60) {10} else {8}
}

fun buyTicket(list: MutableList<MutableList<String>>) {
    println("Enter a row number:")
    val orderRow = readln().toInt()

    println("Enter a seat number in that row:")
    val orderSeat = readln().toInt()

    val price = checkPrice(list,orderRow)

    try {
        if (list[orderRow-1][orderSeat-1] == "B") {
            print("That ticket has already been purchased!\n")
            buyTicket(list)
        } else {
            list[orderRow-1][orderSeat-1] = "B"
            println("Ticket price: \$$price")
            SOLD_TICKETS++
            FACT_INCOME += price
        }
    } catch (e: Exception) {
        print ("Wrong input!\n")
        buyTicket(list)
    }
}

fun calcTotalIncome(list: MutableList<MutableList<String>>): Int {
    var totalIncome = 0
    for(i in 1..list.size) {
        totalIncome += checkPrice(list,i) * list[0].size
    }
    return totalIncome
}

fun formatPercentage(percentage: Double): String {
    return "%.2f".format(percentage)
}

fun showStats(list: MutableList<MutableList<String>>) {
    val possibleIncome = calcTotalIncome(list)
    println("Number of purchased tickets: $SOLD_TICKETS")
    println("Percentage: "+ formatPercentage(SOLD_TICKETS.toDouble()/CINEMA_SIZE.toDouble()*100) + "%")
    println("Current income: \$$FACT_INCOME")
    println("Total income: \$$possibleIncome\n")
}

fun printMenu(list: MutableList<MutableList<String>>) {
    println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit\n")
    when(readln().toInt()) {
        1 -> showSeats(list)
        2 -> buyTicket(list)
        3 -> showStats(list)
        0 -> return
    }
    printMenu(list)
}

fun main() {
    printMenu(createCinema())
}
