package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;

@SuppressWarnings("serial")
public class BodiesTable extends JPanel {
	
	private JTable _table;
	private BodiesTableModel _model;
	
	BodiesTable(Controller ctrl) {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), 
				"Bodies", TitledBorder.LEFT, TitledBorder.TOP));
		
		_model = new BodiesTableModel(ctrl);
		_table = new JTable(_model);
		
		_table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(_table,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scrollPane);
	}
}