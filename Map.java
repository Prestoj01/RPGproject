import java.util.HashMap;

public class Map {
    HashMap<String, Place> map;
    public Map(){
        map = new HashMap<>();
    }

    public boolean hasPlace(String name) {
        if (map.containsKey(name)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean mapEmpty() {
        if (map.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
    
    //adds place to the map
    public void addPlace(Place place){
        map.put(place.getName(), place);
    }

    public void removePlace(Place place){
        map.remove(place.getName(), place);
    }

    public void listPlaces() {
        for (String name : map.keySet()) {
            System.out.println(name);
        }
    }

    //returns place
    public Place getPlace(String name){
       return map.get(name); 
    }
}