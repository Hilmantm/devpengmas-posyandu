package id.kodesumsi.telkompengmas.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.kodesumsi.telkompengmas.BuildConfig
import id.kodesumsi.telkompengmas.data.ParentRepositoryImpl
import id.kodesumsi.telkompengmas.data.PosyanduRepositoryImpl
import id.kodesumsi.telkompengmas.data.PublicRepositoryImpl
import id.kodesumsi.telkompengmas.data.source.local.LocalDataSource
import id.kodesumsi.telkompengmas.data.source.network.RemoteDataSource
import id.kodesumsi.telkompengmas.domain.interactor.UserInteractor
import id.kodesumsi.telkompengmas.domain.repository.ParentRepository
import id.kodesumsi.telkompengmas.domain.repository.PosyanduRepository
import id.kodesumsi.telkompengmas.domain.repository.PublicRepository
import id.kodesumsi.telkompengmas.domain.usecase.UserUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Injection {

    @Provides
    @Singleton
    fun providesParentRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): ParentRepository {
        return ParentRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun providesPosyanduRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): PosyanduRepository {
        return PosyanduRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun providesPublicRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): PublicRepository {
        return PublicRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Provides
    @Singleton
    fun providesUserInteractor(
        parentRepository: ParentRepository,
        posyanduRepository: PosyanduRepository,
        publicRepository: PublicRepository
    ): UserUseCase {
        return UserInteractor(parentRepository, posyanduRepository, publicRepository)
    }

}