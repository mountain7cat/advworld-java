package advworld.level;

import java.util.*;

import advworld.util.Node;

public class Location {
	
	private Node<Location> myNode;
	private Vector<Exit> exits;
	private String myName;
	private HashMap<String,Location> hm = null;
	
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
	
	public String getName(){
		return myName;
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
		this.hm = new HashMap<String,Location>();//builds the hashmap content at runtime		
		while(!children.isEmpty()){
			Location L = children.remove(0).getData();
			hm.put(L.getName(), L);
		}
			
		return hm;
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
	 * @return String containing room description
	 */
	public String description(){
		return "no description";
	}
	
	//add exit
	public boolean addExit(Exit e){
		return exits.add(e);
	}
	
	//remove exit
	public boolean removeExit(Exit e){
		return exits.remove(e);
	}
	
	//return a Vector of exits
	public Vector<Exit> exits(){
		return exits;	
	}
}
