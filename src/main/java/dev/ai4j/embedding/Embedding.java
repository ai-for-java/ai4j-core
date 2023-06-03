package dev.ai4j.embedding;

import java.util.List;
import java.util.Objects;

public class Embedding {

    private final String contents;
    private final List<Double> vector; // TODO float?

    public Embedding(String contents, List<Double> vector) {
        this.contents = contents;
        this.vector = vector; // TODO copy?
    }

    public String contents() {
        return contents;
    }

    public List<Double> vector() {
        return vector;
    }

    public static Embedding from(String contents, List<Double> vector) {
        return new Embedding(contents, vector);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Embedding embedding = (Embedding) o;
        return Objects.equals(contents, embedding.contents)
                && Objects.equals(vector, embedding.vector);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents, vector);
    }

    @Override
    public String toString() {
        return "Embedding{" +
                "contents='" + contents + '\'' +
                ", vector=" + vector +
                '}';
    }
}