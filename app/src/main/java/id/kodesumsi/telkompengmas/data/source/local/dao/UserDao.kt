package id.kodesumsi.telkompengmas.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import io.reactivex.rxjava3.core.Completable

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun login(user: UserEntity): Completable

}