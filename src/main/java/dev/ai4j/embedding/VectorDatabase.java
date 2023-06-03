package dev.ai4j.embedding;

import java.util.List;

public interface VectorDatabase {

    void persist(Embedding embedding);

    void persist(Iterable<Embedding> embeddings);

    List<Embedding> findRelated(Embedding embedding, int maxResults);

    // TODO delete, update, size, etc
}
