package com.example.jetpack_compose_all_in_one.features.random_fox.model

import com.example.jetpack_compose_all_in_one.di.RandomFoxAPI
import com.example.jetpack_compose_all_in_one.utils.ResultState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RandomFoxImpl @Inject constructor(@RandomFoxAPI val service: RandomFoxService) :
    IRandomFoxRepository {
    override suspend fun getRandomFoxCoroutines(): ResultState<RandomFoxResponse> {
        val response = service.getRandomFoxUsingCoroutines()
        when {
            response.isSuccessful -> {
                return ResultState.Success(response.body())
            }

            !response.isSuccessful -> {
                return ResultState.Error(response.errorBody().toString())
            }

            else -> {}
        }

        return ResultState.Loading()
    }

    override suspend fun getRandomFoxFlow() =
        flow {
            service.getRandomFoxUsingCoroutines().apply {
                this.body()?.let {
                    emit(
                        ResultState.Success(
                            it
                        )
                    ).runCatching {
                        emit(
                            ResultState.Error<RandomFoxResponse>(
                                it.toString()
                            )
                        )
                    }
                }
            }
        }

    override fun getRandomFoxRxJava() = service.getRandomFoxUsingRxJava()
}