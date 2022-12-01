package data

data class Elf(val initialCaloriesCarried: Int) {
    private val listOfCalories = ArrayList<Int>()

    init {
        listOfCalories.add(initialCaloriesCarried)
    }

    fun addCaloriesCarried(caloriesCarried: Int) {
        listOfCalories.add(caloriesCarried)
    }

    fun getListOfCaloriesCarried() : ArrayList<Int> {
        return listOfCalories
    }

    fun getTotalCalories() : Int {
        return listOfCalories.sum()
    }
}