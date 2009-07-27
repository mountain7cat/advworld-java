package advworld.level;

import java.util.*;

import advworld.monsters.Monster;
import advworld.objects.Objects;
import advworld.util.Node;
import advworld.util.Utility;

public class Location {
	
	private Node<Location> myNode;
	private String myName;
	private Vector<Path> connections = new Vector<Path>();
	private Vector<Objects> myObjects = new Vector<Objects>();
	private Vector<Monster> myMonsters = new Vector<Monster>();
	
	private String[] levels = {"Room", "Building", "Town", "City", "State", "Country", "Continent", "World"};
	
	private String type = "Location";

	private HashMap<String,Location> myChildren = null;
	
	public Location(){
		myNode = new Node<Location>(this);
		myName = this.toString();
	}
	
	public Location(String name){
		setInfo(name);
	}
	
	public Location(String name, Location Parent){
		setInfo(name);
		Parent.addChild(this);
	}
	
	public Location(String name, Location Parent, Location child){
		setInfo(name);
		Parent.addChild(this);
		this.addChild(child);
	}
	
	public Location(String name, Location Parent, Iterator<Location> children){
		setInfo(name);
		Parent.addChild(this);
		while(children.hasNext())
			addChild(children.next());
	}
	
	private void setInfo(String name){
		myNode = new Node<Location>(this);
		myName = name;
	}
	
	/**
	 * execute command that is available for this location
	 * @param command the command inputed
	 */
	public void action(String command){
		//does nothing for now
	};
	
	public Node<Location> getNode(){
		return myNode;
	}
	
	public String getName() {
		return myName;
	}
	
	public String getType(){
		return type;
	}
	
	public String getTypeName(){
		return this.getType()+ " " + myName;
	}
	
	public Vector<Objects> getMyObjects(){
		return myObjects;
	}
	
	public void setName(String myName) {
		this.myName = myName;
	}
	
	/**
	 * Add a child location to this location
	 * @param child
	 * @return true if addition is successful, false otherwise
	 */
	public boolean addChild(Location child){
		return myNode.addChild(child.getNode());
	}
	
	/**
	 * get Children of location
	 * @return a Hashmap of children locations, with location name
	 * as key and location object as value.
	 * If no children, return a hashmap with no children
	 */
	public HashMap<String,Location> getChildren(){
		List<Node<Location>> children = getNode().getChildren();
		this.myChildren = new HashMap<String,Location>();//builds the hashmap content at runtime		
		while(!children.isEmpty()){
			Location L = children.remove(0).getData();
			myChildren.put(L.getName(), L);
		}
			
		return myChildren;
	}
	
	/**
	 * return a specifically named child location
	 * @param childName
	 * @return Location, if it exist, or returns null
	 */
	public Location getChild(String childName){
		return getChildren().get(childName);
	}
	
	/**
	 * set this location's parent through adding itself to parent's children
	 * list
	 * @param parent
	 */
	public void setParent(Location parent){
		parent.addChild(this);
	}
	
	/**
	 * description of room returned as String
	 */
	public String description(){
		return this.getType() + " Name:   " + myName + "\n" +
				"Exits:  " + connections.toString();
	}
	
	//add exit
	public boolean addExit(Path e){
		return connections.add(e);
	}
	
	//remove exit
	public boolean removeExit(Path e){
		return connections.remove(e);
	}
	
	//return a Vector of exits
	public Vector<Path> exits(){
		return connections;	
	}
	
	//equals function, 
	public boolean equals(Object o){
		return myName==((Location)o).getName();
	}
	
	/**
	 * used to make the "lock" function generic
	 * @return
	 */
	public String lockedDescription(){
		return myName + "is locked.";
	}
	
	/**
	 * returns the level number of the location type 0 = room, 1 = building, etc.
	 * @param locationType
	 * @return
	 */
	public int level(String locationType){
		int i;
		for(i = 0; i < levels.length; i++){
			if(locationType.equals(levels[i])){
				break;
			}
		}
		return i;
	}
	
	public void addObject(Objects item){
		myObjects.add(item);
	}
	
	public void removeObject(Objects item){
		myObjects.remove(item);
	}
	
	public void listItems(){
		System.out.print("Items:");
		Iterator<Objects> iter = myObjects.iterator();
		while(iter.hasNext()){
			Objects obj = (Objects) iter.next();
			System.out.print(" " + obj.getName());
		}
	}
	
	public void addMonster(Monster mon){
		myMonsters.add(mon);
	}
	
	public void removeMonster(Monster item){
		myMonsters.remove(item);
	}
	
	public void listMonsters(){
		System.out.print("Monsters:");
		Iterator<Monster> iter = myMonsters.iterator();
		while(iter.hasNext()){
			Monster mon = (Monster) iter.next();
			System.out.print(" " + mon.getName());
		}
	}
}
