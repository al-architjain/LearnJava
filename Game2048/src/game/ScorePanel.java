package game;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

public class ScorePanel extends JPanel {
	
	final AppFrame hostGame;
	private JLabel gameIcon;
	private JLabel scoreLabelCount;
	private JPanel gameScorePanel;
	private static Integer score;
	
	ScorePanel (AppFrame game){
		hostGame = game;
		score = 0;
		setFocusable(false);
		setLayout(new GridLayout(1,2,0,0));
		setBackground(new Color(0x282C31));
		setPreferredSize(new Dimension(350, 100));
		setBorder(BorderFactory.createEmptyBorder(20,5,5,15));

		this.placeGameIcon();
		this.initScorePanel();
	}

	void placeGameIcon() {
		gameIcon = new JLabel();
		// ImageIcon image = new ImageIcon(new ImageIcon("logo2.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		// gameIcon.setIcon(image);
		gameIcon.setText("2048");
		gameIcon.setFont(new Font("Britannic Bold", Font.BOLD, 40));
		gameIcon.setForeground(new Color(0xFDBC3B));
		gameIcon.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		gameIcon.setHorizontalTextPosition(SwingConstants.CENTER);
		gameIcon.setVerticalTextPosition(SwingConstants.BOTTOM);
		// gameIcon.setIconTextGap(0);
		gameIcon.setVerticalAlignment(SwingConstants.CENTER);
		gameIcon.setHorizontalAlignment(SwingConstants.CENTER);
		add(gameIcon);
	}

	void initScorePanel() {
		gameScorePanel = new JPanel();
		gameScorePanel.setBackground(new Color(0xF4F5F7));
		gameScorePanel.setPreferredSize(new Dimension(150, 150));
		gameScorePanel.setLayout(new BorderLayout());
		gameScorePanel.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));

		JLabel scoreLabelText = new JLabel("SCORE:");
		scoreLabelText.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabelText.setFont(new Font("Britannic Bold", Font.BOLD, 25));
		gameScorePanel.add(scoreLabelText, BorderLayout.NORTH);
		
		scoreLabelCount = new JLabel(score.toString());
		scoreLabelCount.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabelCount.setFont(new Font("Britannic Bold", Font.BOLD, 25));
		gameScorePanel.add(scoreLabelCount, BorderLayout.SOUTH);

		add(gameScorePanel);
	}

	public void resetScore() {
		updateScore(0);
	}

	public void updateScore(Integer x) {
		score = x;
		scoreLabelCount.setText(score.toString());
	}

}
