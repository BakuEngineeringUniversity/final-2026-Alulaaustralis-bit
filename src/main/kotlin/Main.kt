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

    println("--- Search by ingredient: 'chicken' ---")
    search.search_by_ingredient("chicken").forEach { println(it.describe()) }

    println("--- Search by name: 'cake' ---")
    search.search_by_name("cake").forEach { println(it.describe()) }

    println("--- Cooking time for BakingRecipe ---")
    println("${r3.getName()}: ${r3.get_cooking_time()} dəqiqə")
}