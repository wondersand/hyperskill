package machine

var labels:MutableList<String> = mutableListOf("water", "milk", "coffee beans", "disposable cups", "money")
var units:MutableList<String> = mutableListOf("ml of ", "ml of ", "grams of ", "", "$")
var resources:MutableList<Int> = mutableListOf(400, 540, 120, 9, 550)

fun main() {
    showMenu()
}

fun printState() {
    println("The coffee machine has:")
    for (i in 0 until labels.lastIndex) {
        println("" + resources[i] + " " + units[i] + labels[i])
    }
    println(units[4] + resources[4] + " of " +  labels[4])

}

fun buyCoffee() {
    println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
    var recipe:MutableList<Int> = mutableListOf<Int>()
    when (readln()) {
        "1" -> recipe = mutableListOf(250, 0, 16, 1, -4) //espresso
        "2" -> recipe = mutableListOf(350, 75, 20, 1, -7) //latte
        "3" -> recipe = mutableListOf(200, 100, 12, 1, -6) //cappuccino
        "back" -> return
    }
    for (i in 0 until labels.lastIndex) {
        if (recipe[i] == 0) { continue } else if (resources[i] / recipe[i] < 1) {
            return println("Sorry, not enough " + labels[i] + "!")
        }
    }
    println("I have enough resources, making you a coffee!")

    for (i in 0..labels.lastIndex) {
        resources[i]-=recipe[i]
    }

}

fun takeRevenue() {
    println("I gave you \$" + resources[4])
    resources[4] = 0
}

fun showMenu() {
    println("Write action (buy, fill, take, remaining, exit):")
    when (readln()) {
        "buy" -> buyCoffee()
        "fill" -> fillMachine()
        "take" -> takeRevenue()
        "remaining" -> printState()
        "exit" -> return
    }
    showMenu()
}

fun fillMachine() {
    for (i in 0 until labels.lastIndex) {
        println("Write how many " + units[i]+labels[i] + "you want to add:")
        resources[i] += readln().toInt()
    }
}
