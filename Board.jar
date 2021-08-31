import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class Board extends JPanel {
	public final static int SIZE = 16;
	public final static int MINES = 40;
	protected static Square[][] squares = new Square[SIZE][SIZE];

	protected static int topGap;
	protected static int boardSize;
	protected static int tileSize;
	protected static int numHidden;
	protected static int minesLeft;

	protected JLabel text;
	protected JButton newGame;
	protected JLabel progress;

	public Board(int width, int height) {
		this.setVisible(true);
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(null);

		boardSize = (int) (width * (SIZE*1.0)/(SIZE+4));
		//boardSize = width;
		tileSize = boardSize / SIZE;
		boardSize = tileSize * SIZE;
		topGap = height - boardSize;

		text = new JLabel();
		text.setBounds(0, 0, width, topGap);
		text.setText("Minesweeper");
		text.setVisible(true);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setVerticalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Helvetica", Font.PLAIN, (int) ((double) text.getWidth() / text.getFontMetrics(text.getFont()).stringWidth(text.getText()) * text.getFont().getSize() - 40)));
		this.add(text);


		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Square s = (Square) e.getSource();
				if (s.getText().equals("")) {
					revealNear(s.x, s.y);
					if (s.data == -1) {
						disable();
						s.setBackground(Color.RED);
						text.setText("You Lose");
						progress.setText("Mines Left: :(");
					}
					else if (numHidden == MINES) {
						disable();
						text.setText("You Win");
						progress.setText("Mines Left: :)");
					}
				}
				else
					s.setText("");

				int count = 0;
				for (int i = 0; i < SIZE; i++)
					for (int j = 0; j < SIZE; j++)
						if (squares[i][j].data == -1)
							count++;
				System.out.println(count);
				System.out.println(numHidden + "\t" + MINES + "\n");
			}
		};

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				squares[i][j] = new Square(i, j);
				Square s = squares[i][j];
				this.add(s);
				s.addActionListener(al);
				s.addMouseListener(new MouseAdapter() {
					boolean pressed;

					@Override
					public void mousePressed(MouseEvent e) {
						pressed = true;
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						if (pressed) {
							if (SwingUtilities.isRightMouseButton(e)) {
								if (s.getText().equals("⚐")) {
									s.setText("");
									progress.setText("Mines Left: " + ++minesLeft);
								}
								else {
									s.setText("⚐");
									progress.setText("Mines Left: " + --minesLeft);
								}
							}
						}
						
					}

					public void mouseExited(MouseEvent e) {
						pressed = false;
					}
					public void mouseEntered(MouseEvent e) {
						pressed = true;
					}
				});
				//squares[i][j].setForeground(Color.BLUE);

				if (squares[i][j].data == -1)
					squares[i][j].setFont(new Font("Helvetica", Font.BOLD, 30));
				else {
					squares[i][j].setFont(new Font("Helvetica", Font.PLAIN, 30));
				}
			}
		}

		ActionListener ng = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numHidden = SIZE*SIZE;
				minesLeft = MINES;
				progress.setText("Mines left: " + minesLeft);
				for (int i = 0; i < SIZE; i++)
					for (int j = 0; j < SIZE; j++) {
						squares[i][j].data = 0;
						squares[i][j].hidden = true;
					}
				text.setText("Minesweeper");

				int count = 0;
				boolean[][] mines = new boolean[SIZE][SIZE];
				while (count < MINES) {
					int temp = (int) (SIZE*SIZE*Math.random());
					int t1 = temp / SIZE;
					int t2 = temp % SIZE;
					if (!(mines[t1][t2])) {
						mines[t1][t2] = true;
						count++;
						squares[t1][t2].data = -1;
					}
				}

				boolean nl, nr, nt, nb;

				for (int i = 0; i < SIZE; i++) {
					for (int j = 0; j < SIZE; j++) {
						squares[i][j].setEnabled(true);
						squares[i][j].setText("");

						count = 0;
						nl = (j>0);
						nr = (j<SIZE-1);
						nt = (i>0);
						nb = (i<SIZE-1);

						if (nl && nt && squares[i-1][j-1].data == -1)
							count++;
						if (nl && squares[i][j-1].data == -1)
							count++;
						if (nl && nb && squares[i+1][j-1].data == -1)
							count++;
						if (nt && squares[i-1][j].data == -1)
							count++;
						if (nb && squares[i+1][j].data == -1)
							count++;
						if (nr && nt && squares[i-1][j+1].data == -1)
							count++;
						if (nr && squares[i][j+1].data == -1)
							count++;
						if (nr && nb && squares[i+1][j+1].data == -1)
							count++;
						if (squares[i][j].data != -1)
							squares[i][j].data = count;
					}
				}
			}
		};

		newGame = new JButton("New Game");
		newGame.setBounds(boardSize, topGap, width-boardSize, tileSize*3);
		newGame.setVisible(true);
		newGame.addActionListener(ng);
		this.add(newGame);

		progress = new JLabel("Mines left: " + minesLeft, SwingConstants.CENTER);
		progress.setBounds(boardSize, topGap+tileSize*3, width-boardSize, tileSize*2);
		progress.setVisible(true);
		progress.setFont(new Font("Helvetica", Font.PLAIN, 20));
		this.add(progress);
	}

	public void disable() {
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++) {
				squares[i][j].setEnabled(false);
				squares[i][j].reveal();
			}
	}

	public void revealNear(int i, int j) {
		numHidden--;
		squares[i][j].reveal();


		if (squares[i][j].data == 0) {
			boolean nl, nr, nt, nb;
			nl = (j>0);
			nr = (j<SIZE-1);
			nt = (i>0);
			nb = (i<SIZE-1);

			if (nl && nt && squares[i-1][j-1].data != -1 && squares[i-1][j-1].hidden)
				revealNear(i-1, j-1);
			if (nl && squares[i][j-1].data != -1 && squares[i][j-1].hidden)
				revealNear(i, j-1);
			if (nl && nb && squares[i+1][j-1].data != -1 && squares[i+1][j-1].hidden)
				revealNear(i+1, j-1);
			if (nt && squares[i-1][j].data != -1 && squares[i-1][j].hidden)
				revealNear(i-1, j);
			if (nb && squares[i+1][j].data != -1 && squares[i+1][j].hidden)
				revealNear(i+1, j);
			if (nr && nt && squares[i-1][j+1].data != -1 && squares[i-1][j+1].hidden)
				revealNear(i-1, j+1);
			if (nr && squares[i][j+1].data != -1 && squares[i][j+1].hidden)
				revealNear(i, j+1);
			if (nr && nb && squares[i+1][j+1].data != -1 && squares[i+1][j+1].hidden)
				revealNear(i+1, j+1);
		}
	}
}
