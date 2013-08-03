/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board
 *  Should not be used directly (use getWidth()/getHeight() instead).
 *  * */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int  PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	public void init() {
		addMouseListeners();
	}

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setupWall();
		setupPaddle();
		setupBall();
		
		waitForClick();
		
		vx = rgen.nextDouble (1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			vx=-vx;
		
		while (true)
		{
			moveBall();
			
			if (ball.getX()+BALL_RADIUS < WIDTH-BALL_RADIUS)
			{
				vx=-vx;
			}
			if (ball.getY()+BALL_RADIUS < HEIGHT-BALL_RADIUS)
			{
				vy=-vy;
			}		
			//pause(delay);
		}
		
	}
	

	
	public void mouseMoved(MouseEvent e) {
		paddle.setLocation(e.getX(), e.getY());
	}

	private void setupWall() 
	{
		for (int j=0; j<NBRICK_ROWS; j++) //build multiple levels
		{
			for (int i=0; i<NBRICKS_PER_ROW; i++) // build single level
		   {
			int xcord = (BRICK_WIDTH + BRICK_SEP) * i;
			int ycord = BRICK_Y_OFFSET + (BRICK_HEIGHT + BRICK_SEP)* j;
			GRect rect = new GRect(xcord, ycord, BRICK_WIDTH, BRICK_HEIGHT);
			rect.setFilled(true);
			switch (j%10){
						case 0: rect.setFillColor(Color.RED);
						break;
						case 1: rect.setFillColor(Color.RED);
						break;
						case 2: rect.setFillColor(Color.ORANGE);
						break;
						case 3: rect.setFillColor(Color.ORANGE);
						break;
						case 4: rect.setFillColor(Color.YELLOW);
						break;
						case 5: rect.setFillColor(Color.YELLOW);
						break;
						case 6: rect.setFillColor(Color.GREEN);
						break;
						case 7: rect.setFillColor(Color.GREEN);
						break;
						case 8: rect.setFillColor(Color.CYAN);
						break;
						case 9: rect.setFillColor(Color.CYAN);
						break;
						}
		    add(rect);
		   }
		}
	}	
	
	private void setupPaddle()
	{
		paddle = new GRect(0, APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	private void setupBall()
	{
		ball = new GOval(WIDTH/2-BALL_RADIUS/2, HEIGHT/2+BALL_RADIUS/2, BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		add(ball);
	}
	
	private void moveBall() {
		ball.move(vx, vy);
	}
	
	private GRect paddle;
	private GOval ball;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx;
	private double vy =5;
	private int delay=1;
}
