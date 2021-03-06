package lab6;

import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ConfigPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1987888545928880987L;
	private final MainFrame frame;
	private JLabel label;
	private JSpinner sidesField;
	private JComboBox<String> colorCombo;

	public ConfigPanel(MainFrame frame) {
		this.frame = frame;
		init();
	}

	public JComboBox<String> getColorCombo() {
		return colorCombo;
	}

	public int sides() {
		try {
			sidesField.commitEdit();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return (Integer) sidesField.getValue();
	}

	public void init() {
		label = new JLabel("Number of sides:");
		sidesField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		sidesField.setValue(6); // default number of sides
		String[] s = { "Black", "Red", "Green", "Blue" };
		colorCombo = new JComboBox<>(s);
		add(label); // JPanel uses FlowLayout by default
		add(sidesField);
		add(colorCombo);
	}

}
