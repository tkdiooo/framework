/**
 * @author 张麒 2016/9/6.
 * @version Description:
 */
var last;
function search(event) {
    last = event.timeStamp;
    //设时延迟0.5s执行
    setTimeout(function () {
        //如果时间差为0（也就是你停止输入0.5s之内都没有其它的keyup事件发生）则做你想要做的事
        if (last - event.timeStamp === 0) {
            //做你要做的事情
            var ipt = $('#fullText');
            var icon = ipt.next('.input-icon-button').find('.ipt-icon');
            if ($.trim(ipt.val()) !== '') {
                if (!icon.hasClass('ipt-icon-clear')) {
                    icon.addClass('ipt-icon-clear');
                }
                gridSearch('fullText', ipt.val());
            } else if (icon.hasClass('ipt-icon-clear')) {
                icon.removeClass('ipt-icon-clear');
                reload();
            }
        }
    }, 500);
}
function fullClick(node) {
    var ipt = $('#fullText');
    var icon = $(node).find('.ipt-icon');
    if (icon.hasClass('ipt-icon-clear')) {
        icon.removeClass('ipt-icon-clear');
        if ($.trim(ipt.val()) !== '') {
            ipt.val('');
            reload();
        }
    } else if ($.trim(ipt.val()) === '') {
        ipt.focus();
    } else {
        icon.addClass('ipt-icon-clear');
    }
}

/*---------------------------------------------------------search---------------------------------------------------------*/

function showSelect(node) {
    var menulink = $(node).next('.toolbar-link-menu');
    if (menulink.is(':hidden')) {
        $(".toolbar-link-menu").fadeOut(100);
        menulink.fadeIn(100);
    } else {
        menulink.fadeOut(100);
    }
}
function hideSelect(node) {
    $(node).find('.toolbar-link-menu').fadeOut(100);
}
function confirmSelect(node, callback) {
    var func = eval(callback);
    new func($(node).attr('id'), $(node).text());
    $(node).parent('.toolbar-link-menu').fadeOut(100);
}

/*---------------------------------------------------------select---------------------------------------------------------*/
function initTree(node, mapping, kv) {
    if ($.trim($('#' + mapping).val()) === '') {
        return;
    }
    var data = {"guid": $('#' + mapping).val()};
    var opt = {waiting: false};
    ajax_action($('#' + mapping).attr('action'), data, opt, function (data) {
        if (data && $(node).next().find('.tree-menu-item').length <= 0) {
            var tree = $(node).next();
            $.each(data, function (i) {
                var item = '<div class="tree-menu-item"';
                if (!this.leaf) {
                    item += 'onmouseleave="clearTree(this);"';
                }
                item += '><div class="menu-list-button" onclick="confirmTree(this, \'' + kv + '\', \'' + mapping + '\');"';
                if (!this.leaf) {
                    item += 'onmouseenter="loadTree(this, \'' + kv + '\', \'' + mapping + '\');"';
                }
                item += '><span class="button-text" id="' + this.nodeID + '">' + this.nodeName + '</span>';
                if (!this.leaf) {
                    item += '<span class="button-right-arrow"></span>';
                }
                item += '</div></div>';
                tree.append(item);
            });
        }
        if ($(node).next().find('.tree-menu-item').length > 0) {
            showTree(node);
        }
    })
}

function loadTree(node, kv, mapping) {
    if ($(node).next('.tree-menu-list').length == 1) {
        showTree(node);
    } else {
        var data = {"guid": $(node).find('.button-text').attr('id')};
        var opt = {waiting: false};
        ajax_action($('#' + kv + 'id').parent('.form-input-multi').attr('action'), data, opt, function (data) {
            if (data.success) {
                var tree = $('<div class="tree-menu-list"></div>');
                $.each(data.result, function (i) {
                    var item = '<div class="tree-menu-item"';
                    if (!this.leaf) {
                        item += 'onmouseleave="clearTree(this);"';
                    }
                    item += '><div class="menu-list-button" onclick="confirmTree(this, \'' + kv + '\', \'' + mapping + '\');"';
                    if (!this.leaf) {
                        item += 'onmouseenter="loadTree(this, \'' + kv + '\', \'' + mapping + '\');"';
                    }
                    item += '><span class="button-text" id="' + this.nodeID + '">' + this.nodeName + '</span>';
                    if (!this.leaf) {
                        item += '<span class="button-right-arrow"></span>';
                    }
                    item += '</div></div>';
                    tree.append(item);
                });
                $(node).after(tree);
                showTree(node);
            }
        })
    }
}

function clearTree(node) {
    $(node).find('.tree-menu-list').hide(100);
}
function toggleTree(node) {
    $(node).next('.tree-menu-list').toggle(100);
}
function showTree(node) {
    $(node).next('.tree-menu-list').show(100);
}
function hideTree(id) {
    var tree = $('#' + id);
    tree.find('.tree-menu-list').hide(100);
    tree.hide(100);
}
function emptyMapping(mapping) {
    $('#' + mapping + 'id').val(0);
    $('#' + mapping + 'name').val('');
    $('#' + mapping + '_tree').empty();
}
function confirmTree(node, kv, mapping) {
    $('#' + kv + 'id').val($(node).find('.button-text').attr('id'));
    $('#' + kv + 'name').val($(node).find('.button-text').text());
    if (mapping && $.trim(mapping) != '') {
        emptyMapping(mapping);
    }
    hideTree(kv + '_tree');
}

/*---------------------------------------------------------tree---------------------------------------------------------*/