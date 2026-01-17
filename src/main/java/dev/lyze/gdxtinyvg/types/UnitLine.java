package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.enums.Range;
import java.io.IOException;
import lombok.*;

public class UnitLine {
    /**
     * Start point of the line.
     */
    private UnitPoint start;
    /**
     * End point of the line.
     */
    private UnitPoint end;

    public UnitLine(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        this(new UnitPoint(stream, range, fractionBits), new UnitPoint(stream, range, fractionBits));
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnitLine))
            return false;
        final UnitLine other = (UnitLine) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$start = this.getStart();
        final Object other$start = other.getStart();
        if (this$start == null ? other$start != null : !this$start.equals(other$start))
            return false;
        final Object this$end = this.getEnd();
        final Object other$end = other.getEnd();
        if (this$end == null ? other$end != null : !this$end.equals(other$end))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnitLine;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $start = this.getStart();
        result = result * PRIME + ($start == null ? 43 : $start.hashCode());
        final Object $end = this.getEnd();
        result = result * PRIME + ($end == null ? 43 : $end.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UnitLine(start=" + this.getStart() + ", end=" + this.getEnd() + ")";
    }

    /**
     * Start point of the line.
     */
    public UnitPoint getStart() {
        return this.start;
    }

    /**
     * End point of the line.
     */
    public UnitPoint getEnd() {
        return this.end;
    }

    public UnitLine() {
    }

    /**
     * Creates a new {@code UnitLine} instance.
     *
     * @param start Start point of the line.
     * @param end   End point of the line.
     */
    public UnitLine(final UnitPoint start, final UnitPoint end) {
        this.start = start;
        this.end = end;
    }
}
