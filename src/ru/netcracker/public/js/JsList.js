// var item = {
//     id: 1,
//     name: 'Nick1',
//     click: function () {
//         alert("msg")
//     }
// };
// var item2 = {
//     id: 2,
//     name: 'Nick2'
// };
// var item3 = {
//     id: 3,
//     name: 'Nick3'
// };
// var item4 = {
//     id: 4,
//     name: 'Nick4'
//
// };
// var item5 = {
//     id: 5,
//     name: 'Nick5'
//
// };
//
// var items = [item4, item5];


(function ($) {

    function polymorph() {
        var len2func = [];
        for (var i = 0; i < arguments.length; i++)
            if (typeof(arguments[i]) == "function")
                len2func[arguments[i].length] = arguments[i];
        return function () {
            return len2func[arguments.length].apply(this, arguments);
        }
    }

    $.fn.myMenu = polymorph(
        function () {
            var $menu = this;
            return {
                add: function (item) {
                    var liElement = $("<li>");
                    if ($("#" + item.id).length == 0) {
                        liElement.html(item.name).attr("id", item.id).appendTo($menu);
                    } else {
                        liElement.uniqueId();
                        liElement.html(item.name).appendTo($menu);
                    }
                    if (item.handlers != null) {
                        for (var handler in item.handlers) {
                            liElement.on(handler, item.handlers[handler]);
                        }
                    }

                    return liElement;
                },

                get: function (id) {
                     return $("#" + id);
                },

                remove: function (item) {
                    var $li = $("#" + item.id);
                    $li.remove();

                    return this;
                },

                clear: function () {

                    $($menu).html("");
                },

                replaceAll: function (items) {

                    $($menu).myMenu().clear();

                    for (var i = 0; i < items.length; i++) {
                        $($menu).myMenu().add(items[i]);
                    }
                    return this;
                },

                setProvider: function ($provider) {
                    var $deferred = new $.Deferred();
                    $provider.get($deferred);

                    $deferred.promise().done(function (items) {
                        for (var i = 0; i < items.length; i++) {
                            $($menu).myMenu().add(items[i]);
                        }
                    });
                }

            };
        },

        function (funcName, param) {
            try {
                $(this).myMenu()[funcName](param);
            } catch (err) {
                console.log(err);
                alert("func: \"" + funcName + "\" isn't exist \n" + err);
            }
            return this
        },

        function (items) {
            $(this).myMenu().replaceAll(items);
        }
    );
})(jQuery);

// var ulElement = $("#myId");
//
// ulElement.myMenu().add(item);
// ulElement.myMenu(items);
//
// ulElement.myMenu().add(item);
// ulElement.myMenu().add(item2);
// ulElement.myMenu().remove(item2);
// ulElement.myMenu().add(item2);
// ulElement.myMenu("add", item3);
// ulElement.myMenu().get(2).click(function () {
//     alert("1");
// });
// var item12 = {
//     id: 1,
//     name: 'Nick12',
//     handlers: {
//         click: function () {
//             alert("msg0");
//         },
//         //
//         // dblclick: function () {
//         //     alert("msg2");
//         // }
//         mouseenter: function () {
//             alert("msg1");
//         },
//         mouseleave: function () {
//             alert("msg2");
//         }
//
//     }
// };
// //ulElement.myMenu("sdg", item);
//
// var AsyncProvider = function () {
//     var item4 = {
//         id: 4,
//         name: 'Nick4'
//     };
//     var item5 = {
//         id: 5,
//         name: 'Nick5'
//     };
//     var items = [item4, item5];
//
//     this.get = function ($deferred) {
//         setTimeout(function () {
//             return $deferred.resolve(items);
//         }, 1000);
//     }
// };
//
// ulElement.myMenu().setProvider(new AsyncProvider());

// var SyncProvider = function() {
//     var item6 = {
//         id: 6,
//         name: 'Nick6'
//     };
//     var item7 = {
//         id: 7,
//         name: 'Nick7'
//     };
//     var items = [item6, item7];
//
//     this.get = function (dfd) {
//         return dfd.resolve(items);
//     }
// };
//
// var dfd = new $.Deferred();
//
// new SyncProvider().get(dfd);
//
// dfd.promise().done(function (items) {
//     for(var i = 0; i < items.length; i++) {
//         alert(items[i].name);
//     }
// });
//
// var dfd2 = new $.Deferred();
// new AsyncProvider().get(dfd2);
//
// dfd2.promise().done(function (items) {
//     for(var i = 0; i < items.length; i++) {
//         alert(items[i].name);
//     }
// });


// var promise = new Promise(function (resolve, reject) {
//     window.setTimeout(
//         function () {
//             resolve("msg");
//         }, Math.random() * 2000 + 1000);
// });
//
// promise.then(function (result) {
//     alert(result);
// });