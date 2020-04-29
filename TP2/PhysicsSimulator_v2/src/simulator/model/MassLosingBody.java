package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class MassLosingBody extends Body {

	private double lossFactor;
	private double lossFrequency;
	private double accumulatedTime;
	
	public MassLosingBody(String id, Vector vel, Vector acc, Vector pos, double mass, double lossFactor, double lossFrequency) {
		super(id, vel, acc, pos, mass);
		this.lossFactor = lossFactor;
		this.lossFrequency = lossFrequency;
		this.accumulatedTime = 0.0;
	}
	
	@Override
	void move(double t) {
		super.move(t);
		this.accumulatedTime += t;
		
		if(this.accumulatedTime >= this.lossFrequency) {
			this.setMass(this.getMass() * (1 - this.lossFactor)); // Lose mass
			this.accumulatedTime = 0.0;
		}
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject jo = super.toJSON();
		
		jo.put("freq", this.lossFrequency);
		jo.put("factor", this.lossFactor);
		
		return jo;
		
	}
	
}
