package com.vitorthemyth.tiktok2023tutorials.di

import com.vitorthemyth.tiktok2023tutorials.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {
        SplashViewModel()
    }
}