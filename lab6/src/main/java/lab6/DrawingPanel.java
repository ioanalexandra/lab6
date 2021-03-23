package lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889716450757428932L;
	final MainFrame frame;
	final static int W = 800, H = 600;
	BufferedImage image; // the offscreen image
	Graphics2D graphics; // the "tools" needed to draw in the image

	public DrawingPanel(MainFrame frame) {
		this.frame = frame;
		createOffscreenImage();
		init();
	}

	private void createOffscreenImage() {
		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
		graphics.setColor(Color.WHITE); // fill the image with white
		graphics.fillRect(0, 0, W, H);
	}

	private void init() {
		setPreferredSize(new Dimension(W, H)); // don’t use setSize. Why?
		setBorder(BorderFactory.createEtchedBorder()); // for fun
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				drawShape(e.getX(), e.getY());
				repaint();
			} // Can’t use lambdas, JavaFX does a better job in these cases
		});
	}

	private void drawShape(int x, int y) {
		int radius = 5; // generate a random number
		int sides = 4; // get the value from UI (in ConfigPanel)
		Color color = new Color(0, 0, 0); // create a transparent random Color.
		graphics.setColor(color);
		graphics.fill(new RegularPolygon(x, y, radius, sides));
	}

	@Override
	public void update(Graphics g) {
	} // Why did I do that?

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}

}
