package dev.ai4j;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.EncodingRegistry;
import com.knuddels.jtokkit.api.ModelType;

import java.util.List;

import static com.knuddels.jtokkit.api.ModelType.GPT_3_5_TURBO;

public class Tokenizer {

    private static final ModelType DEFAULT_MODEL = GPT_3_5_TURBO;
    private static final EncodingRegistry REGISTRY = Encodings.newDefaultEncodingRegistry();

    public List<Integer> encode(String text) {
        return REGISTRY.getEncodingForModel(DEFAULT_MODEL).encodeOrdinary(text);
    }

    public List<Integer> encode(String text, int maxTokensToEncode) {
        return REGISTRY.getEncodingForModel(DEFAULT_MODEL).encodeOrdinary(text, maxTokensToEncode).getTokens();
    }

    public String decode(List<Integer> tokens) {
        return REGISTRY.getEncodingForModel(DEFAULT_MODEL).decode(tokens);
    }

    public int countTokens(String text) {
        return REGISTRY.getEncodingForModel(DEFAULT_MODEL).countTokensOrdinary(text);
    }
}
