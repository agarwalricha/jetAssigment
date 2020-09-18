package com.example.assignment.viewModels

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactory(_application : Application) : ViewModelProvider.NewInstanceFactory(){
    var application : Application = _application
    companion object{
        fun getInstance(application : Application) : ViewModelFactory = ViewModelFactory(application)
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(application) as T
        }
        return super.create(modelClass)
    }

}