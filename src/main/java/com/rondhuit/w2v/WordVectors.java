package com.rondhuit.w2v;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordVectors implements Serializable {

    private static final long serialVersionUID = -272309887639936333L;
    public transient final Charset ENCODING = Charset.forName("UTF-8");
    private int words, size;
    private String[] vocab;
    private float[][] matrix;

    public WordVectors(int layer1Size, int vocabSize, VocabWord[] _vocab,
            double[] syn0) {

        words = vocabSize;
        size = layer1Size;

        vocab = new String[words];
        matrix = new float[words][];

        for (int i = 0; i < words; i++) {
            vocab[i] = _vocab[i].word;
            matrix[i] = new float[size];
            double len = 0;
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (float) syn0[i * layer1Size + j];
                len += matrix[i][j] * matrix[i][j];
            }
            len = Math.sqrt(len);
            for (int j = 0; j < size; j++) {
                matrix[i][j] /= len;
            }
        }

    }

    public int getSize() {
        return size;
    }

    public int getNumWords() {
        return words;
    }

    public String getWord(int idx) {
        return vocab[idx];
    }

    public float getMatrixElement(int row, int column) {
        return matrix[row][column];
    }

    public Word getWord(String input) {
        // linear search the input word in vocabulary

        float[] vec = null;
        int bi = -1;
        double len = 0;
        for (int i = 0; i < words; i++) {
            if (input.equals(getWord(i))) {
                bi = i;
                System.out.printf("\nWord: %s  Position in vocabulary: %d\n",
                        input, bi);
                vec = new float[size];
                for (int j = 0; j < size; j++) {
                    vec[j] = getMatrixElement(bi, j);
                    len += vec[j] * vec[j];
                }
            }
        }
        if (vec == null) {
            return null;
        }

        len = Math.sqrt(len);
        for (int i = 0; i < size; i++) {
            vec[i] /= len;
        }

        return new Word(input, vec, new int[]{bi});
    }

    public SortedSet<Map.Entry<Double,Word>> search(Word word) {
        
        SortedSet<Map.Entry<Double,Word>> sortedset = new TreeSet<Map.Entry<Double, Word>>(
                new Comparator<Map.Entry<Double, Word>>() {
                    @Override
                    public int compare(Entry<Double, Word> o1,
                            Entry<Double, Word> o2) {
                        return o2.getKey().compareTo(o1.getKey());
                    }
                });
        
        for (int i = 0; i < words; i++) {
            if( word.contains(i)) {
                continue;
            }
            
            double dist = 0;
            for (int j = 0; j < size; j++) {
                dist += word.vec[j] * getMatrixElement(i, j);
            }
            
            Word w = new Word(vocab[i], matrix[i], null);
            SimpleEntry<Double, Word> e = new AbstractMap.SimpleEntry<Double, Word>(dist, w);
            sortedset.add(e);
        }
        

        return sortedset;
    }
    
}
