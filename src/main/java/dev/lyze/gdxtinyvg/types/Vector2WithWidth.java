package dev.lyze.gdxtinyvg.types;

import com.badlogic.gdx.math.Vector2;

public class Vector2WithWidth {
    private Vector2 point;
    private float width;

    public Vector2 getPoint() {
        return this.point;
    }

    public float getWidth() {
        return this.width;
    }

    public void setPoint(final Vector2 point) {
        this.point = point;
    }

    public void setWidth(final float width) {
        this.width = width;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vector2WithWidth))
            return false;
        final Vector2WithWidth other = (Vector2WithWidth) o;
        if (!other.canEqual((Object) this))
            return false;
        if (Float.compare(this.getWidth(), other.getWidth()) != 0)
            return false;
        final Object this$point = this.getPoint();
        final Object other$point = other.getPoint();
        if (this$point == null ? other$point != null : !this$point.equals(other$point))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Vector2WithWidth;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + Float.floatToIntBits(this.getWidth());
        final Object $point = this.getPoint();
        result = result * PRIME + ($point == null ? 43 : $point.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Vector2WithWidth(point=" + this.getPoint() + ", width=" + this.getWidth() + ")";
    }

    public Vector2WithWidth(final Vector2 point, final float width) {
        this.point = point;
        this.width = width;
    }
}
