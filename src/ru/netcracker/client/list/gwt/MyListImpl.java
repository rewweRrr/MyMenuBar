package ru.netcracker.client.list.gwt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.netcracker.shared.Person;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.list.providers.ItemCallBack;
import ru.netcracker.client.list.providers.ItemProvider;
import ru.netcracker.client.wrapprs.PersonWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rewweRrr on 05.04.2016
 */
public class MyListImpl extends Composite implements MyList {
    interface MyListUiBinder extends UiBinder<HTMLPanel, MyListImpl> {
    }

    private static MyListUiBinder ourUiBinder = GWT.create(MyListUiBinder.class);

    @UiField
    Element ul;
    private List<PersonWrapper> peopleWrapper;

    public MyListImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        peopleWrapper = new ArrayList<>();
    }


    private String generateId() {
        return HTMLPanel.createUniqueId();
    }

    public void add(Person prn) {
        Element li = DOM.createElement("li");

        if (DOM.getElementById(prn.getId()) != null) {
            add(prn.getName());
        } else {
            li.setId(prn.getId());
            li.setInnerText(prn.getName());
            DOM.appendChild(ul, li);

            peopleWrapper.add(new PersonWrapper(prn.getId(), prn.getName()));
        }
    }

    public void add(String name) {
        Element li = DOM.createElement("li");
        String generatedId = generateId();

        li.setId(generatedId);
        li.setInnerText(name);

        DOM.appendChild(ul, li);
        peopleWrapper.add(new PersonWrapper(generatedId, name));
    }

    public PersonWrapper get(String id) {
        for (PersonWrapper wrapper : peopleWrapper) {
            if (wrapper.getPerson().getId().equals(id)) {
                return wrapper;
            }
        }
        return null;
    }

    public void remove(Person prn) {
        Element li = DOM.getElementById(prn.getId());
        if (li != null) {
            li.getParentElement().removeChild(li);
            removePersonWrapper(prn.getId());
        }
    }

    private void removePersonWrapper(String id) {
        for (int i = 0; i < peopleWrapper.size(); i++) {
            if (peopleWrapper.get(i).getPerson().getId().equals(id)) {
                peopleWrapper.remove(i);
            }
        }
    }

    public void clear() {

        while (ul.hasChildNodes()) {
            ul.removeChild(ul.getLastChild());
            peopleWrapper.clear();
        }
    }

    public void replaceAll(List<Person> people) {
        clear();
        for (Person person : people) {
            add(person);
        }
    }

//    public void addClickHandler(String id, EventListener eventListener) {
//        Element li = DOM.getElementById(id);
//        if (li != null) {
//            DOM.sinkEvents(li, Event.ONCLICK);
//            DOM.setEventListener(li, eventListener);
//        }
//    }

    public void setProvider(ItemProvider provider) {

        provider.get(new ItemCallBack<List<Person>>() {
            public void onFailure(Throwable caught) {

            }

            public void onSuccess(List<Person> result) {
                for (Person prn : result) {
                    add(prn);
                }
            }
        });
    }
}