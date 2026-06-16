package model
import interfaces.CookingMethod

class BakingRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    private var ovenTemp: Double = DEFAULT_OVEN_TEMP,
    private var bakeTime: Double = DEFAULT_BAKE_TIME
) : Recipe(name, ingredients, instructions), CookingMethod {

    fun getOvenTemp(): Double = ovenTemp
    fun setOvenTemp(value: Double) { ovenTemp = value }
    fun getBakeTime(): Double = bakeTime
    fun setBakeTime(value: Double) { bakeTime = value }

    override fun get_cooking_time(): Double = bakeTime

    override fun describe(): String {
        return "Baking Recipe: ${getName()}, Temp: $ovenTemp°C, Time: $bakeTime min"
    }

    companion object {
        // Sabit dəyərlər
        const val DEFAULT_OVEN_TEMP = 180.0
        const val DEFAULT_BAKE_TIME = 40.0
    }
}