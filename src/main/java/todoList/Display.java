package todoList;

import java.util.ArrayList;
import java.util.List;

public class Display {

  private String[] header = {"ID", "Text", "Completed", "Due Date", "Priority", "Category"};

  public Display(List<Todo> todoList) {
    displayTodoList(todoList);
  }

  public void displayTodoList(List<Todo> todoList) {
    if (todoList.isEmpty()) {
      System.out.println("The todo list requesting to be displayed has no results");
    }
    else {
      List<String[]> todosFormatted = formatTodoList(todoList);
      System.out.format("%-5s%-35s%-15s%-15s%-15s%-15s\n", header);
      for (String[] row: todosFormatted) {
        System.out.format("%-5s%-35s%-15s%-15s%-15s%-15s\n", row);
    }
    }


  }

  public List<String[]> formatTodoList(List<Todo> todoList) {
    List<String[]> todosFormatted = new ArrayList<>();
    String[] line;
    for (Todo todo: todoList) {
      line = todo.toString().split("\",\"");
      for (int i=3; i<line.length; i++) {
        if (line[i].equals("null")) {
          line[i] = "?";
          i++;
        }
      }
      todosFormatted.add(line);
    }
    return todosFormatted;
  }

}
