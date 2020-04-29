package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {
	
	protected String typeTag;
	protected String desc;
	
	public Builder(String typeTag, String desc){
		this.typeTag = typeTag;
		this.desc = desc;
	}
	
	protected abstract T createTheInstance(JSONObject data);
	
	public T createInstance(JSONObject info) throws IllegalArgumentException {
		T b = null;
		if( this.typeTag != null && this.typeTag.equals(info.getString("type")) ) {
			b = createTheInstance(info.getJSONObject("data")); // Si data incorrecto: IllegalArgumentException
		}
		return b;
	}
	
	public JSONObject getBuilderInfo() {
		JSONObject info = new JSONObject();
		info.put("type", this.typeTag);
		info.put("data", createData());
		info.put("desc", this.desc);
		return info;
	}
	
	protected double[] jsonArrayTodoubleArray(JSONArray jArray) {
		double[] dArray = new double[jArray.length()];
		for (int i = 0; i < dArray.length; i++)
			dArray[i] = jArray.getDouble(i); 
		return dArray;
	}
	
	protected JSONObject createData() {
		return new JSONObject();
	}
	
}
