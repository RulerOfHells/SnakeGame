package dev.rohan.gamepro.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static javax.imageio.ImageIO.read;

public class Assets {   //Utility class to provide static resources

    public static final BufferedImage[] buttons = new BufferedImage[14];
    public static final BufferedImage[] panels = new BufferedImage[5];
    public static final BufferedImage[] snakeBody = new BufferedImage[6];
    public static final BufferedImage[] snakeHead = new BufferedImage[4];
    public static final BufferedImage[] snakeTail = new BufferedImage[4];
    public static final BufferedImage[] apple = new BufferedImage[1];

    private static final Logger logger =  Logger.getLogger(Assets.class.getName());
    private static final Class<Assets> clazz = Assets.class;

    private static final String GUI_BUTTON = "/textures/gui/buttons/";
    private static final String GUI_PANEL = "/textures/gui/panels/";
    private static final String GRAPHICS = "/textures/graphics/";

    private Assets() {
        throw new IllegalStateException("Utility class");
    }
    
    public static void init() {
        try {
            buttons[0] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "ExitDefault.png")));
            buttons[1] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "ExitHover.png")));
            buttons[2] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "PlayDefault.png")));
            buttons[3] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "PlayHover.png")));
            buttons[4] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "SettingsDefault.png")));
            buttons[5] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "SettingsHover.png")));
            buttons[6] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "RestartDefault.png")));
            buttons[7] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "RestartHover.png")));
            buttons[8] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "HomeDefault.png")));
            buttons[9] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "HomeHover.png")));
            buttons[10] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "CancelDefault.png")));
            buttons[11] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "CancelHover.png")));
            buttons[12] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "EyeDefault.png")));
            buttons[13] = read(requireNonNull(clazz.getResourceAsStream(GUI_BUTTON + "EyeHover.png")));
            
            panels[0] = read(requireNonNull(clazz.getResourceAsStream(GUI_PANEL + "WindowBig.png")));
            panels[1] = read(requireNonNull(clazz.getResourceAsStream(GUI_PANEL + "WindowMedium.png")));
            panels[2] = read(requireNonNull(clazz.getResourceAsStream(GUI_PANEL + "Header.png")));
            panels[3] = read(requireNonNull(clazz.getResourceAsStream(GUI_PANEL + "HeadedBody.png")));
            panels[4] = read(requireNonNull(clazz.getResourceAsStream(GUI_PANEL + "HeadlessBody.png")));

            apple[0] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "apple.png")));

            snakeBody[0] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_horizontal.png")));
            snakeBody[1] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_vertical.png")));
            snakeBody[2] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_topleft.png")));
            snakeBody[3] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_topright.png")));
            snakeBody[4] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_bottomleft.png")));
            snakeBody[5] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "body_bottomright.png")));

            snakeHead[0] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "head_up.png")));
            snakeHead[1] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "head_down.png")));
            snakeHead[2] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "head_left.png")));
            snakeHead[3] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "head_right.png")));

            snakeTail[0] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "tail_up.png")));
            snakeTail[1] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "tail_down.png")));
            snakeTail[2] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "tail_left.png")));
            snakeTail[3] = read(requireNonNull(clazz.getResourceAsStream(GRAPHICS + "tail_right.png")));

        } catch (IOException e) {
            logger.severe("Resources loading failed");
        }
    }
}