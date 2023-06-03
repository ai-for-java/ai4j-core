package dev.ai4j.chat;

public class SystemMessage extends ChatMessage {

    public SystemMessage(String contents) {
        super(contents);
    }

    public static SystemMessage of(String contents) {
        return new SystemMessage(contents);
    }

    public static SystemMessage systemMessage(String contents) {
        return of(contents);
    }
}
