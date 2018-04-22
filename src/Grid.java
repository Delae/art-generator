import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Grid extends Art {

	public Grid() {
		super();
		setSquares();
		setColors();
	}

	private int squareSize;
	private int numberOfSquaresX;
	private int numberOfSquaresY;
	private List<Color> backGroundGridColors = new ArrayList<Color>();
	private int colorIndex = backGroundGridColors.size() - 1;
	private int backGroundColorIndex = getMaxRandom(colorIndex);
	private int spaceBetweenSquares = 0;
	private int colorsVariation = getMinMaxRandom(5, 255);
	private boolean horizontalLines = getRandomBoolean();

	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackGround(g2d);
		g2d.setColor(backGroundShapesColor);
	}

	private void setColors() {
		Color squareColor = getBrightTranslucentColor(255);
		for (int i = 0; i < numberOfSquaresX; i++) {
			for (int j = 0; j < numberOfSquaresY; j++) {
				squareColor = getShades(squareColor, colorsVariation);
				backGroundGridColors.add(squareColor);
			}
		}
		backGroundShapesColor = backGroundGridColors.get(getMaxRandom(backGroundGridColors.size() - 1));
		backGroundShapesColor = new Color(backGroundShapesColor.getRed(), backGroundShapesColor.getGreen(),
				backGroundShapesColor.getBlue(), 50);
		colorIndex = backGroundGridColors.size() - 1;
		backGroundColorIndex = getMaxRandom(colorIndex);
	}

	private void setBackGround(Graphics2D g2d) {
		colorIndex = backGroundGridColors.size() - 1;
		g2d.setColor(backGroundGridColors.get(backGroundColorIndex));
		g2d.fillRect(0, 0, width, height);
		int largeur = numberOfSquaresX * squareSize + spaceBetweenSquares * numberOfSquaresX;
		System.out.println("numberOfSquaresX = " + numberOfSquaresX);
		System.out.println("numberOfSquaresY = " + numberOfSquaresY);
		if(horizontalLines){
			for (int j = 0; j < numberOfSquaresY; j++) {
				for (int i = 0; i < numberOfSquaresX; i++) {
					if (colorIndex >= 0) {
						g2d.setColor(backGroundGridColors.get(colorIndex));
					}
					g2d.fillRect(i * squareSize + i * spaceBetweenSquares, j * squareSize + j * spaceBetweenSquares,
							squareSize, squareSize);
					colorIndex--;
				}
			}
		} else {
			for (int i = 0; i < numberOfSquaresX; i++) {
				for (int j = 0; j < numberOfSquaresY; j++) {
					if (colorIndex >= 0) {
						g2d.setColor(backGroundGridColors.get(colorIndex));
					}
					g2d.fillRect(i * squareSize + i * spaceBetweenSquares, j * squareSize + j * spaceBetweenSquares,
							squareSize, squareSize);
					colorIndex--;
				}
			}
		}
	}

	private void setSquares() {
		squareSize = getMinMaxRandom(5, 25);
		while (((width % squareSize) != 0) || ((height % squareSize) != 0)) {
			squareSize = getMinMaxRandom(5, 25);
		}
		System.out.println("SquareSize = " + squareSize);
		spaceBetweenSquares = getMinMaxRandom(0, 20);
		System.out.println("SpaceBetweenSquares = " + spaceBetweenSquares);
		numberOfSquaresX = width / squareSize;
		numberOfSquaresY = height / squareSize;
	}

}
