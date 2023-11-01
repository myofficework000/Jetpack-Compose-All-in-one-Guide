package com.technocraze.mvvmdatehistroy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpack_compose_all_in_one.demos.history_of_day.model.Dates
import com.example.jetpack_compose_all_in_one.demos.history_of_day.repository.HistoryRepository
import com.example.jetpack_compose_all_in_one.demos.history_of_day.service.NetworkResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel
@Inject constructor(private val historyRepository: HistoryRepository) : ViewModel() {

  private val _historyState = MutableStateFlow<HistoryState>(HistoryState(false, emptyList(), ""))

  val historyState: StateFlow<HistoryState>
    get() = _historyState

  data class HistoryState(
    var isLoading: Boolean = false,
    var historyDates: List<Dates> = emptyList(),
    var errorMessage: String = "Error message"
  )

  init {
    _historyState.update {state->
      state.copy(
        isLoading = true,
        errorMessage = ""
      )
    }
    getHistory()
  }

  fun getHistory() {
    viewModelScope.launch(IO) {
      var res = historyRepository.fetchHistory()
      when (res){
        is NetworkResult.Success ->  {
          _historyState.update {state->
            state.copy(
              isLoading = false,
              historyDates = res.data!!,
              errorMessage = ""
            )
          }
        }
        is NetworkResult.Error ->{
          _historyState.update {state->
            state.copy(
              isLoading = false,
              errorMessage = res.message!!
            )
          }
        }
        else -> {}
      }
    }
  }


}