package commandLineParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * class CommandLine, provides parsing for command line options
 */
public class CommandLine {

  private HashMap<String, String> todoArguments = new HashMap<>();
  private HashMap<String, String> inputCommands = new HashMap<>();
  private List<Integer> todoToComplete = new ArrayList<>();
  private List<String> requiredCommands;
  private String csvFile;


  /**
   * Constructor for class CommandLine
   */
  public CommandLine() { }

  /**
   * Parse the arguments according to the specified options.
   * @param validOptions - Options, representing the valid command options
   * @param args - String[] -
   */
  public void parser(Options validOptions, String[] args) {
    List<String> commands =  validOptions.optCommands();
    this.requiredCommands = validOptions.requiredOptions();

    for (int i = 0; i< args.length; i++){
      if (commands.contains(args[i])) { // If this command is an option
        Option command = validOptions.getCommandOption(args[i]); // get that command option

        // If arguments are expected with that command, retrieve and store arguments
        if (command.getHasArgs()) {
          String argument = extractArguments(args, i+1);

          // Add TodoList ID to complete later
          if (command.getOpt().equals("--complete-todo")) {
            System.out.println("Added todo to complete - " + argument);
            todoToComplete.add(Integer.parseInt(argument)); }

          // Store argument value
          if (command.getOpt().equals("--csv-file")) {
            this.csvFile = argument;
          } else {
            todoArguments.put(command.getOpt(), argument);
          }

        }
        //Save command to validate later in case this command needs another command to be valid
        inputCommands.put(command.getOpt(), command.getRequiredOption());
      }
    }
    // Perform validations
    verifyArgumentProvided();
    verifyValidCommands();
    verifyRequiredCommands();
    verifyOneSortOnly();
  }


  /**
   * Helper method that returns the argument(s) value of a command option
   *
   * Since the value arguments of an option could have more than one word then it retrieves all the
   * input words followed by that command until another command is found or the end of the command
   * line args is reached
   *
   * @param args - String[], representing the command line args
   * @param index - Integer, representing the index to start getting arguments values
   * @return
   */
  private String extractArguments(String[] args, Integer index) {
    String arguments = "";
    if (!index.equals(args.length)) {
      while (!args[index].startsWith("--")) {
        arguments += args[index] + " ";
        index++;
        if (index.equals(args.length)) { break; }
      }
    }
    return arguments.substring(0, arguments.length()-1);
  }


  /**
   * Verifies that arguments that are required were provided
   * If they are not provided an exception is thrown
   */
  public void verifyArgumentProvided() {
    // Check TodoText is given an argument
    if (inputCommands.containsKey("--todo-text") && todoArguments.get("--todo-text").equals("")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "--todo-text provided but no description given");
    }

    // Check csvFile is given an argument
    if (csvFile.equals("")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "--csv-file provided but no file path given");
    }
  }


  /**
   * Validates if a given given command is valid or not
   *
   * In case a command needs another command along with it to be valid check that that other
   * command was given as well, If it wasn't given it's counted as an InvalidCommand and an
   * exception is thrown
   *
   * For example for "--show-incomplete" the command "--display" needs to be given as well etc.
   * Throws an exception if command is invalid
   */
  public void verifyValidCommands() {
    for (Map.Entry<String,String> entry: inputCommands.entrySet()) {
      if (entry.getValue() != null) { // if the command need to be validated
        if (!inputCommands.containsKey(entry.getValue())) {
          throw new IllegalArgumentException("\nERROR: ERROR: " + entry.getKey() +
              " is provided, but not " +  entry.getValue()+ "\n");
        }
      }
    }
  }


  /**
   * Verifies that required command options were given
   *
   * In this case the "--csv-file" is the only required command
   */
  public void verifyRequiredCommands() {
    for (String command: requiredCommands) {
      if (!inputCommands.containsKey(command)) {
        throw new IllegalArgumentException("\nERROR: ERROR: command \"" + command +
            "\" is required but was not provided\n");
      }
    }
  }


  /**
   * Verifies that only one sort option if given at a time
   * An exception is thrown if both sorts are provided
   */
  public void verifyOneSortOnly() {
    if (inputCommands.containsKey("--sort-by-date") &
        inputCommands.containsKey("--sort-by-priority")) {
      throw new IllegalArgumentException("\nERROR: ERROR: "
          + "Only one sort can be be applied at a time\n");
    }
  }


  /**
   * @return Boolean, true if --add-todo command was given
   */
  public boolean hasAddTodo() { return (this.inputCommands.containsKey("--add-todo"));}

  /**
   * @return Boolean, true if --complete-todo command was given
   */
  public boolean hasCompleteTodo() { return (this.inputCommands.containsKey("--complete-todo")); }

  /**
   * @return Boolean, true if --display command was given
   */
  public boolean hasDisplay() { return (this.inputCommands.containsKey("--display")); }

  /**
   * @return Boolean, true if --show-incomplete command was given
   */
  public boolean hasShowIncomplete() { return (this.inputCommands.containsKey("--show-incomplete")); }

  /**
   * @return Boolean, true if --show-category command was given
   */
  public boolean hasShowCategory() { return (this.inputCommands.containsKey("--show-category")); }

  /**
   * @return Boolean, true if --sort-by-date command was given
   */
  public boolean hasSortByDate() { return (this.inputCommands.containsKey("--sort-by-date")); }

  /**
   * @return Boolean, true if --sort-by-priority command was given
   */
  public boolean hasSortByPriority() { return (this.inputCommands.containsKey("--sort-by-priority")); }


  /**
   * @return - String, representing the category to show
   */
  public String getCategory() { return todoArguments.get("--show-category"); }

  /**
   * @return - HashMap, representing the argument values
   */
  public HashMap<String, String> getTodoArguments() { return todoArguments; }


  /**
   * @return - List, representing the ID of todolist to complete
   */
  public List<Integer> getTodoToComplete() { return todoToComplete; }


  /**
   * @return - String, representing the csvFile name
   */
  public String getCsvFile() { return csvFile; }

  // TODO: Add hashcode, equals & toString

}
