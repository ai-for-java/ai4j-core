package dev.ai4j.document;

import java.util.List;

public interface DocumentSplitter {

    List<Document> split(Document document);
}
