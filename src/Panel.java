import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panel extends JPanel {

	private Title title = new Title();
	private Art art = new Art();
	private String artTitle = title.get();
	private Grid grid = new Grid();
	private Gradient gradient = new Gradient();
	private Halo halo = new Halo();
	private Stripes stripes = new Stripes();
	private Ocean ocean = new Ocean();
	
	public void paintComponent(Graphics g) {
		switch(art.getBackGroundStyle()){
		case GRADIENT:
			gradient.drawBackGround(g);
			break;
		case GRID:
			grid.drawBackGround(g);
			break;
		case HALO:
			halo.drawBackGround(g);
			break;
		case STRIPES:
			stripes.drawBackGround(g);
			break;
		case OCEAN:
			ocean.drawBackGround(g);
			break;
		}
		drawTitleZone(g);
	}

	private void drawTitleZone(Graphics g) {
		int yTitleZone = (int) (getHeight() * 0.80);
		int heightTitleZone = getHeight() - yTitleZone;
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, yTitleZone, getWidth(), heightTitleZone);
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Georgia", Font.PLAIN, 15));
		printCenteredWithNewLines(g, artTitle, getWidth(), yTitleZone);
	}

	private void printCenteredWithNewLines(Graphics g, String string, int width, int y) {
		String[] chars = string.split("\n");
		FontMetrics fm = g.getFontMetrics();
		for (int i = 0; i < chars.length; i++) {
			if (chars.length > 1) {
				g.drawString(chars[i], (width - fm.stringWidth(chars[i])) / 2, y + (70 / chars.length) + i * 15);
			} else {
				g.drawString(chars[i], (width - fm.stringWidth(chars[i])) / 2, y + 40);
			}
		}
	}
	
	public void reinitialize(){
		art = new Art();
		System.out.println("Background : " + art.getBackGroundStyle());
		switch(art.getBackGroundStyle()){
		case GRADIENT:
			gradient = new Gradient();
			break;
		case GRID:
			grid = new Grid();
			break;
		case HALO:
			halo = new Halo();
			break;
		case STRIPES:
			stripes = new Stripes();
			break;
		case OCEAN:
			ocean = new Ocean();
			break;
		}
		this.artTitle = title.get();
	}

}