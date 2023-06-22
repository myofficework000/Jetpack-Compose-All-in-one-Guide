package com.example.jetpack_compose_all_in_one.android_architectures.mvp.presenter

interface DogMVP {

    interface DogPresenter{
        fun getDog()
        fun attachView(view: DogView)
        fun detachView()
    }

    interface DogView{
        fun setResult(url: String)
        fun onLoad(isLoading: Boolean)
        fun showError(message: String)
    }
}