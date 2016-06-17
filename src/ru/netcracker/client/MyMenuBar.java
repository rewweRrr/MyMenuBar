package ru.netcracker.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import ru.netcracker.client.entity.Person;
import ru.netcracker.client.list.gwt.MyListImpl;
import ru.netcracker.client.list.js.MyListJsImpl;
import ru.netcracker.client.list.providers.ItemCallBack;
import ru.netcracker.client.list.providers.ItemProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rewweRrr on 04.04.2016
 */
public class MyMenuBar implements EntryPoint {
    public void onModuleLoad() {
        UBMyMenuBar menuBar = new UBMyMenuBar();
        RootPanel.get().add(menuBar);

        MyListImpl list = menuBar.getList();


        list.add("Nick");
        list.add("Pasha");
        list.add("Cath");

        //list.remove("2");

        list.add("Gala");


        //Window.alert(list.get(3).getName());

        list.clear();
        list.add("Cath");
        list.add("Cath");

        list.add(new Person("3", "Nick"));

        final ArrayList<Person> people = new ArrayList<Person>();

        people.add(new Person("4", "Pasha"));
        people.add(new Person("5", "Nick"));
        people.add(new Person("6", "Yuriy"));

        list.replaceAll(people);

        //Element el = list.getEl(5);
        //Person prn = list.get(6);

        list.addClickHandler("5", new EventListener() {
            public void onBrowserEvent(Event event) {
                Window.alert("msg");
            }
        });

        list.setProvider(new ItemProvider() {
            public void get(ItemCallBack<List<Person>> itemCallBack) {
                itemCallBack.onSuccess(people);
            }
        });

//        list.getProvider().get(new ItemCallBack<List<Person>>() {
//            public void onFailure(Throwable caught) {
//
//            }
//
//            public void onSuccess(List<Person> result) {
//                Window.alert("Suceess " + result.get(0).getId());
//            }
//        });

        MyListJsImpl jsList = menuBar.getJsList();

        jsList.add(new Person("1", "Nick"));
        jsList.add("Cath");
        jsList.add("Pasha");
        //jsList.clear();
        jsList.add("Nick");
        //jsList.replaceAll(people);
//        Window.alert(jsList.get("ui-id-3").getName());
//        jsList.addClickHandler("ui-id-3", new EventListener() {
//            public void onBrowserEvent(Event event) {
//                Window.alert("Abra Kadabra");
//            }
//        });

    }
}
