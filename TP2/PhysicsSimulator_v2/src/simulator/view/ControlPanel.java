package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import javax.swing.*;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements SimulatorObserver{
	
	private final Double _deltaTimeDefaultValue = 2500d;
	
	private Controller _ctrl;
	private boolean _stopped;
	private Double _deltaTime;
	
	private JToolBar toolBar1;
	private JToolBar toolBar2;
	
	private JButton loadButton;
	private JButton lawsButton;
	private JButton startButton;
	private JButton stopButton;
	private JButton exitButton;
	
	private JLabel stepLabel;
	private JLabel deltaTimeLabel;
	
	private JSpinner stepSpinner;
	private JTextField deltaTimeText;
	
	ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		_stopped = true;
		_deltaTime = _deltaTimeDefaultValue;
		initGUI();
		_ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// Build the tool bar by adding buttons, etc.
		this.setLayout(new BorderLayout());

		toolBar1 = new JToolBar();
		toolBar1.setFloatable(false);
		this.add(toolBar1, BorderLayout.LINE_START);
		
		toolBar2 = new JToolBar();
		toolBar2.setFloatable(false);
		this.add(toolBar2, BorderLayout.LINE_END);
		
		createLoadButton(toolBar1);
		toolBar1.addSeparator();
		
		createLawsButton(toolBar1);
		toolBar1.addSeparator();
		
		createStartButton(toolBar1);	
		createStopButton(toolBar1);
		toolBar1.addSeparator();
		
		createStepSpinner(toolBar1);
		toolBar1.addSeparator();
		
		createDeltaTimeText(toolBar1);
		toolBar1.addSeparator();
		
		createExitButton(toolBar2);
		toolBar2.addSeparator();
	}
	
	private void createLoadButton(JToolBar toolBar) {
		loadButton = new JButton();
		loadButton.setToolTipText("Load bodies files");
		loadButton.setIcon(new ImageIcon("icons/open.png"));
		
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser("E:\\eclipse-workspace\\PhysicsSimulator_V2\\resources\\examples");
				
				int sel = fileChooser.showOpenDialog(null);
				
				if(sel == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try(InputStream in = new FileInputStream(file)){
						_ctrl.reset();
						_ctrl.loadBodies(in);
					}
					catch(Exception ex) {
						ex.getStackTrace();
						JOptionPane.showMessageDialog(null, ex.getMessage(), "INVALID FILE", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		toolBar.add(loadButton);
	}
	
	private void createLawsButton(JToolBar toolBar) {
		lawsButton = new JButton();
		
		lawsButton.setToolTipText("Change gravity law");
		lawsButton.setIcon(new ImageIcon("icons/physics.png"));
		
		lawsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = new Object[_ctrl.getGravityLawsFactory().getInfo().size()];
				int i = 0;
				
				for(JSONObject jo : _ctrl.getGravityLawsFactory().getInfo()) {
					options[i] = jo.get("desc").toString();
					i++;
				}
				
				String n = (String) JOptionPane.showInputDialog(null,
						"Select gravity laws to be used.",
						"Gravity Laws Selector",
						JOptionPane.INFORMATION_MESSAGE, null, options, null);
				
				if(n != null) {
					for(JSONObject jo : _ctrl.getGravityLawsFactory().getInfo()) {
						if(n.equals(jo.get("desc"))) {
							_ctrl.setGravityLaws(jo);
						}
					}
				}
			}
		});
		
		toolBar.add(lawsButton);
	}
	
	private void createStartButton(JToolBar toolBar) {
		startButton = new JButton();
		
		startButton.setToolTipText("Start the simulation");
		startButton.setIcon(new ImageIcon("icons/run.png"));
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadButton.setEnabled(false);
				lawsButton.setEnabled(false);
				startButton.setEnabled(false);
				stepSpinner.setEnabled(false);
				deltaTimeText.setEnabled(false);

				_stopped = false;
				
				_ctrl.setDeltaTime(_deltaTime);
				
				run_sim(Integer.parseInt(stepSpinner.getValue().toString()));
			}
		});
		
		toolBar.add(startButton);
	}
	
	private void createStopButton(JToolBar toolBar) {
		stopButton = new JButton();
		
		stopButton.setToolTipText("Stop the simulation");
		stopButton.setIcon(new ImageIcon("icons/stop.png"));
		
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_stopped = true;
				setEnableAll(true);	
			}
		});
		
		toolBar.add(stopButton);
	}
	
	private void createExitButton(JToolBar toolBar) {
		exitButton = new JButton();
		
		exitButton.setToolTipText("Exit the editor");
		exitButton.setIcon(new ImageIcon("icons/exit.png"));
		
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] options = {"Yes", "No"};
				int n = JOptionPane.showOptionDialog(null, "Do you want to exit?",
						"EXIT?",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						options, 
						options[1]);
				if(n == JOptionPane.YES_OPTION) System.exit(0);
			}
		});
		
		toolBar.add(exitButton);
	}
	
	private void createStepSpinner(JToolBar toolBar) {
		stepLabel = new JLabel("Steps:");
		stepSpinner = new JSpinner();
		
		stepSpinner.setToolTipText("Set number of steps");
		
		stepSpinner.setPreferredSize(new Dimension(80,20));
		
		stepSpinner.setModel(new SpinnerNumberModel(1500, 0, Integer.MAX_VALUE, 1));
		
		toolBar.add(stepLabel);
		toolBar.add(stepSpinner);
	}
	
	private void createDeltaTimeText(JToolBar toolBar) {
		deltaTimeLabel = new JLabel("Delta-Time:");
		deltaTimeText = new JTextField(5);
		
		deltaTimeText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					_deltaTime = Double.parseDouble(deltaTimeText.getText());
				}
				catch (Exception ex1) {
					// Delta time is not a double so it sets default value
					_deltaTime = _deltaTimeDefaultValue;
				}
			}
		});

		toolBar.add(deltaTimeLabel);
		toolBar.add(deltaTimeText);
	}
	
	// other private/protected method
	private void run_sim(int n) {
		if ( n>0 && !_stopped ) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// Show the error in a dialog box
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				
				// Enable all buttons
				setEnableAll(true);
				
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
			
		} else {
			_stopped = true;
			// Enable all buttons
			setEnableAll(true);
		}
	}
	
	private void setEnableAll(boolean enable) {
		loadButton.setEnabled(enable);
		lawsButton.setEnabled(enable);
		startButton.setEnabled(enable);
		stopButton.setEnabled(enable);
		exitButton.setEnabled(enable);
		stepSpinner.setEnabled(enable);
		deltaTimeText.setEnabled(enable);
	}
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		deltaTimeText.setText(String.valueOf(dt));
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		deltaTimeText.setText(String.valueOf(dt));
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		deltaTimeText.setText(String.valueOf(dt));
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	}
	
}
