package lab6;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3324406852037247002L;
	
	private ConfigPanel configPanel;
	private ControlPanel controlPanel;
	DrawingPanel canvas;

	public MainFrame() {
		super("My Drawing Application");
		Init();
	}

	void Init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		canvas = new DrawingPanel(this);
		configPanel = new ConfigPanel(this);;
		controlPanel = new ControlPanel(this);
		add(canvas, BorderLayout.CENTER);
		add(configPanel, BorderLayout.NORTH );
		add(controlPanel, BorderLayout.SOUTH);
		pack(); 
	}
}
