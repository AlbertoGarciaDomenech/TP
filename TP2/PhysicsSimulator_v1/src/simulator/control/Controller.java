package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.*;

public class Controller {

	private PhysicsSimulator simulator;
	private Factory<Body> factory;
	
	
	
	public Controller(PhysicsSimulator _pSimulator, Factory<Body> _factory) {
		
		this.simulator = _pSimulator;
		this.factory = _factory;
		
	}
	
	public void loadBodies(InputStream in) {
		 JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInupt.getJSONArray("bodies");
		 
		 for (int i = 0; i < bodies.length(); i++)
			 simulator.addBody(factory.createInstance(bodies.getJSONObject(i)));
	}
	
	public void run(int steps, OutputStream out) throws IOException {
		JSONObject jOut = new JSONObject();
		JSONArray jA = new JSONArray();

		jA.put(simulator.toJSON());
		
		for(int i = 0; i < steps; i++) {
			simulator.advance();
			jA.put(simulator.toJSON());
		}
		
		jOut.put("states", jA);
		
		out.write(jOut.toString().getBytes());
	}
	

}
