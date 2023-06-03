package dev.ai4j.chat;

import dev.ai4j.Tokenizer;

import java.util.Objects;

public abstract class ChatMessage {

    private static final Tokenizer TOKENIZER = new Tokenizer();
    private static final int EXTRA_TOKENS_PER_EACH_CHAT_MESSAGE = 5;

    private final String contents;
    private final int numberOfTokens;

    ChatMessage(String contents) {
        this.contents = contents;
        this.numberOfTokens = countTokens(contents);
    }

    public String contents() {
        return contents;
    }

    public int numberOfTokens() {
        return numberOfTokens;
    }

    private static int countTokens(String contents) {
        // TODO calculate according to a used model
        // see https://jtokkit.knuddels.de/docs/getting-started/recipes/chatml
        // see https://github.com/openai/openai-cookbook/blob/main/examples/How_to_count_tokens_with_tiktoken.ipynb
        return TOKENIZER.countTokens(contents) + EXTRA_TOKENS_PER_EACH_CHAT_MESSAGE; // approximating for now
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contents);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "contents='" + contents + '\'' +
                ", numberOfTokens=" + numberOfTokens +
                '}';
    }
}