package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class Body {
	
	private String id;
	private Vector a;
	private Vector v;
	private Vector p;
	private double mass;
	
	public Body(String id, Vector vel, Vector acc, Vector pos, double mass) {
		this.id = id;
		this.v = vel;
		this.a = acc;
		this.p = pos;
		this.mass = mass;
	}
	
	public String getId() {
		return id;
	}
	
	public Vector getVelocity() {
		return new Vector(v);
	}
	
	public Vector getAcceleration() {
		return new Vector(a);
	}
	
	public Vector getPosition() {
		return new Vector(p);
	}
	
	double getMass() {
		return mass;
	}
	
	void setVelocity(Vector v) {
		this.v = new Vector(v);
	}
	
	void setAcceleration(Vector a) {
		this.a = new Vector(a);
	}
	
	void setPosition(Vector p) {
		this.p = new Vector(p);
	}
	
	void setMass(double mass) {
		this.mass = mass;
	}
	
	void move(double t) {
		this.p = this.p.plus(this.v.scale(t).plus(this.a.scale(t * t * 0.5)));	
		this.v = this.v.plus(this.a.scale(t));
	}
	
	public boolean equals(Body b) {
		return b.getId().equals(this.id);
	}
	
	public String toString() {
		return "{ " + "id: " + this.id + ", " + "mass: " + this.mass + ", " 
		+ "pos: " + this.p + ", " + "vel: " 
		+ this.v + ", " + "acc: " + this.a + " }";
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		
		jo.put("id", this.id);
		jo.put("mass", this.mass);
		jo.put("pos", this.p.toJSON());
		jo.put("vel", this.v.toJSON());
		jo.put("acc", this.a.toJSON());
		
		return jo;
	}
}
