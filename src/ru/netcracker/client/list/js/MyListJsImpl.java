package ru.netcracker.client.list.js;

import com.google.gson.Gson;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.netcracker.client.entity.Person;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.list.providers.ItemProvider;

import java.util.List;

/**
 * Created by nivo0616 on 06.06.2016
 */
public class MyListJsImpl extends Composite implements MyList {

    interface MyListJsImplUiBinder extends UiBinder<HTMLPanel, MyListJsImpl> {
    }

    private static MyListJsImplUiBinder ourUiBinder = GWT.create(MyListJsImplUiBinder.class);

    @UiField
    Element ul;

    public MyListJsImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        ul.setId("jsList");
    }


    public native void add(Person prn) /*-{
        var $ulElement = $wnd.$(this.@MyListJsImpl::ul);
        $ulElement.myMenu("add", {id: prn.@Person::getId()(), name: prn.@Person::getName()()});
    }-*/;

    public native void add(String name) /*-{
        var $ulElement = $wnd.$(this.@MyListJsImpl::ul);
        var $liElement = $wnd.$("<li>").uniqueId();

        $liElement.html(name).appendTo($ulElement);
    }-*/;

    public native Person get(String id) /*-{
        var $liElement = $wnd.$("#" + id);

        return @Person::new(Ljava/lang/String;Ljava/lang/String;)(id, $liElement.text());
    }-*/;

    public native void remove(Person prn) /*-{
        $wnd.$(this.@MyListJsImpl::ul).myMenu("remove", {id: prn.@Person::getId()(), name: prn.@Person::getName()()})
    }-*/;

    public native void clear() /*-{
        $wnd.$(this.@MyListJsImpl::ul).myMenu("clear");
    }-*/;

    public void replaceAll(List<Person> people) {
        clear();

        for (Person person : people) {
            add(person);
        }
    }

    public native void addClickHandler(String id, EventListener eventListener) /*-{
        var $liElement = $wnd.$("#" + id);
        $liElement.click(function () {
            eventListener.@EventListener::onBrowserEvent(*)();
        });
    }-*/;

    public void setProvider(ItemProvider provider) {
        //TODO
    }

}