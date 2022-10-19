package todoList;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.Before;
import org.junit.Test;

public class TodoListTest {
  TodoList testTodoList;
  TodoList testTodoList2;
  ArrayList<Todo> todoLists;
  ArrayList<Todo> todoLists2;
  Todo testTodo;
  Todo testTodo2;
  Todo testTodo3;
  String description;
  Boolean isCompleted;
  Boolean isCompleted2;
  LocalDate dueDate;
  LocalDate dueDate2;
  Integer priority;
  String category;
  Integer id;
  Integer id2;
  List<Integer> todosToBeCompleted;

  @Before
  public void setUp() throws Exception {
    description = "Make a phone call";
    isCompleted = false;
    isCompleted2 = true;
    dueDate = LocalDate.of(2021, 10, 05);
    dueDate2 = LocalDate.of(2022, 10, 05);
    priority = 3;
    category = "private";
    testTodo = new Todo(null, description, isCompleted, dueDate, priority, category);
    testTodo2 = new Todo();
    testTodo3 = new Todo(null, "Complete spreadsheet", isCompleted2, dueDate2, 1,
        category);
    todoLists = new ArrayList<Todo>();
    todoLists.add(testTodo);
    testTodoList = new TodoList(todoLists);
    todosToBeCompleted = new ArrayList<Integer>();
  }

  @Test
  public void getTodoLists() {
    assertEquals(todoLists, testTodoList.getTodoLists());
  }

  @Test
  public void addToDo() {
    testTodoList.addToDo(testTodo2);
    assertEquals(testTodo2, testTodoList.getTodoLists().get(1));
  }

  @Test
  public void completeToDo() {
    todosToBeCompleted.add(1);
    testTodoList.completeToDo(todosToBeCompleted);
    assertTrue(testTodoList.getTodoLists().get(0).getCompleted());
  }

  @Test
  public void sortByDate() {
    testTodoList.addToDo(testTodo2);
    testTodoList.addToDo(testTodo3);
    todoLists2 = new ArrayList<Todo>();
    todoLists2.add(0, testTodo);
    todoLists2.add(1, testTodo3);
    todoLists2.add(2, testTodo2);
    testTodoList2 = new TodoList(todoLists2);
    TodoList sortedByDate = testTodoList.sortByDate();
    assertEquals(sortedByDate, testTodoList2);
  }

  @Test
  public void sortByPriority() {
    testTodoList.addToDo(testTodo2);
    testTodoList.addToDo(testTodo3);
    todoLists2 = new ArrayList<Todo>();
    todoLists2.add(0, testTodo3);
    todoLists2.add(1, testTodo);
    todoLists2.add(2, testTodo2);
    testTodoList2 = new TodoList(todoLists2);
    TodoList sortedByPriority = testTodoList.sortByPriority();
    assertEquals(sortedByPriority, testTodoList2);
  }

  @Test
  public void filterIncomplete() {
    testTodoList.addToDo(testTodo2);
    testTodoList.addToDo(testTodo3);
    testTodoList.addToDo(testTodo3);
    ArrayList<Todo> todoLists2 = new ArrayList<Todo>();
    todoLists2.add(testTodo);
    todoLists2.add(testTodo2);
    testTodoList2 = new TodoList(todoLists2);
    TodoList filteredIncomplete = testTodoList.filterIncomplete();
    assertEquals(filteredIncomplete, testTodoList2);
  }

  @Test
  public void filterCategory() {
    testTodoList.addToDo(testTodo2);
    testTodoList.addToDo(testTodo3);
    ArrayList<Todo> todoLists2 = new ArrayList<Todo>();
    todoLists2.add(testTodo);
    todoLists2.add(testTodo3);
    testTodoList2 = new TodoList(todoLists2);
    TodoList filteredCategory = testTodoList.filterCategory(category);
    assertEquals(filteredCategory, testTodoList2);
  }

  @Test
  public void testEquals() {
    assertTrue(testTodoList.equals(testTodoList));
    assertFalse(testTodoList.equals(testTodoList.getTodoLists()));
    ArrayList<Todo> todoLists2 = new ArrayList<Todo>();
    todoLists2.add(testTodo);
    testTodoList2 = new TodoList(todoLists2);
    assertTrue(testTodoList.equals(testTodoList2));
    testTodoList2.addToDo(testTodo3);
    testTodoList2.addToDo(testTodo3);
    assertFalse(testTodoList.equals(testTodoList2));
  }

  @Test
  public void testHashCode() {
    int hash = Objects.hash(todoLists);
    assertEquals(hash, testTodoList.hashCode());
  }

  @Test
  public void testToString() {
    String expectedString = "TodoList{" +
        "todoLists=" + todoLists +
        '}';
    assertEquals(expectedString, testTodoList.toString());
  }
}