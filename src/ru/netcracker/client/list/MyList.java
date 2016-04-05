package ru.netcracker.client.list;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.netcracker.client.pojo.Person;

/**
 * Created by rewweRrr on 05.04.2016
 */
public class MyList extends Composite {
    interface MyListUiBinder extends UiBinder<HTMLPanel, MyList> {
    }

    private static MyListUiBinder ourUiBinder = GWT.create(MyListUiBinder.class);

    @UiField
    Element ul;

    public MyList() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }

    public void addLi(Person prn) {
        Element li = DOM.createElement("li");
        li.setInnerText(prn.getId() + ") "+prn.getName());
        DOM.appendChild(ul, li);
    }
}