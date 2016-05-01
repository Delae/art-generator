
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Gradient extends Art {

	public Gradient() {
		super();
		setColors();
		setShapes();
	}

	int numberOfShapes = getMinMaxRandom(1, 20);
	private List<Integer> shapesCoordinates = new ArrayList<Integer>();
	private List<Integer> shapesSize = new ArrayList<Integer>();

	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackGround(g2d);
		g2d.setColor(backGroundShapesColor);
		for (int i = 0; i < numberOfShapes; i++) {
			g2d.fillOval(shapesCoordinates.get(i * 2) - 100, shapesCoordinates.get(i * 2 + 1) - 100,
					shapesSize.get(i * 2), shapesSize.get(i * 2 + 1));
		}
	}

	private void setColors() {
		int indexColor3 = getMaxRandom(14);
		backGroundShapesColor = translucentColor(colors[indexColor3]);

		boolean brightColors = getRandomBoolean();
		if (brightColors) {
			boolean brightBackGround = getRandomBoolean();
			if(brightBackGround){
				backGroundColor1 = getBrightTranslucentColor(255);
				backGroundColor2 = getDarkTranslucentColor(255);	
			} else {
				backGroundColor1 = getDarkTranslucentColor(255);
				backGroundColor2 = getBrightTranslucentColor(255);
			}
		} else {
			backGroundColor1 = getPaleTranslucentColor(255);
			backGroundColor2 = getDarkTranslucentColor(255);
		}

		backGroundShapesColor = new Color(backGroundColor2.getRed(), backGroundColor2.getGreen(),
				backGroundColor2.getBlue(), getMinMaxRandom(50, 100));

		for (int i = 0; i < backGroundGradient.length; i++) {
			if (i % 2 == 0) {
				backGroundGradient[i] = getMaxRandom(width - i * 100);
			} else {
				backGroundGradient[i] = getMaxRandom(height - i * 100);
			}
		}
	}

	private void setShapes() {
		for (int i = 0; i < numberOfShapes * 2; i++) {
			if (i % 2 == 0) {
				shapesCoordinates.add(getMaxRandom(width));
				shapesSize.add(getMaxRandom(width));
			} else {
				shapesCoordinates.add(getMaxRandom(height));
				shapesSize.add(getMaxRandom(height));
			}
		}
	}

	private void setBackGround(Graphics2D g2d) {
		GradientPaint gp = new GradientPaint(backGroundGradient[0], backGroundGradient[1], backGroundColor1,
				backGroundGradient[2], backGroundGradient[3], backGroundColor2, false);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}

}
