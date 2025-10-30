package com.example.midterm_jen_henry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import java.util.ArrayList;
import java.util.List;


public class MultiplicationFragment extends Fragment {
    private EditText editTextNumber;
    private Button buttonGenerate, buttonHistory;
    private ListView listViewTable;
    private ArrayAdapter<String> adapter;
    private List<String> tableList = new ArrayList<>();
    private SharedViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiplication, container, false);

        // Initializing the ViewModel
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        // Initializing the views
        editTextNumber = view.findViewById(R.id.editTextNumber);
        buttonGenerate = view.findViewById(R.id.buttonGenerate);
        buttonHistory = view.findViewById(R.id.buttonHistory);
        listViewTable = view.findViewById(R.id.listViewTable);

        // Setting up the adapter
        adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, tableList);
        listViewTable.setAdapter(adapter);

        // Generate button listener
        buttonGenerate.setOnClickListener(v -> generateTable());

        // History button listener
        buttonHistory.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HistoryFragment())
                    .addToBackStack(null)
                    .commit();
        });

        // ListView item click
        listViewTable.setOnItemClickListener((parent, view1, position, id) -> {
            showDeleteDialog(position);
        });

        return view;
    }

    private void generateTable() {
        String input = editTextNumber.getText().toString();

        if (input.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter a number", Toast.LENGTH_SHORT).show();
            return;
        }

        int number = Integer.parseInt(input);
        tableList.clear();

        for (int i = 1; i <= 10; i++) {
            tableList.add(number + " × " + i + " = " + (number * i));
        }

        adapter.notifyDataSetChanged();
        viewModel.addGeneratedNumber(number);
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Delete Row")
                .setMessage("Do you want to delete this row?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    String deletedItem = tableList.get(position);
                    tableList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(requireContext(), "Deleted: " + deletedItem, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
