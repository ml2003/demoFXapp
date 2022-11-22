package com.example.demofx;

import java.util.HashMap;
import java.util.Map;

public class GuessNumber {
    Map<String,Complexity> complexity = new HashMap<>();

    public GuessNumber() {
        complexity.put("EASY", new Complexity(4,10));
        complexity.put("MEDIUM", new Complexity(5,100));
        complexity.put("HARD", new Complexity(7, 1000));
    }
}
