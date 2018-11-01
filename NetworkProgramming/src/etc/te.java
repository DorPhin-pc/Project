package etc;

import java.util.HashMap;
import java.util.Iterator;

public class te {
	public static void main(String[] args) {
		HashMap<String,Integer>map = new HashMap<String,Integer>();
		map.put("c",3);
		map.put("a",4);
		
		System.out.println(map.get("a"));
		System.out.println(map.get("b"));
		
		
		Iterator mapIt = map.keySet().iterator();
		
		while(mapIt.hasNext()) {
			System.out.println(mapIt.next());
		}
	}
}
