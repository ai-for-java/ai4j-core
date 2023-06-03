package dev.ai4j.chat;

public class UserMessage extends ChatMessage {

    public UserMessage(String contents) {
        super(contents);
    }

    public static UserMessage of(String contents) {
        return new UserMessage(contents);
    }

    public static UserMessage userMessage(String contents) {
        return of(contents);
    }
}
