package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.enums.Range;
import java.io.IOException;
import lombok.*;

public class UnitRectangle {
    /**
     * Horizontal distance of the left side to the origin.
     */
    private Unit x;
    /**
     * Vertical distance of the upper side to the origin.
     */
    private Unit y;
    /**
     * Horizontal extent of the rectangle.
     */
    private Unit width;
    /**
     * Vertical extent of the rectangle origin.
     */
    private Unit height;

    public UnitRectangle(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        this(new Unit(stream, range, fractionBits), new Unit(stream, range, fractionBits),
                new Unit(stream, range, fractionBits), new Unit(stream, range, fractionBits));
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnitRectangle))
            return false;
        final UnitRectangle other = (UnitRectangle) o;
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
        final Object this$width = this.getWidth();
        final Object other$width = other.getWidth();
        if (this$width == null ? other$width != null : !this$width.equals(other$width))
            return false;
        final Object this$height = this.getHeight();
        final Object other$height = other.getHeight();
        if (this$height == null ? other$height != null : !this$height.equals(other$height))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnitRectangle;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $x = this.getX();
        result = result * PRIME + ($x == null ? 43 : $x.hashCode());
        final Object $y = this.getY();
        result = result * PRIME + ($y == null ? 43 : $y.hashCode());
        final Object $width = this.getWidth();
        result = result * PRIME + ($width == null ? 43 : $width.hashCode());
        final Object $height = this.getHeight();
        result = result * PRIME + ($height == null ? 43 : $height.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UnitRectangle(x=" + this.getX() + ", y=" + this.getY() + ", width=" + this.getWidth() + ", height="
                + this.getHeight() + ")";
    }

    /**
     * Horizontal distance of the left side to the origin.
     */
    public Unit getX() {
        return this.x;
    }

    /**
     * Vertical distance of the upper side to the origin.
     */
    public Unit getY() {
        return this.y;
    }

    /**
     * Horizontal extent of the rectangle.
     */
    public Unit getWidth() {
        return this.width;
    }

    /**
     * Vertical extent of the rectangle origin.
     */
    public Unit getHeight() {
        return this.height;
    }

    public UnitRectangle() {
    }

    /**
     * Creates a new {@code UnitRectangle} instance.
     *
     * @param x      Horizontal distance of the left side to the origin.
     * @param y      Vertical distance of the upper side to the origin.
     * @param width  Horizontal extent of the rectangle.
     * @param height Vertical extent of the rectangle origin.
     */
    public UnitRectangle(final Unit x, final Unit y, final Unit width, final Unit height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
