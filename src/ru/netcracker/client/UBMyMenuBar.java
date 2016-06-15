package ru.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import ru.netcracker.client.list.gwt.MyListImpl;
import ru.netcracker.client.list.js.MyListJsImpl;

/**
 * Created by rewweRrr on 04.04.2016
 */
public class UBMyMenuBar extends Composite {
    interface UBMyMenuBarUiBinder extends UiBinder<FlowPanel, UBMyMenuBar> {
    }

    private static UBMyMenuBarUiBinder ourUiBinder = GWT.create(UBMyMenuBarUiBinder.class);

    @UiField
    MyListImpl list;

    @UiField
    MyListJsImpl jsList;

    public MyListJsImpl getJsList() {
        return jsList;
    }

    public MyListImpl getList() {
        return list;
    }

    public UBMyMenuBar() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }


}