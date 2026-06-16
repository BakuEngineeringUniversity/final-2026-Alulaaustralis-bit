package model

abstract class Recipe(
    private var name: String,
    private var ingredients: List<String>,
    private var instructions: String
) {
    fun getName(): String = name
    fun setName(value: String) { name = value }

    fun getIngredients(): List<String> = ingredients
    fun setIngredients(value: List<String>) { ingredients = value }

    fun getInstructions(): String = instructions
    fun setInstructions(value: String) { instructions = value }

    abstract fun describe(): String
}