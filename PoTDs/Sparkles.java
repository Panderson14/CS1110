	// Luther (lat7h)


	import java.awt.Color;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
	import java.awt.RenderingHints;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseMotionAdapter;
	import java.awt.image.BufferedImage;
	import java.util.ArrayList;

	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.Timer;


	public class Sparkles extends MouseMotionAdapter implements ActionListener {

		/**
		 * A simple method to make the game runnable.
		 */
		public static void main(String[] args) {
			new Sparkles();
		}

		
		private JFrame window;         // the window itself
		private BufferedImage content; // the current game graphics
		private Graphics2D paintbrush; // for drawing things in the window
		private Timer gameTimer;       // for keeping track of time passing
		
		private ArrayList<Particle> sparks; // the particles
		private Rectangle bounds;           // the bounds of the screen
		private int mouseX, mouseY;         // where we last saw the mouse
		
		/**
		 * Sets up an 800x600 window which generates sparks 
		 */
		public Sparkles() {
			this.window = new JFrame("Sparkles!");
			this.content = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
			this.paintbrush = (Graphics2D)this.content.getGraphics();
			this.paintbrush.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			this.window.setContentPane(new JLabel(new ImageIcon(this.content)));
			this.window.pack();
			this.window.setVisible(true);
			this.window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.window.getContentPane().addMouseMotionListener(this);

			this.bounds = new Rectangle(0,0, 800, 600);
			this.sparks = new ArrayList<Particle>();

			this.gameTimer = new Timer(20, this); // tick at 1000/20 fps
			this.gameTimer.start();               // and start ticking now
		}
		
		

		/**
		 * This method gets called each time the mouse moves.
		 * All we do here is remember that motion happened.
		 */
		@Override
		public void mouseMoved(MouseEvent event) {
			this.mouseX = event.getX();
			this.mouseY = event.getY();
		}

		/**
		 * Java will call this every time the gameTimer ticks (50 times a second).
		 * If you want to stop the game, invoke this.gameTimer.stop() in this method.
		 */
		public void actionPerformed(ActionEvent event) {
			if (! this.window.isValid()) { // the "close window" button
				this.gameTimer.stop();     // should stop the timer
				return;                    // and stop doing anything else
			}                              
			
			// a new spark every tick
			double maxSpeed = 500;
			sparks.add(new Particle(this.mouseX, this.mouseY, maxSpeed*(2*Math.random()-1), maxSpeed*(2*Math.random()-1)));
			
			// and physics on all sparks: gravity, drag, momentum, and collision
			for(int i = 0; i < sparks.size(); i += 1) {
				Particle p = sparks.get(i);
				p.applyForce(0, 980.0, 0.02);
				p.applyDrag(0.005, 0.02);
				p.timePasses(0.02);
				p.bounceIfOutsideOf(this.bounds, Math.random()); // random bounciness for more interesting ground
			}
			
			this.refreshScreen(); // redraws the screen after things move
		}

		/**
		 * Re-draws the screen. You should erase the old image and draw a 
		 * new one, but you should not change anything in this method
		 * (use actionPerformed instead if you need something to change).
		 */
		private void refreshScreen() {
			this.paintbrush.setColor(new Color(150, 210, 255)); // pale blue
			this.paintbrush.fillRect(0, 0, this.content.getWidth(), this.content.getHeight()); // erases the previous frame

			// a set of colors to cycle through 
			Color[] colors = { Color.RED, Color.WHITE, Color.YELLOW, Color.ORANGE };
			
			// don't keep too many particles, but remove in colors.length chunks
			if (sparks.size() > 500) {
				for(int i=0; i<colors.length; i+=1) {
					sparks.remove(0);
				}
			}
			
			// draw them all
			for(int i = 0; i < sparks.size(); i += 1) {
				this.paintbrush.setColor(colors[i%colors.length]);
				Particle p = sparks.get(i);
				this.paintbrush.fillOval(p.getIntX()-3, p.getIntY()-3, 6, 6);
			}
			
			this.window.repaint();  // displays the frame to the screen
		}


	}
