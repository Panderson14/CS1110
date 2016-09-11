import java.awt.Rectangle;

public class Test {
	public static void main(String[] args) throws Exception{
				
		Particle p1 = new Particle(10.0, 20.0);
		System.out.println(p1.toString());                      // should return "Particle at <10.0,20.0> with velocity <0.0,0.0> and mass 1.0"
		Particle p2 = new Particle(10.0, 20.0, 2.0, 2.0);
		System.out.println(p2.toString());                      // should return "Particle at <10.0,20.0> with velocity <2.0,2.0> and mass 1.0"
		Particle p3 = new Particle(10.0, 20.0, 1.0, 3.0, 4.0);
		System.out.println(p3.toString());                      // should return "Particle at <10.0,20.0> with velocity <1.0,3.0> and mass 4.0"
		p2.timePasses(0.02);
		System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <2.0,2.0> and mass 1.0"
		p2.applyForce(0, 9.8, 0.02);
		System.out.println(p2.getSpeed());                      // should return 2.9702552078903928
		System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <2.0,2.196> and mass 1.0"
		p2.applyDrag(0.1, 0.02);
		System.out.println(p2.toString());                      // should return "Particle at <10.04,20.04> with velocity <1.9881189791684384,2.1829546391269457> and mass 1.0"
		p2.bounceIfOutsideOf(new Rectangle(0,0, 10, 20), 0.5);
		System.out.println(p2.toString());                      // should return "Particle at <10.0,20.0> with velocity <-0.9940594895842192,-1.0914773195634728> and mass 1.0"
		
		
		Particle p4 = new Particle(-3, -3, -10, -10, 4.0);
		p4.bounceIfOutsideOf(new Rectangle(0,0, 10, 20), 0.5);
		System.out.println(p4.toString());        
	}
}
