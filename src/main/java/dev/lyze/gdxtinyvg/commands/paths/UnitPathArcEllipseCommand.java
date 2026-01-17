package dev.lyze.gdxtinyvg.commands.paths;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LittleEndianInputStream;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.enums.UnitPathCommandType;
import dev.lyze.gdxtinyvg.types.Unit;
import dev.lyze.gdxtinyvg.types.UnitPoint;
import dev.lyze.gdxtinyvg.types.Vector2WithWidth;
import java.io.IOException;

/**
 * Draws an ellipse segment between the current and the target point.
 */
public class UnitPathArcEllipseCommand extends UnitPathCommand {
    /**
     * If true, the large portion of the ellipse segment is drawn.
     */
    private boolean largeArc;
    /**
     * Determines if the ellipse segment is left- or right bending.
     */
    private boolean sweep;
    /**
     * The radius of the ellipse in horizontal direction.
     */
    private float radiusX;
    /**
     * The radius of the ellipse in vertical direction.
     */
    private float radiusY;
    /**
     * The rotation of the ellipse in mathematical negative direction, in degrees.
     */
    private float rotation;
    /**
     * The end point of ellipse circle segment.
     */
    private Vector2 target;

    public UnitPathArcEllipseCommand(Unit lineWidth, TinyVG tinyVG) {
        super(UnitPathCommandType.ARC_ELLIPSE, lineWidth, tinyVG);
    }

    @Override
    public void read(LittleEndianInputStream stream) throws IOException {
        dev.lyze.gdxtinyvg.enums.Range range = getTinyVG().getHeader().getCoordinateRange();
        int fractionBits = getTinyVG().getHeader().getFractionBits();
        int largeArcSweepPaddingByte = stream.readUnsignedByte();
        largeArc = (largeArcSweepPaddingByte & 1) == 1;
        sweep = ((largeArcSweepPaddingByte & 2) >> 1) == 1;
        radiusX = new Unit(stream, range, fractionBits).convert();
        radiusY = new Unit(stream, range, fractionBits).convert();
        rotation = new Unit(stream, range, fractionBits).convert();
        target = new UnitPoint(stream, range, fractionBits).convert();
    }

    @Override
    public Array<Vector2WithWidth> calculatePoints(Vector2 start, float lastLineWidth, Array<Vector2WithWidth> path) {
        float radiusMin = start.dst(target) / 2.0F;
        float radiusLim = (float) Math.sqrt(radiusX * radiusX + radiusY * radiusY);
        float upScale = 1.0F;
        if (radiusLim < radiusMin)
            upScale = radiusMin / radiusLim;
        float ratio = radiusX / radiusY;
        float[][] rot = rotationMat(-rotation * MathUtils.degreesToRadians);
        float[][] transform = new float[2][2];
        transform[0][0] = rot[0][0] / upScale;
        transform[0][1] = rot[0][1] / upScale;
        transform[1][0] = rot[1][0] / upScale * ratio;
        transform[1][1] = rot[1][1] / upScale * ratio;
        float[][] transformBack = new float[2][2];
        transformBack[0][0] = rot[1][1] * upScale;
        transformBack[0][1] = -rot[0][1] / ratio * upScale;
        transformBack[1][0] = -rot[1][0] * upScale;
        transformBack[1][1] = rot[0][0] / ratio * upScale;
        Array<Vector2WithWidth> helper = new Array<Vector2WithWidth>();
        renderCircle(helper, applyMat(transform, start), applyMat(transform, target), radiusX * upScale, largeArc,
                sweep, lastLineWidth, calculateLineWidth(lastLineWidth));
        for (int i = 0; i < helper.size; i++) {
            path.add(new Vector2WithWidth(applyMat(transformBack, helper.get(i).getPoint()), helper.get(i).getWidth()));
        }
        return path;
    }

    private Array<Vector2WithWidth> renderCircle(Array<Vector2WithWidth> helper, Vector2 p0, Vector2 p1, float radius,
            boolean largeArc, boolean turnLeft, float startWidth, float endWidth) {
        float r = radius;
        boolean leftSide = (turnLeft && largeArc) || (!turnLeft && !largeArc);
        Vector2 delta = p1.cpy().sub(p0).scl(0.5F);
        Vector2 midpoint = p0.cpy().add(delta);
        Vector2 radiusVec = leftSide ? new Vector2(-delta.y, delta.x) : new Vector2(delta.y, -delta.x);
        float lenSquared = radiusVec.len2();
        if (lenSquared - 0.03F > r * r || r < 0)
            r = (float) Math.sqrt(lenSquared);
        Vector2 toCenter = radiusVec.cpy().scl((float) Math.sqrt(Math.max(0, r * r / lenSquared - 1)));
        Vector2 center = midpoint.cpy().add(toCenter);
        float angle = MathUtils.asin(MathUtils.clamp((float) Math.sqrt(lenSquared) / r, -1, 1)) * 2;
        float arc = largeArc ? MathUtils.PI2 - angle : angle;
        Vector2 pos = p0.cpy().sub(center);
        for (int i = 0; i < getTinyVG().getCurvePoints(); i++) {
            float[][] stepMat = rotationMat(i * (turnLeft ? -arc : arc) / getTinyVG().getCurvePoints());
            Vector2 point = applyMat(stepMat, pos).add(center);
            helper.add(new Vector2WithWidth(point,
                    MathUtils.lerp(startWidth, endWidth, (float) i / getTinyVG().getCurvePoints())));
        }
        helper.add(new Vector2WithWidth(p1, endWidth));
        return helper;
    }

    private Vector2 applyMat(float[][] mat, Vector2 p) {
        return new Vector2(p.x * mat[0][0] + p.y * mat[0][1], p.x * mat[1][0] + p.y * mat[1][1]);
    }

    private float[][] rotationMat(float angle) {
        float s = MathUtils.sin(angle);
        float c = MathUtils.cos(angle);
        float[][] mat = new float[2][2];
        mat[0][0] = c;
        mat[0][1] = -s;
        mat[1][0] = s;
        mat[1][1] = c;
        return mat;
    }
}
