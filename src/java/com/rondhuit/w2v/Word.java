package com.rondhuit.w2v;

import java.util.ArrayList;
import java.util.List;

public class Word {
    
    public String word;
    float[] vec;
    List<Integer> bi;

    public Word(String word, float[] vec, int [] bi) {
        this.word = word;
        this.vec = vec;
        this.bi = new ArrayList<Integer>();
        for (int i : bi) {
            this.bi.add(i);
        }
    }

    public boolean contains(int i) {
        return bi.contains(i);
    }


}
