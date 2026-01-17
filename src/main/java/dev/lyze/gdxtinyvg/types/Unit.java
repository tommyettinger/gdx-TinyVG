package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.enums.FractionBits;
import dev.lyze.gdxtinyvg.enums.Range;
import java.io.IOException;
import lombok.*;

/**
 * The unit is the common type for both positions and sizes in the vector
 * graphic. It is encoded as a signed integer with a configurable amount of bits
 * (see Coordinate Range) and fractional bits.
 */
public class Unit {
    private int value;
    private Range range;
    private int fractionBits;

    public Unit(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        this(range.read(stream), range, fractionBits);
    }

    public Unit(Unit unit) {
        this.value = unit.value;
        this.range = unit.range;
        this.fractionBits = unit.fractionBits;
    }

    /**
     * @return Returns the actual float value.
     */
    public float convert() {
        if (fractionBits == 0)
            return value;
        int num = value >> fractionBits;
        int decimals = value << -fractionBits >>> -fractionBits;
        return num + FractionBits.calculate(decimals, fractionBits);
    }

    public String toString() {
        return "Unit(FloatValue=" + convert() + ", value=" + this.getValue() + ", range=" + this.getRange()
                + ", fractionBits=" + this.getFractionBits() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Unit))
            return false;
        final Unit other = (Unit) o;
        if (!other.canEqual((Object) this))
            return false;
        if (this.getValue() != other.getValue())
            return false;
        if (this.getFractionBits() != other.getFractionBits())
            return false;
        final Object this$range = this.getRange();
        final Object other$range = other.getRange();
        if (this$range == null ? other$range != null : !this$range.equals(other$range))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Unit;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getValue();
        result = result * PRIME + this.getFractionBits();
        final Object $range = this.getRange();
        result = result * PRIME + ($range == null ? 43 : $range.hashCode());
        return result;
    }

    public int getValue() {
        return this.value;
    }

    public Range getRange() {
        return this.range;
    }

    public int getFractionBits() {
        return this.fractionBits;
    }

    public void setValue(final int value) {
        this.value = value;
    }

    public void setRange(final Range range) {
        this.range = range;
    }

    public void setFractionBits(final int fractionBits) {
        this.fractionBits = fractionBits;
    }

    public Unit() {
    }

    public Unit(final int value, final Range range, final int fractionBits) {
        this.value = value;
        this.range = range;
        this.fractionBits = fractionBits;
    }
}
