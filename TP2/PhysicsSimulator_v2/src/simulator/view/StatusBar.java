package simulator.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements SimulatorObserver {

	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies; // for number of bodies
	
	private JLabel _timeLabel;
	private JLabel _lawsLabel;
	private JLabel _bodiesLabel;
	
	StatusBar(Controller ctrl) {
		_currTime = new JLabel();
		_currLaws = new JLabel();
		_numOfBodies = new JLabel();
		
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		
		// Complete the code to build the status bar
		createLabel(_timeLabel, "Time: ", _currTime);
		
		this.add(createSeparator(SwingConstants.VERTICAL, Color.BLACK));
		
		createLabel(_bodiesLabel, "Bodies: ", _numOfBodies);
		
		this.add(createSeparator(SwingConstants.VERTICAL, Color.BLACK));
		
		createLabel(_lawsLabel, "Laws: ", _currLaws);
	}
	
	// other private/protected methods
	private void createLabel(JLabel textLabel, String text, JLabel valueLabel) {
		textLabel = new JLabel(text);
		
		this.add(textLabel);
		this.add(valueLabel);
	}
	private JSeparator createSeparator(int orientation, Color color) {
		JSeparator separator = new JSeparator(orientation);
		separator.setBackground(color);
		
		return separator;
	}
	
	// SimulatorObserver methods
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
		this._currLaws.setText(gLawsDesc.toString());
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		this._numOfBodies.setText(Integer.toString(bodies.size()));
		this._currTime.setText(Double.toString(time));
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		this._currLaws.setText(gLawsDesc.toString());
	}

}
