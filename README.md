# GIS packman game

A packman game based on GIS.
Basically, there are fruits spread throughout a given map.  Each fruit has real life coordinates and weight. There are also packmans with coordinates and eating radius who eat the fruits. A collection of packmans and fruits is a "game". 

A game can be created by the user using the GUI for positioning fruits and packmans on the map and can be saved us a csv file.  Additionally, a saved game can be loaded from a csv file. 

When running, the game algorithm solves the game. It calculates a path for each packman so all the fruits will be eaten. 

About the algorithm-it is acceptable but not optimal. Basically each packman moves toward the nearest fruit.
 The solution (the packmans paths) can be saved as a kml file. 

The project is capable of:
- general GIS utilities and location calculations.
- load and extract data from a csv file as well as writing data to a csv or kml file.
- representing geographic coordinates on the map. (Throughout conversion from coridianates to a ratio object and from ratio object to the relative position on the window)
