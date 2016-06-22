package ru.netcracker.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import ru.netcracker.shared.Person;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.list.gwt.MyListImpl;
import ru.netcracker.client.list.js.MyListJsImpl;
import ru.netcracker.client.list.providers.ItemCallBack;
import ru.netcracker.client.list.providers.ItemProvider;

import java.util.ArrayList;
import java.util.List;

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
    CheckBox chooseProvider;
    @UiField
    VerticalPanel providerPanel;
    @UiField
    RadioButton radioClient;
    @UiField
    RadioButton radioServer;
    @UiField
    Button providerButton;
    @UiField
    TextBox idBox;
    @UiField
    TextBox nameBox;
    @UiField
    Button addButton;
    @UiField
    Button clearButton;
    @UiField
    Button removeButton;

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
        providerPanel.getElement().setAttribute("hidden", "true");

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
    @UiHandler("addButton")
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
    @UiHandler("clearButton")
    public void onClickClearBtn(ClickEvent event) {
        currentList.clear();
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("removeButton")
    public void onClickRemoveBtn(ClickEvent event) {
        if (!idBox.getText().equals("")) {
            currentList.remove(new Person(idBox.getText(), nameBox.getText()));
        }
    }

    @UiHandler("chooseProvider")
    public void onValueChange(ValueChangeEvent<Boolean> event) {
        if (event.getValue()) {
            providerPanel.getElement().removeAttribute("hidden");
        } else {
            providerPanel.getElement().setAttribute("hidden", "true");
        }
    }

    @SuppressWarnings("UnusedParameters")
    @UiHandler("providerButton")
    public void onClickProviderBtn(ClickEvent event) {
        if (radioClient.getValue()) {
            currentList.setProvider(new ItemProvider() {

                public void get(final ItemCallBack<List<Person>> itemCallBack) {
                    final List<Person> people = new ArrayList<>();
                    people.add(new Person("1", "qwerty"));
                    people.add(new Person("2", "qwerty2"));

                    Timer t = new Timer() {
                        public void run() {
                            itemCallBack.onSuccess(people);
                        }
                    };

                    t.schedule(1500);
                    //itemCallBack.onSuccess(people);
                }
            });
        }
        if (radioServer.getValue()) {
            //TODO
            Window.alert("TODO");
        }
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