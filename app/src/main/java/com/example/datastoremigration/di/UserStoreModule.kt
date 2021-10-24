package com.example.datastoremigration.di

import com.example.datastoremigration.store.UserStoreImpl
import com.example.datastoremigration.store.UserStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserStoreModule {

    @Singleton
    @Binds
    abstract fun bindUserStore(impl: UserStoreImpl): UserStore
}
