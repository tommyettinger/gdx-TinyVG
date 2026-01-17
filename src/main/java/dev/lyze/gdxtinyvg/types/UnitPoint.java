package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.enums.Range;
import java.io.IOException;
import lombok.*;

/**
 * Points are a X and Y coordinate pair.
 */
public class UnitPoint {
    /**
     * Horizontal distance of the point to the origin.
     */
    private Unit x;
    /**
     * Vertical distance of the point to the origin.
     */
    private Unit y;

    public UnitPoint(UnitPoint point) {
        x = new Unit(point.x);
        y = new Unit(point.y);
    }

    /**
     * @return Converts x and y coordinates of the Unit into a Vector2. Instantiates
     *         a new vector.
     */
    public Vector2 convert() {
        return convert(new Vector2());
    }

    /**
     * @param storage The vector which gets written to.
     * @return Converts x and y coordinates of the Unit into a Vector2.
     */
    public Vector2 convert(Vector2 storage) {
        return storage.set(x.convert(), y.convert());
    }

    public UnitPoint(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        this(new Unit(stream, range, fractionBits), new Unit(stream, range, fractionBits));
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnitPoint))
            return false;
        final UnitPoint other = (UnitPoint) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$x = this.getX();
        final Object other$x = other.getX();
        if (this$x == null ? other$x != null : !this$x.equals(other$x))
            return false;
        final Object this$y = this.getY();
        final Object other$y = other.getY();
        if (this$y == null ? other$y != null : !this$y.equals(other$y))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnitPoint;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $x = this.getX();
        result = result * PRIME + ($x == null ? 43 : $x.hashCode());
        final Object $y = this.getY();
        result = result * PRIME + ($y == null ? 43 : $y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UnitPoint(x=" + this.getX() + ", y=" + this.getY() + ")";
    }

    /**
     * Horizontal distance of the point to the origin.
     */
    public Unit getX() {
        return this.x;
    }

    /**
     * Vertical distance of the point to the origin.
     */
    public Unit getY() {
        return this.y;
    }

    public UnitPoint() {
    }

    /**
     * Creates a new {@code UnitPoint} instance.
     *
     * @param x Horizontal distance of the point to the origin.
     * @param y Vertical distance of the point to the origin.
     */
    public UnitPoint(final Unit x, final Unit y) {
        this.x = x;
        this.y = y;
    }
}
