package dev.ai4j.chat;

public class AiMessage extends ChatMessage {

    public AiMessage(String contents) {
        super(contents);
    }

    public static AiMessage of(String contents) {
        return new AiMessage(contents);
    }

    public static AiMessage aiMessage(String contents) {
        return of(contents);
    }
}
