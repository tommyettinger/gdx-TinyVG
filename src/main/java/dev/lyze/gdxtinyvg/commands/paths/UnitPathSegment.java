package dev.lyze.gdxtinyvg.commands.paths;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.types.UnitPoint;
import dev.lyze.gdxtinyvg.types.Vector2WithWidth;
import java.io.IOException;

public class UnitPathSegment {
    private final UnitPathCommand[] commands;
    private UnitPoint start;

    public UnitPathSegment(int commandCount) {
        commands = new UnitPathCommand[commandCount];
    }

    public UnitPathSegment read(LittleEndianInputStream stream, TinyVG tinyVG) throws IOException {
        start = new UnitPoint(stream, tinyVG.getHeader().getCoordinateRange(), tinyVG.getHeader().getFractionBits());
        for (int i = 0; i < commands.length; i++)
            commands[i] = UnitPathCommand.readCommand(stream, tinyVG);
        return this;
    }

    public Array<Vector2WithWidth> calculatePoints(float lineWidth) {
        com.badlogic.gdx.math.Vector2 point = start.convert();
        Array<Vector2WithWidth> path = new Array<Vector2WithWidth>();
        for (UnitPathCommand command : commands) {
            command.calculatePoints(point, lineWidth, path);
            // fail save when a segment is only (close -)
            if (path.size == 0)
                continue;
            Vector2WithWidth lastPoint = path.get(path.size - 1);
            point = lastPoint.getPoint();
            lineWidth = lastPoint.getWidth();
        }
        // When last command is unit path command shape drawer closes the path.
        // It bugs out when it needs to close a path, and the start point is the same as
        // the end point.
        // Therefore, let's make sure that this isn't the case.
        if (commands[commands.length - 1] instanceof UnitPathCloseCommand
                && path.get(0).getPoint().equals(path.get(path.size - 1).getPoint()))
            path.removeIndex(path.size - 1);
        return path;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnitPathSegment))
            return false;
        final UnitPathSegment other = (UnitPathSegment) o;
        if (!other.canEqual((Object) this))
            return false;
        if (!java.util.Arrays.deepEquals(this.getCommands(), other.getCommands()))
            return false;
        final Object this$start = this.getStart();
        final Object other$start = other.getStart();
        if (this$start == null ? other$start != null : !this$start.equals(other$start))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UnitPathSegment;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + java.util.Arrays.deepHashCode(this.getCommands());
        final Object $start = this.getStart();
        result = result * PRIME + ($start == null ? 43 : $start.hashCode());
        return result;
    }

    public UnitPathCommand[] getCommands() {
        return this.commands;
    }

    public UnitPoint getStart() {
        return this.start;
    }
}
