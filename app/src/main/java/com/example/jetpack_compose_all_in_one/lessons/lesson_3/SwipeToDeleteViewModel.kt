package com.example.jetpack_compose_all_in_one.lessons.lesson_3

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SwipeToDeleteViewModel @Inject constructor(private val personRepository: PersonRepository) :
    ViewModel() {

    private val _personList = MutableStateFlow<List<Person>>(listOf())
    val personList = _personList.asStateFlow()


    fun getPersonList() {
        _personList.value = personRepository.getPersonList()
    }

    fun deletePerson(person: Person) {
        val list = _personList.value.toMutableList()
        list.remove(person)
        _personList.value = list
    }

}