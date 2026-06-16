package app

import model.*
import service.RecipeSearch
import java.io.PrintStream

fun main() {
    System.setOut(PrintStream(System.out, true, "UTF-8"))

    val recipesByCuisine: Map<CuisineType, List<Recipe>> = mapOf(
        CuisineType.AZERBAIJANI to listOf(
            VegetarianRecipe(
                "Piti",
                listOf("noxud", "kartof", "soğan", "ərik gavalısı", "duz"),
                "Saxsı qabda bişirin."
            ),
            NonVegetarianRecipe(
                "Dövgə",
                listOf("quzu", "noxud", "düyü", "kefir", "göyərti"),
                "Bişirib qarışdırın."
            ),
            BakingRecipe(
                "Paxlava",
                listOf("xəmir", "qoz", "şəkər"),
                "Sobada bişir.",
                180.0,
                40.0
            )
        ),
        CuisineType.ITALIAN to listOf(
            VegetarianRecipe(
                "Margherita Pizza",
                listOf("xəmir", "sous", "pendir"),
                "Sobada bişir."
            )
        ),
        CuisineType.CHINESE to listOf(
            VegetarianRecipe(
                "Fried Rice",
                listOf("düyü", "yumurta", "tərəvəz"),
                "Qızart."
            )
        ),
        CuisineType.AMERICAN to listOf(
            VegetarianRecipe(
                "Mac and Cheese",
                listOf("makaron", "pendir"),
                "Qarışdır."
            )
        ),
        CuisineType.OTHER to listOf(
            VegetarianRecipe(
                "Soup",
                listOf("tərəvəz"),
                "Qaynat."
            )
        )
    )

    var currentCuisine = selectCuisine()
    val search = RecipeSearch()

    fun loadCuisine(cuisine: CuisineType) {
        search.clearRecipes()
        recipesByCuisine[cuisine]?.forEach { search.addRecipe(it) }
        println("\nLoaded: ${cuisine.displayName()}\n")
    }

    loadCuisine(currentCuisine)

    while (true) {
        println("===== MENU (${currentCuisine.displayName()}) =====")
        println("1. Ingredient search")
        println("2. Name search")
        println("3. Show all")
        println("4. Change cuisine")
        println("0. Exit")
        print("Choice: ")

        when (readLine()?.trim()) {
            "1" -> {
                print("Ingredient: ")
                val ing = readLine().orEmpty()
                search.search_by_ingredient(ing).forEach {
                    println(it.describe())
                }
            }

            "2" -> {
                print("Name: ")
                val name = readLine().orEmpty()
                search.search_by_name(name).forEach {
                    println(it.describe())
                }
            }

            "3" -> {
                search.getAllRecipes().forEach {
                    println(it.describe())
                }
            }

            "4" -> {
                currentCuisine = selectCuisine()
                loadCuisine(currentCuisine)
            }

            "0" -> return
        }

        println()
    }
}

fun selectCuisine(): CuisineType {
    println("""
        1. Azerbaijan
        2. Italy
        3. China
        4. America
        5. Other
    """.trimIndent())

    return when (readLine()?.trim()) {
        "1" -> CuisineType.AZERBAIJANI
        "2" -> CuisineType.ITALIAN
        "3" -> CuisineType.CHINESE
        "4" -> CuisineType.AMERICAN
        "5" -> CuisineType.OTHER
        else -> CuisineType.AZERBAIJANI
    }
}

fun CuisineType.displayName(): String = when (this) {
    CuisineType.AZERBAIJANI -> "Azerbaijan"
    CuisineType.ITALIAN -> "Italy"
    CuisineType.CHINESE -> "China"
    CuisineType.AMERICAN -> "America"
    CuisineType.OTHER -> "Other"
}