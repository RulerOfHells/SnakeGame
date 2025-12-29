package dev.rohan.gamepro.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

public class TextureAdjust {

    private TextureAdjust() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Shifts blue hues to a target color while preserving perceptual luminance.
     * @param img The source image.
     * @param targetColor The color to shift toward.
     * @param sensitivity Sensitivity power (values less than 1 will experience more shift).
     * @return A new processed BufferedImage.
     */
    public static BufferedImage shiftBlueToTarget(BufferedImage img, Color targetColor, double sensitivity) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage result = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        int[] pixels = ((DataBufferInt) result.getRaster().getDataBuffer()).getData();

        double tr = targetColor.getRed();
        double tg = targetColor.getGreen();
        double tb = targetColor.getBlue();

        // Perceptual weights for Luminance
        double targetLum = (0.299 * tr) + (0.587 * tg) + (0.114 * tb);

        for (int i = 0; i < pixels.length; i++) {
            int argb = pixels[i];
            int alpha = (argb >> 24) & 0xFF;
            int red = (argb >> 16) & 0xFF;
            int green = (argb >> 8) & 0xFF;
            int blue = (argb) & 0xFF;

            // Calculate "Blueness" relative to Red and Green
            int blueness = Math.max(0, blue - Math.max(red, green));

            if (blueness > 0) {
                // Apply sensitivity power curve
                double factor = Math.pow(blueness / 255.0, sensitivity);

                // Calculate current brightness
                double currentLum = (0.299 * red) + (0.587 * green) + (0.114 * blue);

                // Calculate ratio to keep the new color as bright as the old blue
                double lumRatio = (targetLum > 0.000001) ? (currentLum / targetLum) : 0.0;

                int matchedR = (int) (tr * lumRatio);
                int matchedG = (int) (tg * lumRatio);
                int matchedB = (int) (tb * lumRatio);

                // Swap colors: Blend original color OUT and matched color IN
                int finalR = clamp((int) (red * (1 - factor) + (matchedR * factor)));
                int finalG = clamp((int) (green * (1 - factor) + (matchedG * factor)));
                int finalB = clamp((int) (blue * (1 - factor) + (matchedB * factor)));

                pixels[i] = (alpha << 24) | (finalR << 16) | (finalG << 8) | finalB;
            }
        }
        return result;
    }

    public static BufferedImage[] shiftBlueToTarget(BufferedImage[] img, Color targetColor, double sensitivity) {
        List<BufferedImage> result = new ArrayList<>();
        for(var i : img)
            result.add(shiftBlueToTarget(i, targetColor, sensitivity));
        return result.toArray(new BufferedImage[0]);
    }

    private static int clamp(int val) {
        return Math.max(0, Math.min(255, val));
    }
}