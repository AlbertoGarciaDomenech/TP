package simulator.view;

import simulator.model.Body;

public class BodyinRadio {

	private Body _b;
	private boolean _inRadio;
	private int num;
	
	public BodyinRadio() {
		
	}
	
	public BodyinRadio(Body b, boolean r){
		_b = b;
		_inRadio = r;
		num = 0;
	}
	
	public Body getB() {
		return _b;
	}
	
	public boolean getBoolean() {
		return _inRadio;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setB(Body b) {
		_b = b;
	}
	
	public void setBoolean(boolean r) {
		_inRadio = r;
	}
	
	public void setNum(int n) {
		this.num = n;
	}
	
}
