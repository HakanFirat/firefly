package com.example.fireflyhakanfirat.di.module

import com.example.fireflyhakanfirat.domain.*
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class DomainModule {

    @Binds
    internal abstract fun provideRegisterUseCase(registerUseCaseImpl: RegisterUseCaseImpl): RegisterUseCase

    @Binds
    internal abstract fun provideLoginUseCase(loginUseCaseImpl: LoginUseCaseImpl): LoginUseCase

    @Binds
    internal abstract fun provideGetUserListUseCase(getUserListUseCaseImpl: GetUserListUseCaseImpl): GetUserListUseCase

    @Binds
    internal abstract fun provideGetUserUseCase(getUserUseCaseImpl: GetUserUseCaseImpl): GetUserUseCase

    @Binds
    internal abstract fun provideUpdateUserUseCase(updateUserUseCaseImpl: UpdateUserUseCaseImpl): UpdateUserUseCase

    @Binds
    internal abstract fun provideDeleteUserUseCase(deleteUserUseCaseImpl: DeleteUserUseCaseImpl): DeleteUserUseCase

}