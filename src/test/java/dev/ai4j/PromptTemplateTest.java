package dev.ai4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PromptTemplateTest {

    @Test
    void should_create_prompt_from_template_with_one_parameter() {

        PromptTemplate promptTemplate = new PromptTemplate("My name is {{name}}.");

        String prompt = promptTemplate.format("name", "Klaus");

        assertThat(prompt).isEqualTo("My name is Klaus.");
    }

    @Test
    void should_create_prompt_from_template_with_multiple_parameters() {

        PromptTemplate promptTemplate = new PromptTemplate("My name is {{name}} {{surname}}.");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "Klaus");
        parameters.put("surname", "Heißler");


        String prompt = promptTemplate.format(parameters);


        assertThat(prompt).isEqualTo("My name is Klaus Heißler.");
    }

    @NullSource
    @ValueSource(strings = {"", " ", "  "})
    @ParameterizedTest
    void should_throw_when_template_is_invalid(String template) {

        assertThatThrownBy(() -> new PromptTemplate(template))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Template cannot be null or empty");
    }

    @NullSource
    @ValueSource(strings = {"", " ", "  "})
    @ParameterizedTest
    void should_throw_when_parameter_name_is_invalid(String parameterName) {
        PromptTemplate promptTemplate = new PromptTemplate("Hello {{name}}");

        assertThatThrownBy(() -> promptTemplate.format(parameterName, "World"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Parameter name cannot be null or empty");
    }

    @Test
    void should_throw_when_parameter_name_is_unknown() {
        PromptTemplate promptTemplate = new PromptTemplate("My name is {{name}}.");

        assertThatThrownBy(() -> promptTemplate.format("banana", "banana"))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("There is no parameter 'banana' in prompt template 'My name is {{name}}.'");
    }

    @Test
    void should_throw_when_parameter_value_is_invalid() {
        PromptTemplate promptTemplate = new PromptTemplate("Hello {{name}}");

        assertThatThrownBy(() -> promptTemplate.format("name", null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Parameter value cannot be null");
    }

    @Test
    void should_throw_when_not_all_parameters_are_resolved() {
        PromptTemplate promptTemplate = new PromptTemplate("{{greeting}} {{name}}");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "Klaus");

        assertThatThrownBy(() -> promptTemplate.format(parameters))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Parameter(s) [greeting] are not resolved in prompt '{{greeting}} Klaus'");
    }
}