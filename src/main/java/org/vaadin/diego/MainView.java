package org.vaadin.diego;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;




import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a button and a click listener.
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {

        createBeanGrid();
        createNonBeanGrid();
    }

    private void createBeanGrid() {
        Grid<Person> grid = new Grid<>(Person.class);
        grid.setItems(new Person("Person 1", 99), new Person("Person 2", 1111),
                new Person("Person 3", 1));
        grid.setColumns("name", "age");

        Button button = new Button("change order", event -> {
            grid.setColumns("age", "name");
        });

        add(button, grid);
    }

    private void createNonBeanGrid() {
        Grid<Person> grid = new Grid<>();

        grid.setItems(new Person("Person 1", 99), new Person("Person 2", 1111),
                new Person("Person 3", 1));

        grid.addColumn(Person::getName).setHeader("name").setKey("name");
        grid.addColumn(Person::getAge).setHeader("age").setKey("age");

        Button button = new Button("change order", event -> {
            // removes all the columns
            grid.getColumns().forEach(personColumn -> grid.removeColumn(personColumn));

            // adds columns manually
            grid.addColumn(Person::getAge).setHeader("age").setKey("age");
            grid.addColumn(Person::getName).setHeader("name").setKey("name");
        });

        add(button, grid);
    }
}
