package id.kodesumsi.telkompengmas.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSourceImpl
import id.kodesumsi.telkompengmas.data.source.local.room.PengmasDatabase
import id.kodesumsi.telkompengmas.utils.Constant.Companion.DATABASE_NAME
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDataConfig {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext ctx: Context
    ): PengmasDatabase {
        return Room.databaseBuilder(
            ctx,
            PengmasDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: PengmasDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(database.userDao())
    }

}