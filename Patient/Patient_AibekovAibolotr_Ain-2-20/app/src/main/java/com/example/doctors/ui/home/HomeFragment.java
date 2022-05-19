package com.example.doctors.ui.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.doctors.MainActivity;
import com.example.doctors.dao.PatientDAO;
import com.example.doctors.databinding.FragmentHomeBinding;
import com.example.doctors.models.Patient;
import com.example.doctors.ui.room.AppDatabase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AppDatabase appDatabase;
    private PatientDAO patientDAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView recyclerView = binding.patientRecycler;
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recyclerView.setHasFixedSize(true);
        PatientAdapter patientAdapter = new PatientAdapter();
        recyclerView.setAdapter(patientAdapter);
        appDatabase = Room.databaseBuilder(binding.getRoot().getContext(), AppDatabase.class, "database").allowMainThreadQueries().build();
        patientDAO = appDatabase.patientDAO();
        patientAdapter.setList(patientDAO.getAll());

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}