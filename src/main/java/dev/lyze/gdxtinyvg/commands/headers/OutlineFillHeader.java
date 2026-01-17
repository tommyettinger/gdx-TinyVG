package dev.lyze.gdxtinyvg.commands.headers;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.StyleType;
import dev.lyze.gdxtinyvg.styles.Style;
import dev.lyze.gdxtinyvg.types.TinyVGIO;
import dev.lyze.gdxtinyvg.types.Unit;
import java.io.IOException;

public class OutlineFillHeader<TData> extends CommandHeader<TData> {
    private Style secondaryStyle;
    private float lineWidth;

    public OutlineFillHeader(Class<TData> clazz) {
        super(clazz);
    }

    @Override
    public OutlineFillHeader<TData> read(LittleEndianInputStream stream, StyleType primaryStyleType, TinyVG tinyVG)
            throws IOException {
        int rectangleStyleByte = stream.readUnsignedByte();
        int rectangleCounts = (rectangleStyleByte & 63) + 1;
        StyleType secondaryStyleType = StyleType.valueOf((rectangleStyleByte & 192) >> 6);
        primaryStyle = primaryStyleType.read(stream, tinyVG);
        secondaryStyle = secondaryStyleType.read(stream, tinyVG);
        lineWidth = new Unit(stream, tinyVG.getHeader().getCoordinateRange(), tinyVG.getHeader().getFractionBits())
                .convert();
        data = new Array<>(rectangleCounts);
        for (int i = 0; i < data.items.length; i++)
            data.add(TinyVGIO.read(getClazz(), stream, tinyVG.getHeader().getCoordinateRange(),
                    tinyVG.getHeader().getFractionBits()));
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OutlineFillHeader))
            return false;
        final OutlineFillHeader<?> other = (OutlineFillHeader<?>) o;
        if (!other.canEqual((Object) this))
            return false;
        if (!super.equals(o))
            return false;
        if (Float.compare(this.getLineWidth(), other.getLineWidth()) != 0)
            return false;
        final Object this$secondaryStyle = this.getSecondaryStyle();
        final Object other$secondaryStyle = other.getSecondaryStyle();
        if (this$secondaryStyle == null ? other$secondaryStyle != null
                : !this$secondaryStyle.equals(other$secondaryStyle))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OutlineFillHeader;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        result = result * PRIME + Float.floatToIntBits(this.getLineWidth());
        final Object $secondaryStyle = this.getSecondaryStyle();
        result = result * PRIME + ($secondaryStyle == null ? 43 : $secondaryStyle.hashCode());
        return result;
    }

    public Style getSecondaryStyle() {
        return this.secondaryStyle;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }
}
