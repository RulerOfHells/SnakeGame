package dev.rohan.gamepro.uicomponent;

import java.awt.*;

public class ColorSlider {
    private final int x;
    private final int y;
    private final int width;
    private final String label;
    private volatile float value; // 0.0 to 1.0
    private final int min;
    private final int max;

    private final Rectangle track;
    private boolean dragging = false;
    private final Color themeColor;

    public ColorSlider(int x, int y, int width, String label, int min, int max, Color themeColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        int height = 10; // Thickness of the track
        this.label = label;
        this.min = min;
        this.max = max;
        this.themeColor = themeColor;
        this.value = 0.5f; // Default to middle

        this.track = new Rectangle(x, y + 20, width, height);
    }

    public void tick(int mx, int my, boolean isMouseDown) {
        Rectangle thumbHitbox = getThumbBounds();

        // Start dragging if mouse is pressed over the thumb
        if (isMouseDown && thumbHitbox.contains(mx, my)) {
            dragging = true;
        }

        // Stop dragging if mouse is released
        if (!isMouseDown) {
            dragging = false;
        }

        if (dragging) {
            // Update value based on mouse X, clamped between 0 and 1
            float newValue = (float) (mx - x) / width;
            value = Math.max(0, Math.min(1, newValue));
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(label + ": %.1f".formatted(getFloatValue()), x, y + 10);

        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(track.x, track.y, track.width, track.height, 10, 10);

        g2d.setColor(themeColor);
        g2d.fillRoundRect(track.x, track.y, (int)(width * value), track.height, 10, 10);

        Rectangle thumb = getThumbBounds();
        g2d.setColor(Color.WHITE);
        g2d.fillOval(thumb.x, thumb.y, thumb.width, thumb.height);

        if (dragging) {
            g2d.setColor(themeColor);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(thumb.x, thumb.y, thumb.width, thumb.height);
        }
    }

    private Rectangle getThumbBounds() {
        int thumbSize = 20;
        int thumbX = x + (int) (width * value) - (thumbSize / 2);
        int thumbY = track.y + (track.height / 2) - (thumbSize / 2);
        return new Rectangle(thumbX, thumbY, thumbSize, thumbSize);
    }

    public float getFloatValue() {
        return min + (value * (max - min));
    }

    public int getIntValue() {
        return (int) getFloatValue();
    }
}