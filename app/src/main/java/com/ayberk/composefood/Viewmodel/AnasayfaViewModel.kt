package com.ayberk.composefood.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.composefood.Repo.YemeklerDaoRepository
import com.ayberk.composefood.entity.Yemekler

class AnasayfaViewModel(application: Application) : AndroidViewModel(application) {

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var yrepo = YemeklerDaoRepository(application)

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

     fun yemekleriYukle() {
        yrepo.tumYemekleriAl()
    }

}