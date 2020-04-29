package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {
	
	private static final double G = 6.67E-11;
	
	@Override
	public void apply(List<Body>bodies) {	
		for(Body bi : bodies) {
			Vector Fi = new Vector(bi.getAcceleration().dim());
			
			if(bi.getMass() == 0.0) {
				bi.setAcceleration(new Vector(bi.getAcceleration().dim())); // acceleration = 0
				bi.setVelocity(new Vector(bi.getVelocity().dim())); // velocity = 0
			}
			else {
				for(Body bj : bodies) {
					if(!bj.equals(bi)) {
						Vector Fij = force(bi, bj);
						Fi = Fi.plus(Fij);
					}
				}
				bi.setAcceleration(Fi.scale(1.0d / bi.getMass()));
			}
		}
	}
	
	private Vector force(Body a, Body b) {
		double distance = Math.abs(a.getPosition().distanceTo(b.getPosition())); // |pb - pa|
		Vector direction = b.getPosition().minus(a.getPosition()).direction(); // direction(pb - pa)
		
		double fab = G * ( (a.getMass() * b.getMass()) / (distance * distance) );
		
		return direction.scale(fab);
	}
	
	public String toString() {
		return "Newton's Universal Gravitation.";
	}
	
}
