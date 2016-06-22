package ru.netcracker.client.wrapprs;

import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.EventListener;
import ru.netcracker.shared.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nivo0616 on 21.06.2016
 */
public class PersonWrapper {

    private Person person;
    private Map<Integer, EventListener> clickHandlers;

    public Person getPerson() {
        return person;
    }

    public PersonWrapper(String id, String name) {
        this.person = new Person(id, name);
        this.clickHandlers = new HashMap<>();
    }

    public PersonWrapper on(int type, EventListener listener) {
        clickHandlers.put(type, listener);
        drawWrapper();
        return this;
    }

    public PersonWrapper off() {
        for (Map.Entry<Integer, EventListener> entry : clickHandlers.entrySet()) {
            entry.setValue(null);
        }

        drawWrapper();

        clickHandlers.clear();
        return this;
    }

    private void drawWrapper() {
        Element currentElement = DOM.getElementById(person.getId());
        currentElement.setInnerText(person.getName());

//        GQuery query = GQuery.$(currentElement)

        for (Map.Entry<Integer, EventListener> entry : clickHandlers.entrySet()) {
            DOM.sinkEvents(currentElement, entry.getKey());
            DOM.setEventListener(currentElement, entry.getValue());
        }

    }
}
