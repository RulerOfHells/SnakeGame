package dev.rohan.gamepro;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {   //Utility class to provide static resources
    private Assets() {
        throw new IllegalStateException("Utility class");
    }
    static final BufferedImage[] menu = new BufferedImage[2];
    static final BufferedImage[] pause = new BufferedImage[3];
    static void init() {
        try {
            menu[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/start1_cpy.png"));
            menu[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/start2_cpy.png"));
            
            pause[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy.png"));
            pause[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy1.png"));
            pause[2] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}