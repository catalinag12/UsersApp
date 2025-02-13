package com.example.usersapp.view.DI

import com.example.usersapp.data.UserRemoteSource
import com.example.usersapp.data.UserRepositoryImpl
import com.example.usersapp.domain.UserRepository
import com.example.usersapp.presentation.UserInteractor
import com.example.usersapp.presentation.UserInteractorImpl
import com.example.usersapp.view.MainActivity
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Component(
    modules = [NetworkingModule::class, DataModule::class]
)
interface UserComponent {
    fun inject(target: MainActivity)
}

@Module
internal class DataModule {

    @Provides
    fun provideInteractor(impl: UserInteractorImpl): UserInteractor = impl

    @Provides
    fun provideRepository(impl: UserRepositoryImpl): UserRepository = impl

}


@Module
internal class NetworkingModule {

    //  @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): UserRemoteSource =
        retrofit.create(UserRemoteSource::class.java)
}