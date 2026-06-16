package model

class VegetarianRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String
) : Recipe(name, ingredients, instructions) {

    override fun describe(): String {
        return "Vegetarian Recipe: ${getName()}"
    }
}