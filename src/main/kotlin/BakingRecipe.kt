class BakingRecipe(
    name: String,
    ingredients: List<String>,
    instructions: String,
    private var ovenTemp: Double,
    private var bakeTime: Double
) : Recipe(name, ingredients, instructions), CookingMethod {

    fun getOvenTemp(): Double = ovenTemp
    fun setOvenTemp(value: Double) { ovenTemp = value }

    fun getBakeTime(): Double = bakeTime
    fun setBakeTime(value: Double) { bakeTime = value }

    override fun get_cooking_time(): Double = bakeTime

    override fun describe(): String {
        return "Baking Recipe: ${getName()}, Temp: $ovenTemp°C, Time: $bakeTime min"
    }
}