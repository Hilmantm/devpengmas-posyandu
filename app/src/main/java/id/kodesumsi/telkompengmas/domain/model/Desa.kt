package id.kodesumsi.telkompengmas.domain.model

data class Desa(

    val id: Int,

    val name: String

) {

    override fun toString(): String {
        return name
    }

}
