package com.example.doctors.ui.doctors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doctors.MainActivity;
import com.example.doctors.databinding.FragmentDoctorsBinding;
import com.example.doctors.models.Person;

import java.util.ArrayList;

public class DoctorsFragment extends Fragment implements LifecycleOwner {

    private FragmentDoctorsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DoctorsViewModel doctorsViewModel =
                new ViewModelProvider(this).get(DoctorsViewModel.class);

        binding = FragmentDoctorsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recycler = binding.recycler;
        recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recycler.setHasFixedSize(true);
        DoctorsAdapter doctorsAdapter = new DoctorsAdapter();
        recycler.setAdapter(doctorsAdapter);
        doctorsViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Person>>() {
            @Override
            public void onChanged(ArrayList<Person> people) {
                doctorsAdapter.setList(people);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}