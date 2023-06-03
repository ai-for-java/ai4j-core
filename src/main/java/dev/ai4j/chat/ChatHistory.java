package dev.ai4j.chat;

import java.util.List;

public interface ChatHistory {

    void add(ChatMessage chatMessage);

    List<ChatMessage> history();
}
