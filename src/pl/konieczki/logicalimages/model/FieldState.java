package pl.konieczki.logicalimages.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum FieldState {
    EMPTY(' '),
    FULL('X'),
    UNDEFINED('?');

    private final char marker;

    public static FieldState fromCode(char c) {
        return Arrays.stream(FieldState.values())
                .filter(stan -> stan.getMarker() == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Niepoprawne oznaczenie stanu: " + c));
    }
}
