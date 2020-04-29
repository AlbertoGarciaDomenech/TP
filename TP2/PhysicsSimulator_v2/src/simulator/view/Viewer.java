	package simulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

@SuppressWarnings("serial")
public class Viewer extends JComponent implements SimulatorObserver {
	
	private static final int _WIDTH = 1000;
	private static final int _HEIGHT = 1000;
	
	// Añade constantes para los colores
	private static final Color crossColor = Color.RED;
	private static final Color helpColor = Color.RED;
	private static final Color bodyColor = Color.BLUE;
	private static final Color bodyTextcolor = Color.BLACK;
	
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	private void initGUI() {
		// Add border with title
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		this.setBorder(BorderFactory.createTitledBorder(border, "Viewer", TitledBorder.LEFT, TitledBorder.TOP));
		
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
					case '-':
						_scale = _scale * 1.1;
						break;
					case '+':
						_scale = Math.max(1000.0, _scale / 1.1);
						break;
					case '=':
						autoScale();
						break;
					case 'h':
						_showHelp = !_showHelp;
						break;
					default:
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {	
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// use ’gr’ to draw not ’g’
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		
		// Draw a cross at center
		drawCenterCross(gr, crossColor);

		// Draw bodies
		drawBodies(gr, bodyColor, bodyTextcolor);
		
		// Draw help if _showHelp is true
		drawHelpText(gr, helpColor);
	}
	
	// other private/protected methods
	private void drawCenterCross(Graphics2D gr, Color color) {
		gr.setColor(color);
		gr.drawLine(_centerX - 5, _centerY, _centerX + 5, _centerY);
		gr.drawLine(_centerX, _centerY - 5, _centerX, _centerY + 5);
	}
	private void drawBodies(Graphics2D gr, Color bodyColor, Color textColor) {
		for(Body b : _bodies) {
			Vector v = b.getPosition();
			double x = v.coordinate(0);
			double y = v.coordinate(1);
			// Draw body
			gr.setColor(bodyColor);
			gr.fillOval(_centerX + (int)(x/_scale), _centerY - (int)(y/_scale), 10, 10);
			// Draw name
			gr.setColor(textColor);
			gr.drawString(b.getId(), _centerX + (int)(x/_scale), _centerY - (int)(y/_scale));
		}
	}
	private void drawHelpText(Graphics2D gr, Color color) {
		if(_showHelp) {
			String help1 =  "h: toggle help, +: zoom-in,  -: zoom-out, =: fit";
			String help2 =  "Scaling ratio: " + _scale;
			gr.setColor(color);
			gr.drawString(help1, 10, 25);
			gr.drawString(help2, 10, 40);
		}
	}
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
						
			Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(), (double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	
	// SimulatorObserver methods
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;
		autoScale();
		repaint();
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		repaint();
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
	}

}

