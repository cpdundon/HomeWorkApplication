package com.example.homeworkapplication.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.homeworkapplication.R;
import com.example.homeworkapplication.adapter.PersonAdapter;
import com.example.homeworkapplication.databinding.ActivityMainBinding;
import com.example.homeworkapplication.model.Person;
import com.example.homeworkapplication.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    // TODO: 2/3/2021 Get data from viewmodel and load into recyclerview..

    private MainViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        setupViews();
        initObservers();
    }

    private void initObservers() {
        viewModel.getPeople().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                PersonAdapter personAdapter = new PersonAdapter(people);
                binding.rvImageList.setAdapter(personAdapter);
            }
        });

//        viewModel.getErrorMessage().observe(this, errorMessage -> {
//
//        });
    }

    private void setupViews() {
        binding.btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String countString = ((AppCompatEditText) findViewById(R.id.et_count)).getText().toString();
                if (!countString.isEmpty())
                    viewModel.loadPeopleList(Integer.parseInt(countString));
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvImageList.setLayoutManager(linearLayoutManager);
    }
}