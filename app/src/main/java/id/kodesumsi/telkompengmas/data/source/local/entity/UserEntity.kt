package id.kodesumsi.telkompengmas.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val name: String,

    val email: String,

    val idDesa: Int,

    val idPosyandu: Int,

    val token: String,

    val userRole: Int

)