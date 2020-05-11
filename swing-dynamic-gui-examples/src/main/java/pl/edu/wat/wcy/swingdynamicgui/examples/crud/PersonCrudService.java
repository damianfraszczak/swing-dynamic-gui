package pl.edu.wat.wcy.swingdynamicgui.examples.crud;

import pl.edu.wat.wcy.swingdynamicgui.examples.model.Person;
import pl.edu.wat.wcy.swingdynamicgui.examples.model.Sex;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PersonCrudService {
    private List<Person> personList = generatePersons(50);

    private static List<Person> generatePersons(int size) {
        List<Person> persons = new LinkedList<>();
        for(int i =0; i < size;i++){
            persons.add(Person.builder()
                    .name("Person " + i)
                    .surname("Surname for person " + i )
                    .sex(Math.random() > 0.5 ? Sex.MALE : Sex.FEMALE)
                    .salary(Math.random() * 1000)
                    .build());
        }
        return persons;
    }

    public CompletableFuture<Person> add(Person person) {
        personList.add(person);
        return CompletableFuture.completedFuture(person);
    }
    public CompletableFuture<Person> update(Person person) {
        return CompletableFuture.completedFuture(person);
    }
    public CompletableFuture<Person> delete(Person person) {
        personList.remove(person);
        return CompletableFuture.completedFuture(person);
    }
    public CompletableFuture<List<Person>> list() {
        return CompletableFuture.completedFuture(personList);
    }
}
