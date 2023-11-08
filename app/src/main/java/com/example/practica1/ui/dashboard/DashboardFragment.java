package com.example.practica1.ui.dashboard;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practica1.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private EditText txtName, txtDescription;
    private ArrayList<String> consolas;
    private ListView lyt1;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> descriptions;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        txtName = binding.txtName;
        txtDescription = binding.txtDescription;
        lyt1 = binding.listConsoles;
        consolas = new ArrayList<>();
        descriptions = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, consolas);
        lyt1.setAdapter(adapter);

        binding.btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveConsole();
            }
        });
        lyt1.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(getContext(), "Consola: "+consolas.get(position) + " "+descriptions.get(position), Toast.LENGTH_SHORT).show();
        });
        final TextView textView = binding.textDashboard;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void saveConsole(){
        if(txtName.getText().toString().isEmpty()||txtDescription.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Debe rellenar los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        descriptions.add(txtDescription.getText().toString());
        consolas.add(txtName.getText().toString());
        adapter.notifyDataSetChanged();
        Toast.makeText(getContext(), "Consola agregada", Toast.LENGTH_SHORT).show();
    }
}