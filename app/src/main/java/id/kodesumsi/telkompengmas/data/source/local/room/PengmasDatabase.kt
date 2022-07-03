package id.kodesumsi.telkompengmas.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.kodesumsi.telkompengmas.data.source.local.dao.DoctorDao
import id.kodesumsi.telkompengmas.data.source.local.dao.UserDao
import id.kodesumsi.telkompengmas.data.source.local.entity.DoctorEntity
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import id.kodesumsi.telkompengmas.utils.Constant.Companion.DATABASE_VERSION

@Database(entities = [UserEntity::class, DoctorEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class PengmasDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun doctorDao(): DoctorDao

}