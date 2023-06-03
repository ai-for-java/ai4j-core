package dev.ai4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromptTemplate {

    private static final Pattern PROMPT_TEMPLATE_PARAMETER_PATTERN = Pattern.compile("\\{\\{(.+?)}}");

    private final String template;

    public PromptTemplate(String template) {
        if (template == null || template.trim().isEmpty()) {
            throw new IllegalArgumentException("Template cannot be null or empty");
        }
        this.template = template;
    }

    public String format(Map<String, Object> parameters) {
        String prompt = template;

        if (parameters == null || parameters.isEmpty()) {
            throw new IllegalArgumentException("Parameters cannot be null or empty");
        }

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            prompt = replaceAll(prompt, entry.getKey(), entry.getValue().toString());
        }

        verifyAllParametersResolved(prompt);

        return prompt;
    }

    public String format(String parameterName, Object parameterValue) {
        String result = replaceAll(template, parameterName, parameterValue);

        verifyAllParametersResolved(result);

        return result;
    }

    private static String replaceAll(String template, String parameterName, Object parameterValue) {
        validate(parameterName);
        validate(parameterValue);
        verifyParameterExists(parameterName, template);
        return template.replaceAll(inDoubleCurlyBracketsEscaped(parameterName), parameterValue.toString());
    }

    private static void validate(String parameterName) {
        if (parameterName == null || parameterName.trim().isEmpty()) {
            throw new IllegalArgumentException("Parameter name cannot be null or empty");
        }
    }

    private static void validate(Object parameterValue) {
        if (parameterValue == null || parameterValue.toString() == null) {
            throw new IllegalArgumentException("Parameter value cannot be null");
        }
    }

    private static void verifyParameterExists(String parameterName, String template) {
        if (!template.contains(inDoubleCurlyBrackets(parameterName))) {
            throw new IllegalArgumentException(String.format("There is no parameter '%s' in prompt template '%s'", parameterName, template));
        }
    }

    private static String inDoubleCurlyBrackets(String parameterName) {
        return "{{" + parameterName + "}}";
    }

    private static String inDoubleCurlyBracketsEscaped(String parameterName) {
        return "\\{\\{" + Pattern.quote(parameterName) + "\\}\\}";
    }

    private static void verifyAllParametersResolved(String prompt) {
        List<String> unresolvedParameterNames = getUnresolvedParameterNames(prompt);

        if (!unresolvedParameterNames.isEmpty()) {
            throw new IllegalArgumentException(String.format("Parameter(s) [%s] are not resolved in prompt '%s'",
                    String.join(", ", unresolvedParameterNames),
                    prompt
            ));
        }
    }

    private static List<String> getUnresolvedParameterNames(String prompt) {
        List<String> unresolvedParameterNames = new ArrayList<>();
        Matcher matcher = PROMPT_TEMPLATE_PARAMETER_PATTERN.matcher(prompt);

        while (matcher.find()) {
            unresolvedParameterNames.add(matcher.group(1));
        }

        return unresolvedParameterNames;
    }

    public static PromptTemplate from(String template) {
        return new PromptTemplate(template);
    }
}
