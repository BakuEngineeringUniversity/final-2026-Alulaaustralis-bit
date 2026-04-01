class NonVegetarianRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String
) : Recipe(name, ingredients, instructions) {

    override fun describe(): String {
        return "Non-Vegetarian Recipe: ${getName()}"
    }
}