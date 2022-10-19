package todoList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Objects;

/**
 * class Todo, representing a task that user stores in a todo application
 */
public class Todo {

  private String description;
  private Boolean isCompleted;
  private LocalDate dueDate;
  private Integer priority;
  private String category;
  private Integer id;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  /**
   * Constructor for class Todo
   * @param id - Integer, representing the id of the todo
   * @param description - String, a description of the task to be done. This field is required
   * @param isCompleted - Boolean, indicates whether the task is completed or incomplete
   * @param dueDate - LocalDate, due date
   * @param priority - Integer, indicates the priority of the todo
   * @param category - String, used to group related todos
   */
  public Todo(Integer id,String description, Boolean isCompleted, LocalDate dueDate, Integer priority,
      String category) {
    this.description = description;
    this.isCompleted = isCompleted;
    this.dueDate = dueDate;
    this.priority = this.validatePriority(priority); //need to validate
    this.category = category;
    this.id = id;
  }

  /**
   * Constructor for class Todo
   */
  public Todo() {
    this.isCompleted = false;
    this.priority = 3;
  }

  /**
   * Helper method that checks if the priority is between 1 and 3 (including them)
   * @param priority - Integer, indicates the priority of the todo
   * @return priority - Integer, indicates the priority of the todo if it is between 1 and 3,
   * otherwise throws the IllegalArgumentException
   */
  private Integer validatePriority(Integer priority) {
    if (priority < 1 || priority > 3)
      throw new IllegalArgumentException("Priority must be between 1 and 3");
    else
      return priority;
  }

  /**
   * @return description - String, a description of the task to be done. This field is required
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * @return isCompleted - Boolean, indicates whether the task is completed or incomplete
   */
  public Boolean getCompleted() {
    return this.isCompleted;
  }

  /**
   * @return dueDate - LocalDate, due date
   */
  public LocalDate getDueDate() {
    return this.dueDate;
  }

  /**
   * @return priority - Integer, indicates the priority of the todo
   */
  public Integer getPriority() {
    return this.priority;
  }

  /**
   * @return category - String, used to group related todos
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * @return ID - Integer, representing the id of the todo
   */
  public Integer getId() {
    return this.id;
  }

  /**
   * Changes the status of the task to completed
   */
  public void setCompleted() {
    this.isCompleted = true;
  }

  /**
   * nested class TodoGenerator, generates a Todo object
   */
  public static class TodoGenerator {
    private HashMap<String, String> todoArguments;
    private Todo todo = new Todo();
    private Integer id;

    /**
     * Constructor of the TodoGenerator class
     * @param todoArguments - HashMap<String, String>, stores the arguments parsed
     * @param id - Integer, represents the unique id of the todo
     */
    public TodoGenerator(HashMap<String, String> todoArguments, Integer id) {
      this.todoArguments = todoArguments;
      this.todo.id = id + 1;
      generateTodo();
    }

    /**
     * Generates a new Todo Object based on the arguments given
     */
    private void generateTodo() {
      if (todoArguments.containsKey("--todo-text")) {
        todo.description = todoArguments.get("--todo-text");
      }
      if (todoArguments.containsKey("--is-completed")) {
        todo.isCompleted = Boolean.parseBoolean(todoArguments.get("--is.completed"));
      }
      if (todoArguments.containsKey("--due")) {
        todo.dueDate =  LocalDate.parse(todoArguments.get("--due"), DATE_FORMATTER);
      }
      if (todoArguments.containsKey("--priority")) {
        todo.priority = todo.validatePriority(Integer.parseInt(todoArguments.get("--priority")));
      }
      if(todoArguments.containsKey("--category")) {
        todo.category = todoArguments.get("--category");
      }
    }

    /**
     * @return - representing the todo object generated
     */
    public Todo getTodo() {
      return todo;
    }
  }

  @Override
  public String toString() {
    return id +
        "\",\"" + description  +
        "\",\"" + isCompleted +
        "\",\"" + dueDate +
        "\",\"" + priority +
        "\",\"" + category;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Todo)) {
      return false;
    }
    Todo todo = (Todo) o;
    return Objects.equals(getId(), todo.getId());
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(getDescription(), isCompleted, getDueDate(), getPriority(), getCategory(), getId());
  }
}