//Patrick Anderson (psa5dg)
//Russell Green (rmg5qa)

import java.awt.Rectangle;

public class CollisionBox {

	private Rectangle rect;
	
	public CollisionBox(Rectangle rect)
	{
		this.rect = rect;
	}
	public CollisionBox(int x, int y, int width, int height)
	{
		this.rect = new Rectangle(x, y, width, height);
	}
	public Rectangle getRectangle()
	{
		return this.rect;
	}
	public boolean collidesWith(CollisionBox other)
	{
		return (this.rect.intersects(other.getRectangle()));
	}
	public void moveTo(int x, int y)
	{
		this.rect.setLocation(x, y);
	}
	public void moveCenterTo(int x, int y)
	{
		this.rect.setLocation(x-(int)(this.rect.getWidth())/2, y-(int)(this.rect.getHeight())/2);
	}
	public boolean isHigherThan(CollisionBox other)
	{
		if (this.rect.getCenterY() < other.getRectangle().getCenterY()) return true;
		return false;
	}
	public boolean isLeftOf(CollisionBox other)
	{
		if (this.rect.getCenterX() < other.getRectangle().getCenterX()) return true;
		return false;
	}
	public int verticalDifference(CollisionBox other)
	{
		if (isHigherThan(other))
		{
			int min = (int) other.getRectangle().getY();
			int max = (int) ((this.rect.getY())+(this.rect.getHeight()));
			return max-min;
		}
		int min = (int) this.rect.getY();
		int max = (int) ((other.getRectangle().getY())+(other.getRectangle().getHeight()));
		return max-min;
	}
	public int horizontalDifference(CollisionBox other)
	{
		if (isLeftOf(other))
		{
			int min = (int) other.getRectangle().getX();
			int max = (int) ((this.rect.getX())+(this.rect.getWidth()));
			return max-min;
		}
		int min = (int) this.rect.getX();
		int max = (int) ((other.getRectangle().getX())+(other.getRectangle().getWidth()));
		return max-min;
	}
	public boolean isSmallerOverlapVertical(CollisionBox other)
	{
		if (verticalDifference(other) < horizontalDifference(other)) return true;
		return false;
	}
}
