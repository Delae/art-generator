import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

public class Button extends JButton implements MouseListener {

	private String name;
	private Panel panel;
	
	public Button(Panel panel, String str){
		super(str);
		this.name = str;
		this.setBackground(Color.LIGHT_GRAY);
		this.setFont(new Font("Georgia", Font.PLAIN, 15));
		this.addMouseListener(this);
		this.panel = panel;
	}
	
	public void mouseClicked(MouseEvent e) {
		panel.reinitialize();
		panel.repaint();
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}
