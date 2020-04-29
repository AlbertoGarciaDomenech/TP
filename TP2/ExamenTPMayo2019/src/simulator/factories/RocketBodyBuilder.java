package simulator.factories;

import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLosingBody;
import simulator.model.RocketBody;

public class RocketBodyBuilder extends Builder<Body>{

	public RocketBodyBuilder()	 {
		super("rocket", "Rocket Body");
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
		
		double comb = data.getDouble("comb");
		double loss = data.getDouble("loss"); 
		
		return new RocketBody(id, vel, acc, pos, mass, comb, loss);
	}

	@Override
	protected JSONObject createData() {
		JSONObject data = new JSONObject();
		
		data.put("id", "the identifier");
		data.put("pos", "the position");
		data.put("vel", "the velocity");
		data.put("mass", "the mass");
		data.put("comb", "the combustible");
		data.put("loss", "the comb losing factor");
		
		return data;
	}

}
