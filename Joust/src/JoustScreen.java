//Patrick Anderson (psa5dg)
//Russell Green (rmg5qa)

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;


public class JoustScreen extends KeyAdapter implements ActionListener {

	/**
	 * A simple method to make the game runnable. You should not modify 
	 * this main method: it should print out a list of extras you added
	 * and then say "new JoustScreen();" -- nothing more than that.
	 */
	public static void main(String[] args) {
		System.out.println("A game over mechanism");
		System.out.println("Prevent players from spamming the flap buttons");
		System.out.println("Moving obstacles");
		System.out.println("Birds visually flap");
		// add a list of all extras you did
		new JoustScreen();
	}
	
	// DO NOT CHANGE the next four fields (the window and timer)
	private JFrame window;         // the window itself
	private BufferedImage content; // the current game graphics
	private Graphics2D paintbrush; // for drawing things in the window
	private Timer gameTimer;       // for keeping track of time passing
	// DO NOT CHANGE the previous four fields (the window and timer)
	
	
	// TODO: add your own fields here
	private Bird bird1;
	private Bird bird2;
	private Scoreboard score;
	private Rectangle ob1;
	private Rectangle ob2;
	private boolean a;
	private boolean s;
	private boolean k;
	private boolean l;
	private int whichPic1;
	private int whichPic2;
	private int count1;
	private int count2;
	private int countob1;
	private int countob2;
	private int countflap1;
	private int countflap2;
	private boolean counter1;
	private boolean counter2;
	
	public JoustScreen() {
		// DO NOT CHANGE the window, content, and paintbrush lines below
		this.window = new JFrame("Joust Clone");
		this.content = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
		this.paintbrush = (Graphics2D)this.content.getGraphics();
		this.window.setContentPane(new JLabel(new ImageIcon(this.content)));
		this.window.pack();
		this.window.setVisible(true);
		this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.window.addKeyListener(this);
		// DO NOT CHANGE the window, content, and paintbrush lines above


		// TODO: add anything else you might need (e.g., a couple of Bird objects, some walls)	
		this.bird1 = new Bird("birdr", 50, 50);
		this.bird2 = new Bird("birdg", 750, 50);
		this.score = new Scoreboard();
		this.ob1 = new Rectangle(400, 200, 20, 200);
		this.ob2 = new Rectangle(50, 400, 200, 20);
		this.a = false;
		this.s = false;
		this.k = false;
		this.l = false;
		this.whichPic1 = 0;
		this.whichPic2 = 3;
		this.count1 = 0;
		this.count2 = 0;
		this.countob1 = 0;
		this.countob2 = 0;
		this.counter1 = false;
		this.counter2 = false;
		this.countflap1 = 0;
		this.countflap2 = 0;

		// DO NOT CHANGE the next two lines nor add lines after them
		this.gameTimer = new Timer(20, this); // tick at 1000/20 fps
		this.gameTimer.start();               // and start ticking now
		// DO NOT CHANGE the previous two lines nor add lines after them
	}
	
	/**
	 * This method gets called each time a player presses a key.
	 * You can find out what key the pressed by comparing event.getKeyCode() with KeyEvent.VK_...
	 */
	public void keyPressed(KeyEvent event) {
		
		// TODO: handle the keys you want to use to run your game
		if (event.getKeyCode() == KeyEvent.VK_A) this.a = true;
		if (event.getKeyCode() == KeyEvent.VK_S) this.s = true;
		if (event.getKeyCode() == KeyEvent.VK_K) this.k = true;
		if (event.getKeyCode() == KeyEvent.VK_L) this.l = true;
	}

	/**
	 * Java will call this every time the gameTimer ticks (50 times a second).
	 * If you want to stop the game, invoke this.gameTimer.stop() in this method.
	 */
	public void actionPerformed(ActionEvent event) {
		// DO NOT CHANGE the next four lines, and add nothing above them
		if (! this.window.isValid()) { // the "close window" button
			this.gameTimer.stop();     // should stop the timer
			return;                    // and stop doing anything else
		}                              
		// DO NOT CHANGE the previous four lines
		
		
		// TODO: add every-frame logic in here (gravity, momentum, collisions, etc)	
		keyboard();
		bird1.getPart().bounceIfOutsideOf(new Rectangle(25, 25, 750, 550), .8);
		bird2.getPart().bounceIfOutsideOf(new Rectangle(25, 25, 750, 550), .8);
		theWall();
		intersection();
		picFlip();
		flapMaker();
		weather();
		obMover();
		flapBreaker();

		// DO NOT CHANGE the next line; it must be last in this method
		this.refreshScreen(); // redraws the screen after things move
		// DO NOT CHANGE the above line; it must be last in this method
	}

	/**
	 * Re-draws the screen. You should erase the old image and draw a 
	 * new one, but you should not change anything in this method
	 * (use actionPerformed instead if you need something to change).
	 */
	private void refreshScreen() {
		this.paintbrush.setColor(new Color(150, 210, 255)); // pale blue
		this.paintbrush.fillRect(0, 0, this.content.getWidth(), this.content.getHeight()); // erases the previous frame

		
		// TODO: replace the following example code with code that does 
		// the right thing (i.e., draw the birds, walls, and score)
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		this.paintbrush.setFont(f);
		this.bird1.draw(this.paintbrush, this.whichPic1, bird1.getRect().getRectangle().getX(), bird1.getRect().getRectangle().getY());
		this.bird2.draw(this.paintbrush, this.whichPic2, bird2.getRect().getRectangle().getX(), bird2.getRect().getRectangle().getY());
		this.score.draw(this.paintbrush);
		this.paintbrush.setColor(Color.BLACK);
		this.paintbrush.fill(this.ob1);
		this.paintbrush.fill(this.ob2);
		
		if ((this.score.getScore1() - this.score.getScore2()) > 2 && this.score.getScore1() >= 10) gameOver("Red wins!");	
		if ((this.score.getScore2() - this.score.getScore1()) > 2 && this.score.getScore2() >= 10) gameOver("Green wins!");
		
		// DO NOT CHANGE the next line; it must be last in this method
		this.window.repaint();  // displays the frame to the screen
		// DO NOT CHANGE the above line; it must be last in this method
	}

	private void gameOver(String msg)
	{
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 90);
		Rectangle2D r = f.getStringBounds(msg, this.paintbrush.getFontRenderContext());
		this.paintbrush.setFont(f);
		this.paintbrush.setColor(Color.BLUE);
		this.paintbrush.drawString(msg, 400-(int)r.getWidth()/2, 300);
		this.gameTimer.stop();
	}
	private void picFlip()
	{		
		if(bird1.getRect().getRectangle().getX() > bird2.getRect().getRectangle().getX())
		{
			if (this.whichPic1 == 0) this.whichPic1 = 3;
			if (this.whichPic1 == 1) this.whichPic1 = 4;
			if (this.whichPic1 == 2) this.whichPic1 = 5;
			if (this.whichPic2 == 3) this.whichPic2 = 0;
			if (this.whichPic2 == 4) this.whichPic2 = 1;
			if (this.whichPic2 == 5) this.whichPic2 = 2;
		}
		if(bird2.getRect().getRectangle().getX() > bird1.getRect().getRectangle().getX())
		{
			if (this.whichPic1 == 3) this.whichPic1 = 0;
			if (this.whichPic1 == 4) this.whichPic1 = 1;
			if (this.whichPic1 == 5) this.whichPic1 = 2;
			if (this.whichPic2 == 0) this.whichPic2 = 3;
			if (this.whichPic2 == 1) this.whichPic2 = 4;
			if (this.whichPic2 == 2) this.whichPic2 = 5;
		}
	}
	private void flapMaker()
	{
		if (this.countflap1 < 5) this.countflap1++;
		else {
			this.countflap1 = 0;
			if (this.whichPic1 == 1 || this.whichPic1 == 2) this.whichPic1 = 0;
			if (this.whichPic1 == 4 || this.whichPic1 == 5) this.whichPic1 = 3;
		}
		
		if (this.countflap2 < 5) this.countflap2++;
		else {
			this.countflap2 = 0;
			if (this.whichPic2 == 1 || this.whichPic2 == 2) this.whichPic2 = 0;
			if (this.whichPic2 == 4 || this.whichPic2 == 5) this.whichPic2 = 3;
		}
	}
	private void weather()
	{
		double dt = .02;
		bird1.getPart().applyForce(0, 3000, dt);
		bird1.getPart().applyDrag(.005, dt);
		bird1.getPart().timePasses(dt);
		bird1.setRect(bird1.getPart().getX(),bird1.getPart().getY());
		
		bird2.getPart().applyForce(0, 3000, dt);
		bird2.getPart().applyDrag(.005, dt);
		bird2.getPart().timePasses(dt);
		bird2.setRect(bird2.getPart().getX(),bird2.getPart().getY());
	}
	private void obMover()
	{
		this.countob2++;
		if (this.countob2 < 101)
		{
			this.ob2.setLocation((int)this.ob2.getX()+5, (int)this.ob2.getY());
		}
		if (this.countob2 >= 101)
		{
			this.ob2.setLocation((int)this.ob2.getX()-5, (int)this.ob2.getY());
			if (this.countob2 == 200) this.countob2 = 0;
		}
		
		this.countob1++;
		if (this.countob1 < 101)
		{
			this.ob1.setLocation((int)this.ob1.getX(), (int)this.ob1.getY()-2);
		}
		if (this.countob1 >= 101)
		{
			this.ob1.setLocation((int)this.ob1.getX(), (int)this.ob1.getY()+2);
			if (this.countob1 == 200) this.countob1 = 0;
		}
	}
	private void flapBreaker()
	{
		if (this.counter1)
		{
			this.count1++;
			if (this.count1 == 10)
			{
				this.counter1 = false;
				this.count1 = 0;
			}
		}
		if (this.counter2)
		{
			this.count2++;
			if (this.count2 == 10)
			{
				this.counter2 = false;
				this.count2 = 0;
			}
		}
	}
	private void intersection()
	{
		if(bird2.getRect().getRectangle().intersects(bird1.getRect().getRectangle()) && (bird1.getRect().getRectangle().getY() < bird2.getRect().getRectangle().getY()))
		{
			bird2.teleport();
			this.score.incrementScore1();	
		}
		if(bird1.getRect().getRectangle().intersects(bird2.getRect().getRectangle()) && (bird2.getRect().getRectangle().getY() < bird1.getRect().getRectangle().getY()))
		{
			bird1.teleport();
			this.score.incrementScore2();	
		}
	}
	private void keyboard()
	{
		double dt = .02;
		if(this.a && this.count1==0 && !this.counter1)
		{
			this.countflap1 = 0;
			bird1.getPart().applyForce(-20000, -40000, dt);
			bird1.getPart().timePasses(dt);
			bird1.setRect(bird1.getPart().getX(),bird1.getPart().getY());
			if(bird1.getRect().getRectangle().getX() > bird2.getRect().getRectangle().getX()) this.whichPic1 = 5;
			else this.whichPic1 = 1;
			this.a = false;
			this.counter1 = true;
		}
		if(this.s && this.count1==0 && !this.counter1)
		{
			this.countflap1 = 0;
			bird1.getPart().applyForce(20000, -40000, dt);
			bird1.getPart().timePasses(dt);
			bird1.setRect(bird1.getPart().getX(),bird1.getPart().getY());
			if(bird1.getRect().getRectangle().getX() > bird2.getRect().getRectangle().getX()) this.whichPic1 = 4;
			else this.whichPic1 = 2;
			this.s = false;
			this.counter1 = true;
		}
		if(this.k && this.count2==0 && !this.counter2)
		{
			this.countflap2 = 0;
			bird2.getPart().applyForce(-20000, -40000, dt);
			bird2.getPart().timePasses(dt);
			bird2.setRect(bird2.getPart().getX(),bird2.getPart().getY());
			if(bird1.getRect().getRectangle().getX() > bird2.getRect().getRectangle().getX()) this.whichPic2 = 2;
			else this.whichPic2 = 4;
			this.k = false;
			this.counter2 = true;
		}
		if(this.l && this.count2==0 && !this.counter2)
		{
			this.countflap2 = 0;
			bird2.getPart().applyForce(20000, -40000, dt);
			bird2.getPart().timePasses(dt);
			bird2.setRect(bird2.getPart().getX(),bird2.getPart().getY());
			if(bird1.getRect().getRectangle().getX() > bird2.getRect().getRectangle().getX()) this.whichPic2 = 1;
			else this.whichPic2 = 5;
			this.l = false;
			this.counter2 = true;
		}
	}
	private void theWall()
	{
		if (bird1.getRect().getRectangle().intersects(this.ob1))
		{
			if (bird1.getRect().getRectangle().createIntersection(this.ob1).getHeight() < bird1.getRect().getRectangle().createIntersection(this.ob1).getWidth())
			{
				bird1.getPart().bounceY(this.ob1, .5);
			}
			if (bird1.getRect().getRectangle().createIntersection(this.ob1).getHeight() > bird1.getRect().getRectangle().createIntersection(this.ob1).getWidth())
			{
				bird1.getPart().bounceX(this.ob1, .5);
			}
		}
		if (bird1.getRect().getRectangle().intersects(this.ob2))
		{
			if (bird1.getRect().getRectangle().createIntersection(this.ob2).getHeight() < bird1.getRect().getRectangle().createIntersection(this.ob2).getWidth())
			{
				bird1.getPart().bounceY(this.ob2, .5);
			}
			if (bird1.getRect().getRectangle().createIntersection(this.ob2).getHeight() > bird1.getRect().getRectangle().createIntersection(this.ob2).getWidth())
			{
				bird1.getPart().bounceX(this.ob2, .5);
			}
		}
		if (bird2.getRect().getRectangle().intersects(this.ob1))
		{
			if (bird2.getRect().getRectangle().createIntersection(this.ob1).getHeight() < bird2.getRect().getRectangle().createIntersection(this.ob1).getWidth())
			{
				bird2.getPart().bounceY(this.ob1, .5);
			}
			if (bird2.getRect().getRectangle().createIntersection(this.ob1).getHeight() > bird2.getRect().getRectangle().createIntersection(this.ob1).getWidth())
			{
				bird2.getPart().bounceX(this.ob1, .5);
			}
		}
		if (bird2.getRect().getRectangle().intersects(this.ob2))
		{
			if (bird2.getRect().getRectangle().createIntersection(this.ob2).getHeight() < bird2.getRect().getRectangle().createIntersection(this.ob2).getWidth())
			{
				bird2.getPart().bounceY(this.ob2, .5);
			}
			if (bird2.getRect().getRectangle().createIntersection(this.ob2).getHeight() > bird2.getRect().getRectangle().createIntersection(this.ob2).getWidth())
			{
				bird2.getPart().bounceX(this.ob2, .5);
			}
		}
	}
}
