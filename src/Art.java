import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Art {

	protected int width = 500;
	protected int height = 400;
	protected Color backGroundColor1 = Color.RED;
	protected Color backGroundColor2 = Color.ORANGE;
	protected Color backGroundShapesColor = Color.YELLOW;
	protected int[] backGroundGradient = { 0, 0, 0, 0 };

	protected Color[] colors = new Color[] { Color.WHITE, Color.YELLOW, Color.ORANGE, Color.RED,
			new Color(250, 50, 150), // pink
			new Color(102, 0, 102), // purple
			new Color(200, 50, 120), // reddish purple
			Color.BLUE, Color.BLUE.darker(), Color.BLACK, new Color(100, 50, 0), // brown
			Color.GREEN, Color.GREEN.darker().darker(), new Color(150, 150, 0) // khaki
																				// green
	};

	protected enum BackGroundEnum {
		GRADIENT, GRID, HALO, STRIPES, OCEAN
	}

	protected enum PolygonEnum {
		RANDOM_TRIANGLE, HEXAGON, REGULAR_TRIANGLE
	}

	protected int backGroundStyleIndex = getMaxRandom(BackGroundEnum.values().length);
	protected BackGroundEnum backGroundStyle = BackGroundEnum.values()[backGroundStyleIndex];

	public Art() {
	}

	protected Color translucentColor(Color colorBefore) {
		Color colorAfter = new Color(colorBefore.getRed(), colorBefore.getGreen(), colorBefore.getBlue(),
				getMinMaxRandom(50, 250));
		return colorAfter;
	}

	protected int getMaxRandom(int maxNumber) {
		int random = (int) (Math.random() * maxNumber);
		return random;
	}

	protected int getMinMaxRandom(int minNumber, int maxNumber) {
		int random = getMaxRandom(maxNumber);
		if (random < minNumber) {
			random = minNumber;
		}
		return random;
	}

	protected int getRandom(int choice1, int choice2, int choice3) {
		int roulette = getMaxRandom(3);
		if (roulette < 1) {
			roulette = choice1;
		} else if (roulette < 2) {
			roulette = choice2;
		} else {
			roulette = choice3;
		}
		return roulette;
	}

	public BackGroundEnum getBackGroundStyle() {
		return backGroundStyle;
	}

	protected Color getPaleTranslucentColor(int transparency) {
		Color brightColor = new Color(getMinMaxRandom(150, 255), getMinMaxRandom(150, 255), getMinMaxRandom(150, 255),
				transparency);
		return brightColor;
	}

	protected Color getDarkTranslucentColor(int transparency) {
		Color darkColor = new Color(getMinMaxRandom(0, 50), getMinMaxRandom(0, 50), getMinMaxRandom(0, 50),
				transparency);
		return darkColor;
	}

	protected Color getShades(Color color, int variation) {
		int[] colors = { color.getRed(), color.getGreen(), color.getBlue() };
		for (int i = 0; i < colors.length; i++) {
			colors[i] = (int) (colors[i] + getMaxRandom(variation) - variation / 2);
			if (colors[i] > 255) {
				colors[i] = 255 - getMaxRandom(variation);
			} else if (colors[i] < 0) {
				colors[i] = getMaxRandom(variation);
			} else {
			}
		}
		Color newColor = new Color(colors[0], colors[1], colors[2]);
		return newColor;
	}

	protected Color getBrightTranslucentColor(int translucence) {
		int red = getRandom(0, 255, getMaxRandom(255));
		int green = getRandom(0, 255, getMaxRandom(255));
		int blue = getRandom(0, 255, getMaxRandom(255));
		while (red != 255 && green != 255 && blue != 255) {
			if (red != 255) {
				red = 255;
			} else if (green != 255) {
				green = 255;
			} else if (blue != 255) {
				blue = 255;
			}
		}
		Color brightColor = new Color(red, green, blue, translucence);
		return brightColor;
	}

	protected PolygonEnum getRandomPolygon() {
		int shapeIndex = getMaxRandom(PolygonEnum.values().length);
		PolygonEnum shape = PolygonEnum.values()[shapeIndex];
		return shape;
	}

	protected void setPolygons(List<int[]> xShapes, List<int[]> yShapes, int numberOfPolygons,
			int polygonConfiguration, boolean useMargins) {
		int marginRight = 0;
		int marginTop = 0;
		if (getRandomBoolean() && useMargins) {
			marginRight = getMaxRandom(width);
			if (marginRight > (int) (width / 3)) {
				marginRight = (marginRight / 3) * -1;
			}
			marginTop = getMaxRandom(height);
			if (marginTop > (int) (height / 3)) {
				marginTop = (marginTop / 3) * -1;
			}
		}
		for (int i = 0; i < numberOfPolygons * 2; i++) {
			PolygonEnum polygon = getRandomPolygon();
			switch (polygonConfiguration) {
			case 0:
				polygon = getRandomPolygon();
				break;
			case 1:
				polygon = PolygonEnum.HEXAGON;
				break;
			case 2:
				polygon = PolygonEnum.RANDOM_TRIANGLE;
				break;
			case 3:
				polygon = PolygonEnum.REGULAR_TRIANGLE;
				break;
			}
			int xRandomPosition = getMinMaxRandom(0, width) + marginRight;
			int yRandomPosition = getMinMaxRandom(0, height) + marginTop;
			int size = getMinMaxRandom(1, 4);
			switch (polygon) {
			case RANDOM_TRIANGLE:
				int[] xRandomTriangle = { getMinMaxRandom(0, 20) * size + xRandomPosition,
						getMinMaxRandom(0, 20) * size + xRandomPosition,
						getMinMaxRandom(0, 20) * size + xRandomPosition };
				int[] yRandomTriangle = { getMinMaxRandom(0, 20) * size + yRandomPosition,
						getMinMaxRandom(0, 20) * size + yRandomPosition,
						getMinMaxRandom(0, 20) * size + yRandomPosition };
				xShapes.add(xRandomTriangle);
				yShapes.add(yRandomTriangle);
				break;
			case HEXAGON:
				int[] xHexagon = { 0 * size + xRandomPosition, 6 * size + xRandomPosition, 12 * size + xRandomPosition,
						12 * size + xRandomPosition, 6 * size + xRandomPosition, 0 * size + xRandomPosition };
				int[] yHexagon = { 3 * size + yRandomPosition, 0 * size + yRandomPosition, 3 * size + yRandomPosition,
						9 * size + yRandomPosition, 12 * size + yRandomPosition, 9 * size + yRandomPosition };
				xShapes.add(xHexagon);
				yShapes.add(yHexagon);
				break;
			case REGULAR_TRIANGLE:
				int[] xRegularTriangle = { 0 * size + xRandomPosition, 8 * size + xRandomPosition,
						4 * size + xRandomPosition };
				int[] yRegularTriangle = { 0 * size + yRandomPosition, 0 * size + yRandomPosition,
						6 * size + yRandomPosition };
				xShapes.add(xRegularTriangle);
				yShapes.add(yRegularTriangle);
				break;
			}
		}
	}

	protected boolean getRandomBoolean() {
		boolean random = false;
		int tmp = getMaxRandom(2);
		if (tmp > 0) {
			random = true;
		}
		return random;
	}
}