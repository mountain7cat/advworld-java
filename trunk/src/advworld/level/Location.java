package advworld.level;

import java.util.*;

import advworld.util.Node;
import advworld.util.Utility;

public class Location {
	
	private Node<Location> myNode;
	private Vector<Path> connections = new Vector<Path>();
	private String myName;

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
		return "Room Name:   " + myName + "\n" +
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
}
