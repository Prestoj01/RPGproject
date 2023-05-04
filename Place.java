import java.util.LinkedHashMap;

public class Place {
    private String name;
    private LinkedHashMap<String, Character> opponents;

    public Place(String name) {
        this.name = name;
        opponents = new LinkedHashMap<String, Character>();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

    public boolean placeEmpty() {
        if (opponents.isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }

    public void listOpponents() {
        for (String name : opponents.keySet()) {
            System.out.println(name);
        }
    }

    public void addOpponent(String name, Character character) {
        opponents.put(name, character);
    }

    public void removeOpponent(String name, Character character) {
        opponents.remove(name, character);
    }

    public boolean hasOpponent (String name) {
        if (opponents.containsKey(name)) {
            return true;
        }
        else {
            return false;
        }
    }

    public Character getCharacter(String name) {
        return opponents.get(name);
    }
}