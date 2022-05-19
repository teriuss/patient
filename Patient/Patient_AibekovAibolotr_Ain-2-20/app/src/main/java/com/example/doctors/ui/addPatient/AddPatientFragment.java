package com.example.doctors.ui.addPatient;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.example.doctors.R;
import com.example.doctors.dao.PatientDAO;
import com.example.doctors.databinding.FragmentAddPatientBinding;
import com.example.doctors.models.Patient;
import com.example.doctors.ui.doctors.DoctorsViewModel;
import com.example.doctors.ui.home.HomeFragment;
import com.example.doctors.ui.room.AppDatabase;

import java.net.Inet4Address;


public class AddPatientFragment extends Fragment {

    private FragmentAddPatientBinding binding;
    private AppDatabase appDatabase;
    private PatientDAO patientDAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DoctorsViewModel doctorsViewModel =
                new ViewModelProvider(this).get(DoctorsViewModel.class);

        binding = FragmentAddPatientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.appDatabase = Room.databaseBuilder(binding.getRoot().getContext(), AppDatabase.class, "database").allowMainThreadQueries().build();

        patientDAO = appDatabase.patientDAO();

        binding.addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient patient = new Patient();
                patient.setF_name(binding.fName.getText().toString());
                patient.setL_name(binding.lName.getText().toString());
                patient.setPatronymic(binding.patronymic.getText().toString());
                patient.setPhone(binding.phone.getText().toString());
                if(!(patient.getF_name().isEmpty() || patient.getL_name().isEmpty() || patient.getPhone().isEmpty() || patient.getPatronymic().isEmpty())){
                    patientDAO.insertPatient(patient);
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    navController.navigateUp();
                    navController.navigate(R.id.navigation_home);
                } else{
                    AlertDialog alertDialog = new AlertDialog.Builder(binding.getRoot().getContext()).create();
                    alertDialog.setTitle("Ошибка");
                    alertDialog.setMessage("Введите все данные");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

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