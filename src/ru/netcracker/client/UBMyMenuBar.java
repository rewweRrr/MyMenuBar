package ru.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.pojo.Person;

/**
 * Created by rewweRrr on 04.04.2016
 */
public class UBMyMenuBar extends Composite {
    interface UBMyMenuBarUiBinder extends UiBinder<FlowPanel, UBMyMenuBar> {
    }

    private static UBMyMenuBarUiBinder ourUiBinder = GWT.create(UBMyMenuBarUiBinder.class);

    @UiField
    MyList list;

    public UBMyMenuBar() {
        initWidget(ourUiBinder.createAndBindUi(this));

        Person prn = new Person();
        prn.setName("Nick");
        prn.setId(1);
        list.addLi(prn);
    }


}