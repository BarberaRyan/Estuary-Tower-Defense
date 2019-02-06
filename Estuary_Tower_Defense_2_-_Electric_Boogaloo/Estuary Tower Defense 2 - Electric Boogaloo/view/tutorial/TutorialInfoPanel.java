package tutorial;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Creates the information panel located on the side panel of the game. It displays educational facts, 
 * costs, and ranges needed for the user playing the game. 
 * 
 * @author Ryan Barbera, Aaron George, Nick Norton, Thomas Pennington, Grant Zhao
 *
 */

public class TutorialInfoPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	private JPanel creaturePanel = new JPanel(); 
	private JPanel factPanel = new JPanel();
	private JPanel costPanel = new JPanel();
	private JPanel damagePanel = new JPanel();
	
	private JLabel creatureName = new JLabel("Creature Name: ");
	private JLabel creatureFact = new JLabel("Creature Fact: ");
	private JLabel creatureCost = new JLabel("Creature Cost (SandDollars): ");
	private JLabel creatureDamage = new JLabel("Creature Damage: ");
	
	private JLabel creature = new JLabel(""); 
	private JTextArea fact = new JTextArea(); 
	private JLabel cost = new JLabel(""); 
	private JLabel damage = new JLabel("");
	
	private Font firstLabelFont = new Font(Font.DIALOG, Font.PLAIN, 14);
	private Font factAreaFont = new Font(Font.DIALOG, Font.PLAIN, 12);
	private Font secondLabelFont = new Font(Font.DIALOG, Font.BOLD, 14);
		
	private final double DIVIDE_FACTOR = .40;
	
	/**
	 * Creates a BoardMenuInfoPanel object that sets up the information panel on the side panel.
	 * Sets the appropriate sizes and layouts needed for a user friendly layout to display important 
	 * game information. 
	 */
	public TutorialInfoPanel(){
		setPreferredSize(new Dimension(260,225));
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4,1));

		creaturePanel.setLayout(new BoxLayout(creaturePanel, BoxLayout.X_AXIS));
		creaturePanel.setBackground(Color.WHITE);
		factPanel.setLayout(new BoxLayout(factPanel, BoxLayout.X_AXIS));
		factPanel.setBackground(Color.WHITE);
		costPanel.setLayout(new BoxLayout(costPanel, BoxLayout.X_AXIS));
		costPanel.setBackground(Color.WHITE);
		damagePanel.setLayout(new BoxLayout(damagePanel, BoxLayout.X_AXIS));
		damagePanel.setBackground(Color.WHITE);
		
		fact.setEditable(false); // disables the text area from being written in and overridden
		fact.setLineWrap(true);
		fact.setBackground(Color.WHITE);
		fact.setFont(factAreaFont);
		
		// sets the Fonts for the first set of labels in the grid 
		creatureName.setFont(firstLabelFont); creatureFact.setFont(firstLabelFont);
		creatureCost.setFont(firstLabelFont); creatureDamage.setFont(firstLabelFont);
		
		// sets the Fonts for the second set of labels in the grid 
		creature.setFont(secondLabelFont); 
		cost.setFont(secondLabelFont); 
		damage.setFont(secondLabelFont); 
		
		// adds the creature panel and labels to the grid
		creaturePanel.add(creatureName); 		
		creaturePanel.add(creature);
		
		// adds the fact panel and labels to the grid 
		factPanel.add(creatureFact);
		factPanel.add(fact);
		
		// adds the cost panel and labels to the grid 
		costPanel.add(creatureCost);
		costPanel.add(cost);
		
		// adds the damage panel and labels to the grid 
		damagePanel.add(creatureDamage);
		damagePanel.add(damage); 
		
		
		// adds all additional panels to the screen to give out information 
		add(creaturePanel);
		add(factPanel);
		add(costPanel);
		add(damagePanel);
	}
	
	/**
	 * Sets the creature text label equal to the creature that is selected based upon the button clicked. 
	 * 
	 * @param s the text that will be entered to set the name of the creature 
	 */
	public void setCreatureText(String s){
		creature.setText("" + s);
	}
	
	/**
	 * Sets the creature fact based upon what fact is given for a particular creature. 
	 * When a condition is met for a new line requirement, this will take care of this 
	 * automatically and properly display the fact in the text area. 
	 * 
	 * @param text the creatures fact description based on the tower that is selected
	 */
	public void setFactText(String text) {
		int textLength = text.length();
        String temp = "";
        String newSentence = "";
        
        String[] wordList = text.split(" ");
               
        for (String word : wordList) {
            if ((temp.length() + word.length()) < textLength*DIVIDE_FACTOR) {  
                temp += " " + word;
            } else {
            	newSentence += temp +"\n"; // add new line character
                temp = word;
            }
        }
		fact.setText("" + (newSentence.replaceFirst(" ", "")+ temp));
    }
	
	/**
	 * Sets the cost JLabel to a given creatures cost when a particular creature is clicked on.
	 * 
	 * @param costCreature the cost of the creature that is passed in from the towers
	 */
	public void setCost(int costCreature){
		cost.setText("" + costCreature);
	}
	
	/**
	 * Sets the damage JLabel to a given creatures damage when a particular creature is clicked on.
	 * 
	 * @param damageCreature the damage that a particular creature has from the towers
	 */
	public void setDamage(int damageCreature){
		damage.setText("" + damageCreature);
	}

}
