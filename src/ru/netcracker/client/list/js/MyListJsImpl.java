package ru.netcracker.client.list.js;

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

    private ItemProvider provider;

    public MyListJsImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        ul.setId("jsList");
    }


    public native void add(Person prn) /*-{
        var ulElement = $wnd.$("#jsList");
        var liElement = $wnd.$("<li>");

        if (prn.@ru.netcracker.client.entity.Person::getId()() != null) {
            liElement.html(prn.@ru.netcracker.client.entity.Person::getName()()).attr("id", prn.@ru.netcracker.client.entity.Person::getId()())
                .appendTo(ulElement);
        } else {
            //TODO
        }

    }-*/;

    public native void add(String name) /*-{
        var ulElement = $wnd.$("#jsList");
        var liElement = $wnd.$("<li>").uniqueId();

        liElement.html(name).appendTo(ulElement);
    }-*/;

    public native Person get(String id) /*-{
        var liElement = $wnd.$("#" + id);
        ;
        var prn = @ru.netcracker.client.entity.Person::new(Ljava/lang/String;Ljava/lang/String;)(id, liElement.text());

        return prn;
    }-*/;

    public Element getEl(String id) {
        return null;
    }

    public native void remove(String id) /*-{
        var liElement = $wnd.$("#" + id).remove();
    }-*/;

    public native void clear() /*-{
        var ulElement = $wnd.$("#jsList").html("");
    }-*/;

    public native void replaceAll(List<Person> people) /*-{
        this.@ru.netcracker.client.list.js.MyListJsImpl::clear()();

        var ulElement = $wnd.$("#jsList");
        for (var i = 0; i < people.@java.util.List::size()(); i++) {
            this.@ru.netcracker.client.list.js.MyListJsImpl::add(Lru/netcracker/client/entity/Person;)(people.@java.util.List::get(*)(i));
        }
    }-*/;

    public native void addClickHandler(String id, EventListener eventListener) /*-{
        var liElement = $wnd.$("#" + id);
        liElement.click(function () {
            eventListener.@com.google.gwt.user.client.EventListener::onBrowserEvent(*)();
        });
    }-*/;

    public void setProvider(ItemProvider provider) {
        this.provider = provider;
    }

    public ItemProvider getProvider() {
        return provider;
    }
}