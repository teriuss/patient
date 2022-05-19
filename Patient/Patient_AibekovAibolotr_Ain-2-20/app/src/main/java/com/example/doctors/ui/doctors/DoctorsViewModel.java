package com.example.doctors.ui.doctors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.doctors.R;
import com.example.doctors.models.Person;

import java.util.ArrayList;

public class DoctorsViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Person>> persons;
    private ArrayList<Person> doctorList;

    public void setList(ArrayList<Person> person) {
        doctorList = person;
        init();
    }

    public DoctorsViewModel() {
        persons = new MutableLiveData<>();
        init();
    }

    private void init(){
        populateList();
        persons.setValue(doctorList);
    }

    private void populateList(){
        doctorList = new ArrayList<>();
        doctorList.add(new Person("Кубатбеков Алакен", 209, "Нейрохирург", R.drawable.img_2));
        doctorList.add(new Person("Керимов Адис", 508, "Стоматолог", R.drawable.img_10));
        doctorList.add(new Person("Исмаилов Эрсултан", 308, "Хирург", R.drawable.img));
        doctorList.add(new Person("Ахметов Исмаил", 205, "Педиатор", R.drawable.img_3));
        doctorList.add(new Person("Нуржанов Салмоор", 102, "Уролог", R.drawable.img_4));
        doctorList.add(new Person("Куштарбеков Кубанычбек", 304, "Психотерапевт", R.drawable.img_5));
        doctorList.add(new Person("Чандаев Бекжан", 303, "Лор", R.drawable.img_1));
        doctorList.add(new Person("Бакаев Дастан", 406, "Окулист", R.drawable.img_7));
        doctorList.add(new Person("Айбеков Айболот", 207, "Семейный врач", R.drawable.img_8));
        doctorList.add(new Person("Мелисов Санжар", 402, "Хирург", R.drawable.img_9));
    }

    public LiveData<ArrayList<Person>> getMutableLiveData() {
        return persons;
    }
}