package pl.konieczki.logicalimages.model;

import lombok.Getter;
import lombok.NonNull;

public class FieldSequence extends FieldsRange {

    @Getter
    private final int id;
    @Getter
    private final int length;
    @Getter
    private boolean completed = false;
    @Getter
    private boolean identified = false;

    public FieldSequence(int id, int length) {
        super(FieldsRange.UNDEFINED, FieldsRange.UNDEFINED);
        if (length < 1)
            throw new IllegalArgumentException("Minimalna długość wynosi 1");
        this.id = id;
        this.length = length;
    }

    public FieldSequence(int id, int length, int fieldFrom, int fieldTo) {
        this(id, length);
        this.setFieldFrom(fieldFrom);
        this.setFieldTo(fieldTo);
    }

    public boolean setFieldFrom(int poleOd) {
        if (this.fieldFrom != FieldsRange.UNDEFINED && poleOd > this.fieldFrom)
            throw new IllegalArgumentException("Przesuniecie wsteczne (z " + this.fieldFrom + " na " + poleOd + ")");
        final boolean changed = this.fieldFrom != poleOd;
        if (changed) {
            this.fieldFrom = poleOd;
            if (this.fieldTo == FieldsRange.UNDEFINED)
                this.fieldTo = poleOd;
            this.identified = true;
            checkCompleted();
        }
        return changed;
    }

    public boolean setFieldTo(int poleDo) {
        if (this.fieldTo != FieldsRange.UNDEFINED && poleDo < this.fieldTo)
            throw new IllegalArgumentException("Przesuniecie wsteczne (z " + this.fieldTo + " na " + poleDo + ")");
        final boolean changed = this.fieldTo != poleDo;
        if (changed) {
            this.fieldTo = poleDo;
            if (this.fieldFrom == FieldsRange.UNDEFINED)
                this.fieldFrom = poleDo;
            this.identified = true;
            checkCompleted();
        }
        return changed;
    }

    public boolean setRange(@NonNull FieldsRange zakres) {
        final boolean b1 = this.setFieldFrom(zakres.fieldFrom);
        final boolean b2 = this.setFieldTo(zakres.fieldTo);
        return b1 || b2;
    }

    @Override
    public String toString() {
        return String.format("%d[%d:%d%s]", this.length, this.fieldFrom, this.fieldTo, this.completed ? "*" : "");
    }

    private void checkCompleted() {
        final int definedLength = super.getLength();
        if (definedLength > this.length)
            throw new IllegalStateException(
                    "Długość wg zakresów (" + definedLength + ") przekracza długość zdefiniowaną ("
                            + this.length + ")"
            );
        this.completed = length == definedLength;
    }
}