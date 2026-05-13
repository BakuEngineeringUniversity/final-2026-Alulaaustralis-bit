fun main() {
    System.setOut(java.io.PrintStream(System.out, true, "UTF-8"))

    val recipesByCuisine = mapOf(
        CuisineType.AZERBAIJANI to listOf(
            VegetarianRecipe(
                "Piti",
                listOf("noxud", "kartof", "soğan", "ərik gavalısı", "duz"),
                "Saxsı qabda bütün inqredientləri birlikdə bişirin."
            ),
            NonVegetarianRecipe(
                "Dövgə",
                listOf("quzuəti", "noxud", "düyü", "kefir", "göyərti"),
                "Əti qaynadın, noxud əlavə edin, kefir tökün, qarışdırın."
            ),
            BakingRecipe(
                "Paxlava",
                listOf("xəmir", "qoz", "şəkər", "yağ", "həşərot yağı"),
                "Xəmiri yayın, qoz doldurun, kəsin, sobada bişirin.",
                180.0, 40.0
            )
        ),
        CuisineType.ITALIAN to listOf(
            VegetarianRecipe(
                "Margherita Pizza",
                listOf("xəmir", "tomat sousu", "mozzarella", "reyhan"),
                "Xəmir üzərinə sousa sürtün, pendir əlavə edin, sobada bişirin."
            ),
            NonVegetarianRecipe(
                "Spaghetti Bolognese",
                listOf("spagetti", "qiymə ət", "tomat", "soğan", "sarımsaq"),
                "Əti qızardın, tomat əlavə edin, bişmiş spagetti ilə qarışdırın."
            ),
            BakingRecipe(
                "Tiramisu",
                listOf("maskarpone", "yumurta", "şəkər", "espresso", "savoiardi"),
                "Krem hazırlayın, şirniyyatı batırın, qatlayın, soyudun.",
                0.0, 240.0
            )
        ),
        CuisineType.CHINESE to listOf(
            VegetarianRecipe(
                "Tərəvəz Fried Rice",
                listOf("düyü", "yumurta", "yerkökü", "göy soğan", "soya sousu"),
                "Düyünü qızardın, tərəvəz əlavə edin, soya sousu tökün."
            ),
            NonVegetarianRecipe(
                "Kung Pao Chicken",
                listOf("toyuq", "fıstıq", "bibər", "soya sousu", "zəncəfil"),
                "Toyuğu dilimlə, acı bibər ilə qızart, sousla qarışdır."
            ),
            BakingRecipe(
                "Char Siu Pork",
                listOf("donuz əti", "bal", "soya sousu", "5 ədviyyat", "şərab"),
                "Əti marinə edin, sobada qızardın, bal sürün.",
                200.0, 35.0
            )
        ),
        CuisineType.AMERICAN to listOf(
            VegetarianRecipe(
                "Mac and Cheese",
                listOf("makaron", "çedar pendir", "süd", "yağ", "un"),
                "Sousu hazırlayın, bişmiş makaron ilə qarışdırın."
            ),
            NonVegetarianRecipe(
                "BBQ Burger",
                listOf("mal əti kotleti", "çörək", "pendir", "kahı", "pomidor"),
                "Kotleti qızardın, çörəyə yığın, sousa sürtün."
            ),
            BakingRecipe(
                "Brownie",
                listOf("şokolad", "yağ", "şəkər", "yumurta", "un"),
                "Əridin, qarışdırın, sobada bişirin.",
                175.0, 25.0
            )
        ),
        CuisineType.OTHER to listOf(
            VegetarianRecipe(
                "Vegetable Soup",
                listOf("yerkökü", "kartof", "soğan", "kərəviz"),
                "Bütün tərəvəzləri qaynadın."
            ),
            NonVegetarianRecipe(
                "Chicken Salad",
                listOf("toyuq", "kahı", "pomidor", "zeytun yağı"),
                "Bütün inqredientləri qarışdırın."
            ),
            BakingRecipe(
                "Chocolate Cake",
                listOf("un", "şəkər", "kakao", "yumurta"),
                "Qarışdırın və sobada bişirin.",
                180.0, 45.0
            )
        )
    )

    var currentCuisine = selectCuisine()
    val search = RecipeSearch()

    // Seçilmiş mətbəxi yüklə
    fun loadCuisine(cuisine: CuisineType) {
        search.clearRecipes()
        recipesByCuisine[cuisine]?.forEach { search.addRecipe(it) }
        println("\n✅ ${cuisine.displayName()} metbexi yuklendi!\n")
    }

    loadCuisine(currentCuisine)

    while (true) {
        println("===== RESEPT MENYU (${currentCuisine.displayName()}) =====")
        println("1. Ingrediente gore axtar")
        println("2. Ada gore axtar")
        println("3. Butun reseptleri goster")
        println("4. Metbexi deyis")
        println("0. Cix")
        print("Secim: ")

        try {
            when (readLine()?.trim()) {
                "1" -> {
                    print("Ingredient daxil edin: ")
                    val ing = readLine() ?: ""
                    if (ing.isBlank()) throw IllegalArgumentException("Ingredient bos ola bilmez!")
                    val results = search.search_by_ingredient(ing)
                    if (results.isEmpty()) println("Netice tapilmadi.")
                    else results.forEach { println("-> ${it.describe()}") }
                }
                "2" -> {
                    print("Ad daxil edin: ")
                    val name = readLine() ?: ""
                    if (name.isBlank()) throw IllegalArgumentException("Ad bos ola bilmez!")
                    val results = search.search_by_name(name)
                    if (results.isEmpty()) println("Netice tapilmadi.")
                    else results.forEach { println("-> ${it.describe()}") }
                }
                "3" -> {
                    println("\n${currentCuisine.displayName()} reseptleri:")
                    search.getAllRecipes().forEach { r ->
                        println("-> ${r.describe()}")
                        println("   Ingredientler: ${r.getIngredients().joinToString(", ")}")
                        if (r is BakingRecipe) println("   Bisirme vaxi: ${r.get_cooking_time()} deq")
                        println()
                    }
                }
                "4" -> {
                    currentCuisine = selectCuisine()
                    loadCuisine(currentCuisine)
                }
                "0" -> { println("Cixilir..."); break }
                else -> println("Yanlis secim!")
            }
        } catch (e: IllegalArgumentException) {
            println("Xeta: ${e.message}")
        } catch (e: Exception) {
            println("Gozenilmez xeta: ${e.message}")
        }

        println()
    }
}

fun selectCuisine(): CuisineType {
    println("╔══════════════════════════════╗")
    println("║       Metbex secin           ║")
    println("╠══════════════════════════════╣")
    println("║  1. Azerbaycan               ║")
    println("║  2. Italiya                  ║")
    println("║  3. Cin                      ║")
    println("║  4. Amerika                  ║")
    println("║  5. Diger                    ║")
    println("╚══════════════════════════════╝")
    print("Secim: ")

    return try {
        when (readLine()?.trim()) {
            "1" -> CuisineType.AZERBAIJANI
            "2" -> CuisineType.ITALIAN
            "3" -> CuisineType.CHINESE
            "4" -> CuisineType.AMERICAN
            "5" -> CuisineType.OTHER
            else -> { println("Yanlis secim, Azerbaycan secildi."); CuisineType.AZERBAIJANI }
        }
    } catch (e: Exception) {
        println("Xeta: ${e.message}")
        CuisineType.AZERBAIJANI
    }
}

fun CuisineType.displayName(): String = when (this) {
    CuisineType.AZERBAIJANI -> "Azerbaycan"
    CuisineType.ITALIAN     -> "Italiya"
    CuisineType.CHINESE     -> "Cin"
    CuisineType.AMERICAN    -> "Amerika"
    CuisineType.OTHER       -> "Diger"
}