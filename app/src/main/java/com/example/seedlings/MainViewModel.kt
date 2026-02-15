package com.example.seedlings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.seedlings.data.Profile
import com.example.seedlings.data.ViewState
import com.example.seedlings.net.Api
import com.example.seedlings.net.AuthInterceptor
import com.example.seedlings.net.DelProfile
import com.example.seedlings.net.GetProfile
import com.example.seedlings.net.NetService
import com.example.seedlings.net.PutProfile
import com.example.seedlings.net.SetProfile
import com.example.seedlings.net.buildRetrofit
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(private val service: NetService) : ViewModel() {
    private val mUiState = MutableLiveData<ViewState>(ViewState.None)
    val uiState: LiveData<ViewState> get() = mUiState

    fun getProfile() {
        viewModelScope.launch {
            mUiState.value = ViewState.Loading
            mUiState.value = ViewState.Content(service.getProfile())
        }
    }

    fun setProfile(profile: Profile) {
        viewModelScope.launch {
            mUiState.value = ViewState.Loading
            mUiState.value = ViewState.ContentSend(service.setProfile(profile))
        }
    }

    fun delProfile(profile: Profile) {
        viewModelScope.launch {
            mUiState.value = ViewState.Loading
            mUiState.value = ViewState.ContentSend(service.delProfile(profile))
        }
    }

    fun putProfile(profile: List<Profile>) {
        viewModelScope.launch {
            mUiState.value = ViewState.Loading
            mUiState.value = ViewState.ContentAllSend(service.putProfile(profile))
        }
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainModule {
    @Binds
    abstract fun netService(impl: NetService.Impl) : NetService

    @Binds
    abstract fun getProfile(impl: GetProfile.Impl) : GetProfile

    @Binds
    abstract fun setProfile(impl: SetProfile.Impl) : SetProfile

    @Binds
    abstract fun putProfile(impl: PutProfile.Impl) : PutProfile

    @Binds
    abstract fun delProfile(impl: DelProfile.Impl) : DelProfile
}

@Module
@InstallIn(ViewModelComponent::class)
class MainModuleProvider {
    @Provides
    fun okHttp(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        })
        .build()

    @Provides
    fun retrofit(okHttp: OkHttpClient): Retrofit = buildRetrofit(okHttp)

    @Provides
    fun api(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}