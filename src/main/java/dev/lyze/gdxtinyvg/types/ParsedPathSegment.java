package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.utils.Array;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.commands.paths.UnitPathCommand;
import dev.lyze.gdxtinyvg.commands.paths.UnitPathSegment;
import dev.lyze.gdxtinyvg.drawers.chaches.TinyVGDrawerCache;

public class ParsedPathSegment {
    private Array<Vector2WithWidth> points;
    private UnitPathSegment source;
    private TinyVGDrawerCache cache;

    public ParsedPathSegment(UnitPathSegment segment, Array<Vector2WithWidth> points, TinyVG tinyVG,
            boolean calculateTriangles) {
        this.source = segment;
        this.points = points;
        this.cache = new TinyVGDrawerCache(tinyVG).calculateVertices(this);
        if (calculateTriangles)
            cache.calculateTriangles();
    }

    public UnitPathCommand getLastCommand() {
        return source.getCommands()[source.getCommands().length - 1];
    }

    public Array<Vector2WithWidth> getPoints() {
        return this.points;
    }

    public UnitPathSegment getSource() {
        return this.source;
    }

    public TinyVGDrawerCache getCache() {
        return this.cache;
    }

    public void setPoints(final Array<Vector2WithWidth> points) {
        this.points = points;
    }

    public void setSource(final UnitPathSegment source) {
        this.source = source;
    }

    public void setCache(final TinyVGDrawerCache cache) {
        this.cache = cache;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ParsedPathSegment))
            return false;
        final ParsedPathSegment other = (ParsedPathSegment) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$points = this.getPoints();
        final Object other$points = other.getPoints();
        if (this$points == null ? other$points != null : !this$points.equals(other$points))
            return false;
        final Object this$source = this.getSource();
        final Object other$source = other.getSource();
        if (this$source == null ? other$source != null : !this$source.equals(other$source))
            return false;
        final Object this$cache = this.getCache();
        final Object other$cache = other.getCache();
        if (this$cache == null ? other$cache != null : !this$cache.equals(other$cache))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ParsedPathSegment;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $points = this.getPoints();
        result = result * PRIME + ($points == null ? 43 : $points.hashCode());
        final Object $source = this.getSource();
        result = result * PRIME + ($source == null ? 43 : $source.hashCode());
        final Object $cache = this.getCache();
        result = result * PRIME + ($cache == null ? 43 : $cache.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ParsedPathSegment(points=" + this.getPoints() + ", source=" + this.getSource() + ", cache="
                + this.getCache() + ")";
    }
}
