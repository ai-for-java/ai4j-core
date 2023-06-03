package dev.ai4j.embedding;

import dev.ai4j.document.Document;

import java.util.Collection;

public interface EmbeddingModel {

    Embedding embed(Document document);

    Embedding embed(String text);

    Collection<Embedding> embed(Collection<Document> documents); // TODO Iterable, List, Set?
}