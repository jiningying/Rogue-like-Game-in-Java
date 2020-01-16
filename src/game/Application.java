package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Player;
import edu.monash.fit2099.engine.WeaponItem;
import edu.monash.fit2099.engine.World;

/**
 * Class that functions as main function to initialise game elements and whole process
 */
public class Application {

	/**
	 * Main function runs 
	 * @param args arguments that has been put in
	 */
	public static void main(String[] args) {
		GameWorld gameWorld = new GameWorld(new Display());

		//set up map elements and basic elements which makes up the map
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Water(),new Floor(), new Wall(),new Door(),new RocketPad());
		FancyGroundFactory MoongroundFactory = new FancyGroundFactory(new Floor(), new Crater(), new Wall(),new Door());		
		GameMap gameMap;
		GameMap MoongameMap;
		
		System.out.println();
		System.out.println("Welcome to the game!");
		System.out.println("AIM: Try to explore the map and collect all parts of rockets, combining them at Rocket Pad (X)");
		System.out.println("	Fill water in the pistol that can be collected on the Moon, then battle with YugoMaxx.");
		System.out.println("	If you ever make it, don't forget to carry its body back to Earth, then you are the WINNER!");
		System.out.println("Best of luck, take care!!");
		System.out.println();
		
		//create maps for two planets
		List<String> map = Arrays.asList(
				".......................",
				"....#####....######....",
				"....#...#....#....#....",
				"....#...+....#....#....",
				"....#####....##+###....",
				".......................",
				".......................",
				".......................",
				".......................",
				">>>....................",
				">>>...................X");
		
		
		List<String> moonMap = Arrays.asList(
				"~~~~~~~~~~~~~~~~~~~~~~~",
				"~~~~......~~~~~~~~~~~~~",
				"~~~~.~~~.~~~~~~~~~~~~~~",
				"~~~~.~~~......~~~~~~~~~",
				"~~~~.....~~~~......~~~~",
				"~~~~~~~~~~~~~~~~~~~~~~~",
				"~~~~~~~~~~~~~~~~~~~~~~~",
				"~~~~.....~~~~~~~~~~~~~~",
				"~~~~.~~~~~~~~~~~~~~~~~~",
				"~~~~.....~~~~~~~~~~~~~~",
				"~~~~~~~~~~~~~~~~~~~~~~~");
		
		//add maps to the world (making world consists of maps)
		gameMap = new GameMap(groundFactory, map);
		MoongameMap = new GameMap(MoongroundFactory,moonMap);
		gameWorld.addMap(gameMap);
		gameWorld.addMap(MoongameMap);
		
		//create player and npc and other characters
		Actor player = new PlayerStatus("Player", '@', 1, 100 , MoongameMap , gameMap );
		Grunt grunt = new Grunt("Mongo", player , gameMap );
		Grunt gruntTwo = new Grunt("Norbert", player , gameMap );
		Goon goon = new Goon("Goon", player , gameMap );
		Ninja ninja = new Ninja("Ninja", player , gameMap );
		DoctorMaybe miniBoss = new DoctorMaybe("Boss", player , gameMap );
		Q q = new Q("q", player , gameMap );
		
		
		//initialise characters on the moon
		Grunt gruntMoon = new Grunt("MongoMoon", player,MoongameMap);
		Grunt gruntTwoMoon = new Grunt("NorbertMoon", player,MoongameMap);
		Ninja ninjaMoon = new Ninja("NinjaMoon",player,MoongameMap);
		Goon goonMoon = new Goon("GoonMoon",player,MoongameMap);
		FinalBoss YugoMaxx= new FinalBoss("YugoMaxx",player,MoongameMap);
		
		//create basic items on the map
		RocketPlan rocketplan = new RocketPlan("RocketPlan");
		Item rocket = Item.newFurniture("Rocket",'R');
		rocket.getAllowableActions().add(new MoveActorAction(gameMap.at(22, 10), "to Earth!"));
		Item spaceSuit = new Item("Spacesuit", 'S');
		spaceSuit.addSkill(SpaceTravelSkill.SPACETRAVELLER); //In order to move around in the moonbase, the player must be wearing a spacesuit -- to give them the skill to traveling in space
		OxygenDispenser oxygenDispenser = new OxygenDispenser ("Oxygen Dispenser",player);
		WaterPistol WaterPistol = new WaterPistol("WaterPistol",YugoMaxx);
		
		//make player initialise start on Earth
		gameWorld.addPlayer(player, gameMap, 2, 2);
		
		//add actors on the map (Earth)
		
		
		gameMap.addActor(grunt, 0, 0);
		gameMap.addActor(gruntTwo,  10, 10);		
		gameMap.addActor(goon, 5, 6);
		gameMap.addActor(ninja, 8, 8);
		gameMap.addActor(miniBoss, 15, 3);
		gameMap.addActor(q, 3, 10);
		
		//add items on the map (Earth)
		gameMap.addItem(rocketplan,5,3);
		gameMap.addItem(spaceSuit,10,2);
		gameMap.addItem(oxygenDispenser,11,7);

		
		//add actors on the map (Moon)
		
		MoongameMap.addActor(gruntMoon, 0, 1);
		MoongameMap.addActor(gruntTwoMoon, 10, 10);
		MoongameMap.addActor(goonMoon, 5, 6);
		MoongameMap.addActor(ninjaMoon, 8, 8);	
		MoongameMap.addActor(YugoMaxx, 20, 10);
		
		//The enemies on the moonbase don’t need extra oxygen because they have cybernetic implants that provide their bodies with energy.
		gruntMoon.addSkill(SpaceTravelSkill.SPACETRAVELLER);
		gruntTwoMoon.addSkill(SpaceTravelSkill.SPACETRAVELLER);
		goonMoon.addSkill(SpaceTravelSkill.SPACETRAVELLER);
		ninjaMoon.addSkill(SpaceTravelSkill.SPACETRAVELLER);
		YugoMaxx.addSkill(SpaceTravelSkill.SPACETRAVELLER);
		YugoMaxx.addSkill(Exoskeleton.Exoskeleton);
		
		//add items on the map (Moon)
		MoongameMap.addItem(rocket, 0, 0);
		MoongameMap.addItem(WaterPistol,7,7);
		
		//execute		
		gameWorld.run();
	}
}