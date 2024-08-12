// package dev.rohan.gamepro;

// import java.awt.Graphics;
// import java.awt.Point;
// import java.awt.Rectangle;
// import java.util.Deque;
// import java.util.LinkedList;
// import java.awt.Color;

// public class Snake2 {
//     private int body;
//     private int size;
//     private int count;
//     private LinkedList<Point> snake;
//     private Handler handler;
//     private boolean gameOver;

//     public Snake2(Handler handler) {
//         this.handler = handler;
//         reinit();
//     }

//     public void reinit() {
//         count = 0;
//         body = 5;         //Starts with snake having this much body parts
//         size = 30;
//         gameOver = false;

//         snake = new LinkedList<>();
//         snake.addLast(new Point(15, 15));

//         handler.getKeyManager().setDirection('R');  //initial snake direction
//     }

//     private boolean isOutOfBoundsY(int newY) {      //checks if snake head is out of bounds
//         return (newY < 0 || newY > 580 - size);
//     }

//     private boolean isOutOfBoundsX(int newX) {
//         return (newX < 0 || newX > 707 - size);
//     }

//     private boolean checkBoundCrashes(int newX, int newY) {
//         return isOutOfBoundsX(newX) || isOutOfBoundsY(newY);
//     }

//     private boolean checkSelfCrash() {
//         return false;
//     }

//     private void checkGameOver(int newX, int newY) {
//         gameOver = checkBoundCrashes(newX, newY) || checkSelfCrash();
//     }

//     private void move() {                    //Handles snake movements
//         Point head = snake.peekLast();
//         int newX = (int) head.getX();
//         int newY = (int) head.getY();

//         switch(handler.getKeyManager().getDirection()) {    //updates snake head position and registers game over
//             case 'U':
//             newY--;
//             break;

//             case 'D':
//             newY++;
//             break;

//             case 'L':
//             newX--;
//             break;

//             case 'R':
//             newX++;
//             break;

//             default: //do nothing
//         }

//         checkGameOver(newX, newY);
//         collide(newX, newY);

//         for(int i = snake.size()-1; i > 0; i--) {
//             Point current = snake.get(i);
//             Point previous = snake.get(i-1);
//             if (Math.abs(current.getX() - newX) <= size && Math.abs(current.getY() - newY) <= size) {
//                 continue; // Skip update if colliding with head (or other segments)
//             }
//             snake.set(i, previous);
//         }

//         snake.addLast(new Point(newX, newY));

//         if(snake.size() > 3)
//             snake.removeFirst();
//     }

//     private void collide(int newX, int newY) {         //Eats the food on collision
//         int[] foodX = handler.getFood().getFoodX();
//         int[] foodY = handler.getFood().getFoodY();

//         for(int i = 0; i < foodX.length; i++) {     //Loops through each food and check if snake collides
//             var rect = new Rectangle(newX, newY, size, size);
//             if(rect.contains(foodX[i], foodY[i]))
//                 handler.getFood().unPlaceFood(foodX[i], foodY[i]);
//         }
//     }

//     public int getCount() {
//         return count;
//     }

//     public void tick() {
//         move();
//     }

//     public boolean isGameOver() {
//         return gameOver;
//     }

//     public void render(Graphics g) {        //Displays snake on screen
//         for(var body : snake) {
//             int x = (int) body.getX() * size;
//             int y = (int) body.getY() * size;
//             g.setColor(Color.BLUE);
//             g.fillRect(x, y, size, size);
//         }
//     }
// }
