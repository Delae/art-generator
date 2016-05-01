import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class Ocean extends Art {
	public Ocean() {
		super();
		setWaves();
		setPolygons(xShapes, yShapes, numberOfPolygons, polygonConfiguration, true);
	}

	private int numberOfWavesX = 5;
	private int numberOfWavesY = 10;
	private int numberOfWaves = numberOfWavesX * numberOfWavesY;
	private Color seaColor = getDarkTranslucentColor(255);
	private Color skyColor = getPaleTranslucentColor(255);
	private Color wavesColor = new Color(skyColor.getRed(), skyColor.getGreen(), skyColor.getBlue(), getMinMaxRandom(100, 200));
	private int skyHeight = getMinMaxRandom(15, height / 2);
	private List<int[]> xWaves = new ArrayList<int[]>();
	private List<int[]> yWaves = new ArrayList<int[]>();
	private int numberOfPolygons = getMinMaxRandom(1, 50);
	private int polygonConfiguration = getMaxRandom(PolygonEnum.values().length + 1);
	private List<int[]> xShapes = new ArrayList<int[]>();
	private List<int[]> yShapes = new ArrayList<int[]>();

	public void drawBackGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		setBackGround(g2d);
		g2d.setColor(wavesColor);
		for (int i = 0; i < numberOfWaves; i++) {
			g2d.fillPolygon(xWaves.get(i), yWaves.get(i), xWaves.get(i).length);
		}
		g2d.setColor(seaColor);
		for (int i = 0; i < numberOfPolygons; i++) {
			g2d.fillPolygon(xShapes.get(i),
					yShapes.get(i),
					xShapes.get(i).length);
		}
	}

	private void setBackGround(Graphics2D g2d) {
		GradientPaint gp = new GradientPaint(0, skyHeight, skyColor,
				0, skyHeight + (int )((height - skyHeight) / 4), seaColor, false);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
		g2d.setColor(skyColor);
		g2d.fillRect(0, 0, width, skyHeight);
	}

	private void setWaves() {
		numberOfWavesX = (int) ((height - skyHeight) / 30);
		numberOfWavesY = (int) ((height - skyHeight) / 20);
		numberOfWaves = numberOfWavesX * numberOfWavesY;
		for (int i = 0; i < numberOfWavesX; i++) {
			for (int j = 0; j < numberOfWavesY; j++) {
				int waveWidth = j * 20 + 25;
				int waveHeight = j + 3;
				int xMargin = getMaxRandom(100);
				int yMargin = getMaxRandom(100);
				int[] xWave = { i * width / numberOfWavesX - xMargin,
						i * width / numberOfWavesX - xMargin + waveWidth,
						i * width / numberOfWavesX - xMargin + waveWidth - (int) (waveWidth / 4),
						i * width / numberOfWavesX - xMargin + waveWidth - (int) (waveWidth * 0.75) };
				int[] yWave = { j * 10 + yMargin + waveHeight + skyHeight, j * 10 + yMargin + waveHeight + skyHeight, j * 10 + yMargin + skyHeight, j * 10 + yMargin + skyHeight };
				xWaves.add(xWave);
				yWaves.add(yWave);
			}
		}
	}
}