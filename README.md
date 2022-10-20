# To-Do list

### Project Description
Command-line To-Do application. The system will allow a user to add and track the status of their To-Dos by the due date, category, priority, and status. The application stores all To-Dos in a CSV file. 

### Funcionality
- Add a new todo
- Complete an existing todo
- Display todos: can be filtered by status, category and date.
The two filter arguments can be combined but only one sort can be applied at a time.

### Content
Main.java<br />
Display.java - Display class, manages view

/commandLineParser 
<br />&nbsp;&nbsp;&nbsp;&nbsp;CommandLine.java - CommandLine class, provides parsing for command line options
<br />&nbsp;&nbsp;&nbsp;&nbsp;Option.java - Option class, represents a command option and its information
<br />&nbsp;&nbsp;&nbsp;&nbsp;Options.java - Options class, stores a groups of command options

/fileProcessing
<br />&nbsp;&nbsp;&nbsp;&nbsp;CSVReader.java - CSVReader class, reads and process a CVS file to extract its data

/todoList
<br />&nbsp;&nbsp;&nbsp;&nbsp;ITodoList - todolist class interface
<br />&nbsp;&nbsp;&nbsp;&nbsp;Todo.java - class Todo, representing a task that user stores in a todo application
<br />&nbsp;&nbsp;&nbsp;&nbsp;TodoList.java - class TodoList, representing the list of todos

/test
<br />&nbsp;&nbsp;&nbsp;&nbsp;CSVReaderTest.java
<br />&nbsp;&nbsp;&nbsp;&nbsp;TodoListTest.java
<br />&nbsp;&nbsp;&nbsp;&nbsp;TodoTest.java

### Instructions
| Command                                 | Description                             | 
| :------------------                     |   :-------------                            | 
| `--csv-file <path/to/file>`             | The CSV file containing the todos. This option is required. | 
| `--add-todo`                            | Add a new todo. If this option is provided, then --todo-text must also be provided.|                      
| `--todo-text <description>`             | A description of the todo.       |
| `--completed`                           | (Optional) Sets the completed status of a new todo to true.|
| `--due <due date>`.                     | (Optional) Sets the due date of a new todo. You may choose how the date should be formatted.|
| `--priority <value>`                  | (Optional) Sets the priority of a new todo. The value can be 1, 2, or 3. |
| `--category <category name>`            | (Optional) Sets the category of a new todo. The value can be any String. Categories do not need to be pre-defined. |
| `--complete-todo <id>`                  | Mark the Todo with the provided ID as complete.       |
| `--display`                             | Display todos. If none of the following optional arguments are provided, displays all todos.     |
| `--show-incomplete`                     | (Optional) If --display is provided, only incomplete todos should be displayed.    |
|`--show-category <category>`             | (Optional) If --display is provided, only todos with the given category should be displayed.     |
| `--sort-by-date`                        | (Optional) If --display is provided, sort the list of todos by date order (ascending). Cannot be combined with --sort-by- priority.     |
|`--sort-by-priority`                     | (Optional) If --display is provided, sort the list of todos by priority (ascending). Cannot be combined with --sort-by-date.      |
