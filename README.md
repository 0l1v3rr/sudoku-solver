# sudoku-solver
Java sudoku solver. The program reads the sudoku from a .txt file and solves it.
# Setup
- Create a .txt file
- Copy the path to the file and paste it into the code
  - Example: 
    ```java 
       public static void main(String[] args) {
          readFile("sudoku.txt");
          //......
       }
# File format example
```0 0 0 0 0 0 1 5 9
3 6 0 5 2 0 0 0 0
4 9 0 0 0 0 0 0 3
0 0 0 0 0 0 5 0 0
0 8 0 0 1 0 6 0 0
0 0 2 8 7 3 0 4 0
0 1 7 0 3 0 4 0 0
0 2 0 0 6 7 0 0 0
9 0 0 2 0 4 0 0 8
```
- There must be a space between each number
- The number of rows must match the number of columns
