package id.kodesumsi.telkompengmas.domain.model

data class PosyanduSpinner(

    val id: Int? = null,

    val name: String

) {

    override fun toString(): String {
        return name
    }

}
