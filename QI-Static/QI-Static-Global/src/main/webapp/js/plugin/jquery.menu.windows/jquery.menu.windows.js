(function($) {
	$.menu = {
		callback : {},
		error : {},
		datastores : {
			data : null
		},
		data : {
			async : true, // 是否异步加载数据
			type : 'GET', // 请求方式 ("POST" 或 "GET")
			params : {},// 查询的参数
			url : '' // 发送请求的地址
		},
		lang : {
			loading : 'Loading ...',// 加载提示信息
			errormsg : '连接错误!'// 错误提示信息
		},
		mapping : {
			menuId : '',// 菜单ID
			menuUrl : '',// 菜单路径
			menuCode : '',// 菜单编号
			menuName : '',// 菜单名称
			iconPath : ''// 菜单图标路径
		},
		hidden : true,// 是否可隐藏
		title : '',// 菜单标题名称
		targetType : '',// 跳转类型(div,frame)
		target : '',// 跳转目标
		menuId : ''// 标题菜单ID
	};
	$.menu_component = function(t, c) {
		// 方法
		var f = {
			// 取消绑定方法
			unbind : function(fun) {
				$('#' + id).unbind(fun);
			},
			// 绑定方法
			bind : function(id, menuId) {
				$('#' + id).click(function() {
					f.dynamic(menuId);
				});
			},
			// 映射
			mapping : function(d) {
				var person = {};
				for ( var key in d) {
					if (c.mapping.menuId == key) {
						person.menuId = d[key];
					} else if (c.mapping.menuCode == key) {
						person.menuCode = d[key];
					} else if (c.mapping.menuUrl == key) {
						person.menuUrl = d[key];
					} else if (c.mapping.menuName == key) {
						person.menuName = d[key];
					} else if (c.mapping.iconPath == key) {
						person.iconPath = d[key];
					}
				}
				return person;
			},
			// 展开
			showStart : function(menuId) {
				// 背景样式
				$('#menu_title_' + menuId).attr('class',
						'menu-title mousefocus');
				$('div.start-sign').hide();
				// 四角圆弧框：展开宽度473
				$('#navigation_level').width(473);
				// 开始框：展开宽度494
				$(t).width(494);
			},
			// 收起
			closeStart : function(menuId) {
				// 背景样式
				$('#menu_title_' + menuId).attr('class', 'menu-title');
				$('div.start-sign').show();
				// 四角圆弧框：收起宽度250
				$('#navigation_level').width(250);
				// 开始框：收起宽度400
				$(t).width(400);
			},
			// 锁定
			lock : function(menuId) {
				var ricon = $('#menu_title_ricon_' + menuId);
				ricon.attr('class', 'rIcon-lock');
				$('#menu_title_' + menuId).css('padding-right', '9px');
				// 移除所有标题菜单mouseout和mouseover事件
				$('.category').each(function() {
					$(this).unbind('mouseenter');
					$(this).unbind('mouseleave');
				});
				// 移除menu_ulmouseout和mouseover事件
				$('#menu_ul').unbind('mouseenter');
				$('#menu_ul').unbind('mouseleave');
			},
			// 解锁
			unlock : function(menuId) {
				var ricon = $('#menu_title_ricon_' + menuId);
				ricon.attr('class', 'rIcon');
				$('#menu_title_' + menuId).css('padding-right', '5px');
				// 绑定所有标题菜单mouseout和mouseover事件
				$('.category').each(function() {
					var id = $(this).attr('id');
					id = id.substring(id.indexOf('_') + 1);
					$(this).mouseenter(function() {
						f.categoryMouseover(id);
					});

					$(this).mouseleave(function() {
						f.categoryMouseout(id);
					});
				});

				// 绑定menu_ulmouseout和mouseover事件
				$('#menu_ul').mouseenter(function() {
					f.menuUlMouseover();
				});
				$('#menu_ul').mouseleave(function() {
					f.menuUlMouseout();
				});
			},
			// 已有锁定
			ifLock : function(menuId) {
				$('.category').each(function() {
					var id = $(this).attr('id');
					id = id.substring(id.indexOf('_') + 1);
					var ricon = $('#menu_title_ricon_' + id);
					// 如果已有锁定的菜单
					if (ricon.attr('class') == 'rIcon-lock') {
						f.categoryMouseout(id);
						// 改变锁定图标为右箭头图标
						ricon.attr('class', 'rIcon');
						$('#menu_title_' + id).css('padding-right', '5px');
					}
				});
				f.categoryMouseover(menuId);
			},
			categoryClick : function(menuId) {
				var ricon = $('#menu_title_ricon_' + menuId);
				// 如果点击的菜单没锁定
				if (ricon.attr('class') == 'rIcon') {
					// 判断是否已有锁定菜单
					f.ifLock(menuId);
					f.lock(menuId);
				} else {// 如果已锁定
					f.unlock(menuId);
				}
			},
			categoryMouseover : function(menuId) {
				// 隐藏所有子菜单层显示
				f.replaceCalss();
				// 绑定/展示自己的子菜单
				f.dynamic(menuId);
				// 展开
				f.showStart(menuId);
				// 显示子菜单框
				$('#menu_ul').show();
			},
			categoryMouseout : function(menuId) {
				c.menuId = menuId;
				// 隐藏子菜单层显示
				$('#menu_li_' + menuId).hide();
				// 收起
				f.closeStart(menuId);
				$('#menu_ul').hide();
			},
			menuUlMouseover : function() {
				// 显示子菜单
				$('#menu_li_' + c.menuId).show();
				// 显示子菜单框
				$('#menu_ul').show();
				// 展开
				f.showStart(c.menuId);
			},
			menuUlMouseout : function() {
				// 收起子菜单
				$('#menu_li_' + c.menuId).hide();
				f.replaceCalss();
				// 收起子菜单框
				$('#menu_ul').hide();
				// 收起
				f.closeStart(c.menuId);
			},
			initMenu : function(data, menuId) {
				if (!data) {

				} else {
					var navigation_level = $('#navigation_level');
					var menu_title = $('#menu_title_' + menuId);
					// 子菜单框
					var menu_ul = $('#menu_ul');
					// 创建子菜单层
					var menu_li = $('<div>', {
						id : 'menu_li_' + menuId,
						'class' : 'MenuLi-div'
					});
					menu_ul.append(menu_li);

					$.each(data, function(i, d) {
						var e = null;
						// 如果没有设置映射对象,使用默认
						if (c.mapping.menuUrl == '') {
							e = d;
						} else {
							e = f.mapping(d);
						}
						// 子菜单
						var li_div = $('<div>', {
							'class' : 'MenuLi',
							text : e.menuName
						});

						li_div.mouseenter(function() {
							$(this).attr('class', 'MenuLi MenuLi-mouseover');
						});

						li_div.mouseleave(function() {
							$(this).attr('class', 'MenuLi');
						});

						var lIcon = $('<div>', {
							'class' : 'lIcon'
						});

						var icon = $('<img>', {
							'width' : 20,
							'height' : 20,
							'src' : e.iconPath
						});
						lIcon.append(icon);
						li_div.append(lIcon);

						li_div.click(function() {
							if (c.targetType == 'div') {
								$('#' + c.target).load(e.menuUrl, function() {
									if (null != c.callback) {
										c.callback.call(null, e);
									}
								});
							} else if (c.targetType == 'frame') {
								$('#' + c.target).attr('src', e.menuUrl);
								if (null != c.callback) {
									c.callback.call(null, e);
								}
							}
						});
						menu_li.append(li_div);
					});
				}
			},
			// 隐藏所有子菜单层
			replaceCalss : function() {
				$('.MenuLi-div').each(function() {
					$(this).hide();
				});
			},
			dynamic : function(menuId) {
				// 当前点击的菜单下的展示DIV
				var menu_li = $('#menu_li_' + menuId);
				// 如果子菜单层已经创建
				if (menu_li.length > 0) {
					if (menu_li.is(":hidden")) {
						menu_li.show();
					}
				} else {
					// 读取数据
					c.datastores.ajax().findMenu(c.data, {
						menuId : menuId
					}, f.initMenu, menuId);
				}
			},
			init : function(result) {
				if (result == true) {
					var data = $.menu.datastores.data;
					// 四角圆弧框
					var navigation_level = $('<div>', {
						id : 'navigation_level',
						'class' : 'absolute menu-content'
					});
					// 菜单框
					var menu_level = $('<div>', {
						id : 'menu_level',
						'class' : 'MenuLevel'
					});
					// 设置底层四角圆弧框宽度
					navigation_level.width(250);
					// 设置菜单宽度
					menu_level.width(250);
					// 设置菜单高度
					menu_level.height(470);
					// 子菜单框
					var menu_ul = $('<div>', {
						id : 'menu_ul',
						'class' : 'MenuUL absolute'
					});
					menu_ul.css("display", "none");
					// 子菜单层绑定方法
					menu_ul.mouseenter(function() {
						f.menuUlMouseover();
					});
					menu_ul.mouseleave(function() {
						f.menuUlMouseout();
					});
					$.each(data, function(i, d) {
						var e = null;
						// 如果没有设置映射对象,使用默认
						if (c.mapping.menuUrl == '') {
							e = d;
						} else {
							e = f.mapping(d);
						}
						var category_div = $('<div>', {
							id : 'category_' + e.menuId,
							'class' : 'category'
						});
						var menu_title = $('<div>', {
							id : 'menu_title_' + e.menuId,
							'class' : 'menu-title'
						});
						menu_title.css('padding-right', '5px');

						var lIcon = $('<div>', {
							id : 'menu_lIcon',
							'class' : 'lIcon'
						});
						var icon = $('<img>', {
							id : 'menu_icon',
							'width' : 32,
							'height' : 32,
							'src' : e.iconPath
						});
						lIcon.append(icon);

						menu_title.append(lIcon);
						var cIcon = $('<div>', {
							id : 'menu_cIcon',
							'class' : 'cIcon',
							text : e.menuName
						});
						menu_title.append(cIcon);
						var rIcon = $('<div>', {
							id : 'menu_title_ricon_' + e.menuId,
							'class' : 'rIcon'
						});
						menu_title.append(rIcon);

						category_div.mouseenter(function() {
							f.categoryMouseover(e.menuId);
						});

						category_div.mouseleave(function() {
							f.categoryMouseout(e.menuId);
						});
						// 标题菜单点击事件
						category_div.click(function() {
							f.categoryClick(e.menuId);
						});

						category_div.append(menu_title);
						menu_level.append(category_div);
					});
					var search_level = $(
							'<div>',
							{
								id : 'menu_search_level',
								'class' : 'search-level-w',
								'html' : '<input type="text" class="search-input" id="menu_find" />'
							});

					// var logout_div = $('<div>', {
					// 'class' : 'abs start-logout'
					// });
					// logout_div.click(function(){
					// logout();
					// });
					// logout_div.html('注销');
					navigation_level.append(menu_level);
					navigation_level.append(menu_ul);
					navigation_level.append(search_level);
					// navigation_level.append(logout_div);
					$(t).append(navigation_level);
					$(t).width(400);
				} else if (typeof (result) == "string") {
					c.error.call(null, result);
				}
			}
		};
		c.datastores.ajax().initMenu(c.data, f.init);
	};
	$.fn.menu = function(opts) {
		return this.each(function() {
			if (opts != null) {
				var conf = $.extend($.menu, opts);
				$.menu_component(this, conf);
			}
		});
	};
})(jQuery);

// 创建数据源
(function($) {
	$.extend($.menu.datastores, {
		ajax : function() {
			return {
				/**
				 * 初始化菜单
				 * 
				 * @param data
				 *            jquery.ajax参数
				 * @param callback
				 *            回调方法
				 * @returns
				 */
				initMenu : function(data, callback) {
					if (data.url) {
						$.ajax({
							type : data.type,
							url : data.url,
							data : data.params,
							dataType : "json",
							success : function(d, textStatus) {
								if (null != d) {
									$.menu.datastores.data = d;
									callback.call(null, true);
								} else {
									callback.call(null, "返回结果对象为空！");
								}
							},
							error : function(xhttp, textStatus, errorThrown) {
								callback.call(null, errorThrown);
							}
						});
					} else {
						callback.call(null, "ajax链接路径为空！");
					}
				},
				// 根据params查询菜单
				findMenu : function(data, params, callback, menuId) {
					$.ajax({
						type : data.type,
						url : data.url,
						data : params,
						dataType : "json",
						success : function(d, textStatus) {
							callback.call(null, d, menuId);
						},
						error : function(xhttp, textStatus, errorThrown) {
							callback.call(null, false, menuId);
						}
					});
				}
			};
		}
	});
})(jQuery);