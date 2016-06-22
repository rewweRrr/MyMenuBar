package ru.netcracker.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import ru.netcracker.shared.Person;
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
        menuBar.getList().add(new Person("01", "0oo0"));
        RootPanel.get().add(menuBar);

        final MyListImpl list = menuBar.getList();

        list.add("Nick");
        list.add("Pasha");
        list.add("Cath");


        //list.remove("2");

        list.add("Gala");


        //Window.alert(list.get(3).getName());

        //list.clear();
        list.add("Cath");
        list.add("Cath");

        list.add(new Person("3", "Nick"));
        list.add(new Person("2.5", "Nick"));
        list.get("3").on(Event.ONCLICK, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                Window.alert("click");
            }
        });

        list.get("2.5").on(Event.ONCLICK, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                Window.alert("off first click");
                list.get("3").off();
            }
        });

        list.get("3").on(Event.ONMOUSEOUT, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                Window.alert("Win");
            }
        });



        final ArrayList<Person> people = new ArrayList<>();

        people.add(new Person("4", "Pasha"));
        people.add(new Person("5", "Nick"));
        people.add(new Person("6", "Yuriy"));

        //list.replaceAll(people);

        //Element el = list.getEl(5);
        //Person prn = list.get(6);

//        list.addClickHandler("3", new EventListener() {
//            public void onBrowserEvent(Event event) {
//                Window.alert("msg");
//            }
//        });

//        list.setProvider(new ItemProvider() {
//            public void get(ItemCallBack<List<Person>> itemCallBack) {
//                itemCallBack.onSuccess(people);
//            }
//        });

        MyListJsImpl jsList = menuBar.getJsList();

        jsList.add(new Person("1", "Nick2"));
        jsList.get("1").on(Event.ONCLICK, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                Window.alert("Click");
            }
        });

        jsList.add("Cath");
        jsList.add("Pasha");
        //jsList.clear();
        jsList.add("Nick");
        //jsList.replaceAll(people);
        jsList.add("Nick");
        jsList.add("Nick");
        //Window.alert(jsList.get("ui-id-4").getName());
//        jsList.addClickHandler("ui-id-4", new EventListener() {
//            public void onBrowserEvent(Event event) {
//                Window.alert("Abra Kadabra");
//            }
//        });
        jsList.setProvider(new ItemProvider() {
            public void get(ItemCallBack<List<Person>> itemCallBack) {
                itemCallBack.onSuccess(people);
            }
        });
    }
}
