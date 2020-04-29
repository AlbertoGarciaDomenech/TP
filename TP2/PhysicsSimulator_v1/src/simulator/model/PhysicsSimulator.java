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
	
	public PhysicsSimulator(GravityLaws laws, double dt) throws IllegalArgumentException{
		if(laws == null) { throw new IllegalArgumentException("Invalid Gravity Law.");}
		if(dt < 0.0d) {throw new IllegalArgumentException("Invalid DeltaTime.");}
		this.dt = dt;
		this.laws = laws;
		this.actualTime = 0.0;
		this.bodies = new ArrayList<Body>();
	}
	
	public void advance() {
		laws.apply(bodies);
		
		for(Body b : bodies) {
			b.move(dt);
		}
		
		this.actualTime += this.dt;
	}
	
	public void addBody(Body b) throws IllegalArgumentException{
		for(Body b1 : bodies) {
			if(b1.equals(b)){throw new IllegalArgumentException("A body with the same id already exists in the simulator");}
		}
		bodies.add(b);
//		if(bodies.contains(b)) {throw new IllegalArgumentException("A body with the same id already exists in the simulator");}
//		bodies.add(b);
	}
	
	public String toString() {
		JSONObject jo = new JSONObject();
		
		jo.put("time", this.actualTime);
		
		JSONArray ja = new JSONArray();
		for(Body b : bodies) {
			ja.put(b.toString());
		}
		
		jo.put("bodies", ja);
		
		return jo.toString();
		
		/*StringBuilder strB = new StringBuilder();
		for(Body b : bodies) {
			strB.append(b);
			if(b.equals(bodies.get(bodies.size() - 1))) {}
			else strB.append(", ");
		}
		
		return "time: " + this.actualTime + ", \"bodies\": [" + strB.toString() + "] ";*/
		//{ "time": T, "bodies": [json1, json2, . . .] }
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
