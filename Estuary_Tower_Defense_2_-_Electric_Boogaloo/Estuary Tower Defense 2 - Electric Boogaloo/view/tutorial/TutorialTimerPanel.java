package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Keeps track of the time that is left in the game. It runs off of a thread so that 
 * the computers time is not the primary source of time so it can count down 1 second 
 * at a time. This places a time string and a graphic bar onto the timer panel to see 
 * the count down happen during rounds.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */
public class TutorialTimerPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private long time; // the time that is passed in
	private long roundTime;

	private static String timeString; // the time string representation of the countdown
	boolean isRoundActive;

	private Font timerFont = new Font(Font.DIALOG, Font.PLAIN, 16); // sets font

	private final int WIDTH = 115; // width of timer bar graphics
	private final int HEIGHT = 20; // height of timer bar graphics 
	private final int X = 140; // location x coordinate of graphics timer 
	private final int Y = 28; // location y coordinate of graphics timer 
	
	private final int TIMERSTRING_XCOORD = 175;
	private final int TIMERSTRING_YCOORD = 43;

	private int currentWidth = WIDTH; // current width of the timer bar measured against the moving timer
	private double currentTimeBar = 0; // total time that is still left on the timer bar 


	/**
	 * Constructor to create a TutorialTimerPanel on the side panel of the screen. 
	 * This will be used so that the user will be able to keep track of the time left 
	 * for each round when this completes the setup. Adds the appropriate labels to the 
	 * screen based on the layout that was used. 
	 */
	public TutorialTimerPanel() {
		timeString = "00:00";

		setPreferredSize(new Dimension(260, 35));
		setBackground(Color.WHITE);
		setFont(timerFont);
		setLayout(new GridLayout(1, 2));

		// adds the JLabel to the screen
		JLabel timer = new JLabel("Time Remaining: ");
		timer.setFont(timerFont);
		add(timer);
	}

	/**
	 * This takes the amount of seconds that the user enters and will convert it to
	 * a time representation in minutes and seconds for displaying on the screen. The 
	 * maximum amount of time that is able to be displayed is 99 minutes and 59 seconds. 
	 * 
	 * @param roundTime the amount of time in seconds that will appear on the screen
	 */
	public void setTime(long roundTime) {
		this.roundTime = roundTime;
		time = roundTime/33;
		currentTimeBar = time;
		long minutes = (time / 60) % 60;
		long seconds = time % 60;
		timeString = String.format("%02d:%02d", minutes, seconds);
	}

	/**
	 * Will decrease the amount of time that is left on the timer bar graphic with 
	 * each second that goes by in proportion to the time on the clock to total time started with.
	 * 
	 * @param amount the amount of time that will be burned with each time tick
	 * @param timeRatio the amount of time that was started with compared to the time left on the clock 
	 */
	public void burnTimerBar(int amount, double timeRatio) {
		currentTimeBar -= amount;
		if (currentTimeBar < 0) {
			currentTimeBar = 0;
		}
		currentWidth = (int) (timeRatio * WIDTH);
	}

	/**
	 * Paints everything onto the screen for both the time graphics bar and the time 
	 * string. Makes a call to the draw method to set up the time graphics bar on the timer 
	 * panel. Will repaint after this method is done running. 
	 * 
	 * @param g the graphic that will be drawn onto the screen for the timer panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g); // draws the timer graphic bar onto the screen for the timer panel 
		g.drawString(timeString, TIMERSTRING_XCOORD, TIMERSTRING_YCOORD); // draws to specified location of timer string
	}

	/**
	 * Draws onto the screen the timer graphic bar for timer panel and places it accordingly 
	 * to the positions that were specified.
	 * 
	 * @param g the graphic that will be drawn onto the screen for the timer panel 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(X, Y, currentWidth, HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(X, Y, WIDTH, HEIGHT);
	}
}
