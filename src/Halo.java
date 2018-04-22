import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Halo extends Art {
	public Halo() {
		super();
		setPolygons(xShapes, yShapes, numberOfPolygons, polygonConfiguration, false);
	}

	private Color backGroundColor1 = new Color(getMaxRandom(50), getMaxRandom(50), getMaxRandom(50));
	private int numberOfCircles = getMinMaxRandom(1, 200);
	private int numberOfPolygons = getMinMaxRandom(5, 50);
	private Color backGroundColor2 = getPaleTranslucentColor(10);
	private List<int[]> xShapes = new ArrayList<int[]>();
	private List<int[]> yShapes = new ArrayList<int[]>();
	private int polygonConfiguration = getMaxRandom(PolygonEnum.values().length + 1);
	private int haloMarginLeft = getMinMaxRandom(50, width) - 150;
	private int haloMarginTop = getMinMaxRandom(50, height) - 150;
	
	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackGround(g2d);
		g2d.setColor(backGroundColor2);
		for (int i = 0; i < numberOfCircles; i++) {
			g2d.fillOval(haloMarginLeft + i * 3, haloMarginTop + i * 2, width - i * 10, height - i * 10);
		}
		for (int i = 0; i < numberOfPolygons; i++) {
			g2d.setColor(backGroundColor1);
			g2d.fillPolygon(xShapes.get(i), yShapes.get(i), xShapes.get(i).length);
		}
	}

	private void setBackGround(Graphics2D g2d) {
		g2d.setPaint(backGroundColor1);
		g2d.fillRect(0, 0, width, height);
	}	
}