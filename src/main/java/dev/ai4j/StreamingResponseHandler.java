package dev.ai4j;

public interface StreamingResponseHandler {

    void onPartialResponse(String partialResponse);

    default void onComplete() {
    }

    void onError(Throwable error);
}
