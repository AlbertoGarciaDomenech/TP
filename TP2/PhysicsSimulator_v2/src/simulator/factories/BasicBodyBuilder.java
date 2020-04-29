package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{
	
	public BasicBodyBuilder(){
		super("basic", "Default Body");
	}

	@Override
	protected Body createTheInstance(JSONObject data) throws IllegalArgumentException{	
		try {
			
			String id = data.getString("id");
			
			double mass = data.getDouble("mass");
			
			double[] v = jsonArrayTodoubleArray(data.getJSONArray("vel"));
			Vector vel = new Vector(v);
			
			Vector acc = new Vector(vel.dim());
			
			double[] p = jsonArrayTodoubleArray(data.getJSONArray("pos"));
			Vector pos = new Vector(p);
			
			return new Body(id, vel, acc, pos, mass);
		}
		catch(JSONException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("pos", "the position");
		data.put("vel", "the velocity");
		data.put("mass", "the mass");
		
		return data;
	}
	
}
