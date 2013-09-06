//package Test;
//
//import game.Entities.Zombie;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class ZombieTester{
//	Zombie zombie = new Zombie();
//	@Test
//	public void testZombieCanUpdate(){
//		zombie.update();
//	}
//	@Test
//	public void canDie(){
//		zombie.health = 5;
//		zombie.takeDamage(10);
//		assertEquals(zombie.isDead, true);
//	}
//	@Test
//	public void canTakeDamage(){
//		zombie.health = 50;
//		zombie.takeDamage(10);
//		assertEquals(zombie.health, 40);
//	}
//	@Test
//	public void testWillMoveTowardsPlayer(){
//		zombie.canSeePlayer = true;
//		zombie.isInRangeToAttackPlayer = false;
//		zombie.updateState();
//		assertEquals(zombie.thoughts, "Moving To Player");
//	}
//	@Test
//	public void testWillAttackPlayer(){
//		zombie.canSeePlayer = true;
//		zombie.isInRangeToAttackPlayer = true;
//		zombie.updateState();
//		assertEquals(zombie.thoughts, "Attacking");
//	}
//}
