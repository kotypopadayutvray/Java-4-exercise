import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import figures.DrawableRect;

public class Scribble extends Applet {
	int last_x, last_y;
	Color color;

	private int getRandom() {
		return (0 + (int) (Math.random() * 255));
	}

	public void init() {
		color = new Color(getRandom(), getRandom(), getRandom());
		// Определяет, создает и регистрирует объект MouseListener.
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					last_x = e.getX();
					last_y = e.getY();
				} else if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) {
					color = new Color(getRandom(), getRandom(), getRandom());
				}
			}
		});
		// Определяет, создает и регистрирует объект MouseMotionListener.
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
					int width = 50;
					int height = 50;
					DrawableRect rect = new DrawableRect(last_x - (width / 2), last_y - (height / 2), last_x + width, last_y + height);
					Graphics g = getGraphics();
					rect.draw(g, color);
					last_x = e.getX();
					last_y = e.getY();
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