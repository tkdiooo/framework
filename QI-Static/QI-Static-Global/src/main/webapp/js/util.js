/**
 * 工具类js
 */

/**
 * 创建css/js文件
 */
function createjscssfile(filename, filetype) {
    if (filetype == "js") {
        var fileref = document.createElement('script')
        fileref.setAttribute("type", "text/javascript")
        fileref.setAttribute("src", filename)
    } else if (filetype == "css") {
        var fileref = document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", filename)
    }
    return fileref
}

/**
 * 动态加载一个js/css文件
 *
 * @param filename
 * @param filetype
 */
function loadjscssfile(filename, filetype) {
    var fileref = createjscssfile(filename, filetype)
    if (typeof fileref != "undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}

/**
 * 替换已经加载的js/css文件
 *
 * @param oldfilename
 * @param newfilename
 * @param filetype
 */
function replacejscssfile(oldfilename, newfilename, filetype) {
    var targetelement = (filetype == "js") ? "script"
        : (filetype == "css") ? "link" : "none"
    var targetattr = (filetype == "js") ? "src" : (filetype == "css") ? "href"
        : "none"
    // 获取所有的script/link标签
    var allsuspects = document.getElementsByTagName(targetelement)
    for (var i = allsuspects.length; i >= 0; i--) {
        // 如果不等于空，并且匹配
        if (allsuspects[i]
            && allsuspects[i].getAttribute(targetattr) != null
            && allsuspects[i].getAttribute(targetattr).indexOf(oldfilename) != -1) {
            // 创建新的文件
            var newelement = createjscssfile(newfilename, filetype)
            // 替换
            allsuspects[i].parentNode.replaceChild(newelement, allsuspects[i])
        }
    }
}

/**
 * 删除已经加载过的js/css文件
 *
 * @param filename
 * @param filetype
 */
function removejscssfile(filename, filetype) {
    var targetelement = (filetype == "js") ? "script"
        : (filetype == "css") ? "link" : "none"
    var targetattr = (filetype == "js") ? "src" : (filetype == "css") ? "href"
        : "none"
    var allsuspects = document.getElementsByTagName(targetelement)
    for (var i = allsuspects.length; i >= 0; i--) {
        if (allsuspects[i]
            && allsuspects[i].getAttribute(targetattr) != null
            && allsuspects[i].getAttribute(targetattr).indexOf(filename) != -1)
            allsuspects[i].parentNode.removeChild(allsuspects[i])
    }
}

/**
 * 替换域，跨域调用方法使用
 */
function domain() {
    var _surl = String(document.domain);
    if (_surl.lastIndexOf(".") != -1) {
        var _surl1 = _surl.substring(0, _surl.lastIndexOf("."));
        if (_surl1.lastIndexOf(".") != -1) {
            _surl1 = _surl1.substring(_surl1.lastIndexOf(".") + 1, _surl1.length);
        }
        _surl = _surl1 + _surl.substring(_surl.lastIndexOf("."), _surl.length);
    }
    //noinspection JSAnnotator
    document.domain = _surl;
}

/**
 * javascript map
 * @constructor
 */
function Map() {
    this.elements = new Array();

    //获取MAP元素个数
    this.size = function () {
        return this.elements.length;
    }

    //判断MAP是否为空
    this.isEmpty = function () {
        return (this.elements.length < 1);
    }

    //删除MAP所有元素
    this.clear = function () {
        this.elements = new Array();
    }

    //向MAP中增加元素（key, value)
    this.put = function (_key, _value) {
        this.elements.push({
            key: _key,
            value: _value
        });
    }

    //删除指定KEY的元素，成功返回True，失败返回False
    this.remove = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    this.elements.splice(i, 1);
                    return true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }

    //获取指定KEY的元素值VALUE，失败返回NULL
    this.get = function (_key) {
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    return this.elements[i].value;
                }
            }
        } catch (e) {
            return null;
        }
    }

    //获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
    this.element = function (_index) {
        if (_index < 0 || _index >= this.elements.length) {
            return null;
        }
        return this.elements[_index];
    }

    //判断MAP中是否含有指定KEY的元素
    this.containsKey = function (_key) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].key == _key) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }

    //判断MAP中是否含有指定VALUE的元素
    this.containsValue = function (_value) {
        var bln = false;
        try {
            for (i = 0; i < this.elements.length; i++) {
                if (this.elements[i].value == _value) {
                    bln = true;
                }
            }
        } catch (e) {
            bln = false;
        }
        return bln;
    }

    //获取MAP中所有VALUE的数组（ARRAY）
    this.values = function () {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].value);
        }
        return arr;
    }

    //获取MAP中所有KEY的数组（ARRAY）
    this.keys = function () {
        var arr = new Array();
        for (i = 0; i < this.elements.length; i++) {
            arr.push(this.elements[i].key);
        }
        return arr;
    }
}

function invokingFunction(callback, data) {
    if (callback != null) {
        if (typeof callback === "function") {
            callback(data);
        } else {
            var func = eval(callback);
            new func(data);
        }
    }
}

Date.prototype.Format = function (fmt) {
    //author: meizz
    var o =
    {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


Date.prototype.addDays = function (d) {
    this.setDate(this.getDate() + d);
};


Date.prototype.addWeeks = function (w) {
    this.addDays(w * 7);
};


Date.prototype.addMonths = function (m) {
    var d = this.getDate();
    this.setMonth(this.getMonth() + m);

    if (this.getDate() < d)
        this.setDate(0);
};


Date.prototype.addYears = function (y) {
    var m = this.getMonth();
    this.setFullYear(this.getFullYear() + y);

    if (m < this.getMonth()) {
        this.setDate(0);
    }
};

Number.prototype.add = function (arg) {
    var r1, r2, m;
    try {
        r1 = arg.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = this.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    return (arg * m + this * m) / m
};

Number.prototype.sub = function (arg) {
    var r1, r2, m, n;
    try {
        r1 = arg.toString().split(".")[1].length
    } catch (e) {
        r1 = 0
    }
    try {
        r2 = this.toString().split(".")[1].length
    } catch (e) {
        r2 = 0
    }
    m = Math.pow(10, Math.max(r1, r2))
    n = (r1 >= r2) ? r1 : r2;

    return ((arg * m - this * m) / m).toFixed(n);
};


Number.prototype.mul = function (arg) {
    var m = 0, s1 = arg.toString(), s2 = this.toString();
    try {
        m += s1.split(".")[1].length
    } catch (e) {
    }
    try {
        m += s2.split(".")[1].length
    } catch (e) {
    }
    return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
};

Number.prototype.div = function (arg) {
    var t1 = 0, t2 = 0, r1, r2;
    try {
        t1 = this.toString().split(".")[1].length
    } catch (e) {
    }
    try {
        t2 = arg.toString().split(".")[1].length
    } catch (e) {
    }
    with (Math) {
        r1 = Number(this.toString().replace(".", ""))
        r2 = Number(arg.toString().replace(".", ""))
        return (r1 / r2) * pow(10, t2 - t1);
    }
};

/**
 * 处理数字
 * @param num 要四舍五入的数
 * @param v 表示要保留的小数位数
 * @returns {number}
 */
function decimal(num, v) {
    var vv = Math.pow(10, v);
    return Math.round(num * vv) / vv;
}