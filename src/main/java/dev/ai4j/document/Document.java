package dev.ai4j.document;

import java.util.Objects;

public class Document {

    private final String contents;

    public Document(String contents) {
        this.contents = contents;
    }

    public String contents() {
        return contents;
    }

    public static Document from(String contents) {
        return new Document(contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(contents, document.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }

    @Override
    public String toString() {
        return "Document{" +
                "contents='" + contents + '\'' +
                '}';
    }
}
