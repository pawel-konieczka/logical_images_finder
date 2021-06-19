package pl.konieczki.logicalimages.model;

import lombok.Getter;

@Getter
public class FieldsRange {

    public static final int UNDEFINED = -1;

    protected int fieldFrom;
    protected int fieldTo;

    protected FieldsRange(int fieldFrom, int fieldTo) {
        this.fieldFrom = fieldFrom;
        this.fieldTo = fieldTo;
    }

    public int getLength() {
        if (this.fieldFrom != UNDEFINED && this.fieldTo != UNDEFINED)
            return this.fieldTo - this.fieldFrom + 1;
        return 0;
    }

    @Override
    public String toString() {
        return "[" + this.fieldFrom + "," + this.fieldTo + "]";
    }

    public static FieldsRange createUndefined() {
        return createSingle(UNDEFINED);
    }

    public static FieldsRange createSingle(int field) {
        return new FieldsRange(field, field);
    }

    public static FieldsRange createRange(int from, int to) {
        return new FieldsRange(from, to);
    }
}