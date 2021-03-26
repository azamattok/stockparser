package kz.dilau.htc.filemanager.util;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageUtil {
    private ImageUtil() {}

    public static Dimension calculateDimension(int srcWidth, int srcHeight, int size1, int size2) {
        if (srcWidth <= 0 || srcHeight <= 0 || size1 <= 0 || size2 <= 0) {
            throw new IllegalArgumentException("Incorrect input data");
        }

        double destScale = size1 / (double) size2;
        double srcScale = srcWidth / (double) srcHeight;

        int width;
        int height;
        if ((srcScale >= 1 && destScale >= 1) || (srcScale <= 1 && destScale <= 1)) {
            width = size1;
            height = size2;
        } else {
            width = size2;
            height = size1;
        }
        double scale = width / (double) height;
        if (srcScale > scale) {
            scale = width / (double) srcWidth;
        } else {
            scale = height / (double) srcHeight;
        }
        scale = Math.min(scale, 1);
        return new Dimension((int) Math.round(srcWidth * scale), (int) Math.round(srcHeight * scale));
    }

    public static byte[] resizeImage(byte[] srcData, int width, int height) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(srcData);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            BufferedImage srcImage = ImageIO.read(is);
            Dimension destSize = calculateDimension(srcImage.getWidth(), srcImage.getHeight(),
                    width > 0 ? width : Constants.DEFAULT_IMG_SIZE_1,
                    height > 0 ? height : Constants.DEFAULT_IMG_SIZE_2);
            BufferedImage destImage = resizeImage(srcImage, (int) destSize.getWidth(), (int) destSize.getHeight());
            ImageIO.write(destImage, getFormatName(srcData), os);
            return os.toByteArray();
        }
    }

    private static BufferedImage resizeImage(BufferedImage srcImage, int width, int height) {
        int type = srcImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : srcImage.getType();

        BufferedImage resizedImage = new BufferedImage(width, height, type);

        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(srcImage, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }

    private static String getFormatName(byte[] srcData) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(srcData);
             ImageInputStream iis = ImageIO.createImageInputStream(is)) {
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                return Constants.DEFAULT_RESIZE_FORMAT;
            }
            ImageReader reader = iter.next();

            return reader.getFormatName();
        }
    }
}
