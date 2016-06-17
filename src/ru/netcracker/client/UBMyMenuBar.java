package ru.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.netcracker.client.entity.Person;
import ru.netcracker.client.list.MyList;
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
    @UiField
    TextBox idBox;
    @UiField
    TextBox nameBox;
    @UiField
    Button addBtn;
    @UiField
    Button clearBtn;

    private MyList currentList;

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

        setCurrentList(listsBox.getSelectedItemText());
    }


    @SuppressWarnings("UnusedParameters")
    @UiHandler("listsBox")
    public void onClickBox(ClickEvent event) {
        changeList(listsBox.getSelectedItemText());
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("addBtn")
    public void onClickAddBtn(ClickEvent event) {
        if (!idBox.getText().equals("") && !nameBox.getText().equals("")) {
            currentList.add(new Person(idBox.getText(), nameBox.getText()));
        } else if (idBox.getText().equals("") && !nameBox.getText().equals("")) {
            currentList.add(nameBox.getText());
        } else {
            Window.alert("Enter person name");
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("clearBtn")
    public void onClickClearBtn(ClickEvent event) {
        currentList.clear();
    }

    private void changeList(String listId) {
        setCurrentList(listId);
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

    private void setCurrentList(String listId) {
        if (listId.equals(list.getElement().getId())) {
            currentList = list;
        } else {
            currentList = jsList;
        }
    }
}