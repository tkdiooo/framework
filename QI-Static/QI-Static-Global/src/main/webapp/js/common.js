/**
 * 全局主要JS<br>
 *
 * @author 张麒
 */

window.alert = function (msg, callback) {
    parent.layer.msg(msg, {
        time: 0,
        shade: [0.8, '#393D49'],
        area: '300px',
        btn: ['确认']
        , yes: function (index) {
            invokingFunction(callback);
            parent.layer.close(index);
        }
    });
};

window.confirm = function (msg, callback) {
    parent.layer.msg(msg, {
        time: 0,
        shade: [0.8, '#393D49'],
        area: '300px',
        btn: ['确认', '取消']
        , yes: function (index) {
            invokingFunction(callback);
            parent.layer.close(index);
        }
    });
};

window.open = function (opt) {
    var index = layer.load();
    layerOpen(opt);
    layer.close(index);
};

function bindHotKey(keys, callback) {
    jQuery.hotkeys.add(keys, function () {
        invokingFunction(callback);
    });
}

function bindEnterHotKey(elements, callback) {
    $('#' + elements).bind('keydown', function (evt) {
        if (evt.keyCode === 13) invokingFunction(callback);
    });
}


var win = {};
var browser = {};

/**
 * 初始化窗口高度、宽度
 */
function initWindow() {
    //获取当前浏览器宽度和高度
    //win.width = typeof window.innerWidth == 'undefined' ? document.documentElement.clientWidth : window.innerWidth;
    //win.height = typeof window.innerHeight == 'undefined' ? document.body.scrollHeight : window.innerHeight;
    win.width = $(window).width();
    win.height = $(document).height();
    // 高度最小600
    if (win.height < 600) {
        win.height = 600;
        $(document.body).height(win.height);
    }
    // 宽度最小960
    if (win.width < 960) {
        win.width = 960;
        $(document.body).width(win.width);
    }
}

/**
 * 初始化浏览器判断
 */
function initBrowser() {
    var userAgent = navigator.userAgent, rMsie = /(msie\s|trident.*rv:)([\w.]+)/, rFirefox = /(firefox)\/([\w.]+)/, rOpera = /(opera).+version\/([\w.]+)/, rChrome = /(chrome)\/([\w.]+)/, rSafari = /version\/([\w.]+).*(safari)/;
    var ua = userAgent.toLowerCase();
    // 判断是否是IE
    var match = rMsie.exec(ua);
    if (match !== null) {
        browser.type = 'IE';
        browser.version = match[2];
    } else if ((match = rFirefox.exec(ua)) !== null) {
        browser.type = match[1];
        browser.version = match[2];
    } else if ((match = rOpera.exec(ua)) !== null) {
        browser.type = match[1];
        browser.version = match[2];
    } else if ((match = rChrome.exec(ua)) !== null) {
        browser.type = match[1];
        browser.version = match[2];
    } else if ((match = rSafari.exec(ua)) !== null) {
        browser.type = match[1];
        browser.version = match[2];
    } else if ((match = rSafari.exec(ua)) !== null) {
        browser.type = match[1];
        browser.version = match[2];
    } else {
        browser.type = '';
        browser.version = '';
    }
}

/**
 * 初始化文本框特效
 */
function initInput() {
    $("input").each(function (i, input) {
        if (input.placeholder !== "") {
            $(input).focus(function () {
                input.place = input.placeholder;
                input.placeholder = '';
            }).blur(function () {
                input.placeholder = input.place;
            });
        }
    });
    $("textarea").each(function (i, input) {
        if (input.placeholder !== "") {
            $(input).focus(function () {
                input.place = input.placeholder;
                input.placeholder = '';
            }).blur(function () {
                input.placeholder = input.place;
            });
        }
    });
}

/**
 * 禁用鼠标右键
 */
$(document).on('contextmenu', function () {
    return false;
});

$(function () {
    initBrowser();
    initWindow();
    initInput();
    //$('.body-main').height(win.height - 20);
});

//发送ajax请求
function ajax_action(actionUrl, data, opt, callback) {
    var defaults = {
        contentType: "application/x-www-form-urlencoded",
        async: true,
        waiting: true
    };
    if (actionUrl.indexOf("?") !== -1) {
        actionUrl += "&ajaxTimeFresh=" + Math.random();
    } else {
        actionUrl += "?ajaxTimeFresh=" + Math.random();
    }
    var plugin = this;
    plugin.settings = $.extend({}, defaults, opt);
    $.ajax({
        url: actionUrl,
        method: "post",
        cache: false,
        dataType: "json",
        contentType: plugin.settings.contentType,
        async: plugin.settings.async,
        data: data,
        beforeSend: function () {
            if (plugin.settings.waiting) {
                parent.layer.load(2, {
                    shade: [0.8, '#393D49']
                });
            }
        },
        success: function (data) {
            if (plugin.settings.waiting) {
                parent.layer.closeAll('loading');
            }
            invokingFunction(callback, data);
        },
        error: function (data) {
            alert(data.statusText, function () {
                parent.layer.closeAll('loading');
            });
            if (typeof plugin.settings.errorHandle === "function") {
                plugin.settings.errorHandle(data);
            }
        }
    });
}

// 加载页面
function load_url(url, container, opt) {
    var defaults = {
        data: null,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: 'html',
        waiting: false
    };
    if (url.indexOf("?") !== -1) {
        url += "&ajaxTimeFresh=" + Math.random();
    } else {
        url += "?ajaxTimeFresh=" + Math.random();
    }
    //var containerHtml = container.html();
    var plugin = this;
    plugin.settings = $.extend({}, defaults, opt);
    $.ajax({
        type: "POST",
        url: url,
        dataType: plugin.settings.dataType,
        contentType: plugin.settings.contentType,
        data: plugin.settings.data,
        cache: false, // (warning: this will cause a timestamp and will call the request twice)
        async: true,
        beforeSend: function () {
            //container.html('<h1><i class="fa fa-cog fa-spin"></i> </h1>');
            if (plugin.settings.waiting) {
                parent.layer.load(2, {
                    shade: [0.8, '#393D49']
                });
            }

        },
        /*complete: function(){
         // Handle the complete event
         // alert("complete")
         },*/
        success: function (data) {
            if (plugin.settings.waiting) {
                parent.layer.closeAll();
            }
            container.css({
                opacity: '0.0'
            }).html(data).delay(50).animate({
                opacity: '1.0'
            }, 300);
        },
        error: function (xhr, ajaxOptions, thrownError) {
            if (plugin.settings.waiting) {
                parent.layer.closeAll();
            }
            var data = '<div class="wrap"><div class="errorBox"><h3>该页面无法显示……</h3><p>今天的你很幸运！据统计，网站用户能达到这个页面的可能只有0.0001%</p></div></div>';
            container.html(data);
        }
    });
}

function layerOpen(opt) {
    var defaults = {
        type: 1,
        title: '弹出框',
        shadeClose: true,
        shade: [0.8, '#393D49'],
        move: false
    };
    var plugin = this;
    plugin.settings = $.extend({}, defaults, opt);
    parent.layer.open(plugin.settings);
}
/*---------------------------------------------------------------------------------------------------*/
/**
 * 全局Ajax调用方法
 *
 * @param url：访问路径
 * @param data：参数
 * @param CallBack：回调方法
 * @param mask:
 *            是否显示遮罩、false或null为显示
 */
function ajax(url, data, CallBack, mask) {
    $.ajax({
        type: "POST",
        url: url,
        data: data,
        dataType: "json",
        beforeSend: function () {
            if (!mask)
                showMask();
        },
        success: function (data, textStatus) {
            if (!mask)
                hideMask();
            CallBack.call(this, data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            if ($('#loading').length > 0) {
                setTimeout("$('#loading').hide(),showWarning('"
                    + XMLHttpRequest.responseText + "')", 200);
            } else {
                if (!mask)
                    hideMask();
                jAlertError(XMLHttpRequest.responseText);

            }
        }
    });
}

/**
 * 显示遮罩
 */
function showMask() {
    if ($('#loading').length > 0) {
        $('#loading').show();
    } else {
        $('#loading', window.parent.parent.document).show();
    }
}

/**
 * 隐藏遮罩
 */
function hideMask() {
    if ($('#loading').length > 0) {
        $('#loading').hide();
    } else {
        $('#loading', window.parent.parent.document).hide();
    }
}

/**
 * 全局错误提示框，根据调用位置弹出提示框
 *
 * @param message：消息
 * @param callback：回调事件
 */
function jAlertError(message, callback) {
    // Windows级别调用
    if (self === top) {
        jAlertDialog('错误', message, callback);
    }
    // 弹出窗口级别调用
    else {
        var parent = window.parent.parent.parent.parent;
        parent.jAlertDialog('错误', message, callback);
    }
}

/**
 * 全局消息提示框，根据调用位置弹出提示框
 *
 * @param message：消息
 * @param callback：回调事件
 */
function jAlert(message, callback) {
    // Windows级别调用
    if (self === top) {
        jAlertDialog('提示', message, callback);
    }
    // 弹出窗口级别调用
    else {
        var parent = window.parent.parent.parent.parent;
        parent.jAlertDialog('提示', message, callback);
    }
}
/**
 * 全局弹出确认框，根据调用位置弹出提示框
 *
 * @param message：消息
 * @param ok：确定按钮回调方法
 * @param cancel：取消按钮回调方法
 */
function jConfirm(message, ok, cancel) {
    // Windows级别调用
    if (self === top) {
        jConfirmDialog('提示', message, ok, cancel);
    }
    // 弹出窗口级别调用
    else {
        var parent = window.parent.parent.parent.parent;
        parent.jConfirmDialog('提示', message, ok, cancel);
    }
}

/**
 * 全局弹出模式窗口
 *
 * @param title
 * @param url
 * @param width
 * @param height
 */
function jDialog(title, url, width, height) {
    // Windows级别调用
    if (self === top) {
        jModalDialog(title, url, width, height);
    }
    // 弹出窗口级别调用
    else {
        window.parent.parent.jModalDialog(title, url, width, height);
    }
}

/**
 * 全局弹出模式窗口jDialog，右上关闭事件绑定方法
 *
 * @param callback
 */
function jClose(callback) {
    // Windows级别调用
    if (self === top) {
        jDialogClose(callback);
    }
    // 弹出窗口级别调用
    else {
        window.parent.jDialogClose(callback);
    }
}

/**
 * 全局弹出模式窗口jDialog，关闭事件
 */
function jCancel() {
    // Windows级别调用
    if (self === top) {
        jDialogCancel();
    }
    // 弹出窗口级别调用
    else {
        window.parent.jDialogCancel();
    }
}

/**
 * 页面跳转通用方法，如果不使用则面包屑会出现错误
 *
 * @param title：标题名称
 * @param src：跳转路径
 * @param callback：回调事件
 */
function redirect(title, src, callback) {
    // 添加面包屑
    parent.frames[0].addBreadCrumb(title, src);
    if ($.trim(src) !== '') {
        window.location = src;
    }
    if (callback !== null) {
        callback.call(null);
    }
}

/**
 * 弹出Windows窗口，暂时有问题。
 *
 * @param evt
 */
function showWindwos(evt) {
    window.parent.parent.openWindwos(evt);
}

function populate(key, params) {
    alert('populate');
    var param = [];
    for (i = 0; i < params.length; i++) {
        var array = params[i].split(',');
        for (k = 0; k < array.length; k++) {
            param.push({
                name: 'params[' + k + '].key',
                value: key[i]
            });
            param.push({
                name: 'params[' + k + '].logic',
                value: 'and'
            });
            param.push({
                name: 'params[' + k + '].comparison',
                value: '='
            });
            param.push({
                name: 'params[' + k + '].value',
                value: array[k]
            });
        }
    }
    return param;
}