package com.example.midterm_jen_henry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private Button buttonBack;
    private ListView listViewHistory;
    private ArrayAdapter<Integer> adapter;
    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Initialize views
        buttonBack = view.findViewById(R.id.buttonBack);
        listViewHistory = view.findViewById(R.id.listViewHistory);

        // Setup adapter
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, viewModel.getGeneratedNumbers());
        listViewHistory.setAdapter(adapter);

        // Back button click
        buttonBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return view;
    }

}