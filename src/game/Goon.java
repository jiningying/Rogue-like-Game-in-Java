package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Child class for Actor that creats different types of actors with different functionality
 */
public class Goon extends Actor {
	
	private ArrayList<String> insultingList = new ArrayList<String>();
	private Random rand = new Random();
	private GameMap gamemap;
	private Actor player;
	/**
	 * Constructor.
	 * Create Goon including name, displayChar, priority, hitpoints
	 * Assign it with the item it has
	 * 
	 * 
	 * @param name the name of ACTOR
	 * @param player the actor(Goon)
	 */
	public Goon(String name, Actor player,GameMap gamemap) {
		super(name, 'G', 5 , 50);
		this.player = player;
		this.gamemap = gamemap;
		this.addItemToInventory(Key.newInventoryItem("Key", 'k'));
		//behaviour that this type of character specifically has
		addBehaviour(new FollowBehaviour(player)); 
		insultingList.add("You are not that good.");
		insultingList.add("Do something meaningful, just quit the game!");
		insultingList.add("You are worse than I thought...");

	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>(); // list of Actions implemented with interface ActionFactory

	private void addBehaviour(FollowBehaviour followBehaviour) {
		actionFactories.add(followBehaviour);		
	}

	
	//twice as much damage as Grunts
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(8, "pokes");
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
		//get 10 numbers randomly to get 10% chance (of insulting)
		if(gamemap == ((PlayerStatus)player).CurrentMap){
		int randomNumber1 = rand.nextInt(9);
		int randomNumber2 = rand.nextInt(2);
		//check if it insulted (0 for success, and 1-9 for fail)
		if (randomNumber1 == 0) {
			display.println(name + " says: " +  insultingList.get(randomNumber2));
		}

		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		}
		return new SkipTurnOtherPlanet();
		}
	}



