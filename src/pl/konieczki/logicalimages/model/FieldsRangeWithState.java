package pl.konieczki.logicalimages.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class FieldsRangeWithState extends FieldsRange {

    private final FieldState state;

    public FieldsRangeWithState(int from, int to, @NonNull FieldState state) {
        super(from, to);
        this.state = state;
    }

    @Override
    public String toString() {
        return "[" + this.fieldFrom + "," + this.fieldTo + "," + this.state.getMarker() + "]";
    }
}
