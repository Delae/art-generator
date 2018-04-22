import java.awt.Color;

import javax.swing.*;

public class Window extends JFrame {

	public int windowWidth = 500;
	public int windowHeight = 400;
	
	public Window(){
		this.setTitle("Art generator - by Z. Thivet");
		this.setSize(windowWidth, windowHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		Panel panel = new Panel();
		Button button = new Button(panel, "+");
		panel.setLayout(null);
		button.setBounds((int) (this.getWidth() * 0.83), (int) (this.getHeight() * 0.79), 45, 45);
		panel.add(button);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public int getWindowWidth(){
		return windowWidth;
	}
	
}