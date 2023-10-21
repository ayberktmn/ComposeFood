package com.ayberk.composefood.Viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AnasayfaViewModelFactory(var application: Application):ViewModelProvider.NewInstanceFactory() {

  override  fun <T : ViewModel> create(modelClass: Class<T>): T {
      return AnasayfaViewModel(application) as T
  }
}