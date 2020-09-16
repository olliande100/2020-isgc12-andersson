package com.evabui.labb2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ordgenerator {
    private List<String> ord;
    private Random random;

    public Ordgenerator() {
        this.random = new Random();
        this.ord = new ArrayList<>();
        ord.add("bibliotek");
        ord.add("badhus");
        ord.add("husbil");
        ord.add("motorcykel");
        ord.add("demokrati");
        ord.add("ugn");
    }

    public String slumpaOrd() {
        int index = this.random.nextInt(this.ord.size());
        String ordet = this.ord.get(index);
        return ordet;
    }
}
