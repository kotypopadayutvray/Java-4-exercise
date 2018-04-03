import java.awt.*;
import java.applet.*;
import figures.DrawableRect;
import figures.ColoredRect;

public class BouncingBoxes extends Applet implements Runnable {
	int [][]dxdy;
	Thread animator;
	figures.rectangle.Rectangle[] rectangles;
	volatile boolean pleaseStop;

	public void paint(Graphics g) {
		for (int i = 0; i < rectangles.length; i++) {
			if (i < 10) {
				int coords[] = rectangles[i].rect_print();
				g.setColor(Color.red);
				g.drawRect(coords[0], coords[1], Math.abs(coords[2] - coords[0]), Math.abs(coords[3] - coords[1]));
			} else if (i < 20) {
				((DrawableRect)rectangles[i]).draw(g);
			} else {
				((ColoredRect)rectangles[i]).draw(g);
			}
		}
	}

	public void animate() {
		Rectangle bounds = getBounds();

		int dx = 0;
		int dy = 0;
		int []coords;

		for (int i = 0; i < rectangles.length; i++) {
			coords = rectangles[i].rect_print();
			dx = dxdy[i][0];
			dy = dxdy[i][1];

			if (coords[0] + dx < 0 || coords[2] + dx > bounds.width) {
				dx = -dx;
			}

			if (coords[1] + dy < 0 || coords[3] + dy > bounds.height) {
				dy = -dy;
			}

			rectangles[i].move(dx, dy);
			dxdy[i][0] = dx;
			dxdy[i][1] = dy;
		}

		repaint();
	}

	public void run() {
		while (!pleaseStop) {
			animate();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}

	public void start() {
		this.rectangles = new figures.rectangle.Rectangle[30];
		this.dxdy = new int[30][2];
		for (int i = 0; i < rectangles.length; i++) {
			dxdy[i][0] = (int)(1 + Math.random() * 15);
			dxdy[i][1] = (int)(1 + Math.random() * 15);
			int x1 = (int)(1 + Math.random() * 300);
			int y1 = (int)(1 + Math.random() * 300);
			int x2 = (int)(x1 + Math.random() * 50);
			int y2 = (int)(y1 + Math.random() * 50);
			if (i < 10) {
				rectangles[i] = new figures.rectangle.Rectangle(x1, y1, x2, y2);
			} else if (i < 20) {
				rectangles[i] = new DrawableRect(x1, y1, x2, y2);
			} else {
				rectangles[i] = new ColoredRect(x1, y1, x2, y2);
			}
		}
		animator = new Thread(this);
		pleaseStop = false;
		animator.start();
	}

	public void stop() {
		pleaseStop = false;
	}
}