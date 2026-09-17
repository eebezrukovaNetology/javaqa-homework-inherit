import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddAndFindAllTasksInTodos() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        Epic epic = new Epic(2, new String[]{"Хлеб"});

        todos.add(simpleTask);
        todos.add(epic);

        Task[] expected = {simpleTask, epic};
        assertArrayEquals(expected, todos.findAll());
    }

    @Test
    public void shouldSearchTasksIfSeveralMatches() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Epic epic = new Epic(2, new String[]{"Купить хлеб"});
        Meeting meeting = new Meeting(3, "Обсуждение", "Проект", "Завтра");

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        assertArrayEquals(expected, todos.search("Купить"));
    }

    @Test
    public void shouldSearchTasksIfOneMatch() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Epic epic = new Epic(2, new String[]{"Купить хлеб"});

        todos.add(simpleTask);
        todos.add(epic);

        Task[] expected = {simpleTask};
        assertArrayEquals(expected, todos.search("молоко"));
    }

    @Test
    public void shouldSearchTasksIfNoMatches() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");

        todos.add(simpleTask);

        Task[] expected = {};
        assertArrayEquals(expected, todos.search("Хлеб"));
    }
}
