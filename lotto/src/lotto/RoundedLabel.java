package lotto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

import javax.swing.JLabel;

public class RoundedLabel extends JLabel {
	private int number;
	private Color backgroundColor;

	public RoundedLabel(int number) {
		this.number = number;
		this.backgroundColor = Color.decode("#EEEEEE");

		setPreferredSize(new Dimension(54, 54));
		setBackground(this.backgroundColor);
		setForeground(Color.WHITE);
		setHorizontalAlignment(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setFont(new Font("SansSerif", Font.BOLD, 13));
	}

	@Override
	public void setText(String number) {
		super.setText(number);

		try {
			this.number = Integer.parseInt(number);

			if (this.number >= 1 && this.number <= 10) {
				this.backgroundColor = Color.decode("#fbc400"); // 배경색 설정
			} else if (this.number >= 11 && this.number <= 20) {
				this.backgroundColor = Color.decode("#69c8f2"); // 배경색 설정
			} else if (this.number >= 21 && this.number <= 30) {
				this.backgroundColor = Color.decode("#ff7272"); // 배경색 설정
			} else if (this.number >= 31 && this.number <= 40) {
				this.backgroundColor = Color.decode("#aaaaaa"); // 배경색 설정
			} else if (this.number >= 41 && this.number <= 45) {
				this.backgroundColor = Color.decode("#b0d840"); // 배경색 설정
			} else {
				this.backgroundColor = Color.decode("#EEEEEE"); // 배경색 설정
			}
		} catch (Exception error) {
			// System.out.println("error : " + error.toString());
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int diameter = Math.min(getWidth(), getHeight()) - 20;
		int x = (getWidth() - diameter) / 2;
		int y = (getHeight() - diameter) / 2;
		g2d.setColor(this.backgroundColor);
		g2d.fillOval(x, y, diameter, diameter);
		g2d.setColor(Color.WHITE);
		String text = String.valueOf(number);
		FontRenderContext frc = g2d.getFontRenderContext();
		Font font = getFont();
		TextLayout layout = new TextLayout(text, font, frc);
		float textX = (float) (getWidth() - layout.getBounds().getWidth()) / 2;
		float textY = (float) (getHeight() - layout.getBounds().getHeight()) / 2 + 10; // + layout.getAscent()
		layout.draw(g2d, textX, textY);
		g2d.dispose();
	}
}