package dev.ai4j.chat;

import dev.ai4j.StreamingResponseHandler;

import java.util.List;

public interface ChatModel {

    AiMessage chat(List<ChatMessage> messages);

    AiMessage chat(ChatMessage... messages);

    String chat(String userMessage);

    void chat(List<ChatMessage> messages, StreamingResponseHandler streamingResponseHandler);

    <S> S getOne(Class<S> structured); // TODO move somewhere else? separate model for this?

    <S> List<S> getMultiple(Class<S> structured, int n);
}
