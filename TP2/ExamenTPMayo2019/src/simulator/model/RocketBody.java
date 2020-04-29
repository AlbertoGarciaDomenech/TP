package simulator.model;

import org.json.JSONObject;

import simulator.misc.Vector;

public class RocketBody extends Body{

	private double _comb, _loss;
	
	public RocketBody(String id, Vector vel, Vector acc, Vector pos, double mass, double comb, double loss) {
		super(id, vel, acc, pos, mass);
		
		this._comb = comb;
		this._loss = loss;
	
	}
	
	@Override
	void move(double t) {
		if(_comb > 0) {
			double newP[] = new double[2];
			double coord_x = this.getPosition().coordinate(0);
			double coord_y = this.getPosition().coordinate(1);
			coord_x += this.getVelocity().coordinate(0) * t;
			newP[0] = coord_x;
			coord_y += this.getVelocity().coordinate(1) * t;
			newP[1] = coord_y;
			Vector newPosition = new Vector(newP);
			this.setPosition(newPosition);
			_comb -= _loss;
		}
		else {
		super.move(t);
		}
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject jo = super.toJSON();
		
		jo.put("comb", this._comb);
		jo.put("loss", this._loss);
		
		return jo;
		
	}

}
