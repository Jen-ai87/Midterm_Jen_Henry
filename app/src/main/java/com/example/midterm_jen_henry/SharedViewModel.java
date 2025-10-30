package com.example.midterm_jen_henry;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
public class SharedViewModel extends ViewModel {
    private List<Integer> generatedNumbers = new ArrayList<>();

    public List<Integer> getGeneratedNumbers() {
        return generatedNumbers;
    }

    public void addGeneratedNumber(int number) {
        if (!generatedNumbers.contains(number)) {
            generatedNumbers.add(number);
        }
    }
}
