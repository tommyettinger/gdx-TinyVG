package dev.lyze.gdxtinyvg;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.enums.ColorEncoding;
import dev.lyze.gdxtinyvg.enums.Range;
import dev.lyze.gdxtinyvg.utils.StreamUtils;
import java.io.IOException;
import java.util.Arrays;

/**
 * Each TVG file starts with a header defining some global values for the file
 * like scale and image size.
 */
public class TinyVGHeader {
    /**
     * Must be 1. For future versions, this field might decide how the rest of the
     * format looks like.
     */
    private int version;
    /**
     * Defines the number of fraction bits in a Unit value.
     */
    private int fractionBits;
    /**
     * Defines the type of color information that is used in the color table.
     */
    private ColorEncoding colorEncoding;
    /**
     * Defines the number of total bits in a Unit value and thus the overall
     * precision of the file.
     */
    private Range coordinateRange;
    /**
     * Encodes the maximum width of the output file in pixels.
     */
    private int width;
    /**
     * Encodes the maximum height of the output file in pixels.
     */
    private int height;
    /**
     * The number of colors in the color table.
     */
    private int colorTableCount;

    public void read(LittleEndianInputStream stream) throws IOException {
        int[] magic = StreamUtils.readNBytes(stream, 2);
        if (magic[0] != 114 && magic[1] != 86)
            throw new IllegalStateException("Bad magic numbers: " + Arrays.toString(magic) + ", expected 0x72, 0x56");
        version = stream.readUnsignedByte();
        if (version != 1)
            throw new IllegalStateException("Bad version: " + version + ", expected 1");
        // u4, u2, u2
        int fractionBitsColorCoordinateRange = stream.readUnsignedByte();
        fractionBits = (fractionBitsColorCoordinateRange & 15);
        colorEncoding = ColorEncoding.valueOf((fractionBitsColorCoordinateRange & 48) >> 4);
        coordinateRange = Range.valueOf((fractionBitsColorCoordinateRange & 192) >> 6);
        width = coordinateRange.read(stream);
        height = coordinateRange.read(stream);
        colorTableCount = StreamUtils.readVarUInt(stream);
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TinyVGHeader))
            return false;
        final TinyVGHeader other = (TinyVGHeader) o;
        if (!other.canEqual((Object) this))
            return false;
        if (this.getVersion() != other.getVersion())
            return false;
        if (this.getFractionBits() != other.getFractionBits())
            return false;
        if (this.getWidth() != other.getWidth())
            return false;
        if (this.getHeight() != other.getHeight())
            return false;
        if (this.getColorTableCount() != other.getColorTableCount())
            return false;
        final Object this$colorEncoding = this.getColorEncoding();
        final Object other$colorEncoding = other.getColorEncoding();
        if (this$colorEncoding == null ? other$colorEncoding != null : !this$colorEncoding.equals(other$colorEncoding))
            return false;
        final Object this$coordinateRange = this.getCoordinateRange();
        final Object other$coordinateRange = other.getCoordinateRange();
        if (this$coordinateRange == null ? other$coordinateRange != null
                : !this$coordinateRange.equals(other$coordinateRange))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TinyVGHeader;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getVersion();
        result = result * PRIME + this.getFractionBits();
        result = result * PRIME + this.getWidth();
        result = result * PRIME + this.getHeight();
        result = result * PRIME + this.getColorTableCount();
        final Object $colorEncoding = this.getColorEncoding();
        result = result * PRIME + ($colorEncoding == null ? 43 : $colorEncoding.hashCode());
        final Object $coordinateRange = this.getCoordinateRange();
        result = result * PRIME + ($coordinateRange == null ? 43 : $coordinateRange.hashCode());
        return result;
    }

    /**
     * Must be 1. For future versions, this field might decide how the rest of the
     * format looks like.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Defines the number of fraction bits in a Unit value.
     */
    public int getFractionBits() {
        return this.fractionBits;
    }

    /**
     * Defines the type of color information that is used in the color table.
     */
    public ColorEncoding getColorEncoding() {
        return this.colorEncoding;
    }

    /**
     * Defines the number of total bits in a Unit value and thus the overall
     * precision of the file.
     */
    public Range getCoordinateRange() {
        return this.coordinateRange;
    }

    /**
     * Encodes the maximum width of the output file in pixels.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Encodes the maximum height of the output file in pixels.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * The number of colors in the color table.
     */
    public int getColorTableCount() {
        return this.colorTableCount;
    }
}
