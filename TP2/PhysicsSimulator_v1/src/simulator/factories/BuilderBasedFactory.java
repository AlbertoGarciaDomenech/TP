package simulator.factories;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T>{
	
	private	List<Builder<T>> builders;
	private List<JSONObject> factoryElements;

	public BuilderBasedFactory(List<Builder<T>> _builders){
		this.builders = new ArrayList<Builder<T>>(_builders);
		this.factoryElements = new ArrayList<JSONObject>(getInfo());
	}

	@Override
	public T createInstance(JSONObject info) throws IllegalArgumentException{
		T instance = null;
		int i = 0;
		
		while(i < builders.size() && instance == null) {
			Builder<T> b = builders.get(i);
			instance = b.createInstance(info); // Si type incorrecto: instance = null; Si data incorrecto: IllegalArgumentException
			i++;
		}
		
		if(instance == null) throw new IllegalArgumentException("Incorrect body type in JSON file");
		
		return instance;
		
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		
		for(Builder<T> b : builders) {
			list.add(b.getBuilderInfo());
		}
		
		return list;
	};
	
}
