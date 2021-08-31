import javax.swing.*;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Square extends JButton {
	protected int x;
	protected int y;
	protected int data;
	protected boolean hidden;
	protected boolean toggle;
	public Square (int uX, int uY) {
		super();
		x = uX;
		y = uY;
		this.setBounds(x*Board.tileSize, y*Board.tileSize+Board.topGap, Board.tileSize, Board.tileSize);
		this.setMargin(new Insets(0, 0, 0, 0));
		this.setEnabled(false);
		hidden = true;
		//this.setFont(new Font("Helvetica", Font.PLAIN, 30));
	}

	public void reveal() {
		if (data == -1)
			this.setText("*");
		else if (data == 0)
			this.setText("");
		else
			this.setText(data+"");
		this.setEnabled(false);
		hidden = false;
	}

	public void toggle() {
		if (!(toggle))
			this.setText("⚐");
		else
			this.setText("");

		toggle = !toggle;
	}
}