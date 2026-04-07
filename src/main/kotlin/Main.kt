fun main() {
    val search = RecipeSearch()

    val r1 = VegetarianRecipe(
        "Vegetable Soup",
        listOf("carrot", "potato", "onion"),
        "Boil all vegetables together."
    )
    val r2 = NonVegetarianRecipe(
        "Chicken Salad",
        listOf("chicken", "lettuce", "tomato"),
        "Mix all ingredients."
    )
    val r3 = BakingRecipe(
        "Chocolate Cake",
        listOf("flour", "sugar", "cocoa", "eggs"),
        "Mix and bake.",
        180.0,
        45.0
    )

    search.addRecipe(r1)
    search.addRecipe(r2)
    search.addRecipe(r3)

    while (true) {
        println("\n===== RESEPT MENYU =====")
        println("1. İnqredientə görə axtar")
        println("2. Ada görə axtar")
        println("3. Bişirmə vaxtını göstər")
        println("0. Çıx")
        print("Seçim: ")

        when (readLine()?.trim()) {
            "1" -> {
                print("İnqredient daxil edin: ")
                val ing = readLine() ?: ""
                val results = search.search_by_ingredient(ing)
                if (results.isEmpty()) println("Nəticə tapılmadı.")
                else results.forEach { println(it.describe()) }
            }
            "2" -> {
                print("Ad daxil edin: ")
                val name = readLine() ?: ""
                val results = search.search_by_name(name)
                if (results.isEmpty()) println("Nəticə tapılmadı.")
                else results.forEach { println(it.describe()) }
            }
            "3" -> println("${r3.getName()}: ${r3.get_cooking_time()} dəqiqə")
            "0" -> { println("Çıxılır..."); break }
            else -> println("Yanlış seçim!")
        }
    }
}