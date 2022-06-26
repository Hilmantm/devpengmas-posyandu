package id.kodesumsi.telkompengmas.domain.model

data class Child(

    val id: Int,

    val name: String,

    val surname: String,

    val birthDate: String,

    val address: String,

    val idDesa: Int,

    val idPosyandu: Int,

    val idParent: Int,

    val parentName: String,

    val height: Float,

    val weight: Float,

    val headCircumference: Float,

    val gender: Int

)