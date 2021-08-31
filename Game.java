import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game {
	protected Board board;
	protected static int click;
	protected static int width;
	protected static int height;
	public final static double SCALE_WIDTH = 600.0/1366;
	public final static double ASPECT_RATIO = 6.0/6;
	
	public Game() { // sets everything up, runs the program
		Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		width = (int) (r.getWidth() * SCALE_WIDTH); // scales the frame based on the screen resolution
		height = (int) (ASPECT_RATIO * width);
		board = new Board(width, height);
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(board);
		frame.pack();
		frame.setResizable(false);
		double screenWidth = r.getWidth();
		double screenHeight = r.getHeight();
		frame.setLocation((int) ((screenWidth - width) / 2), (int) ((screenHeight - height) / 2));

		
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
