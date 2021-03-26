package kz.dilau.htc.filemanager;

import kz.dilau.htc.filemanager.util.ImageUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class ImageUtilTest {
    @Test
    public void calculateDimensionTest() {
        int size1 = 600;
        int size2 = 800;

        calculateDimension600x800Test(size1, size2);
        calculateDimension600x800Test(size2, size1);
    }

    private void calculateDimension600x800Test(int size1, int size2) {
        Dimension dimension;

        dimension = ImageUtil.calculateDimension(300, 400, size1, size2);
        Assert.assertEquals(300, dimension.width);
        Assert.assertEquals(400, dimension.height);

        dimension = ImageUtil.calculateDimension(400, 300, size1, size2);
        Assert.assertEquals(400, dimension.width);
        Assert.assertEquals(300, dimension.height);

        dimension = ImageUtil.calculateDimension(1200, 1600, size1, size2);
        Assert.assertEquals(600, dimension.width);
        Assert.assertEquals(800, dimension.height);

        dimension = ImageUtil.calculateDimension(1600, 1200, size1, size2);
        Assert.assertEquals(800, dimension.width);
        Assert.assertEquals(600, dimension.height);

        dimension = ImageUtil.calculateDimension(1200, 1200, size1, size2);
        Assert.assertEquals(600, dimension.width);
        Assert.assertEquals(600, dimension.height);

        dimension = ImageUtil.calculateDimension(1200, 2400, size1, size2);
        Assert.assertEquals(400, dimension.width);
        Assert.assertEquals(800, dimension.height);

        dimension = ImageUtil.calculateDimension(1200, 800, size1, size2);
        Assert.assertEquals(800, dimension.width);
        Assert.assertEquals(533, dimension.height);

        dimension = ImageUtil.calculateDimension(2400, 1200, size1, size2);
        Assert.assertEquals(800, dimension.width);
        Assert.assertEquals(400, dimension.height);

        dimension = ImageUtil.calculateDimension(800, 1200, size1, size2);
        Assert.assertEquals(533, dimension.width);
        Assert.assertEquals(800, dimension.height);
    }
}
