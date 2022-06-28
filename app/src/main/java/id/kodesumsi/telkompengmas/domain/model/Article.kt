package id.kodesumsi.telkompengmas.domain.model

data class Article(

    val id: Int? = null,

    val thumbUrl: String? = null,

    val title: String? = null,

    val author: String? = null,

    val createdAt: String? = null,

    val data: String? = null,

    val tag: List<String> = listOf(),

    val url: String? = null

)
