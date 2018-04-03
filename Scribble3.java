import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import figures.figures.DrawableRect;

public class Scribble3 extends Applet {
	int last_x, last_y;
	public void init() {
		// Определяет, создает и регистрирует объект MouseListener.
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					last_x = e.getX(); last_y = e.getY();
				}
			}
		});
		// Определяет, создает и регистрирует объект MouseMotionListener.
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					Graphics g = getGraphics();
					int x = e.getX(), y = e.getY();
					g.setColor(Color.black);
					g.drawLine(last_x, last_y, x, y);
					last_x = x; last_y = y;
				}
			}
		});
		// Создает кнопку Clear.
		Button b = new Button("Clear");
		// Определяет, создает и регистрирует объект слушателя
		// для обработки события, связанного с нажатием кнопки.
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// стирание каракулей
				Graphics g = getGraphics();
				g.setColor(getBackground());
				g.fillRect(0, 0, getSize().width, getSize().height);
			}
		});
		// Добавляет кнопку в апплет.
		this.add(b);
	}
}