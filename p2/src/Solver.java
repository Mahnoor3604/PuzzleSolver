import java.util.Arrays;

public class Solver {
    private BreadthFirstSearch bfs;

    public Solver() {
        bfs = new BreadthFirstSearch();
    }

    public byte[] solve(byte[][] start) {
        Board b = new Board(start);

        return bfs.pathTo(b);
    }


    // Below is a toy example of how the Solver class would be used.
    public static void main(String[] args) {
        byte[][] solved = { { 0 }, { 1, 1 }, { 1, 1, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 } };

        byte[][] Easy50_05 = { { 1 }, { 1, 1 }, { 1, 0, 1 }, { 2, 2, 2, 2 }, { 3, 3, 3, 3, 3 } };

        byte[][] hard = { { 1 }, { 1, 1 }, { 2, 0, 1 }, { 2, 1, 2, 2 }, { 3, 3, 3, 3, 3 } };

        Solver solver = new Solver();
        byte[] sol = solver.solve(solved);
        System.out.println(Arrays.toString(sol));
        // Should print out []


        sol = solver.solve(Easy50_05);
        System.out.println(Arrays.toString(sol));
        // Should print out either [0, 5] or [5, 0]



        sol = solver.solve(hard);
        System.out.println(Arrays.toString(sol));
        // I believe the only solution here is [3, 5, 0, 0]
    }
}

