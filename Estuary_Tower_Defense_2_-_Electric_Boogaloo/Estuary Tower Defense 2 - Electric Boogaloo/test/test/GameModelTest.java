package test;
import main.EnemyModel;
import main.Factory;
import main.GameModel;
import main.PlayerModel;
import main.Posn;
import main.TowerModel;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameModelTest {
	
	Factory factory = new Factory();
	GameModel testModel = new GameModel();
	ArrayList<EnemyModel> enemyList = new ArrayList<EnemyModel>();
	ArrayList<TowerModel> towerList = new ArrayList<TowerModel>();
	ArrayList<Posn> posnArr = new ArrayList<Posn>();
	
	//EnemyModel enemy1 = factory.makeIntEnemy(0);
	//EnemyModel enemy2 = factory.makeIntEnemy(0);
	
	@Test
	public void isReloadingTest() {
		TowerModel tower1 = factory.makeIntTower(0);
		tower1.setReloadCount(1);
		towerList.add(tower1);
		testModel.setTowerList(towerList);	
		assertEquals("The turret should have to reload at this time and return true",testModel.isReloading(0), true);//This should 
		assertEquals("The turret's reload count should have been reduced to 0",testModel.getTowerList().get(0).getReloadCount(), 0);
		assertEquals("The turret should be ready to fire and return false",testModel.isReloading(0), false);//This should 
	}
	
	@Test 
	public void isReloadingTest2(){
		TowerModel tower1 = factory.makeIntTower(4);
		TowerModel tower2 = factory.makeIntTower(5);
		tower1.setReloadCount(2);
		tower2.setReloadCount(1);
		towerList.add(tower1);
		towerList.add(tower2);
		testModel.setTowerList(towerList);
		assertEquals("The turret's reload count should have been reduced to 0",testModel.getTowerList().get(1).getReloadCount(), 1);
		assertEquals("The turret should be ready to fire and return false",testModel.isReloading(0), true);//This should 
		}
	
	@Test
	public void resetReloadTest(){
		TowerModel tower1 = factory.makeIntTower(1);
		tower1.setReloadCount(0);
		towerList.add(tower1);
		testModel.setTowerList(towerList);
		//assertEquals("The reload count of the turret should equal 0", testModel.getTowerList().get(0).getReloadCount(), 0);
		testModel.resetReload(0);
		assertEquals("The reload count of the turret should equal it's reload time as it has been reloaded", testModel.getTowerList().get(0).getReloadCount(), testModel.getTowerList().get(0).getReloadTime());
	}
	
	@Test
	public void resetReloadTest2(){
		TowerModel tower1 = factory.makeIntTower(9);
		tower1.setReloadCount(0);
		towerList.add(tower1);
		testModel.setTowerList(towerList);
		//assertEquals("The reload count of the turret should equal 0", testModel.getTowerList().get(0).getReloadCount(), 0);
		testModel.resetReload(0);
		assertEquals("The reload count of the turret should equal it's reload time as it has been reloaded", testModel.getTowerList().get(0).getReloadCount(), testModel.getTowerList().get(0).getReloadTime());
		
		TowerModel tower2 = factory.makeIntTower(10);
		tower2.setReloadCount(0);
		towerList.add(tower2);
		testModel.setTowerList(towerList);
		//assertEquals("The reload count of the turret should equal 0", testModel.getTowerList().get(0).getReloadCount(), 0);
		testModel.resetReload(0);
		assertEquals("The reload count of the turret should equal it's reload time as it has been reloaded", testModel.getTowerList().get(0).getReloadCount(), testModel.getTowerList().get(0).getReloadTime());
	
		TowerModel tower3 = factory.makeIntTower(11);
		tower3.setReloadCount(0);
		towerList.add(tower3);
		testModel.setTowerList(towerList);
		//assertEquals("The reload count of the turret should equal 0", testModel.getTowerList().get(0).getReloadCount(), 0);
		testModel.resetReload(0);
		assertEquals("The reload count of the turret should equal it's reload time as it has been reloaded", testModel.getTowerList().get(0).getReloadCount(), testModel.getTowerList().get(0).getReloadTime());
	}
	
	@Test
	public void isInRangeTest(){
		TowerModel tower1 = factory.makeIntTower(2);
		Posn posn1 = new Posn(0,0);
		tower1.setPosn(posn1);
		towerList.add(tower1);
		testModel.setTowerList(towerList);
		
		EnemyModel enemy1 = factory.makeIntEnemy(0);
		enemy1.setPosn(posn1);
		EnemyModel enemy2 = factory.makeIntEnemy(1);
		Posn posn2 = new Posn(100000,100000);
		enemy2.setPosn(posn2);
		
		//testModel.setEnemyList(enemyList);
		assertEquals("Enemy 1 (0,0) should be in range of Turret (0,0)", testModel.isInRange(0, enemy1), true);
		assertEquals("Enemy 2 (1000000,100000) should not be in range of Turret (0,0)", testModel.isInRange(0, enemy2), false);
	}
	
	@Test
	public void isInRangeTest2(){
		TowerModel tower1 = factory.makeIntTower(8);
		Posn posn1 = new Posn(10,0);
		tower1.setPosn(posn1);
		towerList.add(tower1);
		testModel.setTowerList(towerList);
		
		EnemyModel enemy1 = factory.makeIntEnemy(0);
		enemy1.setPosn(posn1);
		EnemyModel enemy2 = factory.makeIntEnemy(1);
		Posn posn2 = new Posn(100000,100000);
		enemy2.setPosn(posn2);
		
		//testModel.setEnemyList(enemyList);
		assertEquals("Enemy 1 (0,0) should be in range of Turret (0,0)", testModel.isInRange(0, enemy1), true);
		assertEquals("Enemy 2 (1000000,100000) should not be in range of Turret (0,0)", testModel.isInRange(0, enemy2), false);
	}
	
	@Test
	public void damageEnemyTest(){
		//Returns true if enemy is dead
		TowerModel tower1 = factory.makeIntTower(3);
		tower1.setDamage(1);
		towerList.add(tower1);
		testModel.setTowerList(towerList);
		
		EnemyModel enemy1 = factory.makeIntEnemy(3);
		enemy1.setCurrentHealth(2);
		
		assertEquals("Function should return false as enemy is still alive", testModel.damageEnemy(0, enemy1), false);
		assertEquals("Enemy should have 1 health", enemy1.getCurrentHealth(), 1);
		assertEquals("Function should return true as enemy is now dead", testModel.damageEnemy(0, enemy1), true);

	}
	
	@Test
	public void damageEnemyTest2(){
		//Returns true if enemy is dead
		TowerModel tower1 = factory.makeIntTower(6);
		tower1.setDamage(1);
		towerList.add(tower1);
		
		TowerModel tower2 = factory.makeIntTower(7);
		tower2.setDamage(1);
		towerList.add(tower2);
		testModel.setTowerList(towerList);
		
		EnemyModel enemy1 = factory.makeIntEnemy(3);
		enemy1.setCurrentHealth(2);
		
		assertEquals("Function should return false as enemy is still alive", testModel.damageEnemy(0, enemy1), false);
		assertEquals("Enemy should have 1 health", enemy1.getCurrentHealth(), 1);
		assertEquals("Function should return true as enemy is now dead", testModel.damageEnemy(0, enemy1), true);
		
		assertEquals("Function should return true as enemy is still alive", testModel.damageEnemy(1, enemy1), true);
		assertEquals("Enemy should have -1 health", enemy1.getCurrentHealth(), -1);
		assertEquals("Function should return true as enemy is now dead", testModel.damageEnemy(1, enemy1), true);

	}
	
	@Test
	public void moveEnemyTest(){
		Posn posn1 = new Posn(0,0);
		Posn posn2 = new Posn(1,0);
		posnArr.add(posn1);
		posnArr.add(posn2);
		testModel.setPath(posnArr, 2);
		EnemyModel enemy1 = factory.makeIntEnemy(2);
		enemy1.setSpeed(1);
		enemy1.setPosn(posn1);
		enemy1.setPositionIndex(0);
		
		testModel.moveEnemy(enemy1);
		assertEquals("Enemy's posn index should be incrased by one", enemy1.getPositionIndex(),1);
	}
	
	@Test
	public void isOutofBoundsTest1(){
		Posn posn1 = new Posn(0,0);
		Posn posn2 = new Posn(1,0);
		posnArr.add(posn1);
		posnArr.add(posn2);
		testModel.setPath(posnArr, 2);
		EnemyModel enemy1 = factory.makeIntEnemy(4);
		enemy1.setSpeed(1);
		enemy1.setPosn(posn2);
		enemy1.setPositionIndex(1);
		assertEquals("This function should return false as it is in bounds", testModel.isOutOfBounds(enemy1),false);
		enemy1.setPositionIndex(2);
		assertEquals("This function should return true as it is out of bounds", testModel.isOutOfBounds(enemy1),true);
	}
	
	@Test
	public void isOutofBoundsTest2(){
		Posn posn1 = new Posn(0,0);
		Posn posn2 = new Posn(1,0);
		posnArr.add(posn1);
		posnArr.add(posn2);
		testModel.setPath(posnArr, 2);
		EnemyModel enemy1 = factory.makeIntEnemy(5);
		enemy1.setSpeed(2);
		enemy1.setPosn(posn2);
		enemy1.setPositionIndex(1);
		assertEquals("This function should return false as it is in bounds", testModel.isOutOfBounds(enemy1),false);
		enemy1.setPositionIndex(2);
		assertEquals("This function should return true as it is out of bounds", testModel.isOutOfBounds(enemy1),true);
	}
	
	@Test
	public void spawnEnemyTest(){
		Posn posn1 = new Posn(0,0);
		Posn posn2 = new Posn(1,0);
		posnArr.add(posn1);
		posnArr.add(posn2);
		testModel.setPath(posnArr, 2);	
		assertNotEquals("This should return an enemymodel and not ", testModel.spawnEnemy(), null);
	}
	
	@Test
	public void reducePlayerHealthTest(){
		PlayerModel player = new PlayerModel();
		player.setHealth(10);
		testModel.setPlayer(player);
		testModel.reducePlayerHealth(1);
		assertEquals("Player Health should equal 9", testModel.getPlayer().getHealth(), 9);
		testModel.reducePlayerHealth(2);
		assertEquals("Player Health should equal 7", testModel.getPlayer().getHealth(), 7);	
	}
	
	@Test
	public void reducePlayerMoneyTest(){
		PlayerModel player = new PlayerModel();
		player.setMoney(10);
		testModel.setPlayer(player);
		testModel.reducePlayerMoney(1);
		assertEquals("Player Money should equal 9", testModel.getPlayer().getMoney(), 9);
		testModel.reducePlayerMoney(2);
		assertEquals("Player Money should equal 7", testModel.getPlayer().getMoney(), 7);	
	}
	
	@Test
	public void increasePlayerMoneyTest(){
		PlayerModel player = new PlayerModel();
		player.setMoney(10);
		testModel.setPlayer(player);
		testModel.increasePlayerMoney(1);
		assertEquals("Player Money should equal 11", testModel.getPlayer().getMoney(), 11);
		testModel.increasePlayerMoney(2);
		assertEquals("Player Money should equal 13", testModel.getPlayer().getMoney(), 13);	
	}
	
	@Test
	public void gainBountyTest(){
		PlayerModel player = new PlayerModel();
		player.setMoney(10);
		testModel.setPlayer(player);
		EnemyModel enemy1 = factory.makeIntEnemy(0);
		enemyList.add(enemy1);
		testModel.setEnemyList(enemyList);
		testModel.gainBounty(0);
		assertEquals("Player's money should have increased by the bounty of the enemy",testModel.getPlayer().getMoney(),10+enemy1.getBounty());
	}
	
	
	@Test
	public void isPlayerDeadTest(){
		PlayerModel player = new PlayerModel();
		player.setHealth(1);
		testModel.setPlayer(player);
		assertEquals("Player should be alive so return false",testModel.isPlayerDead(), false);
		player.setHealth(-1);
		assertEquals("Player should be dead so return true",testModel.isPlayerDead(), true);

	}
	
	@Test 
	public void increasePlayerScoreTest(){
		PlayerModel player = new PlayerModel();
		testModel.setPlayer(player);
		player.setScore(10);
		testModel.increaseScore(14);
		
		assertEquals("Increases the player money by 10, should return 24", 24, player.getScore());
	}
}
