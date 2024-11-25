import java.util.Stack;
import java.util.TreeMap;

import edu.princeton.cs.algs4.Queue;



public class BreadthFirstSearch {

    private TreeMap<Board, Board> edgeTo;

    public BreadthFirstSearch() {
        edgeTo = new TreeMap<Board, Board>();
        bfs();
    }

    private void bfs() {
        Queue<Board> q = new Queue<Board>();
        Board b = new Board(0);
        edgeTo.put(b, null);
        q.enqueue(b);

        while (!q.isEmpty()) {
            b = q.dequeue();
            for (byte i = 0; i < 6; i += 1) {
                Board temp = new Board (b.board);
                if (temp.move(i) && !edgeTo.containsKey(temp)) {
                    edgeTo.put(temp, b);
                    q.enqueue(temp);
                }
            }
        }
    }

    public byte[] pathTo(Board b) {
        Stack<Byte> path = new Stack<Byte>();
        while (edgeTo.get(b) != null) {
            Byte x = b.findMove(edgeTo.get(b));
            path.push(x);
            b = edgeTo.get(b);
        }

        int size = path.size();
        byte[] solvedPath = new byte[size]; //creates a new byte array based on size of path

        int i = 0;
        for (byte x : path) {
            solvedPath[i] = x;
            i += 1;
        }

        return solvedPath;
    }
}
