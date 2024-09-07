package dev.pablorjd.focustimer.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.pablorjd.focustimer.data.repository.LocalStorageRepositoryImp
import dev.pablorjd.focustimer.domain.repository.LocalStorageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindLocalStorageRepository(localStorageRepositoryImp: LocalStorageRepositoryImp): LocalStorageRepository
}