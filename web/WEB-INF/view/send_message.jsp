<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="../interface/includes.jsp"%>
    <script>
    (function($) {
    /**
    * attaches a character counter to each textarea element in the jQuery object
    * usage: $("#myTextArea").charCounter(max, settings);
    */

    $.fn.charCounter = function (max, settings) {
    max = max || 100;
    settings = $.extend({
    container: "<span></span>",
    classname: "charcounter",
    format: "(%1 characters remaining)",
    pulse: true,
    delay: 0
    }, settings);
    var p, timeout;

    function count(el, container) {
    el = $(el);
    if (el.val().length > max) {
    el.val(el.val().substring(0, max));
    if (settings.pulse && !p) {
    pulse(container, true);
    };
    };
    if (settings.delay > 0) {
    if (timeout) {
    window.clearTimeout(timeout);
    }
    timeout = window.setTimeout(function () {
    container.html(settings.format.replace(/%1/, (max - el.val().length)));
    }, settings.delay);
    } else {
    container.html(settings.format.replace(/%1/, (max - el.val().length)));
    }
    };

    function pulse(el, again) {
    if (p) {
    window.clearTimeout(p);
    p = null;
    };
    el.animate({ opacity: 0.1 }, 100, function () {
    $(this).animate({ opacity: 1.0 }, 100);
    });
    if (again) {
    p = window.setTimeout(function () { pulse(el) }, 200);
    };
    };

    return this.each(function () {
    var container;
    if (!settings.container.match(/^<.+>$/)) {
    // use existing element to hold counter message
    container = $(settings.container);
    } else {
    // append element to hold counter message (clean up old element first)
    $(this).next("." + settings.classname).remove();
    container = $(settings.container)
    .insertAfter(this)
    .addClass(settings.classname);
    }
    $(this)
    .unbind(".charCounter")
    .bind("keydown.charCounter", function () { count(this, container); })
    .bind("keypress.charCounter", function () { count(this, container); })
    .bind("keyup.charCounter", function () { count(this, container); })
    .bind("focus.charCounter", function () { count(this, container); })
    .bind("mouseover.charCounter", function () { count(this, container); })
    .bind("mouseout.charCounter", function () { count(this, container); })
    .bind("paste.charCounter", function () {
    var me = this;
    setTimeout(function () { count(me, container); }, 10);
    });
    if (this.addEventListener) {
    this.addEventListener('input', function () { count(this, container); }, false);
    };
    count(this, container);
    });
    };

    })(jQuery);

    $(function() {
    $(".counted").charCounter(320,{container: "#counter"});
    });
    </script>
    <title>Send message | Memoria</title>
</head>
<body>
<%@include file="../interface/header.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-sm-4 col-md-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form accept-charset="UTF-8" action="send_message" method="POST">
                        <input type="hidden" name="email" value="<%=request.getParameter("email")%>">
                        <textarea class="form-control counted" name="text" placeholder="Type in your message" rows="5" style="margin-bottom:10px;"></textarea>
                        <h6 class="pull-right" id="counter">320 characters remaining</h6>
                        <button class="btn btn-info" type="submit">Send new message</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../interface/footer.jsp"%>
</body>
</html>