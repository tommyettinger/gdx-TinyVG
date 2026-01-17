package dev.lyze.gdxtinyvg.styles;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.drawers.GradientShapeDrawer;
import dev.lyze.gdxtinyvg.enums.Range;
import dev.lyze.gdxtinyvg.enums.StyleType;
import dev.lyze.gdxtinyvg.utils.StreamUtils;
import java.io.IOException;

/**
 * @see StyleType
 */
public class FlatColoredStyle extends Style {
    private int colorIndex;

    public FlatColoredStyle(TinyVG tinyVG) {
        super(tinyVG);
    }

    @Override
    public void read(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        colorIndex = StreamUtils.readVarUInt(stream);
    }

    @Override
    public void start(GradientShapeDrawer drawer) {
        drawer.setGradientColors(getTinyVG().getColorTable()[colorIndex], getTinyVG().getColorTable()[colorIndex]);
        drawer.setGradientStyle(StyleType.FLAT);
        drawer.applyShaderValues();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FlatColoredStyle))
            return false;
        final FlatColoredStyle other = (FlatColoredStyle) o;
        if (!other.canEqual((Object) this))
            return false;
        if (this.getColorIndex() != other.getColorIndex())
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof FlatColoredStyle;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getColorIndex();
        return result;
    }

    @Override
    public String toString() {
        return "FlatColoredStyle(colorIndex=" + this.getColorIndex() + ")";
    }

    public int getColorIndex() {
        return this.colorIndex;
    }
}
