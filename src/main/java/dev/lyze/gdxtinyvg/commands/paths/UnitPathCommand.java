package dev.lyze.gdxtinyvg.commands.paths;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.UnitPathCommandType;
import dev.lyze.gdxtinyvg.types.Unit;
import dev.lyze.gdxtinyvg.types.Vector2WithWidth;
import java.io.IOException;

public abstract class UnitPathCommand {
    private final UnitPathCommandType type;
    private final Unit lineWidth;
    private final TinyVG tinyVG;

    public UnitPathCommand(UnitPathCommandType type, Unit lineWidth, TinyVG tinyVG) {
        this.type = type;
        this.lineWidth = lineWidth;
        this.tinyVG = tinyVG;
    }

    public abstract void read(LittleEndianInputStream stream) throws IOException;

    public abstract Array<Vector2WithWidth> calculatePoints(Vector2 start, float lastLineWidth,
            Array<Vector2WithWidth> path);

    protected float calculateLineWidth(float lastLineWidth) {
        return lineWidth == null ? lastLineWidth : lineWidth.convert();
    }

    public static UnitPathCommand readCommand(LittleEndianInputStream stream, TinyVG tinyVG) throws IOException {
        int instructionLineWidthByte = stream.readUnsignedByte();
        int instruction = instructionLineWidthByte & 7;
        boolean hasLineWidth = ((instructionLineWidthByte & 16) >> 4) == 1;
        Unit lineWidth = null;
        if (hasLineWidth)
            lineWidth = new Unit(stream, tinyVG.getHeader().getCoordinateRange(), tinyVG.getHeader().getFractionBits());
        return UnitPathCommandType.valueOf(instruction).read(stream, lineWidth, tinyVG);
    }

    public UnitPathCommandType getType() {
        return this.type;
    }

    public Unit getLineWidth() {
        return this.lineWidth;
    }

    public TinyVG getTinyVG() {
        return this.tinyVG;
    }
}
