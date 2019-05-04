package by.it.mazniou.jsoup_agregator.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;

import java.util.List;

public interface View {
    void update(List<Vacancy> vacancies);
    void setController(Controller controller);
}
/*Кратко - используя View(вид) пользователь генерирует события, которые обрабатывает контроллер.
Контроллер принимает решение, какие данные ему нужны, и обращается к нужной моделе.
Модель получает данные, например, из БД или из URL-а. Потом модель передает данные во View.
View отображает данные.*/