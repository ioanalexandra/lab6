package lab6;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9090252862406465508L;
	final MainFrame frame;
	JButton saveBtn = new JButton("Save");
	JButton loadBtn = new JButton("Load");
	JButton resetBtn = new JButton("Reset");
	JButton exitBtn = new JButton("Exit");
	// create all buttons (Load, Reset, Exit)
	// ...TODO

	public ControlPanel(MainFrame frame) {
		this.frame = frame;
		init();
	}

	private void init() {
		// schimb default layout manager
		setLayout(new GridLayout(1, 4));

		// add all buttons
		// configurez listeneri pt toate butoanele
		saveBtn.addActionListener(this::save);
		loadBtn.addActionListener(this::load);
		resetBtn.addActionListener(this::reset);
		exitBtn.addActionListener(this::exit);
		// ...TODO
		add(resetBtn);
		add(saveBtn);
		add(loadBtn);
		add(exitBtn);
	}

	private void save(ActionEvent e) {
		// folosim un file chooser pt o interfata mai atractiva a salvarii
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // ca sa pot sa selectez numai directoare
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				// aici salvez imaginea la pathul modificat cu numele fisierului
				ImageIO.write(frame.canvas.image, "PNG", new File(file.getAbsolutePath() + "\\lab6.png"));
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	private void load(ActionEvent e) {
		// folosim ca si mai sus un file chooser doar ca de data asta acesta trebuie sa
		// deschida si fisiere
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				// citesc poza pe care vr s o pun pe ecran
				frame.canvas.image = ImageIO.read(file);

				// pun poza pe ecran
				frame.canvas.graphics = frame.canvas.image.createGraphics();
				frame.canvas.repaint();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}

	private void reset(ActionEvent e) {

		// stergem lista de shapes si culori
		frame.canvas.shapes.clear();
		frame.canvas.colors.clear();
		frame.canvas.graphics.setColor(new Color(255, 255, 255));
		// umplu ecranul cu alb ca sa stearga tot
		frame.canvas.graphics.fillRect(0, 0, DrawingPanel.W, DrawingPanel.H);
		frame.canvas.repaint();
	}

	private void exit(ActionEvent e) {
		System.exit(1); // functie de sistem pt a forta iesirea din program
	}

}
