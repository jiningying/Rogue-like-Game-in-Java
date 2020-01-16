package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Player;

/**
 * Child class for Actions, represents the Quit Game option selected by player
 */
public class QuitGame extends Action {

    private Actor target;
    /**
     * Constructor for QuitGame class
     * @param subject the character that player reacts to
     */
	public QuitGame (Actor subject) {
		this.target = subject;
	}
	
	
	/**
	 * Perform the Action of checking whether player selected QuickGame option, and quit game if yes
	 * 
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		//if player chooses to quit during the process of game
		if ((actor instanceof Player)) {
			((PlayerStatus)actor).setQuitOptionTrue();
		}
		return "Thank you for the support, and don't be frustrated. You can always try the game again, we're ALWAYS here for you !!";
	}

	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Quit Game";
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
		return "Q";
	}
}
