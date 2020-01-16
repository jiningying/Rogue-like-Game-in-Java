package game;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.SkipTurnAction;


/**
 * Child class for Actor that creats different types of actors with different functionality
 */
public class Q extends Actor{
	private GameMap gamemap;
	private Actor player;
//	private Actor target;

	/**
	 * Constructor.
	 * Create Q including name, displayChar, priority, hitpoints
	 * Assign it with the item it has
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(Q)
	 */
	public Q(String name, Actor player,GameMap gamemap) {
		super(name, 'Q' , 5 , 100000); //maxmise the hitpoint as Q is more of a friendly character (do not want to be killed)
		this.gamemap = gamemap;
		this.player = player;
	}
	
	/**
     * Composite all actions that can possibly be performed by this actor
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	public Actions getAllowableActions(Actor target, String direction, GameMap Map) {
		Actions actionsForQ = new Actions(); //create New Actions to make it able to add any actions Q has
		if(gamemap == ((PlayerStatus)player).CurrentMap){
		Actions communicationQ = new Actions(new Communication(target));
		Actions exchangeQ = new Actions(new Exchange(player,this));
		actionsForQ.add(communicationQ);
		actionsForQ.add(exchangeQ);
	
		return actionsForQ;
		}
		else {
		actionsForQ.clear();
		actionsForQ.add(new SkipTurnAction());
		return actionsForQ;
		}
	}

	
	
	/**
     * Override playTurn method to get the actions from this actor
     * if no action is specifically defined for this actor, return one of the default actions randomly
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		//iterate to access each action
		for (Action actionInstance : actions) {
			if((actionInstance != null))
				return actionInstance;
		}
		return super.playTurn(actions, gamemap, display);
	}
}
