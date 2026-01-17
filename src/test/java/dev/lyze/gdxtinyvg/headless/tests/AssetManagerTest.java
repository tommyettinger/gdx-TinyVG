package dev.lyze.gdxtinyvg.headless.tests;

import com.badlogic.gdx.assets.AssetManager;
import dev.lyze.gdxtinyvg.TinyVG;
import dev.lyze.gdxtinyvg.TinyVGAssetLoader;
import dev.lyze.gdxtinyvg.headless.LibgdxHeadlessUnitTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssetManagerTest extends LibgdxHeadlessUnitTest {
    @Test
    public void SquareAssetManagerTest() {
        AssetManager assetManager = new AssetManager();
        assetManager.setLoader(TinyVG.class, new TinyVGAssetLoader());
        assetManager.load("twoSquares.tvg", TinyVG.class);
        assetManager.finishLoading();
        TinyVG tvg = assetManager.get("twoSquares.tvg", TinyVG.class);
        Assertions.assertNotNull(tvg);
    }
}
