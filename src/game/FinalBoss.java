package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.AttackAction;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Child class for Actor that creats different types of actors with different functionality
 */
public class FinalBoss extends Actor {
	private GameMap gamemap;
	private Actor player;
	private int CurrentLocation;
	private Action action;
	private int count = 0;
	
	/**
	 * Constructor.
	 * Create FinalBoss including name, displayChar, priority, hitpoints
	 * Assign it with the item it has
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(FinalBoss)
	 */
	public FinalBoss(String name, Actor player,GameMap gamemap){
		super("YugoMaxx",'Y',2, 50);
		this.player = player;
		this.gamemap =gamemap;
		
	}
	
	/**
     * Composite all actions that can possibly be performed by this actor
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	@Override
	public Actions getAllowableActions(Actor target, String direction, GameMap Map) {
		Actions actionsForBoss = new Actions(); //create New Actions to make it able to add any actions Q has
		
		Actions AttackQ = new Actions(new FinalBossBehaviour(player,this));
		actionsForBoss.add(AttackQ);
	
		return actionsForBoss;
	}
	
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(5, " ");
    }
	
	/**
     * Override playTurn method to get the actions from this actor
     * if no action is specifically defined for this actor, return one of the default actions randomly
     * FinalBoss YugoMaxx to check whether player can use the squirting behaviour by pistol when they are within certain range
     * 
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		if(gamemap == ((PlayerStatus)player).CurrentMap){
			Location here = map.locationOf(this);
			Location there = map.locationOf(player);
			int CurrentLocation = distance(here,there);
			this.CurrentLocation = CurrentLocation;
			//check if player still has its position on the board
			if(this.CurrentLocation < 3) {
			for(Item i : player.getInventory()) {
				if (i.toString().equals("WaterPistol")) {
					if (count == 0) {
					this.action = new SquirtingBehaviour(this,(WaterPistol) i);
					i.getAllowableActions().add(new Actions(action));
					count += 1;
					}
				}
			}
			}
			else {
				for(Item i : player.getInventory()) {
					if (i.toString().equals("WaterPistol")) {
						if (count > 0) {
						i.getAllowableActions().remove(action);
						count = 0;
						}
					}
				}
				
			}
			for (Action actionInstance : actions) {
				if((actionInstance != null))
					return actionInstance;
			}
			return super.playTurn(actions, gamemap, display);
		}
		else {
			return new SkipTurnOtherPlanet();
		}
	}
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
	
	
