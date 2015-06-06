
correct = [[1,2,3],
           [2,3,1],
           [3,1,2]]

incorrect = [[1,2,3,4],
             [2,3,1,3],
             [3,1,2,3],
             [4,4,4,4]]

incorrect2 = [[1,2,3,4],
             [2,3,1,4],
             [4,1,2,3],
             [3,4,1,2]]

incorrect3 = [[1,2,3,4,5],
              [2,3,1,5,6],
              [4,5,2,1,3],
              [3,4,5,2,1],
              [5,6,4,3,2]]

incorrect4 = [['a','b','c'],
              ['b','c','a'],
              ['c','a','b']]

incorrect5 = [ [1, 1.5],
               [1.5, 1]]

"""
 This program takes in a sudoku puzzle as an input and returns whether the puzzle is correct or not.
 @:param inputPuzzle IT is the puzzle on which test is to be performed.
"""
def check_sudoku(inputPuzzle):
    size = len(inputPuzzle)
    count = 1
    while count <= size:
        i = 0
        while i < size:
            j = 0
            row = 0
            col = 0
            while j < size:
                if inputPuzzle[i][j] == count:
                    row = row + 1
                if inputPuzzle[j][i] == count:
                    col = col + 1
                j = j + 1
            if row != 1 or col != 1:
                return False
            i = i + 1
        count = count + 1
    return True



print check_sudoku(incorrect)
#>>> False

print check_sudoku(correct)
#>>> True

print check_sudoku(incorrect2)
#>>> False

print check_sudoku(incorrect3)
#>>> False

print check_sudoku(incorrect4)
#>>> False

print check_sudoku(incorrect5)
#>>> False

