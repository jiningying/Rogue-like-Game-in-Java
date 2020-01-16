
package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.SkipTurnAction;

/**
 * Child Class to represent the Player.
 */
//class to enable to change status on the player
public class PlayerStatus extends Player{

	private int stunRoundCount = 0;
	private int oxygenRoundCount = 0;
	private int oxygen = 0;
	private boolean isStunned = false;
	public GameMap MoonMap;
	public GameMap EMap;
	public GameMap CurrentMap;
	private boolean quitOptionSelected = false;
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param priority How early in the turn the player can act
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public PlayerStatus(String name, char displayChar, int priority, int hitPoints , GameMap MoonMap , GameMap EarthMap) {
		super(name, displayChar, priority, hitPoints);
		this.MoonMap = MoonMap;
		this.EMap = EarthMap;
		this.CurrentMap = EarthMap;
	}
	
	/**
	 * Check if player has already been stunned
	 * @return true - if player is stunned and false for they are not
	 */
	public boolean checkIfStunned() {
		return stunRoundCount > 0;
		
	}
	/**
	 * Add count to 2 if they are not stunned
	 */
	public void addTwoRounds() {
		if (!checkIfStunned()){
			stunRoundCount += 2;
		}
	}
	/**
	 * to decrease one round each time SkipTurnAction() is called
	 */
	public void OneRoundGone() {
		stunRoundCount -= 1;
		}
	
	/**
	 * Check if player has sufficient oxygen
	 * @return true - if player has oxygen count that > 0
	 */
	public boolean checkSufficientOxygen() {
		return oxygen > 0;
	}
	
	/**
	 * Check if player has the option to create tank
	 * @return true - if oxygenRoundCount is 0 indicating the building process for tank hasn't started
	 */
	public boolean canCreateTank() {
		return oxygenRoundCount == 0;
	}
	
	/**
	 * Start building the tank, increase oxygen round count by 1
	 */
	public void tankBuilding() {
		oxygenRoundCount += 1;
	}
	
	/**
	 * Check if the tank has finished building
	 * @return true - if oxygenRoundCount is 2 which is total amount of rounds needed to build tank
	 */
	public boolean isTankBuildingFinished() {
		return oxygenRoundCount == 2;
	}
	
	/**
	 * Check if the tank is in the process of being built
	 * @return true - if oxygenRoundCount is 1 or 2
	 */
	public boolean duringTankBuilding() {
		return oxygenRoundCount == 1 || oxygenRoundCount == 2;
	}
	
	/**
	 * Reset oxygen round count to make tank be able to rebuild
	 */
	public void tankRebuildingEnabled() {
		oxygenRoundCount = 0;
	}
	
	/**
	 * Add oxygen of 10 units 
	 */
	public void addOxygen() {
		oxygen += 10;	
	}
	
	/**
	 * consume oxygen of 1 unit 
	 */
	public void consumeOxygen() {
		oxygen -= 1;	
	}
	
	
	/**
	 * build tank on the current map and adding PickUpFilledTank action to it
	 * @param map the current map in the game
	 */
	public void buildTank(GameMap map) {
		
		//create new item with it's own available actions
		Item oxygenTank = Item.newFurniture("Oxygen Tank", 'T'); //made it cannot be picked up at the first time -- when button was just pressed
		map.addItem(oxygenTank,map.locationOf(this).x(), map.locationOf(this).y());  //add to the same location as the oxygen dispenser (when player is on)
		
		//make the tank able to be picked up when oxygen is filled in (tank building process if finished)
		oxygenTank.getAllowableActions().add(new Actions(new PickUpFilledTank(this)));	
	
	}
	
	/**
	 * set the quitOptionSelected to be true
	 */
	public void setQuitOptionTrue() {
		quitOptionSelected = true;
	}
	
	/**
	 * check the boolean value of quitOptionSelected
	 * @return true if quitOptionSelected is true and vice versa
	 */
	public boolean isQuitOptionSelected() {
		return quitOptionSelected;
	}
	
	/**
     * Override playTurn method to get the actions from this actor
     * if no action is specifically defined for this actor, return one of the default actions randomly
     * Stunning status is checked, and if player is on the Moon oxygen status is being checked, if none oxygen is left player would be sent back to the Earth
     * And whether the tank can be built in the location of OxygenDispenser is controlled
     *
     * @param actions collection of possible Actions for this Actor
     * @param map the map containing the Actor
     * @param display the I/O object to which messages may be written
     * @return the Action to be performed
     */
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
//		System.out.println("Oxygen amount: " + oxygen);
		
		this.CurrentMap = map;
		//stun status checking -- first step
		if (checkIfStunned()) {
			//represent one round of waiting is completed
			OneRoundGone(); 
			
			return showMenu (new Actions(new SkipTurnAction()), display);// let player -- do nothing for a round
		}
		
		// building oxygen in dispenser status checking
		//check if player is possessed with oxygen first
		if (map == MoonMap) {
			//consume 1 unit of oxygen
			if (checkSufficientOxygen()) {
				consumeOxygen();
			}
			//otherwise if the player runs out of oxygen on the moon, a safety system automatically transports them back to the rocket’s location on Earth
			else {
				return new MoveActorAction(EMap.at(22, 10), "to Earth!  (You've run out of oxygen)");
			}
		}
		//if the tank has started building (buiding button has been pressed)
		if (!canCreateTank()) {
			//iterate to access each action
			for (Action actionInstance : actions) {
				if (actionInstance instanceof ProduceOxygen) //to make pressing/building tank not allowed
				actions.remove(actionInstance); //so that The button does not work while the dispenser is producing the tank, or while there is an oxygen tank in the location.
			}
			
			if (isTankBuildingFinished()) {
				buildTank(map);
			}
			else if (duringTankBuilding()){
				//indicates it's in the progress of building, each playTurn adds one more round for the oxygenRoundCount
				tankBuilding();
				//make player not able to do anything else while tank is producing oxygen
				return showMenu (new Actions(new SkipTurnAction()), display);
			}
			//indicates it's in the progress of building, each playTurn adds one more round for the oxygenRoundCount
			tankBuilding();
		}
		return showMenu (actions, display); //show Menu and enable selecting for players
	}
}
