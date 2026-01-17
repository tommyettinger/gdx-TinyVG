package dev.lyze.gdxtinyvg.commands.headers;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.StyleType;
import dev.lyze.gdxtinyvg.types.TinyVGIO;
import dev.lyze.gdxtinyvg.types.Unit;
import java.io.IOException;

public class OutlineHeader<TData> extends CommandHeader<TData> {
    private float lineWidth;

    public OutlineHeader(Class<TData> clazz) {
        super(clazz);
    }

    @Override
    public OutlineHeader<TData> read(LittleEndianInputStream stream, StyleType primaryStyleType, TinyVG tinyVG)
            throws IOException {
        super.read(stream, primaryStyleType, tinyVG);
        lineWidth = new Unit(stream, tinyVG.getHeader().getCoordinateRange(), tinyVG.getHeader().getFractionBits())
                .convert();
        for (int i = 0; i < data.items.length; i++)
            data.add(TinyVGIO.read(getClazz(), stream, tinyVG.getHeader().getCoordinateRange(),
                    tinyVG.getHeader().getFractionBits()));
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OutlineHeader))
            return false;
        final OutlineHeader<?> other = (OutlineHeader<?>) o;
        if (!other.canEqual((Object) this))
            return false;
        if (!super.equals(o))
            return false;
        if (Float.compare(this.getLineWidth(), other.getLineWidth()) != 0)
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OutlineHeader;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        result = result * PRIME + Float.floatToIntBits(this.getLineWidth());
        return result;
    }

    public float getLineWidth() {
        return this.lineWidth;
    }
}
