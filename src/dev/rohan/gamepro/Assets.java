package dev.rohan.gamepro;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Assets {   //Utility class to provide static resources

    public static final BufferedImage[] menu = new BufferedImage[2];
    public static final BufferedImage[] pause = new BufferedImage[3];
    public static final BufferedImage[] snakeBody = new BufferedImage[6];
    public static final BufferedImage[] snakeHead = new BufferedImage[4];
    public static final BufferedImage[] snakeTail = new BufferedImage[4];
    public static final BufferedImage[] apple = new BufferedImage[1];

    private Assets() {
        throw new IllegalStateException("Utility class");
    }
    
    static void init() {
        try {
            menu[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/start1_cpy.png"));
            menu[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/start2_cpy.png"));
            
            pause[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy.png"));
            pause[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy1.png"));
            pause[2] = ImageIO.read(Assets.class.getResourceAsStream("/textures/pause_cpy2.png"));

            apple[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/apple.png"));

            snakeBody[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_horizontal.png"));
            snakeBody[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_vertical.png"));
            snakeBody[2] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_topleft.png"));
            snakeBody[3] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_topright.png"));
            snakeBody[4] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_bottomleft.png"));
            snakeBody[5] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/body_bottomright.png"));

            snakeHead[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/head_up.png"));
            snakeHead[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/head_down.png"));
            snakeHead[2] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/head_left.png"));
            snakeHead[3] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/head_right.png"));

            snakeTail[0] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/tail_up.png"));
            snakeTail[1] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/tail_down.png"));
            snakeTail[2] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/tail_left.png"));
            snakeTail[3] = ImageIO.read(Assets.class.getResourceAsStream("/textures/Graphics/tail_right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}