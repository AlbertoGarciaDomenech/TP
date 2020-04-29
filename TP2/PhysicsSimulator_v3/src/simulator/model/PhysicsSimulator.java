package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PhysicsSimulator {
	
	private double dt;
	private double actualTime;
	private GravityLaws laws;
	private List<Body> bodies;
	private List<SimulatorObserver> observers;
	
	public PhysicsSimulator(GravityLaws laws, double dt) throws IllegalArgumentException {
		this.actualTime = 0.0;
		this.bodies = new ArrayList<Body>();
		this.observers = new ArrayList<SimulatorObserver>();
		setGravityLaws(laws);
		setDeltaTime(dt);
	}
	
	public void reset() {
		bodies.clear();
		actualTime = 0.0;
		
		for(SimulatorObserver o : observers) {
			o.onReset(bodies, actualTime, dt, laws.toString());
		}
	}
	
	public void setDeltaTime(double deltaTime) throws IllegalArgumentException {
		if(dt < 0.0d) {throw new IllegalArgumentException("Invalid DeltaTime.");}
		this.dt = deltaTime;
		
		for(SimulatorObserver o : observers) {
			o.onDeltaTimeChanged(dt);
		}
	}
	
	public void setGravityLaws(GravityLaws gravityLaws) throws IllegalArgumentException{
		if(gravityLaws == null) { throw new IllegalArgumentException("Invalid Gravity Law.");}
		this.laws = gravityLaws;
		
		for(SimulatorObserver o : observers) {
			o.onGravityLawChanged(laws.toString());
		}
	}
	
	public void addObserver(SimulatorObserver o) {
		if(!observers.contains(o)) {
			observers.add(o);
			o.onRegister(bodies, actualTime, dt, laws.toString());
		}
	}
	
	public void advance() {
		laws.apply(bodies);
		
		for(Body b : bodies) {
			b.move(dt);
		}
		
		this.actualTime += this.dt;
		
		for(SimulatorObserver o : observers) {
			o.onAdvance(bodies, actualTime);
		}
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		for(Body b1 : bodies) {
			if(b1.equals(b)){throw new IllegalArgumentException("A body with the same id already exists in the simulator");}
		}
		
		bodies.add(b);
		
		for(SimulatorObserver o : observers) {
			o.onBodyAdded(bodies, b);
		}
	}
	
	public String toString() {
		return toJSON().toString();
	}
	
	public JSONObject toJSON() {
		JSONObject jo = new JSONObject();
		
		jo.put("time", this.actualTime);
		
		JSONArray ja = new JSONArray();
		for(Body b : bodies) {
			ja.put(b.toJSON());
		}
		
		jo.put("bodies", ja);
		
		return jo;
	}
	
}
