package simulator.factories;

import org.json.JSONObject;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLosingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	public MassLosingBodyBuilder(){
		super("mlb", "Mass Losing Body");
	}
	
	@Override
	protected Body createTheInstance(JSONObject data) {	
		String id = data.getString("id");
		double mass = data.getDouble("mass");
		
		double[] v = jsonArrayTodoubleArray(data.getJSONArray("vel"));
		Vector vel = new Vector(v);
		
		Vector acc = new Vector(vel.dim());
		
		double[] p = jsonArrayTodoubleArray(data.getJSONArray("pos"));
		Vector pos = new Vector(p);
		
		double freq = data.getDouble("freq");
		double factor = data.getDouble("factor"); 
		
		return new MassLosingBody(id, vel, acc, pos, mass, factor, freq);
	}
	
	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("pos", "the position");
		data.put("vel", "the velocity");
		data.put("mass", "the mass");
		data.put("freq", "the mass losing frequency");
		data.put("factor", "the mass losing factor");
		
		return data;
	}
	
}
