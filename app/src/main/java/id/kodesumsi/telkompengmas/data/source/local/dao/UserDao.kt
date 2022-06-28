package id.kodesumsi.telkompengmas.data.source.local.dao

import androidx.room.*
import id.kodesumsi.telkompengmas.data.source.local.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserEntity): Completable

    @Query("SELECT * FROM users LIMIT 1")
    fun getUser(): Single<UserEntity>

    @Delete
    fun removeUser(user: UserEntity): Completable

}