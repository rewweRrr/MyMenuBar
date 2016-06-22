package ru.netcracker.client.list;

import ru.netcracker.shared.Person;
import ru.netcracker.client.list.providers.ItemProvider;
import ru.netcracker.client.wrapprs.PersonWrapper;

import java.util.List;

/**
 * Created by nivo0616 on 06.06.2016
 */
public interface MyList {

    void add(Person prn);

    void add(String name);

    PersonWrapper get(String id);

    void remove(Person prn);

    void clear();

    void replaceAll(List<Person> people);

//    void addClickHandler(String id, EventListener eventListener);

    void setProvider(ItemProvider provider);

}
