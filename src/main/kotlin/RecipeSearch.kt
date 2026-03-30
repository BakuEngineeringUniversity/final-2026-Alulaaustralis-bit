class RecipeSearch {
    private val recipes: MutableList<Recipe> = mutableListOf()

    fun addRecipe(recipe: Recipe) {
        recipes.add(recipe)
    }

    fun search_by_ingredient(ingredient: String): List<Recipe> {
        return recipes.filter { recipe ->
            recipe.getIngredients().any { it.contains(ingredient, ignoreCase = true) }
        }
    }

    fun search_by_name(name: String): List<Recipe> {
        return recipes.filter { it.getName().contains(name, ignoreCase = true) }
    }
}