package lab6;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DrawingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889716450757428932L;
	final MainFrame frame;
	final static int W = 800, H = 600;
	BufferedImage image;
	Graphics2D graphics;
	List<Shape> shapes = new LinkedList<>();
	List<Color> colors = new LinkedList<>();

	public DrawingPanel(MainFrame frame) {
		this.frame = frame;
		createOffscreenImage();
		init();
	}

	private void createOffscreenImage() {
		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
		graphics.setColor(Color.WHITE); // umplu imaginea cu alb
		graphics.fillRect(0, 0, W, H);
	}

	private void init() {
		setPreferredSize(new Dimension(W, H));
		setBorder(BorderFactory.createEtchedBorder());
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					drawShape(e.getX(), e.getY());
					repaint();
				}
			}
		});
		// cand apas pe butonul din dreapta sa sterg o forma
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					for (int i = 0; i < shapes.size(); ++i) {
						if (shapes.get(i).contains(e.getX(), e.getY())) {
							shapes.remove(i);
							colors.remove(i);
						}
					}
					repaint();
				}
			}
		});

	}

	private void drawShape(int x, int y) {
		int radius = 35;
		int sides = frame.getSidesNumber();

		// verificam ce culoare punem in functie de ce optiune alegem
		if (frame.getComboElement().equals("Black")) {
			colors.add(new Color(0, 0, 0));
		} else if (frame.getComboElement().equals("Red")) {
			colors.add(new Color(255, 0, 0));
		} else if (frame.getComboElement().equals("Green")) {
			colors.add(new Color(0, 255, 0));
		} else {
			colors.add(new Color(0, 0, 255));
		}
		// adaugam forma la lista ca si dupa le desenam pe toate odata

		shapes.add(new RegularPolygon(x, y, radius, sides));

		// graphics.fill(new RegularPolygon(x, y, radius, sides));
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, this);
		for (int i = 0; i < shapes.size(); i++) {
			g.setColor(colors.get(i));
			((Graphics2D) g).fill(shapes.get(i));
		}
	}

}
