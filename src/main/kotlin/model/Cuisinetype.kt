package model

enum class CuisineType {
    AZERBAIJANI,
    ITALIAN,
    CHINESE,
    AMERICAN,
    OTHER;


    fun displayName(): String = when (this) {
        AZERBAIJANI -> "Azerbaijan"
        ITALIAN -> "Italy"
        CHINESE -> "China"
        AMERICAN -> "America"
        OTHER -> "Other"
    }

    companion object {

        fun fromChoice(choice: String?): CuisineType {
            return when (choice?.trim()) {
                "1" -> AZERBAIJANI
                "2" -> ITALIAN
                "3" -> CHINESE
                "4" -> AMERICAN
                "5" -> OTHER
                else -> AZERBAIJANI
            }
        }
    }
}