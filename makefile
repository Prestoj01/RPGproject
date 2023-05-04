default: Game.java Place.java Map.java Character.java 
	javac Game.java Place.java Map.java Character.java

run: Game.class Place.class Map.class Character.class
	java Game

clean:
	rm -f *.class