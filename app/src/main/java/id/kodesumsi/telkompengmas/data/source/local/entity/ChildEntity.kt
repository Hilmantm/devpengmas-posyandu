package id.kodesumsi.telkompengmas.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChildEntity(

    @PrimaryKey
    val id: Int,
    val name: String

)