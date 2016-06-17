package ru.netcracker.client.list.gwt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.netcracker.client.entity.Person;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.list.providers.ItemProvider;

import java.util.List;

/**
 * Created by rewweRrr on 05.04.2016
 */
public class MyListImpl extends Composite implements MyList {
    interface MyListUiBinder extends UiBinder<HTMLPanel, MyListImpl> {
    }

    private static MyListUiBinder ourUiBinder = GWT.create(MyListUiBinder.class);


    private ItemProvider provider;

    @UiField
    Element ul;

    public MyListImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


    private String generateId() {
        return HTMLPanel.createUniqueId();
    }

    public void add(Person prn) {
        Element li = DOM.createElement("li");
        li.setId(prn.getId());
        li.setInnerText(prn.getName());
        DOM.appendChild(ul, li);
    }

    public void add(String name) {
        Element li = DOM.createElement("li");
        li.setId(generateId());
        li.setInnerText(name);

        DOM.appendChild(ul, li);
    }

    public Person get(String id) {
        Element li = DOM.getElementById(id);

        Person prn = new Person();
        prn.setId(id);
        prn.setName(li.getInnerText());

        return prn;
    }

    public Element getEl(String id) {
        return DOM.getElementById(id);
    }

    public void remove(String id) {
        Element li = DOM.getElementById(id);
        li.getParentElement().removeChild(li);
    }

    public void clear() {

        while (ul.hasChildNodes()) {
            ul.removeChild(ul.getLastChild());
        }
    }

    public void replaceAll(List<Person> people) {
        clear();
        for (Person person : people) {
            add(person);
        }
    }

    public void addClickHandler(String id, EventListener eventListener) {
        Element li = DOM.getElementById("" + id);
        DOM.sinkEvents(li, Event.ONCLICK);
        DOM.setEventListener(li, eventListener);
    }

    public void setProvider(ItemProvider provider) {
        this.provider = provider;

    }

    public ItemProvider getProvider() {
        return provider;
    }
}