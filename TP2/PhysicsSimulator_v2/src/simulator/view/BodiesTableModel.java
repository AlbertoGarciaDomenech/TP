package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	
	private List<Body> _bodies;
	private String[] _columnNames = {"Id", "Mass", "Position", "Velocity", "Acceleration"};
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	
	@Override
	public int getRowCount() {
		return _bodies.size();
	}
	
	@Override
	public int getColumnCount() {
		return _columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return _columnNames[column];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return _bodies.get(rowIndex).getId();
			case 1:
				return _bodies.get(rowIndex).getMass();
			case 2:
				return _bodies.get(rowIndex).getPosition();
			case 3:
				return _bodies.get(rowIndex).getVelocity();
			case 4:
				return _bodies.get(rowIndex).getAcceleration();
			default: 
				return null;
		}
	}

	
	// SimulatorObserver methods
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		fireTableStructureChanged();
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		fireTableStructureChanged();	
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		fireTableStructureChanged();	
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		_bodies = bodies;
		fireTableStructureChanged();	
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
	}
}