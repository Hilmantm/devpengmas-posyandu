package id.kodesumsi.telkompengmas.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.kodesumsi.telkompengmas.data.source.local.entity.DoctorEntity
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface DoctorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveDoctor(doctor: DoctorEntity): Completable

    @Query("SELECT * FROM doctors")
    fun getDoctors(): Flowable<List<DoctorEntity>>

}