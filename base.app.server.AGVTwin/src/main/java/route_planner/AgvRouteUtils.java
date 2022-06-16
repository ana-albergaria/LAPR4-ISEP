package route_planner;

import eapli.base.warehousemanagement.domain.Bin;

import java.awt.geom.Point2D;
import java.util.*;



// A queue node used in BFS
class Node
{
    // (x, y) represents coordinates of a cell in the matrix
    int x, y;

    // maintain a parent node for printing the final path
    Node parent;

    Node(int x, int y, Node parent)
    {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ')';
    }
}

public class AgvRouteUtils {
    // Below arrays detail all four possible movements from a cell
    private static int[] row = { -1, 0, 0, 1 };
    private static int[] col = { 0, -1, 1, 0 };

    // The function returns false if (x, y) is not a valid position
    private static boolean isValid(int x, int y, int N, String[][] matrix) {
        return (x >= 0 && x < N) && (y >= 0 && y < N) && (!matrix[x][y].equals("X")); //the obstacles are marked as X in the matrix
    }

    // Utility function to find path from source to destination
    private static void findPath(Node node, List<Point2D> path)
    {
        if (node != null) {
            findPath(node.parent, path);
            path.add(new Point2D.Double(node.x, node.y));
        }
    }

    // Find the shortest route in a matrix from source cell (x, y) to
    // destination cell (xDest, yDest)
    public static LinkedList<Point2D> findPath(String[][] matrix, int x, int y, int xDest, int yDest)
    {
        // list to store shortest path
        LinkedList<Point2D> path = new LinkedList<>();

        // base case
        if (matrix == null || matrix.length == 0) {
            return path;
        }

        // `N × N` matrix
        int N = matrix.length;

        // create a queue and enqueue the first node
        Queue<Node> q = new ArrayDeque<>();
        Node src = new Node(x, y, null);
        q.add(src);

        // set to check if the matrix cell is visited before or not
        Set<String> visited = new HashSet<>();

        String key = src.x + "," + src.y;
        visited.add(key);

        // loop till queue is empty
        while (!q.isEmpty())
        {
            // dequeue front node and process it
            Node curr = q.poll();
            int i = curr.x, j = curr.y;

            // return if the destination is found
            if (i == xDest && j == yDest) {
                findPath(curr, path);
                return path;
            }

            // value of the current cell
            //int n = matrix[i][j];

            // check all four possible movements from the current cell
            // and recur for each valid movement
            for (int k = 0; k < row.length; k++)
            {
                // get next position coordinates using the value of the current cell
                x = i + row[k];
                y = j + col[k];

                // check if it is possible to go to the next position
                // from the current position
                if (isValid(x, y, N, matrix))
                {
                    // construct the next cell node
                    Node next = new Node(x, y, curr);

                    key = next.x + "," + next.y;

                    // if it isn't visited yet
                    if (!visited.contains(key))
                    {
                        // enqueue it and mark it as visited
                        q.add(next);
                        visited.add(key);
                    }
                }
            }
        }

        // we reach here if the path is not possible
        return path;
    }

    public static void main(String[] args)
    {
        String[][] matrix =
                {
                        { "1", "X", "6", "5", "5", "1", "1", "1", "7", "X" },
                        { "3", "6", "2", "X", "6", "5", "7", "2", "6", "6" },
                        { "1", "3", "6", "1", "1", "1", "7", "1", "X", "5" },
                        { "7", "5", "6", "3", "1", "3", "3", "1", "1", "7" },
                        { "3", "X", "6", "X", "7", "2", "6", "5", "X", "X" },
                        { "3", "2", "5", "1", "2", "5", "1", "2", "3", "X" },
                        { "4", "2", "2", "2", "5", "2", "3", "7", "7", "3" },
                        { "7", "2", "X", "3", "5", "2", "2", "3", "6", "3" },
                        { "5", "1", "X", "2", "6", "X", "6", "7", "3", "7" },
                        { "1", "X", "1", "7", "5", "3", "6", "5", "3", "9" }
                };




        // Find a route in the matrix from source cell (0, 0) to
        // destination cell (N-1, N-1)
        LinkedList<Point2D> path = findPath(matrix, 0, 0, 3,9);


        if (path != null && path.size() > 0) {
            System.out.print("The shortest path is " + path);
        } else {
            System.out.println("Destination is not found");
        }
    }



    public static LinkedList<Point2D> computeFinalRoute(String[][] matrix, int xSource, int ySource, List<Bin> bins) {
        Bin firstBin = bins.get(0);
        int xBin = Math.toIntExact(firstBin.row().beginSquare().wSquare());
        int yBin = Math.toIntExact(firstBin.row().beginSquare().lSquare());
        int xNextBin, yNextBin;


        LinkedList<Point2D> finalRoute = findPath(matrix,xSource,ySource,xBin, yBin);

        for (int i = 0; i < bins.size()-1; i++) {
            xBin = Math.toIntExact(bins.get(i).row().beginSquare().wSquare());
            yBin = Math.toIntExact(bins.get(i).row().beginSquare().lSquare());
            xNextBin = Math.toIntExact(bins.get(i+1).row().beginSquare().wSquare());
            yNextBin = Math.toIntExact(bins.get(i+1).row().beginSquare().lSquare());
            System.out.println(xNextBin);
            System.out.println(yNextBin);
            LinkedList<Point2D> route = findPath(matrix,xBin, yBin, xNextBin, yNextBin);
            route.removeFirst();
            finalRoute.addAll(route);
        }

        xBin = Math.toIntExact(bins.get(bins.size()-1).row().beginSquare().wSquare());
        yBin = Math.toIntExact(bins.get(bins.size()-1).row().beginSquare().lSquare());
        LinkedList<Point2D> comingBackToDock = findPath(matrix, xBin, yBin, xSource, ySource);
        comingBackToDock.removeFirst();
        finalRoute.addAll(comingBackToDock);

        return finalRoute;



    }
}
