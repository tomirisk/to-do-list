package commandLineParser;

import fileProcessing.CSVReader;
import todoList.Display;
import todoList.Todo;
import todoList.Todo.TodoGenerator;
import todoList.TodoList;

public class Main {

  public static void main(String[] args) {

    Options options = new Options();
    CommandLine cli = new CommandLine();

    options.addOption(new Option("--csv-file",  true, true));
    options.addOption(new Option("--add-todo", false, false, "--todo-text"));
    options.addOption(new Option("--todo-text", false, true, "--add-todo"));
    options.addOption(new Option("--completed", false, false, "--add-todo"));
    options.addOption(new Option("--due", false, true, "--add-todo"));
    options.addOption(new Option("--priority", false, true, "--add-todo"));
    options.addOption(new Option("--category", false, true, "--add-todo"));
    options.addOption(new Option("--complete-todo", false, true));
    options.addOption(new Option("--display", false, false));
    options.addOption(new Option("--show-incomplete", false, false, "--display"));
    options.addOption(new Option("--show-category", false, true, "--display"));
    options.addOption(new Option("--sort-by-date", false, false, "--display"));
    options.addOption(new Option("--sort-by-priority", false, false, "--display"));

    cli.parser(options, args);

    CSVReader csvFile = new CSVReader(cli.getCsvFile());
    TodoList todoList = new TodoList(csvFile.getTodoLists());


    if (cli.hasAddTodo()) {
      System.out.println("Adding New todo\n");
      Todo.TodoGenerator newTodo = new TodoGenerator(cli.getTodoArguments(), csvFile.getTodoLists().size());
      todoList.addToDo(newTodo.getTodo());
    }

    Display display1 = new Display(todoList.getTodoLists());

    if (cli.hasCompleteTodo()) {
      System.out.println("\nCompleting a Todo...\n");
      todoList.completeToDo(cli.getTodoToComplete());
    }

    Display display2 = new Display(todoList.getTodoLists());

    if (cli.hasDisplay()) {
       TodoList todoListToDisplay =  new TodoList(todoList.getTodoLists());

       if (cli.hasShowCategory()) {
         System.out.println("\nFiltering by Category..\n");
         todoListToDisplay = todoListToDisplay.filterCategory(cli.getCategory());
       }

      if (cli.hasShowIncomplete()) {
        System.out.println("\nFiltering by Incomplete..\n");
        todoListToDisplay = todoListToDisplay.filterIncomplete();
      }

      if (cli.hasSortByDate()) {
        System.out.println("\nSorting by Date..\n");
        todoListToDisplay = todoListToDisplay.sortByDate();
      } else {
        System.out.println("\nSorting by Priority..\n");
         todoListToDisplay = todoListToDisplay.sortByPriority();
      }

    Display display = new Display(todoListToDisplay.getTodoLists());
    }


  }

}
