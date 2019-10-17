package arjuna.tpi.guiauto.helpers;

import java.util.*;

import arjuna.tpi.enums.Key;

public class Keyboard {
	
	public static class KeyChord {
		private List<String> parts = new ArrayList<String>();

		public KeyChord text(String text) {
			this.parts.add(text);
			return this;
		}
		
		public KeyChord key(Key key) {
			this.parts.add(key.toString());
			return this;
		}

		public List<String> getChordParts(){
			return parts;
		}
	}
	
	public static KeyChord chord() {
		return new KeyChord();
	}

}
