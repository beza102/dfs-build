import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    Set<Vertex<String>>  visited = new HashSet<>();
    printShortWords(vertex, k, visited);

  }
  //helper
  public static void printShortWords(Vertex<String> vertex, int k, Set<Vertex<String>> visited) {
    if (vertex == null || visited.contains(vertex)) return;

    //mark visited
    visited.add(vertex);
    String value = vertex.data;
    //if length of word> k print
    if (value.length() < k) {
      System.out.println(value);
    }

    for (Vertex<String> neighbor : vertex.neighbors) {
      printShortWords(neighbor, k, visited);
    }
  }


  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    if(vertex == null) return "";
  
    Set<Vertex<String>> visited = new HashSet<>(); 

    return longestWord(vertex, visited);
  }

  // Helper 
  public static String longestWord(Vertex<String> vertex, Set<Vertex<String>> visited) {
    if (vertex == null || visited.contains(vertex)) return ""; 

    visited.add(vertex); 

    String longest = vertex.data;          

    // check all neighbors for longer words
    for (Vertex<String> neighbor : vertex.neighbors) {
      String word = longestWord(neighbor, visited);
      if (word.length() > longest.length()) {
        longest = word;               
    }
  }
    return longest;
  }
  

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    Set<Vertex<T>> visited = new HashSet<>();
    printSelfLoopers(vertex, visited);
  }

  //helper
  public static<T> void printSelfLoopers(Vertex<T> vertex, Set<Vertex<T>> visited) {
    if (vertex == null || visited.contains(vertex)) return; 

    visited.add(vertex); 

    // Check if vertex appears in its own neighbors 
    if (vertex.neighbors.contains(vertex)) {
      System.out.println(vertex.data);
    }

    for (Vertex<T> neighbor : vertex.neighbors) {
      printSelfLoopers(neighbor, visited);
    }
  }
  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    if (start == null || destination == null) return false; 

    if (start == destination) return true; 

    Set<Airport> visited = new HashSet<>(); 
    return canReach(start, destination, visited);
  }

  // Helper
  public static boolean canReach(Airport current, Airport destination, Set<Airport> visited) {
    if (current == null || visited.contains(current)) return false;

    if (current == destination) return true; 
    visited.add(current); 

    
    for (Airport neighbor : current.getOutboundFlights()) {
      if (canReach(neighbor, destination, visited)) {
        return true; 
      }
    }
    return false; 
  
  }

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    Set<T> visited = new HashSet<>(); 

    
    if (!graph.containsKey(starting)) {
      return new HashSet<>(graph.keySet());
    }

    
    unreachable(graph, starting, visited);

    Set<T> unreachable = new HashSet<>(graph.keySet()); 

    unreachable.removeAll(visited);    

    return unreachable;                                 
  }
  // Helper 
  public static <T> void unreachable(Map<T, List<T>> graph, T current, Set<T> visited) {
    if (visited.contains(current)) return; 

    visited.add(current); 
    // visit all neighbors recursively
    for (T neighbor : graph.getOrDefault(current, List.of())) {
      unreachable(graph, neighbor, visited);
    }
  }
}
