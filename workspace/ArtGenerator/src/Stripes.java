import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Stripes extends Art {
	
	private Color backGroundColor1 = getDarkTranslucentColor(255);
	private Color backGroundColor2 = getPaleTranslucentColor(255);
	private Color polygonsColor = getPaleTranslucentColor(255);
	private int numberOfStripes = getMinMaxRandom(1, 30);
	private List<Integer> xStripes = new ArrayList<Integer>();
	private List<Integer> widthStripes = new ArrayList<Integer>();
	private int numberOfPolygons = getMinMaxRandom(1, 50);
	private int polygonConfiguration = getMaxRandom(PolygonEnum.values().length + 1);
	private List<int[]> xShapes = new ArrayList<int[]>();
	private List<int[]> yShapes = new ArrayList<int[]>();
	
	public Stripes() {
		super();
		setStripes();
		setColors();
		setPolygons(xShapes, yShapes, numberOfPolygons, polygonConfiguration, true);
	}
	
	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(backGroundColor1);
		g2d.fillRect(0,  0,  width,  height);
		g2d.setColor(backGroundColor2);
		for(int i = 0; i < numberOfStripes; i++){
			g2d.fillRect(xStripes.get(i),  0,  widthStripes.get(i), height);
		}
		for (int i = 0; i < numberOfPolygons; i++) {
			g2d.setColor(polygonsColor);
			g2d.fillPolygon(xShapes.get(i), yShapes.get(i), xShapes.get(i).length);
		}
	}
	
	private void setStripes(){
		int marginLeft = getMaxRandom(width);
		for(int i = 0; i < numberOfStripes; i++){
			xStripes.add(marginLeft + i * 10);
			widthStripes.add(getMinMaxRandom(1, 30));
		}
	}
	
	private void setColors(){
		int random = getMaxRandom(2);
		if(random > 0){
			polygonsColor = backGroundColor1;
		} else {
			polygonsColor = backGroundColor2;
		}
	}
}