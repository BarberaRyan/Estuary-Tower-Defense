package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controller.GameController;
import controller.TutorialGameController;
import main.Factory;
import main.GameModel;
import main.GameView;
import main.PlayerModel;
import main.Posn;
import main.TowerModel;

public class TutorialGameControllerTest {

	GameController gameController = new GameController();
	GameModel gameModel = new GameModel();
	TutorialGameController tutorial = new TutorialGameController(gameController);
	GameView gameView = new GameView(gameController, tutorial);
	Factory factory = new Factory();
	ArrayList<TowerModel> towerList = new ArrayList<TowerModel>();
	
	@Test
	public void isAffordableTest1(){
		PlayerModel player = new PlayerModel();
		player.setMoney(100);
		gameModel.setTowerList(towerList);
		gameModel.setPlayer(player);
		gameController.setModel(gameModel);
		
		boolean result = gameController.isAffordable(0);
		boolean result2 = gameController.isAffordable(8);

		assertTrue(result);
		assertFalse(result2);

	}
	
	@Test
	public void addTowerToModelTest1(){
		TowerModel tower = factory.makeStringTower("Generic Bird");
		Posn posn = new Posn(200,200);
		tower.setPosn(posn);
		towerList.add(tower);
		
		gameController.addTowerToModel(tower, posn);
		
		assertArrayEquals(gameController.getModel().getTowerList().toArray(), towerList.toArray());

	}
}
