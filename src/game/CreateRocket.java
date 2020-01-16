package game;


import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Child class for Actions, represents the creating rocket action
 */
public class CreateRocket extends Action{

	private Location RocketLocation;
	private String direction;
	
	/**
	 * drop a rocket when play drop Engine and body on pad 
	 * 
	 * @param location the destination
	 * @param Direction String representing the direction of the other Actor
	 */
	public CreateRocket(Location location,String Direction) {
		
		this.RocketLocation = location;
		this.direction =  Direction;
	}
	
	/**
	 * Testing the player has enough RocketPart or not and return a result 
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor,GameMap map) {
		int NumberOfPart = 0;
		List<Item> itemList = map.at(RocketLocation.x(),RocketLocation.y()).getItems();
		for(Item item : itemList) {
			if (item.getDisplayChar() == 'B' || item.getDisplayChar() == 'E') {
				NumberOfPart += 1;
				
			}
		}
		if (NumberOfPart == 2) {
			Item Rocket = Item.newFurniture("Rocket",'R');
			Rocket.getAllowableActions().add(new MoveActorAction(((PlayerStatus)actor).MoonMap.at(0, 0),"TO Moon"));
			map.add(new Floor(), RocketLocation);
			map.addItem(Rocket, RocketLocation.x(), RocketLocation.y());
			return "Combining succeeded!, you have WON the game!";
		}
		else if (NumberOfPart == 1) {
			String ContainTest = "";
			for (Item Item : itemList) {
				ContainTest += Item.getDisplayChar();
			}
			if (!ContainTest.contains("B")){
				return "You do not drop the RocketBoby!";
			}
			if(!ContainTest.contains("E")) {
				return "You do not drop the RocketEngine";
			}
			}
		
		return "You didn't drop any RocketPart!";
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " put the RocketBoby and RocketEngine on the pad to build the Rocket! pad at " + direction;
	}
	/**
	 * Returns the key used in the menu to trigger this Action.
	 *
	 * There's no central management system for this, so you need to be careful not to use the same one twice.
	 * See https://en.wikipedia.org/wiki/Connascence
	 *
	 * @return The key we use for this Action in the menu.
	 */
	@Override
	public String hotKey() {
		return "";
	}
}
	