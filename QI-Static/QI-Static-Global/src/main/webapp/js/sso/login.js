///**
// * 初始化遮罩
// */
//function initOverlay() {
//    $('#loading_img').css('top', (win.height - 180) / 2);
//    $('#loading_img').css('left', (win.width - 90) / 2);
//}

/**
 * 背景填充<br>
 * 高度或宽度中最小的一边为主，填充至最大。垂直居中对齐。
 */
function fillBackground(oBgImg) {

}

/**
 * 背景自适应<br>
 * 高度或宽度中最大的一边为主，填充至最大。垂直居中对齐。
 */
function adaptiveBackground(oBgImg) {
    var nImgHeight = oBgImg.height();
    var nImgWidth = oBgImg.width();

    // 判断，如果图片的高度大于最大高度时
    if (nImgHeight > win.height) {
        oBgImg.height(win.height);
        oBgImg.width(win.height * (nImgWidth / nImgHeight));
        oBgImg.css('left', (win.width - oBgImg.width()) / 2 + 'px');
        oBgImg.css('top', '0px');

    } else
    // 判断，如果图片的宽度大于最大宽度时，同时高度小于最小高度时
    if (nImgWidth > win.width) {
        oBgImg.width(win.width);
        oBgImg.height(win.width * (nImgHeight / nImgWidth));
        oBgImg.css('top', (win.height - oBgImg.height()) / 2 + 'px');
        oBgImg.css('left', '0px');

    } else
    // 图片的宽度和高度小于最大宽度和高度
    {
        // 如果图片宽度比例大于桌面宽度比例
        if ((nImgWidth / nImgHeight) > (win.width / win.height)) {
            oBgImg.width(win.width);
            oBgImg.height(win.width * (nImgHeight / nImgWidth));
            oBgImg.css('top', (win.height - oBgImg.height()) / 2 + 'px');
            oBgImg.css('left', '0px');
        } else {
            oBgImg.height(win.height);
            oBgImg.width(win.height * (nImgWidth / nImgHeight));
            oBgImg.css('left', (win.width - oBgImg.width()) / 2 + 'px');
            oBgImg.css('top', '0px');
        }

        // oBgImg.css('top', (win.height - oBgImg.height()) / 2 + 'px');
        // oBgImg.css('left', (win.width - oBgImg.width()) / 2 + 'px');
    }
}

/**
 * 背景拉伸<br>
 * 图片高度宽度和背景高度宽度一样。
 */
function tensileBackground(oBgImg) {

}

/**
 * 背景居中<br>
 * 图片默认高度宽度，垂直居中对齐。
 */
function centeredBackground(oBgImg) {

}

/**
 * 初始化定位
 */
function initPosition() {
    $('#main_panel').css({
        'top': (win.height - 420) / 2,
        'left': (win.width - 390) / 2
    });
    // chrome浏览器特殊处理
    if (browser.type == 'chrome') {
        $('#themeImg').load(function () {
            adaptiveBackground($(this));
        });
    } else {
        adaptiveBackground($('#themeImg'));
    }
}

/**
 * 显示警告
 *
 * @param responseText
 */
function showWarning(responseText) {
    $('#warning_msg').text(responseText);
    $('#warning_div').show();
}

/**
 * 初始化登录form样式
 */
function initForm() {
    if ($('#warning_msg').text() != '') {
        $('#warning_div').show();
    }
}

/**
 * 显示登录form
 */
function showForm() {
    var panel = $('#main_panel');
    if (panel.is(":hidden")) {
        panel.fadeIn('normal');
    } else {
        panel.fadeOut('normal');
    }
}

/**
 * 记住帐号checkbox
 */
//function rmbUserChange() {
//    var rmb = $("#rmbUser");
//    if (rmb.is(':checked')) {
//        rmb.prop("checked", false);
//    } else {
//        rmb.prop("checked", true);
//    }
//}

/**
 * 获取验证码
 */
function getAuthCode() {
    // 获取当前的时间作为参数，无具体意义
    var timenow = new Date().getTime();
    // 每次请求需要一个不同的参数，否则可能会返回同样的验证码
    // 这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。
    $('#authCode').attr('src', webroot + "/system/portal!getAuthCode.action?d=" + timenow);
}

/**
 * 窗口缩放事件
 */
window.onresize = function () {
    initWindow();
    initPosition();
};

$(function () {
    initPosition();
    initForm();
    jQuery.hotkeys.add('return', login);
});
