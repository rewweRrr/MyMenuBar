package ru.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
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

    @UiField
    ListBox listsBox;

    public MyListJsImpl getJsList() {
        return jsList;
    }

    public MyListImpl getList() {
        return list;
    }

    public UBMyMenuBar() {
        initWidget(ourUiBinder.createAndBindUi(this));
        list.getElement().setId("GWT-list");
        jsList.getElement().setId("JS-list");
        jsList.getElement().setAttribute("hidden", "true");
        listsBox.addItem("GWT-list");
        listsBox.addItem("JS-list");
    }


    @SuppressWarnings("UnusedParameters")
    @UiHandler("listsBox")
    void onClickBox(ClickEvent event) {
        changeList(listsBox.getSelectedItemText());
    }

    private void changeList(String listId) {
        hiddenAllLists();
        showList(listId);
    }
    private void showList(String listId) {
        DOM.getElementById(listId).removeAttribute("hidden");
    }

    private void hiddenAllLists() {
        for (int i = 0; i < listsBox.getItemCount(); i++) {
            DOM.getElementById(listsBox.getItemText(i)).setAttribute("hidden", "true");
        }
    }

}