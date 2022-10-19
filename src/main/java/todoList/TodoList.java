package todoList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * class TodoList, representing the list of todos
 */
public class TodoList implements ITodoList{
  private ArrayList<Todo> todoLists;

  /**
   * The constructor of the TodoList class
   * @param todoLists
   */
  public TodoList(ArrayList<Todo> todoLists) {
    this.todoLists = todoLists;
  }

  /**
   * @return todoLists - ArrayList<Todo>, list of todos
   */
  public ArrayList<Todo> getTodoLists() {
    return this.todoLists;
  }

  /**
   * Method that adds a new to do to the list
   * @param todoToBeAdded - Todo, the todo to be added to the list
   */
  public void addToDo(Todo todoToBeAdded) {
    for (Todo todo: this.todoLists) {
      if (todoToBeAdded.getId() == todo.getId())
        break;
    }
    this.todoLists.add(todoToBeAdded);
  }

  /**
   * Method that changes the status of the todo from incomplete to complete
   * @param todosToBeCompleted - List<Integer> - list of todos that should be set completed
   */
  public void completeToDo(List<Integer> todosToBeCompleted) {
    for (Integer ID: todosToBeCompleted) {
      if (ID <= todoLists.size()) {
        Todo newTodo = this.todoLists.get(ID-1);
        newTodo.setCompleted();
        this.todoLists.set(ID-1, newTodo);
      }
      else {
        System.out.println("Todo with the ID" + ID + "does not exist");
      }
    }
  }

  /**
   * Sorts the todos by date in ascending order
   * @return TodoList, sorted by date
   */
  public TodoList sortByDate() {
    ArrayList<Todo> sortedTodoList = this.todoLists;
    Collections.sort(sortedTodoList, new Comparator<Todo>() {
      public int compare(Todo o1, Todo o2) {
        if (o1.getDueDate() == null & o2.getDueDate() == null)
          return 0;
        else if (o1.getDueDate() == null)
          return 1;
        else if (o2.getDueDate() == null)
          return -1;
        return o1.getDueDate().compareTo(o2.getDueDate());
      }
    });
    return new TodoList(sortedTodoList);
  }

  /**
   * Sorts the todos by priority in ascending order
   * @return TodoList, sorted by priority
   */
  public TodoList sortByPriority() {
    ArrayList<Todo> sortedTodoList = this.todoLists;
    Collections.sort(sortedTodoList, new Comparator<Todo>() {
      public int compare(Todo o1, Todo o2) {
        return o1.getPriority().compareTo(o2.getPriority());
      }
    });
    return new TodoList(sortedTodoList);
  }

  /**
   * Filters the todo list to only include incomplete Todos
   * @return TodoList, filtered incomplete
   */
  public TodoList filterIncomplete() {
    ArrayList<Todo> filteredToDoList = new ArrayList<>();
    for (Todo toDo : this.todoLists)
      if (toDo.getCompleted().equals(false))
        filteredToDoList.add(toDo);
    return new TodoList(filteredToDoList);
  }

  /**
   * Filters the list to only include Todos with a particular category
   * @param category - String, the category of the todo
   * @return TodoList filtered by category
   */
  public TodoList filterCategory(String category) {
    ArrayList<Todo> filteredToDoList = new ArrayList<>();
    for (Todo toDo : this.todoLists)
      if (toDo.getCategory() != null)
        if (toDo.getCategory().equals(category))
          filteredToDoList.add(toDo);
    return new TodoList(filteredToDoList);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TodoList)) {
      return false;
    }
    TodoList todoList = (TodoList) o;
    return getTodoLists().equals(todoList.getTodoLists());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTodoLists());
  }

  @Override
  public String toString() {
    return "TodoList{" +
        "todoLists=" + todoLists +
        '}';
  }
}

