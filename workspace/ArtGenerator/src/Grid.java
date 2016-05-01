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
		setShapes();
	}

	private int squareSize = 10;
	private int numberOfSquaresX = 50;
	private int numberOfSquaresY = 40;
	private int numberOfSquares = 2000;
	private List<Color> backGroundGridColors = new ArrayList<Color>();
	private int colorIndex = backGroundGridColors.size() - 1;
	private int backGroundColorIndex = getMaxRandom(colorIndex);
	private int yShapes = 0;
	private int ellipseSize = width;
	private int spaceBetweenSquares = 0;
	private int colorsVariation = getMinMaxRandom(5, 255);
	private boolean horizontalLines = getRandomBoolean();

	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackGround(g2d);
		g2d.setColor(backGroundShapesColor);
		// horizon shape
		/*for (int i = 0; i < 10; i++) {
			g2d.fillOval((int) ((width - ellipseSize) / (3.5 * i)), yShapes, (int) (ellipseSize + (35 * i)),
					(int) (ellipseSize / (1.2 * i)));
		}*/
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

	private void setShapes() {
		yShapes = getMinMaxRandom(5, height / 3);
		ellipseSize = getMinMaxRandom(width / 4, width);
	}

	private void setBackGround(Graphics2D g2d) {
		colorIndex = backGroundGridColors.size() - 1;
		g2d.setColor(backGroundGridColors.get(backGroundColorIndex));
		g2d.fillRect(0, 0, width, height);
		int largeur = numberOfSquaresY * squareSize + spaceBetweenSquares * numberOfSquaresY;
		if(horizontalLines){
			while(largeur < 500){
				spaceBetweenSquares = (spaceBetweenSquares + 1) * 2;
				largeur = numberOfSquaresY * squareSize + spaceBetweenSquares * numberOfSquaresY;
				System.out.println("Ah mais !!!");
			}
		}
		for (int i = 0; i < numberOfSquaresX; i++) {
			for (int j = 0; j < numberOfSquaresY; j++) {
				if (colorIndex >= 0) {
					g2d.setColor(backGroundGridColors.get(colorIndex));
				}
				if(horizontalLines){
					g2d.fillRect(j * squareSize + j * spaceBetweenSquares, i * squareSize + i * spaceBetweenSquares,
							squareSize, squareSize);
				} else {
					g2d.fillRect(i * squareSize + i * spaceBetweenSquares, j * squareSize + j * spaceBetweenSquares,
							squareSize, squareSize);					
				}
				colorIndex--;
			}
		}
	}

	private void setSquares() {
		squareSize = getMinMaxRandom(5, 25);
		while (width % squareSize != 0 || width % squareSize != 0) {
			squareSize = getMinMaxRandom(5, 25);
		}
		spaceBetweenSquares = getMinMaxRandom(0, 20);
		numberOfSquaresX = width / squareSize;
		numberOfSquaresY = height / squareSize;
		numberOfSquares = numberOfSquaresX * numberOfSquaresY;
	}

}
