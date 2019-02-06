package leaderBoard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.GameController;

/**
 * Creates the centerpanel for the leaderboard to display the top 10 scorers in
 * the game ranked from the highest score to the lowest score. The top 10
 * scorers are written to a text document and read in to keep track of these
 * scores.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant
 *         Zhao
 *
 */

public class LeaderBoardCenterPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private ArrayList<String> leaders = new ArrayList<>();
	private final String LEADERS_FILE_IN = "leaders.txt";
	private int index;

	private JPanel centerPanel = new JPanel();
	private JLabel titleLabel = new JLabel();
	private Font highScoreFont = new Font(Font.DIALOG, Font.BOLD, 14);
	private final String PHOTO_BACKGROUND = "images/LeaderBoardPhoto.jpg";
	private BufferedImage leaderboardImage;

	/**
	 * Constructor that creates a LeaderBoardCenterPanel object. It also makes a
	 * call to readLeaderBoardFile to get the data into memory so that it is
	 * accessible if a user wants to see what the current high scores are before
	 * a round is started.
	 * 
	 * @param score the current score of the player in the game 
	 */
	public LeaderBoardCenterPanel(int score) {
		// loads in the photo for the leaderboard background photo
		try {
			leaderboardImage = ImageIO.read(new File(PHOTO_BACKGROUND));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBackground(Color.LIGHT_GRAY); // sets the background of the JFrame center section
		centerPanel.setBackground(Color.WHITE); // sets the background of the center panel
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); // sets the layout of the panel
		centerPanel.setPreferredSize(new Dimension(550, 200)); // sets the preferred size of the panel

		// Sets the title of the center panel high score section to show the different parts
		String headTitle = String.format("%s%30s%40s", "Score", "Name", "Date");
		titleLabel.setFont(highScoreFont); // sets the font of the head label
		titleLabel.setText("" + headTitle); // sets the text of the head label

		centerPanel.add(titleLabel); // adds the title label to the screen
		add(centerPanel); // adds the center panel to the screen

		readLeaderBoardFile(score);
	}

	/**
	 * Reads in the high scorer file and reads in each of the scores only to
	 * determine how the scores are arranged from high to low. If the current
	 * score fits into the list, then the index of the array list is increased
	 * to say that one more record is added to the high scorers.
	 * 
	 * @param score the current score of the game when the game is over when health is out
	 */
	public void readLeaderBoardFile(int score) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(LEADERS_FILE_IN));
			String record = in.readLine();

			while (record != null) {
				leaders.add(record);
				int indexBlank = record.indexOf(" ");
				String scoreList = record.substring(0, indexBlank);
				int oldScoreList = Integer.parseInt(scoreList);
				int scoreGame = score;
				if (oldScoreList > scoreGame) {
					index++;
				}
				record = in.readLine();
			}
			in.close();
			writeToScorePanel(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes in the name of a person when entered into the input box, the high
	 * score, and the current date and formats it for easy viewing into a row in
	 * the centerpanel. If the current high score bumps out a person from the
	 * top 10 then it removes it from the array list and centerpanel. It will
	 * replace the new score in the appropriate position depending on what the
	 * score was and the inital scores that are saved.
	 * 
	 * @param score the current score of the game after the round is over
	 */
	public void writeToScorePanel(int score) {
		if (index < 10) {
			String name = JOptionPane.showInputDialog(this, "Enter Your Name", "High Scorer",
					JOptionPane.PLAIN_MESSAGE);
			Date date = new Date();
			String addRecord = String.format("%d%35s%45s", score, name, date);
			leaders.add(index, addRecord);

			if (leaders.size() > 10) {
				leaders.remove(10);
			}
			saveLeaderBoard(leaders);
		}
		displayLeaderBoard();
	}

	/**
	 * Takes in an arraylist of the top 10 scorers and writes them to a file so
	 * that the data will not be lost when the game is started and stopped. It
	 * is organized by high scores from highest to lowest.
	 * 
	 * @param leaderList
	 *            the arrayList of the top 10 high scorers in the game.
	 */
	private void saveLeaderBoard(ArrayList<String> leaderList) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(LEADERS_FILE_IN));

			for (int x = 0; x < leaderList.size(); x++) {
				out.write(leaderList.get(x));
				out.newLine();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the JLabels to attach to the panel to display each of the top 10
	 * high scorers that are in the leaders arraylist.
	 */
	private void displayLeaderBoard() {
		for (int x = 0; x < leaders.size(); x++) {
			JLabel newRecord = new JLabel();
			newRecord.setFont(highScoreFont);
			newRecord.setText("" + leaders.get(x));
			centerPanel.add(newRecord);
		}
	}

	/**
	 * Draws the loaded image onto the main frame of the leaderboard. Is sized so that 
	 * it takes up the entire screen size of the panel. 
	 * 
	 * @param g the graphics class that allows you to draw images onto screens
	 */
	public void paintComponent(Graphics g) {
		g.drawImage(leaderboardImage, 0, 0, getWidth(), getHeight(), null);
	}
}
