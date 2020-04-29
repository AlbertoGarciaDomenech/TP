package simulator.view;


import java.util.List;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;
import simulator.view.BodyinRadio;

public class DeadZoneCollision implements SimulatorObserver {


	private Double _radio;
	private BodyinRadio [] _bodies;
	
	public DeadZoneCollision(Controller _ctrl, Double radio){
		_bodies = new BodyinRadio[0];
		_radio = radio;
		_ctrl.addObserver(this);

	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		Double d;
		Vector origin = new Vector(2);
		_bodies = new BodyinRadio[bodies.size()];
		int i = 0;
		for(Body b : bodies) {
			_bodies[i] = new BodyinRadio();
			_bodies[i].setB(b);
			d = b.getPosition().distanceTo(origin);
			if(d <= _radio) {
				_bodies[i].setBoolean(true);
				_bodies[i].setNum(1);
			}
			else
				_bodies[i].setBoolean(false);
			i++;
		}
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		Double d;
		Vector origin = new Vector(2);
		_bodies = new BodyinRadio[bodies.size()];
		int i = 0;
		for(Body b : bodies) {
			_bodies[i].setB(b);
			d = b.getPosition().distanceTo(origin);
			if(d <= _radio) {
				_bodies[i].setBoolean(true);
				_bodies[i].setNum(1);			}
			else
				_bodies[i].setBoolean(false);
			i++;
		}
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {

	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		Vector origin = new Vector(2);
		for(Body b : bodies) {
			BodyinRadio aux = null;
			for(BodyinRadio Br : _bodies) {
				if(Br.getB().getId().equals(b.getId())){
					aux = Br;
					break;
				}
			}
			if(aux != null) {
				Double d = b.getPosition().distanceTo(origin);
				if(d <= _radio && aux.getBoolean()) {}
				if(d <= _radio && !aux.getBoolean()) {aux.setBoolean(true);aux.setNum(aux.getNum() + 1);}
				if(d > _radio && aux.getBoolean()) {aux.setBoolean(false);}
				if(d > _radio && !aux.getBoolean()) {}
			}
		}
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	
	}

	@Override
	public void onBodyDeleted(List<Body> bodies) {
	
	}
	public String getCollision() {
		String str = "";
		for(BodyinRadio br : _bodies) {
			str += br.getB().getId() + ":" + br.getNum();
		}
		return str;
	}

}
