package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.*;

public class Controller {

	private PhysicsSimulator simulator;
	private Factory<Body> factory_b;
	private Factory<GravityLaws> factory_g;
	
	public Controller(PhysicsSimulator _pSimulator, Factory<Body> _factory, Factory<GravityLaws> _factoryG) { 
		this.simulator = _pSimulator;
		this.factory_b = _factory;
		this.factory_g = _factoryG;
	}
	
	public void reset() {
		this.simulator.reset();
	}
	
	public void setDeltaTime(double dt) throws IllegalArgumentException{
		this.simulator.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		this.simulator.addObserver(o);
	}
	
	public Factory<GravityLaws> getGravityLawsFactory(){
		return factory_g;
	}
	
	public void setGravityLaws(JSONObject info) throws IllegalArgumentException{
		GravityLaws gLaws =	factory_g.createInstance(info);
		this.simulator.setGravityLaws(gLaws);
	}
	
	
	public void loadBodies(InputStream in) {
		 JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		 JSONArray bodies = jsonInupt.getJSONArray("bodies");
		 
		 for (int i = 0; i < bodies.length(); i++)
			 simulator.addBody(factory_b.createInstance(bodies.getJSONObject(i)));
	}
	
	public int getNumBodies() {
		return simulator.getNumBodies();
	}
	
	public List<String> getBodiesInfo() {
		return simulator.getBodiesInfo();
	}
	
	public void run(int n) { //como el run de abajo pero sin escribir nada por consola
		for(int i = 0; i < n; i++) {
			this.simulator.advance();
		}
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

	public void removeBody(String id) throws IllegalArgumentException{
		try {
		simulator.removeBody(id);
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}
	

}
