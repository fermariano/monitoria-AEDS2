import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Q03 - TP02 - AEDS II
 * 
 * @author Thomas Neuenschwander
 * @since 27/04/2024
 * 
 *        [GitHub](https://github.com/thomneuenschwander)
 */
public class Q03 {

  static final String path = "";
  static final String inputBreak = "FIM";

  /**
   * Searches for a character in a list by name using a sequential search.
   *
   * This method uses a sequential search to find the first character in the list
   * whose name matches the provided name.
   *
   * @param characters The list of characters to search through.
   * @param name       The name of the character to search for.
   * @return An Optional containing the first matching character, or an empty
   *         Optional
   *         if no character with the given name is found.
   * @throws NullPointerException if the list of characters is null.
   *
   * @complexity O(n) where n is the number of characters in the list.
   *             The complexity reflects the need to scan through the list to find
   *             a match.
   */
  static boolean sequentialSearch(List<Character> characters, String name) {
    Optional<Character> characterFound = characters.stream().filter(c -> c.getName().equals(name)).findFirst();
    return characterFound.isPresent();
  }

  public static void main(String[] args) {
    var allCharacters = Character.readCSV(path);

    Scanner sc = new Scanner(System.in);
    List<Character> selectedCharacters = new ArrayList<>();

    processInput(sc, id -> {
      var character = Character.findById(allCharacters, id);
      if (character.isPresent())
        selectedCharacters.add(character.get());
    });
    processInput(sc, name -> {
      if (sequentialSearch(selectedCharacters, name))
        System.out.println("SIM");
      else
        System.out.println("NAO");
    });

    sc.close();
  }

  static void processInput(Scanner sc, Consumer<String> inputOperation) {
    String input;
    while (!(input = sc.nextLine()).equals(inputBreak)) {
      inputOperation.accept(input);
    }
  }
}
