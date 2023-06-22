package com.example.jetpack_compose_all_in_one.android_architectures.mvp.presenter

import com.example.jetpack_compose_all_in_one.android_architectures.mvp.model.DogRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DogPresenter(
    private val dogRepository: DogRepository,
    private var dogView: DogMVP.DogView?
) : DogMVP.DogPresenter {

    override fun attachView(view: DogMVP.DogView) {
        dogView = view
    }

    override fun detachView() {
        dogView = null
    }

    override fun getDog() {
        dogView?.onLoad(true)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val dogResponse = dogRepository.getDogImage()
                withContext(Dispatchers.Main) {
                    if(dogResponse.isSuccessful){
                        dogResponse.body()?.let { dogView?.setResult(it.url) }
                    } else {
                        dogView?.showError("Error fetching dog image")
                    }
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    dogView?.showError("Error fetching dog image : ${e.message}")
                    dogView?.onLoad(false)
                }
            }

        }
    }

}