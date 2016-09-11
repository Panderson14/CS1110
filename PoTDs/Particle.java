//Patrick Anderson (psa5dg)

import java.awt.Rectangle;

public class Particle {

	private double x;
	private double y;
	private double vx;
	private double vy;
	private double mass;
	
	public Particle(double x, double y)
	{
		this.x = x;
		this.y = y;
		this.vx = 0;
		this.vy = 0;
		this.mass = 1;
	}
	public Particle(double x, double y, double vx, double vy)
	{
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.mass = 1;
	}
	public Particle(double x, double y, double vx, double vy, double mass)
	{
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.mass = mass;
	}
	public double getX()
	{
		return this.x;
	}
	public int getIntX()
	{
		return (int)this.x;
	}
	public void setX(double x)
	{
		this.x = x;
	}
	public double getY()
	{
		return this.y;
	}
	public int getIntY()
	{
		return (int)this.y;
	}
	public void setY(double y)
	{
		this.y = y;
	}
	public void setVX(double vx)
	{
		this.vx = vx;
	}
	public void setVY(double vy)
	{
		this.vy = vy;
	}
	public void applyForce(double fx, double fy, double dt)
	{
		this.vx += (dt*fx)/this.mass;
		this.vy += (dt*fy)/this.mass;
	}
	public void timePasses(double dt)
	{
		this.x += this.vx*dt;
		this.y += this.vy*dt;
	}
	public double getSpeed()
	{
		return Math.sqrt((this.vx*this.vx)+(this.vy*this.vy));
	}
	public void applyDrag(double drag, double dt)
	{
		applyForce((-1*this.vx)*getSpeed()*drag, (-1*this.vy)*getSpeed()*drag, dt);
	}
	public void bounceIfOutsideOf(Rectangle r, double bounciness)
	{
		if (this.x < r.getX())
		{
			this.x = r.getX();
			this.vx = Math.abs(this.vx*bounciness);
		}
		if (this.x > r.getWidth())
		{
			this.x = r.getWidth();
			this.vx = -1*Math.abs(this.vx*bounciness);
		}
		if (this.y < r.getY())
		{
			this.y = r.getY();
			this.vy = Math.abs(this.vy*bounciness);
		}
		if (this.y > r.getHeight())
		{
			this.y = r.getHeight();
			this.vy = -1*Math.abs(this.vy*bounciness);
		}
	}
	public String toString()
	{
		return "Particle at <"+this.x+","+this.y+"> with velocity <"+this.vx+","+this.vy+"> and mass "+this.mass;
	}
}
