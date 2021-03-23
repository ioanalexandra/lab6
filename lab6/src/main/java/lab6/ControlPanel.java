package lab6;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090252862406465508L;
	final MainFrame frame;
	JButton saveBtn = new JButton("Save");
	// create all buttons (Load, Reset, Exit)
	// ...TODO

	public ControlPanel(MainFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		// change the default layout manager (just for fun)
		setLayout(new GridLayout(1, 4));

		// add all buttons ...TODO
		// configure listeners for all buttons
		saveBtn.addActionListener(this::save);
		// ...TODO
		add(saveBtn);
	}

	private void save(ActionEvent e) {
		try {
			ImageIO.write(frame.canvas.image, "PNG", new File("C:\\Users\\alexa\\Desktop\\lab6\\poza.png"));
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}

}
