package ru.netcracker.client.list.js;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import ru.netcracker.shared.Person;
import ru.netcracker.client.list.MyList;
import ru.netcracker.client.list.providers.ItemCallBack;
import ru.netcracker.client.list.providers.ItemProvider;
import ru.netcracker.client.wrapprs.PersonWrapper;

import java.util.ArrayList;
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
    private List<PersonWrapper> peopleJsWrapper;

    public MyListJsImpl() {
        initWidget(ourUiBinder.createAndBindUi(this));
        ul.setId("jsList");
        peopleJsWrapper = new ArrayList<>();
    }

    public void add(Person prn) {
        peopleJsWrapper.add(addToJsList(prn));
    }

    private native PersonWrapper addToJsList(Person prn) /*-{
        var $ulElement = $wnd.$(this.@MyListJsImpl::ul);
        var $liElement = $ulElement.myMenu("add", {id: prn.@ru.netcracker.shared.Person::getId()(), name: prn.@ru.netcracker.shared.Person::getName()()});

        return @PersonWrapper::new(Ljava/lang/String;Ljava/lang/String;)($liElement.attr("id"), $liElement.text());
    }-*/;


    public void add(String name) {
        peopleJsWrapper.add(addToJsList(name));
    }

    private native PersonWrapper addToJsList(String name) /*-{
        var $ulElement = $wnd.$(this.@MyListJsImpl::ul);
        var $liElement = $wnd.$("<li>").uniqueId();

        $liElement.html(name).appendTo($ulElement);

        return @PersonWrapper::new(Ljava/lang/String;Ljava/lang/String;)($liElement.attr("id"), name);
    }-*/;

    public native PersonWrapper get(String id) /*-{
        var $liElement = $wnd.$(this.@MyListJsImpl::ul).myMenu().get(id);

        return @PersonWrapper::new(Ljava/lang/String;Ljava/lang/String;)($liElement.attr("id"), $liElement.text());
    }-*/;

    public void remove(Person prn) {
        for (int i = 0; i < peopleJsWrapper.size(); i++) {
            if (peopleJsWrapper.get(i).getPerson().getId().equals(prn.getId())) {
                peopleJsWrapper.remove(i);
            }
        }
        removeFromJsList(prn);
    }

    private native void removeFromJsList(Person prn) /*-{
        $wnd.$(this.@MyListJsImpl::ul).myMenu("remove", {id: prn.@ru.netcracker.shared.Person::getId()(), name: prn.@ru.netcracker.shared.Person::getName()()})
    }-*/;

    public void clear(){
        peopleJsWrapper.clear();
        clearJsList();
    }

    private native void clearJsList() /*-{
        $wnd.$(this.@MyListJsImpl::ul).myMenu().clear();
    }-*/;

    public void replaceAll(List<Person> people) {
        clear();

        for (Person person : people) {
            add(person);
        }
    }

//    public native void addClickHandler(String id, EventListener eventListener) /*-{
//        var $liElement = $wnd.$("#" + id);
//        $liElement.click(function () {
//            eventListener.@EventListener::onBrowserEvent(*)();
//        });
//    }-*/;

    public native void setProvider(ItemProvider provider) /*-{
        var _this = this;
        $wnd.$(this.@MyListJsImpl::ul).myMenu().setProvider({
            get: function ($deferred) {
                //setTimeout(function () {
                _this.@MyListJsImpl::setProviderDeferred(*)(provider, $deferred)
                //},3000);
            }
        });
    }-*/;

    private void setProviderDeferred(ItemProvider provider, final JavaScriptObject deferred) {
        provider.get(new ItemCallBack<List<Person>>() {
            public void onSuccess(List<Person> result) {
                deferredResolve(peopleToJsObject(result), deferred);
            }

            public native void onFailure(Throwable caught) /*-{

            }-*/;
        });
    }

    private native JavaScriptObject peopleToJsObject(List<Person> people)/*-{
        var items = new Array(people.@List::size()());

        for (var i = 0; i < people.@List::size()(); i++) {
            var person = people.@List::get(*)(i);
            items[i] = {id: person.@ru.netcracker.shared.Person::getId()(), name: person.@ru.netcracker.shared.Person::getName()()};
        }
        return items;
    }-*/;

    private native void deferredResolve(JavaScriptObject items, JavaScriptObject deferred) /*-{
        deferred.resolve(items);
    }-*/;

}