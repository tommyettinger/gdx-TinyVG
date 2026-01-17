package dev.lyze.gdxtinyvg.styles;

import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.StyleType;

/**
 * @see StyleType
 */
public class LinearGradientStyle extends GradientStyle {
    public LinearGradientStyle(TinyVG tinyVG) {
        super(tinyVG, StyleType.LINEAR);
    }

    @Override
    public String toString() {
        return "LinearGradientStyle()";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LinearGradientStyle))
            return false;
        final LinearGradientStyle other = (LinearGradientStyle) o;
        if (!other.canEqual((Object) this))
            return false;
        if (!super.equals(o))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LinearGradientStyle;
    }

    @Override
    public int hashCode() {
        final int result = super.hashCode();
        return result;
    }
}
