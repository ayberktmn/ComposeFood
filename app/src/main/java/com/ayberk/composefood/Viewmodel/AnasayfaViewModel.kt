package com.ayberk.composefood.Viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.composefood.Repo.YemeklerDaoRepository
import com.ayberk.composefood.entity.Yemekler

class AnasayfaViewModel : ViewModel() {

    var yemeklerListesi = MutableLiveData<List<Yemekler>>()
    var yrepo = YemeklerDaoRepository()

    init {
        yemekleriYukle()
        yemeklerListesi = yrepo.yemekleriGetir()
    }

     fun yemekleriYukle() {
        yrepo.tumYemekleriAl()
    }

}