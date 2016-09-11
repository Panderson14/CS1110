//Patrick Anderson (psa5dg)
//Russell Green (rmg5qa)

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Bird {
	
	/// imgs: default storage for the pictures of the bird
	private BufferedImage[] imgs;
	
	// TODO: add your own fields here
	private CollisionBox hbox;
	private Particle particle;
	private Random rand;
	
	/**
	 * Creates a bird object with the given image set 
	 * @param basename should be "birdg" or "birdr" (assuming you use the provided images)
	 */
	public Bird(String basename, double x, double y) {
		// You may change this method if you wish, including adding 
		// parameters if you want; however, the existing image code works as is.
		this.imgs = new BufferedImage[6];
		try {
			// 0-2: right-facing (folded, back, and forward wings)
			this.imgs[0] = ImageIO.read(new File(basename+".png"));  
			this.imgs[1] = ImageIO.read(new File(basename+"f.png"));
			this.imgs[2] = ImageIO.read(new File(basename+"b.png"));
			// 3-5: left-facing (folded, back, and forward wings)
			this.imgs[3] = Bird.makeFlipped(this.imgs[0]);
			this.imgs[4] = Bird.makeFlipped(this.imgs[1]);
			this.imgs[5] = Bird.makeFlipped(this.imgs[2]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		this.hbox = new CollisionBox((int)x-25, (int)y-25, 50, 50);
		this.particle = new Particle((int)x, (int)y);
		this.hbox.moveCenterTo(this.particle.getIntX(), this.particle.getIntY());
		this.rand = new Random();
	}
	
	/**
	 * A helper method for flipping in image left-to-right into a mirror image.
	 * There is no reason to change this method.
	 * 
	 * @param original The image to flip
	 * @return A left-right mirrored copy of the original image
	 */
	private static BufferedImage makeFlipped(BufferedImage original) {
		AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
		af.translate(-original.getWidth()+5, 0);
		BufferedImage ans = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
		Graphics2D g = (Graphics2D)ans.getGraphics();
		g.drawImage(original, af, null);
		return ans;
	}	
	public CollisionBox getRect()
	{
		return this.hbox;
	}
	public void setRect(double x, double y)
	{
		this.hbox.moveCenterTo((int)x, (int)y);
	}
	public Particle getPart()
	{
		return this.particle;
	}
	public void teleport()
	{
		this.particle.setX(this.rand.nextDouble()*775 + 25);
		this.particle.setY(50);
		this.particle.setVX(0);
		this.particle.setVY(0);
	}
	
	/**
	 * Draws this bird
	 * @param g the paintbrush to use for the drawing
	 */
	public void draw(Graphics g, int i, double x, double y) {		
		// TODO: find the right x, y, and i instead of the examples given here
		g.drawImage(this.imgs[i], (int)x-12, (int)y-12, null);
		g.setColor(Color.BLACK);
		//g.drawRect((int)x, (int)y, this.getRect().getRectangle().width, this.getRect().getRectangle().height);
	}
}
