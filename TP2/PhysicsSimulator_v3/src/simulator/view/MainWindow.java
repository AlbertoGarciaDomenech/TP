package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import simulator.control.Controller;

public class MainWindow extends JFrame {
	
	private ControlPanel controlPanel;
	private BodiesTable bodiesTable;
	private Viewer viewer;
	private StatusBar statusBar;
	
	private JPanel centerPanel;
	
	Controller _ctrl;
	
	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		
		controlPanel = new ControlPanel(_ctrl);
		mainPanel.add(controlPanel, BorderLayout.PAGE_START);
		
		statusBar = new StatusBar(_ctrl);
		mainPanel.add(statusBar, BorderLayout.PAGE_END);
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		bodiesTable = new BodiesTable(_ctrl);
		bodiesTable.setPreferredSize(new Dimension(300, 200));
		centerPanel.add(bodiesTable);

		viewer = new Viewer(_ctrl);
		viewer.setPreferredSize(new Dimension(700, 500));
		centerPanel.add(viewer);		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(getMinimumSize());
		this.setSize(getPreferredSize());
		this.pack();
		this.setVisible(true);
	}
}
