package boardMenu;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.Posn;

/**
 * Creates the BoardMenuPicturePanel with two different tile images that are used. It resizes the 
 * game map based on ratios that are calculated from the dimensions of the screen. It adds the 
 * appropriate number of tiles to make the image that is read in from the textFile.
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class BoardMenuPicturePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static final String FILENAME = "path.txt";
	
	ArrayList<Posn> points = new ArrayList<Posn>();
	ArrayList<Posn> posns = new ArrayList<Posn>();
	
	private int tileWidth;
	private int tileHeight;
	public 	int screenHeight;
	public int screenWidth;
	
	private BufferedImage grassTileImage;
	private BufferedImage pathTileImage;
	private BufferedImage grassBack;
	
	private boolean isFirstPaint = true;
	
	BufferedImage mapImage;
	
	Dimension centerPanelSize;
	/**
	 * Creates a BoardMenuPicturePanel object to use for the game map with the center panel 
	 * preferred size dimensions that are passed in. Reads in the images needed to create the 
	 * game map on the screen based on the screen size. Sets up parameters that are used to
	 * calculate different sizes of the tiles that are squared up. 
	 * 
	 * @param cps the preferredSize of the centerPanel that is passed in
	 */
	public BoardMenuPicturePanel(Dimension cps) {
		this.tileWidth = 50;
		this.tileHeight = 50;
		screenHeight = (int) (cps.getHeight());
		screenWidth = (int) (cps.getWidth());
		
		try {
			grassTileImage = ImageIO.read(new File("grassTile.png"));
			pathTileImage = ImageIO.read(new File("pathTile.png"));
			grassBack = ImageIO.read(new File("grassBackground6.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Paints the map onto the screen based on a series of conditions that are met in
	 * tiling the map. Resizes the number of tiles needed to fill in the entire center panel 
	 * based on ratios that are calculated. 
	 * 
	 * @param g the graphics class that allows a user to paint onto a panel or frame 
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(grassBack, 0, 0, null);
		FileReader fr = null;
		BufferedReader br = null;
		// TODO don't hardcode this, counters should vary on dimension size
		try {

			fr = new FileReader(new File(FILENAME));
			br = new BufferedReader(fr);

			String sCurrentLine;
			
			ArrayList<String> lineRead = new ArrayList<>();
			
			while ((sCurrentLine = br.readLine()) != null) {
				lineRead.add(sCurrentLine);
				//System.out.println(lineRead);
			}
			//needs an 8*8 text file
			// going to call "tiles" blocks because I'm thinking of the path as tiles
			int hCount = Math.floorDiv(screenHeight,50); //num blocks that fit on height
			int wCount = Math.floorDiv(screenWidth,50); //num blocks  that fit on width
			int wBlock = Math.floorDiv(wCount, 8); //scaling height
			int hBlock = Math.floorDiv(hCount, 8); // scalling width
			int LRPad = (wCount -(wBlock*8))/2; //Left and Right side grass pad amount to preserve ratio
			int[][] IDK = new int[hCount][wCount];
			
			//Pad Everything
			for (int i = 0; i < hCount; i++) {
				for (int j = 0; j < wCount; j++) {
					IDK[i][j] = 0;
				}
			}
			for (int row = 0; row < 8; row++) {
				for (int lineCounter = 0; lineCounter < 8; lineCounter++) {
					if (lineRead.get(row).charAt(lineCounter) == '0') {
						for (int k = 0; k < wBlock; k++) {
							for (int n = 0; n < hBlock; n++) {
								IDK[lineCounter * hBlock + n][row * wBlock + k] = 0;
							}
						}
					} else {
						for (int k = 0; k < hBlock; k++) {
							for (int n = 0; n < wBlock; n++) {
								IDK[lineCounter * hBlock + n][row * wBlock + k] = 1;
							}
						}
					}
				}
			}
			//Clean Up (stepping stone issue)
			for (int i = 2; i < hCount; i++) {
				for (int j = 2; j < wCount - 2; j++) {
					if (IDK[i][j] == 0) {
						if (IDK[i][j - 2] == 1 && IDK[i][j + 2] == 1) {
							IDK[i][j] = 1;
						}
					}
				}
			}
			
			for (int i = 0; i < hCount; i++) {
				for (int j = 0; j < wCount; j++) {
					if (IDK[i][j] == 0) {
						g.drawImage(grassTileImage, (int) (.25 * screenWidth) + i * 50, j * 50, null);
					} else {
						g.drawImage(pathTileImage, (int) (.25 * screenWidth) + i * 50, j * 50, null);
					}
				}
			}
			
			for (int row = 0; row < 8; row++) {
				for (int lineCounter = 0; lineCounter < 8; lineCounter++) {
					if (lineRead.get(row).charAt(lineCounter) == '0') {
						for (int k = 0; k < wBlock; k++) {
							for (int n = 0; n < hBlock; n++) {
								IDK[lineCounter * hBlock + n][row * wBlock + k] = 0;
								// System.out.print(" X:" + (row*wBlock + k) + "Y:" + (lineCounter*hBlock + n));
							}
						}
					} else {
						for (int k = 0; k < hBlock; k++) {
							for (int n = 0; n < wBlock; n++) {
								IDK[lineCounter * hBlock + n][row * wBlock + k] = 1;
								// System.out.print(" X:" + (row*wBlock + k) + "Y:" + (lineCounter*hBlock + n));
							}
						}
					}
				} //System.out.println();
			}
			//Clean Up (stepping stone issue)
			for(int i = 2; i < hCount; i++){
				for(int j= 2; j < wCount-2; j++){
					if( IDK[i][j] == 0){
						if( IDK[i][j-2] == 1 && IDK[i][j+2] == 1){
							IDK[i][j] = 1;
						}
					}
				}
			}
			
			
			if(isFirstPaint) {
				for(int i = 0; i < hCount; i++){
					for(int j = 0; j < wCount; j++){
						if(IDK[i][j] == 0){
							//System.out.print(IDK[i][j]);
							g.drawImage(grassTileImage, (int)(.25*screenWidth)+ i*50, j*50, null);
							//System.out.print("  Grass Tile  " );
							
						}
						else{
							//System.out.print(IDK[i][j]);
							g.drawImage(pathTileImage, (int)(.25*screenWidth)+i*50, j*50, null);
							points.add(new Posn((int)(.25*screenWidth)+ i*50,j*50));
							//System.out.print(" X:"+ ((int)(.25*screenWidth)+i*50) + " Y:" + j*50 + " " );
						}	
					}
				}
				
				
				int xAvg = 0;
				int yAvg = 0;
				for (int i = 0; i < points.size(); i++) {
					xAvg += points.get(i).getXCor();
					yAvg += points.get(i).getYCor();
					if (i % hBlock == 0) {
						posns.add(new Posn(xAvg / hBlock, yAvg / hBlock));
						xAvg = 0;
						yAvg = 0;
					}
				}
				isFirstPaint = false;
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public ArrayList<Posn> getPosns() {
		return posns;
	}
}
