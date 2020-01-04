package Brick_game;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

//import javax.swing.*;
import javax.swing.Timer;

public class Working extends JPanel implements KeyListener, ActionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean play = false;
	private int score = 0;
	
	public int count = 0;
	
	private int totalBricks = 48;
	
	private Timer timer;
	private int delay=8;
	
	private int playerX = 310;
	
	private int ballposX = 300;
	private int ballposY = 500;
	private int ballXdir = -1;
	private int ballYdir = -1;
	
	private Brick_maker map;
	
	public Working()
	{		
		map = new Brick_maker(4, 12);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	
	public void paint(Graphics g)
	{    		
		super.paintComponent(g);
		ImageIcon i=new ImageIcon("My_logo1.png");
		i.paintIcon(this, g, 50, 50);
		
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		// drawing map
		map.draw((Graphics2D) g);
		
		//showing welcome screen
		if(!play) {
			 
			g.setColor(Color.YELLOW);
		     g.setFont(new Font("serif",Font.BOLD, 30));
		     g.drawString("Welcome to the Break_brick", 180,240);
		     
		     g.setColor(Color.WHITE);
		     g.setFont(new Font("serif", Font.PLAIN, 20));
		     g.drawString("Press <- or -> arrow key to start ", 220,300);
		     
		     g.setColor(Color.WHITE);
		     g.setFont(new Font("serif", Font.PLAIN, 20));
		     g.drawString("Press \"space key\" to increase & \"C\" to decrease the speed of ball", 85,350);
		}
		
		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("Score :"+score, 560,30);
		
		// the paddle
		g.setColor(Color.GREEN);
		g.fillRect(playerX, 550, 100, 8);
		
		// the ball
		g.setColor(Color.RED);
		g.fillOval(ballposX, ballposY, 20, 20);
	
		// when you won the game
		if(totalBricks <= 0)
		{
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.YELLOW);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("You Won", 260,300);
             
             g.setColor(Color.WHITE);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);  
		}
		
		// when you lose the game
		if(ballposY > 570)
        {
			 play = false;
             ballXdir = 0;
     		 ballYdir = 0;
             g.setColor(Color.RED);
             g.setFont(new Font("serif",Font.BOLD, 30));
             g.drawString("Game Over, Scores: "+score, 190,300);
             
             g.setColor(Color.WHITE);
             g.setFont(new Font("serif",Font.BOLD, 20));           
             g.drawString("Press (Enter) to Restart", 230,350);        
        }
		
		g.dispose();
	}	

	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{        
			if(playerX >= 600)
			{
				playerX = 600;
			}
			else
			{
				moveRight();
			}
        }
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{          
			if(playerX < 10)
			{
				playerX = 10;
			}
			else
			{
				moveLeft();
			}
        }		
		
		//to increase speed
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			ballXdir += ballXdir;
			ballYdir += ballYdir;
			count++;
		}
		
		//to decrease speed
		if(count !=0) {
		      if(e.getKeyCode() == KeyEvent.VK_C) {
					ballXdir = ballXdir/2;
					ballYdir = ballYdir/2;
		            count--;
		      }
		}
		
		//when reset
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{          
			if(!play)
			{
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -1;
				playerX = 310;
				score = 0;
				totalBricks = 48;
				map = new Brick_maker(4, 12);
				
				repaint();
			}
        }	
		
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	public void moveRight()
	{
		play = true;
		playerX+=20;	
	}
	
	public void moveLeft()
	{
		play = true;
		playerX-=20;	 	
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		timer.start();
		if(play)
		{			
			//here the player is splitted into three parts i.e. in three rectangle
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = -2;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8)))
			{
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			}
			else if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8)))
			{
				ballYdir = -ballYdir;
				
			}
			
			// check map collision with the ball		
			A: for(int i = 0; i<map.map.length; i++)
			{
				for(int j =0; j<map.map[0].length; j++)
				{				
					if(map.map[i][j] > 0)
					{
						//scores++;
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);					
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect))
						{					
							map.setBrickValue(0, i, j);
							score+=5;	
							totalBricks--;
							
							// when ball hit right or left of brick
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width)	
							{
								ballXdir = -ballXdir;
							}
							// when ball hits top or bottom of brick
							else
							{
								ballYdir = -ballYdir;				
							}
							
							break A;
						}
					}
				}
			}
			//from starting position below line decreasing the value of ballposX & Y that's why it's going up if we have taken positive value then it would have moved in apposite direction that is towards the plate
			ballposX += ballXdir;
			ballposY += ballYdir;
			
			if(ballposX < 0)
			{
				ballXdir = -ballXdir;
			}
			if(ballposY < 0)
			{
				ballYdir = -ballYdir;
			}
			if(ballposX > 670)
			{
				ballXdir = -ballXdir;
			}		
			
			repaint();		
		}
	}
}
