package dev.rohan.gamepro;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import static java.util.Objects.requireNonNull;
import static javax.imageio.ImageIO.read;

public class Assets {   //Utility class to provide static resources

    public static final BufferedImage[] menu = new BufferedImage[2];
    public static final BufferedImage[] pause = new BufferedImage[3];
    public static final BufferedImage[] snakeBody = new BufferedImage[6];
    public static final BufferedImage[] snakeHead = new BufferedImage[4];
    public static final BufferedImage[] snakeTail = new BufferedImage[4];
    public static final BufferedImage[] apple = new BufferedImage[1];

    private static final Logger logger =  Logger.getLogger(Assets.class.getName());
    private static final Class<Assets> clazz = Assets.class;

    private Assets() {
        throw new IllegalStateException("Utility class");
    }
    
    static void init() {
        try {
            menu[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/start1_cpy.png")));
            menu[1] = read(requireNonNull(clazz.getResourceAsStream("/textures/start2_cpy.png")));
            
            pause[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/pause_cpy.png")));
            pause[1] = read(requireNonNull(clazz.getResourceAsStream("/textures/pause_cpy1.png")));
            pause[2] = read(requireNonNull(clazz.getResourceAsStream("/textures/pause_cpy2.png")));

            apple[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/apple.png")));

            snakeBody[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_horizontal.png")));
            snakeBody[1] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_vertical.png")));
            snakeBody[2] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_topleft.png")));
            snakeBody[3] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_topright.png")));
            snakeBody[4] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_bottomleft.png")));
            snakeBody[5] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/body_bottomright.png")));

            snakeHead[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/head_up.png")));
            snakeHead[1] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/head_down.png")));
            snakeHead[2] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/head_left.png")));
            snakeHead[3] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/head_right.png")));

            snakeTail[0] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/tail_up.png")));
            snakeTail[1] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/tail_down.png")));
            snakeTail[2] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/tail_left.png")));
            snakeTail[3] = read(requireNonNull(clazz.getResourceAsStream("/textures/Graphics/tail_right.png")));

        } catch (IOException e) {
            logger.severe("Resources loading failed");
        }
    }
}