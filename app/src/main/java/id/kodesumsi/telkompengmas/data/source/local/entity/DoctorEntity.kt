package id.kodesumsi.telkompengmas.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    val name: String,

    val phone: String,

)