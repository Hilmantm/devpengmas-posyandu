package id.kodesumsi.telkompengmas.domain.model

data class ChildStatistics(

    val id: Int,

    val height: Float,

    val weight: Float,

    val headCircumference: Float,

    val date: String,

    val childId: Int,

    val zScore: Float,

    val statistics: Statistics

)
