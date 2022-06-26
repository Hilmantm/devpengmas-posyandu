package id.kodesumsi.telkompengmas.domain.model

data class Article(

    val id: Int,

    val thumbUrl: String,

    val title: String,

    val author: String,

    val createdAt: String,

    val data: String,

    val tag: List<String> = listOf()

)
