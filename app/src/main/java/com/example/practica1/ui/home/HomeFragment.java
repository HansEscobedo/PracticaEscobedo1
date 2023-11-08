package com.example.practica1.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.practica1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private EditText txtRut, txtName2, txtAge;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txtAge =binding.txtAge;
        txtName2 = binding.txtName2;
        txtRut = binding.txtRut;

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void save(){
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(txtRut.getText().toString(), txtName2.getText().toString());
        editor.putString(txtRut.getText().toString()+"a", txtAge.getText().toString());
        editor.commit();
    }

    public void search(){
        SharedPreferences preferences = getActivity().getSharedPreferences("datos", getContext().MODE_PRIVATE);
        String name = preferences.getString(txtRut.getText().toString(), "");
        String age = preferences.getString(txtRut.getText().toString()+"a", "");
        txtAge.setText(age);
        txtName2.setText(name);
    }
}