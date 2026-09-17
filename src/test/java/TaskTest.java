import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void shouldMatchSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить маме");
        assertTrue(simpleTask.matches("маме"));
        assertFalse(simpleTask.matches("папе"));
    }

    @Test
    public void shouldMatchEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);
        assertTrue(epic.matches("Яйца"));
        assertFalse(epic.matches("Сыр"));
    }

    @Test
    public void shouldMatchMeeting() {
        Meeting meeting = new Meeting(
                3,
                "Выкатка приложения",
                "Проект Альфа",
                "Среда в 12:00"
        );
        assertTrue(meeting.matches("Выкатка"));
        assertTrue(meeting.matches("Альфа"));
        assertFalse(meeting.matches("Пятница"));
    }

    @Test
    public void shouldNotMatchBaseTask() {
        Task task = new Task(5);
        assertFalse(task.matches("любой запрос"));
    }

    @Test
    public void shouldSearchWhenExactlyOneTaskMatches() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        todos.add(simpleTask);

        Task[] expected = {simpleTask};
        Task[] actual = todos.search("молоко");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenFewTasksMatch() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Epic epic = new Epic(2, new String[]{"Купить хлеб"});
        Meeting meeting = new Meeting(3, "Обсуждение", "Проект", "Завтра");

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic};
        Task[] actual = todos.search("Купить");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenNoTasksMatch() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        todos.add(simpleTask);

        Task[] expected = {};
        Task[] actual = todos.search("Хлеб");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchWhenAllTasksMatch() {
        Todos todos = new Todos();
        SimpleTask simpleTask = new SimpleTask(1, "Общая задача");
        Meeting meeting = new Meeting(2, "Общая встреча", "Проект", "Завтра");

        todos.add(simpleTask);
        todos.add(meeting);

        Task[] expected = {simpleTask, meeting};
        Task[] actual = todos.search("Общая");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testTaskGettersAndEquals() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);
        Task task3 = new Task(2);
        SimpleTask simple = new SimpleTask(1, "Тест");

        assertEquals(1, task1.getId());
        assertTrue(task1.equals(task1));
        assertTrue(task1.equals(task2));
        assertFalse(task1.equals(task3));
        assertFalse(task1.equals(null));
        assertFalse(task1.equals(simple));
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    public void testSimpleTaskGetters() {
        SimpleTask simpleTask = new SimpleTask(1, "Заголовок");
        assertEquals("Заголовок", simpleTask.getTitle());
    }

    @Test
    public void testEpicGetters() {
        String[] subtasks = {"1"};
        Epic epic = new Epic(1, subtasks);
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    @Test
    public void testMeetingGetters() {
        Meeting meeting = new Meeting(1, "Тема", "Проект", "Старт");
        assertEquals("Тема", meeting.getTopic());
        assertEquals("Проект", meeting.getProject());
        assertEquals("Старт", meeting.getStart());
    }
}
