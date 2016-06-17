package ru.netcracker.client.list;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.EventListener;
import ru.netcracker.client.entity.Person;
import ru.netcracker.client.list.providers.ItemProvider;

import java.util.List;

/**
 * Created by nivo0616 on 06.06.2016
 */
public interface MyList {

    void add(Person prn);

    void add(String name);

    Person get(String id);

    void remove(Person prn);

    void clear();

    void replaceAll(List<Person> people);

    void addClickHandler(String id, EventListener eventListener);

    void setProvider(ItemProvider provider);

}
