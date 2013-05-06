package main;

import java.util.ArrayList;
import java.util.List;

public class Keys {
	public class Key {
		public final String name;
		public boolean isTapped;
		public boolean tappedDown;
		public boolean pressedDown;
		public boolean nextState;
		
		public Key(String name) {
			this.name = name;
			Keys.this.all.add(this);
		}
		
		public void tick() {
			if (isTapped) {
				tappedDown = nextState;
				pressedDown = false;
			}
			else {
				pressedDown = nextState;
				tappedDown = false;
			}
		}
	}
	
	List<Key> all = new ArrayList<Key>();
	
	public Key up = new Key("up");
	public Key down = new Key("down");
	public Key left = new Key("left");
	public Key right = new Key("right");
	
	public void tick() {
		for (Key k : all)
			k.tick();
	}
}
