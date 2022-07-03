package id.kodesumsi.telkompengmas.data.source.network.request

data class UpdateChildDataRequest(

    val childId: Int,

    val weight: Int,

    val height: Int,

    val headCircumference: Int,

    val heightZScore: Float? = null,

    val weightZScore: Float? = null,

    val headCircumferenceZScore: Float? = null

)
