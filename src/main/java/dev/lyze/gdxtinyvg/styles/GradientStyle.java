package dev.lyze.gdxtinyvg.styles;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.drawers.GradientShapeDrawer;
import dev.lyze.gdxtinyvg.enums.Range;
import dev.lyze.gdxtinyvg.enums.StyleType;
import dev.lyze.gdxtinyvg.types.UnitPoint;
import dev.lyze.gdxtinyvg.utils.StreamUtils;
import java.io.IOException;

public abstract class GradientStyle extends Style {
    private final StyleType type;
    private UnitPoint point1;
    private UnitPoint point2;
    private int colorIndex1;
    private int colorIndex2;

    public GradientStyle(TinyVG tinyVG, StyleType type) {
        super(tinyVG);
        this.type = type;
    }

    @Override
    public void read(LittleEndianInputStream stream, Range range, int fractionBits) throws IOException {
        point1 = new UnitPoint(stream, range, fractionBits);
        point2 = new UnitPoint(stream, range, fractionBits);
        colorIndex1 = StreamUtils.readVarUInt(stream);
        colorIndex2 = StreamUtils.readVarUInt(stream);
    }

    @Override
    public void start(GradientShapeDrawer drawer) {
        drawer.setGradientStyle(type);
        drawer.setGradientColors(getTinyVG().getColorTable()[colorIndex1], getTinyVG().getColorTable()[colorIndex2]);
        drawer.setPositions(point1.getX().convert(), point1.getY().convert(), point2.getX().convert(),
                point2.getY().convert());
        drawer.applyShaderValues();
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof GradientStyle))
            return false;
        final GradientStyle other = (GradientStyle) o;
        if (!other.canEqual((Object) this))
            return false;
        if (this.getColorIndex1() != other.getColorIndex1())
            return false;
        if (this.getColorIndex2() != other.getColorIndex2())
            return false;
        final Object this$type = this.type;
        final Object other$type = other.type;
        if (this$type == null ? other$type != null : !this$type.equals(other$type))
            return false;
        final Object this$point1 = this.getPoint1();
        final Object other$point1 = other.getPoint1();
        if (this$point1 == null ? other$point1 != null : !this$point1.equals(other$point1))
            return false;
        final Object this$point2 = this.getPoint2();
        final Object other$point2 = other.getPoint2();
        if (this$point2 == null ? other$point2 != null : !this$point2.equals(other$point2))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof GradientStyle;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getColorIndex1();
        result = result * PRIME + this.getColorIndex2();
        final Object $type = this.type;
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $point1 = this.getPoint1();
        result = result * PRIME + ($point1 == null ? 43 : $point1.hashCode());
        final Object $point2 = this.getPoint2();
        result = result * PRIME + ($point2 == null ? 43 : $point2.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "GradientStyle(type=" + this.type + ", point1=" + this.getPoint1() + ", point2=" + this.getPoint2()
                + ", colorIndex1=" + this.getColorIndex1() + ", colorIndex2=" + this.getColorIndex2() + ")";
    }

    public UnitPoint getPoint1() {
        return this.point1;
    }

    public UnitPoint getPoint2() {
        return this.point2;
    }

    public int getColorIndex1() {
        return this.colorIndex1;
    }

    public int getColorIndex2() {
        return this.colorIndex2;
    }
}
