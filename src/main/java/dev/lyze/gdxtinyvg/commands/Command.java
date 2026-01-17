package dev.lyze.gdxtinyvg.commands;

import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.drawers.TinyVGShapeDrawer;
import dev.lyze.gdxtinyvg.enums.CommandType;
import dev.lyze.gdxtinyvg.enums.StyleType;
import java.io.IOException;

public abstract class Command {
    private final CommandType type;
    private final TinyVG tinyVG;

    public Command(CommandType type, TinyVG tinyVG) {
        this.type = type;
        this.tinyVG = tinyVG;
    }

    public abstract void read(LittleEndianInputStream stream, StyleType primaryStyleType) throws IOException;

    public abstract void draw(TinyVGShapeDrawer drawer);

    public void onPropertiesChanged() {
    }

    public CommandType getType() {
        return this.type;
    }

    public TinyVG getTinyVG() {
        return this.tinyVG;
    }
}
