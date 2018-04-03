import java.awt.*;
import java.applet.*;
import figures.ColoredRect;

public class BouncingBox extends Applet implements Runnable {
	int dx = 11;
	int dy = 7;
	Thread animator;
	ColoredRect colored_rect;
	volatile boolean pleaseStop;

	public void paint(Graphics g) {
		colored_rect.draw(g);
	}

	public void animate() {
		Rectangle bounds = getBounds();
		int coords[] = colored_rect.rect_print();

		if (coords[0] + dx < 0 || coords[2] + dx > bounds.width) {
			dx = -dx;
		}

		if (coords[1] + dy < 0 || coords[3] + dy > bounds.height) {
			dy = -dy;
		}

		colored_rect.move(dx, dy);

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
		animator = new Thread(this);
		colored_rect = new ColoredRect(100, 100, 150, 150);
		pleaseStop = false;
		animator.start();
	}

	public void stop() {
		pleaseStop = false;
	}
}