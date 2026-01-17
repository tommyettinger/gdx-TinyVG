package dev.lyze.gdxtinyvg.styles;

import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.StyleType;

/**
 * @see StyleType
 */
public class RadialGradientStyle extends GradientStyle {
    public RadialGradientStyle(TinyVG tinyVG) {
        super(tinyVG, StyleType.RADIAL);
    }

    @Override
    public String toString() {
        return "RadialGradientStyle(super=" + super.toString() + ")";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RadialGradientStyle))
            return false;
        final RadialGradientStyle other = (RadialGradientStyle) o;
        if (!other.canEqual((Object) this))
            return false;
        if (!super.equals(o))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RadialGradientStyle;
    }

    @Override
    public int hashCode() {
        final int result = super.hashCode();
        return result;
    }
}
