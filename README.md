## game-of-cells
This is my java implementation of Conway's Game of Life.

Demo: https://game-of-cells.herokuapp.com/

### Features:
- Basic Convay's Game of Life rules
- You can change game configuration (edit state transitions, state names, add more states)
- You can change cell colors

### Custom formulas for state transitions:
You can use expressions like this:

```
// Will evolve if around this cell is exactly two neighbours of type "Populated cell":
count("Populated cell") == 2 

// Same rules, but only 50% chance to evolve
count("Populated cell") == 2 AND random(2) == 1

// Will evolve with 10% chance
random(100) > 90 

// Will evolve if sum of Red and Blue neighbours is 5
sum(count("Red"), count("Blue")) == 5 
```

### All available functions:
```
// Returns count of neigbours of type "Populated cell" around current cell
count("Populated cell")

// Returns random number in range [0, 10)
random(10)

// Returns sum of two or more numbers
sum(count("Red"), 1, 2)

// Returns difference between two numbers
diff(10, 5)

// Inverts logical condition
not(false)
```

### TODO:
- Import / export configuration
- Save / load game state (lexicon)
- Optimize evolve method for bigger grid

### Examples:
Basic game of life configuration:
>
![image](https://user-images.githubusercontent.com/37997797/171428174-2ed25985-52db-4589-bd55-01fdfe8beacd.png)

Configuration with 4 cell types:
>
![image](https://user-images.githubusercontent.com/37997797/171427401-9e9fc4ce-0f5c-4e2e-9346-7f64c6cc5ab2.png)
