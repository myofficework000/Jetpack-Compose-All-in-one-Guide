package com.example.jetpack_compose_all_in_one.lessons.lesson_3

class PersonRepositoryImpl : PersonRepository {
    override fun getPersonList(): List<Person> {
        val list = mutableListOf<Person>()
        for (i in 0..50) {
            list.add(Person(i, "Person $i"))
        }
        return list
    }
}
