package com.rondhuit.w2v;

import java.util.ArrayList;
import java.util.List;

public class Word {

    public String word;
    float[] vec;
    List<Integer> bi;

    private Word() {
    }

    public Word(String word, float[] vec, int[] bi) {
        this.word = word;
        this.vec = vec;
        this.bi = new ArrayList<Integer>();
        if (null != bi) {
            for (int i : bi) {
                this.bi.add(i);
            }
        }
    }

    public boolean contains(int i) {
        if (null == bi) {
            return false;
        }
        return bi.contains(i);
    }

    static public Word add(Word w1, Word w2) {
        Word w = new Word();
        w.word = null;
        w.vec = new float[w1.vec.length];
        w.bi = new ArrayList<Integer>();
        for (int i = 0; i < w1.vec.length; i++) {
            w.vec[i] = w1.vec[i] + w2.vec[i];
        }
        if (null != w1.bi) {
            for (int i : w1.bi) {
                w.bi.add(i);
            }
        }
        if (null != w2.bi) {
            for (int i : w2.bi) {
                w.bi.add(i);
            }
        }
        
        return w;
    }

    static public Word minus(Word w1, Word w2) {
        Word w = new Word();
        w.word = null;
        w.vec = new float[w1.vec.length];
        w.bi = new ArrayList<Integer>();
        for (int i = 0; i < w1.vec.length; i++) {
            w.vec[i] = w1.vec[i] - w2.vec[i];
        }
        if (null != w1.bi) {
            for (int i : w1.bi) {
                w.bi.add(i);
            }
        }
        if (null != w2.bi) {
            for (int i : w2.bi) {
                w.bi.add(i);
            }
        }

        return w;
    }

    public Word norm() {
        double len = 0;
        for (int i = 0; i < vec.length; i++) {
            len += vec[i] * vec[i];
        }   

        len = Math.sqrt(len);
        for (int i = 0; i < vec.length; i++) {
            vec[i] /= len;
        }
        return this;
    }

}
