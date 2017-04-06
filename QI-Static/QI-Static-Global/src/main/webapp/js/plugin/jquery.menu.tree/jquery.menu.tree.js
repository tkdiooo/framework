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
			menuId : 'menuId',// 菜单ID
			menuUrl : 'menuUrl',// 菜单路径
			menuCode : 'menuCode',// 菜单编号
			menuName : 'menuName',// 菜单名称
			iconPath : 'iconPath',// 菜单图标路径
			children : 'children',// 子节点
			isLeaf : 'isLeaf',// 是否是叶子（0：否、1：是）
			level : 'level' // 所处层级
		},
		selected : '',// 默认选中ID
		isLazy : true,// 是否延迟加载
		spread : false,// 是否展开
		title : '',// 菜单标题名称
		targetType : '',// 跳转类型(iframe,frameset,function)
		target : ''// 跳转目标
	};
	$.menu_component = function(t, c) {
		// 方法
		var f = {
			// 错误
			error : function(msg) {
				alert(msg);
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
					} else if (c.mapping.children == key) {
						person.children = d[key];
					} else if (c.mapping.isLeaf == key) {
						person.isLeaf = d[key];
					} else if (c.mapping.level == key) {
						person.level = d[key];
					}
				}
				return person;
			},
			// 鼠标悬停样式
			menuHover : function(menu) {
				menu.hover(function() {
					// $(this).addClass('menu-title-hover');
					$(this).find('.menu-title-left').addClass('menu-title-left-hover');
					$(this).find('.menu-title-bg').addClass('menu-title-bg-hover');
					$(this).find('.menu-title-right').addClass('menu-title-right-hover');
				}, function() {
					// $(this).removeClass('menu-title-hover');
					$(this).find('.menu-title-left').removeClass('menu-title-left-hover');
					$(this).find('.menu-title-bg').removeClass('menu-title-bg-hover');
					$(this).find('.menu-title-right').removeClass('menu-title-right-hover');
				});
			},
			// 点击后鼠标悬停样式
			menuClickhover : function(menu) {
				menu.hover(function() {
					// 获取焦点时：添加深蓝色焦点效果
					$(this).find('.menu-title-left').addClass('menu-title-left-hover-blue');
					$(this).find('.menu-title-bg').addClass('menu-title-bg-hover-blue');
					$(this).find('.menu-title-right').addClass('menu-title-right-hover-blue');
					// 获取焦点时：移除白色焦点效果
					$(this).find('.menu-title-left').removeClass('menu-title-left-hover-choose');
					$(this).find('.menu-title-bg').removeClass('menu-title-bg-hover-choose');
					$(this).find('.menu-title-right').removeClass('menu-title-right-hover-choose');
				}, function() {
					// 失去焦点时：移除深蓝色焦点效果
					$(this).find('.menu-title-left').removeClass('menu-title-left-hover-blue');
					$(this).find('.menu-title-bg').removeClass('menu-title-bg-hover-blue');
					$(this).find('.menu-title-right').removeClass('menu-title-right-hover-blue');
					// 失去焦点时：添加白色焦点效果
					$(this).find('.menu-title-left').addClass('menu-title-left-hover-choose');
					$(this).find('.menu-title-bg').addClass('menu-title-bg-hover-choose');
					$(this).find('.menu-title-right').addClass('menu-title-right-hover-choose');
				});
			},
			// 菜单悬停样式回复
			menuRestore : function() {
				if ($('.menu-level').find('.menu-title-left-hover-choose').length > 0) {
					var menu = $('.menu-level').find('.menu-title-left-hover-choose').parent();
					$('.menu-level').find('.menu-title-left-hover-choose').removeClass('menu-title-left-hover-choose');
					$('.menu-level').find('.menu-title-bg-hover-choose').removeClass('menu-title-bg-hover-choose');
					$('.menu-level').find('.menu-title-right-hover-choose').removeClass('menu-title-right-hover-choose');
					menu.unbind('hover');
					f.menuHover(menu);
				}
			},
			// 初始化标题拉伸层
			initResize : function(menu, e) {
				// 左边底层样式
				var mt_left = $('<div>', {
					'id' : 'mt_left_' + e.menuId,
					'class' : 'menu-title-left'
				});
				menu.append(mt_left);

				// 中间可拉伸底层样式
				var mt_bg = $('<div>', {
					'id' : 'mt_bg_' + e.menuId,
					'class' : 'menu-title-bg'
				});
				menu.append(mt_bg);

				// 右边底层样式
				var mt_right = $('<div>', {
					'id' : 'mt_right_' + e.menuId,
					'class' : 'menu-title-right'
				});
				menu.append(mt_right);

				// 创建缩放图标
				if (e.isLeaf != undefined) {
					// 缩放图标
					var shrink = $('<div>', {
						'id' : 'shrink_icon_' + e.menuId,
						'class' : 'zoom'
					});
					// 如果有子节点，添加缩放图标
					if (e.isLeaf == 0) {
						shrink.addClass('keep');
					}

					// 根据层级缩进
					shrink.css('margin-left', (10 * e.level) + 'px');
					// 悬停效果
					shrink.hover(function() {
						if ($(this).hasClass('keep')) {
							$(this).addClass('keep-hover');
						} else {
							$(this).addClass('spread-hover');
						}
					}, function() {
						if ($(this).hasClass('keep')) {
							$(this).removeClass('keep-hover');
						} else {
							$(this).removeClass('spread-hover');
						}
					});
					// 点击事件
					shrink.click(function() {
						var menuId = $(this).attr('id');
						menuId = menuId.substring(menuId.lastIndexOf('_') + 1);
						var menu_div = $('#menu_' + e.menuId);
						// 如果已经有数据
						if (menu_div.find('.menu-li').length > 0) {
							f.menuZoom(menuId);
						} else {
							// 如果e.isLeaf不为空，并且有子节点，加载请求数据库加载数据
							if (undefined != e.isLeaf && e.isLeaf == 0) {
								// 如果配置了延迟加载，请求加载数据，如果没有配置，则不做处理
								if (c.isLazy) {
									c.datastores.ajax().findMenu(c.data, {
										menuId : menuId
									}, f.dataLoad, menuId);
								}
							}
						}
					});
					mt_bg.append(shrink);
				}

				// 文本内容
				var content = $('<div>', {
					'class' : 'title-icon',
					'text' : e.menuName
				});
				mt_bg.append(content);

				// 创建菜单图标
				if (e.iconPath != undefined) {
					// 菜单图标
					var icon = $('<img>', {
						id : 'menu_icon',
						'width' : 20,
						'height' : 20,
						'class' : 'title-icon',
						'src' : e.iconPath
					});
					// 图标根据层级定位
					icon.css('left', ((7 * (e.level + 1)) + (5 * (e.level + 1))) + 'px');
					mt_bg.append(icon);
					// 文本内容根据层级定位
					content.css('left', ((7 * (e.level + 1)) + (5 * (e.level + 1)) + 25) + 'px');
				} else {
					// 没有菜单图标，文本内容根据层级定位
					content.css('left', ((7 * (e.level + 1)) + (5 * (e.level + 1))) + 'px');
				}
			},
			// 菜单单击事件
			menuClick : function(menu, e) {
				// 子节点菜单鼠标点击
				menu.click(function() {
					// 菜单单击样式变更
					f.dynamic(e.menuId, $(this));
				});

				// 执行方法
				if(c.targetType == 'function') {
					menu.click(function() {
						c.target.call(null, e);
					});
				} else 
				// 如果菜单路径不为空，单击执行页面跳转
				if (e.menuUrl != null && e.menuUrl != '') {
					if (c.targetType == 'frameset') {
						menu.click(function() {
							var path = webroot
									+ '/basic/windows!toBreadCrumb.action?frameid='
									+ parent.frames[2].name + '&src='
									+ e.menuUrl + '&title='
									+ e.menuName;
							parent.frames[0].location.href = path;
							parent.frames[2].location.href = e.menuUrl;
						});
					} else if(c.targetType == 'iframe'){
						menu.click(function() {
							$('#' + c.target).attr('src', e.menuUrl);
						});
					} 
				}
			},
			// 菜单缩放事件
			menuZoom : function(menuId) {
				// 触发收缩效果
				var menu_div = $('#menu_' + menuId);
				var sim = $('#shrink_icon_' + menuId);
				// 如果鼠标悬停，是合拢样式
				if (sim.hasClass('keep-hover')) {
					// 移除鼠标悬停合拢样式
					sim.removeClass('keep-hover');
					// 添加鼠标悬停展开样式
					sim.addClass('spread-hover');
				} else 
				// 如果鼠标悬停，是展开样式
				if (sim.hasClass('spread-hover')) {
					// 移除鼠标悬停展开样式
					sim.removeClass('spread-hover');
					// 添加鼠标悬停合拢样式
					sim.addClass('keep-hover');
				}
				// 如果没有展开
				if (sim.hasClass('keep')) {
					sim.removeClass('keep');
					sim.addClass('spread');
					menu_div.show();
				} else {
					sim.addClass('keep');
					sim.removeClass('spread');
					menu_div.hide();
				}
			},
			// 菜单双击事件
			menuDBlClick : function(menu, e) {
				menu.dblclick(function() {
					var menuId = menu.attr('id');
					menuId = menuId.substring(menuId.lastIndexOf('_') + 1);
					var menu_div = $('#menu_' + e.menuId);
					// 如果已经有数据
					if (menu_div.find('.menu-li').length > 0) {
						f.menuZoom(menuId);
					} else {
						// 如果e.isLeaf不为空，并且有子节点，加载请求数据库加载数据
						if (undefined != e.isLeaf && e.isLeaf == 0) {
							// 如果配置了延迟加载，请求加载数据，如果没有配置，则不做处理
							if (c.isLazy) {
								c.datastores.ajax().findMenu(c.data, {
									menuId : menuId
								}, f.dataLoad, menuId);
							}
						}
					}
				});
			},
			// 数据加载
			dataLoad : function(data, menuId) {
				f.initMenu(data, menuId);
				f.menuZoom(menuId);
			},
			initMenu : function(data, menuId, menu_div) {
				if (!data) {

				} else {
					var titleId = 'menu_title_' + menuId;
					if (null == menu_div) {
						menu_div = $('#menu_' + menuId);
					}
					$.each(data, function(i, d) {
						var e = null;
						// 如果没有设置映射对象,使用默认
						if (c.mapping.menuUrl == '') {
							e = d;
						} else {
							e = f.mapping(d);
						}

						var category_div = $('<div>', {
							'class' : 'category'
						});

						// 标题菜单底层
						var menu_li = $('<div>', {
							'id' : 'menu_li_' + e.menuId,
							'name' : 'menu_choose_' + e.menuId,
							'class' : 'menu-li'
						});

						// 拖拽背景样式
						f.initResize(menu_li, e);

						// root菜单鼠标悬停样式
						f.menuHover(menu_li);

						// 鼠标单击事件
						f.menuClick(menu_li, e);

						// 鼠标双击事件
						f.menuDBlClick(menu_li, e);

						// children菜单层
						var menu_li_div = $('<div>', {
							id : 'menu_' + e.menuId,
							name : 'menu_div'
						});

						// 如果子节点不为空，初始化子节点
						if (e.children.length > 0) {
							f.initMenu(e.children, e.menuId, menu_li_div);
						}

						menu_div.css('display', 'none');
						category_div.append(menu_li);
						category_div.append(menu_li_div);
						menu_div.append(category_div);
					});
				}
			},
			// 菜单动态样式
			dynamic : function(menuId, mt) {
				// 移除选中的菜单样式
				f.menuRestore();
				// 添加深蓝色焦点效果
				mt.find('.menu-title-left').addClass('menu-title-left-hover-blue');
				mt.find('.menu-title-bg').addClass('menu-title-bg-hover-blue');
				mt.find('.menu-title-right').addClass('menu-title-right-hover-blue');
				// 鼠标悬停样式修改
				f.menuClickhover(mt);
			},
			init : function(data) {
				if (typeof (data) == "string") {
					f.error(data);
				} else if (!data) {

				} else {
					document.body.onselectstart = document.body.ondrag = function() {
						return false;
					};
					data = $.menu.datastores.data;

					var menu_level = $('<div>', {
						'class' : 'menu-level'
					});

					// IE下文本不能选择
					menu_level[0].onselectstart = function() {
						return false;
					}

					// 缩放淡入淡出效果
					menu_level.hover(function() {
						$(this).find('.zoom').fadeToggle(500);
					}, function() {
						$(this).find('.zoom').fadeToggle(500);
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
							'class' : 'category'
						});

						// 标题菜单底层
						var menu_title = $('<div>', {
							'id' : 'menu_title_' + e.menuId,
							'name' : 'menu_choose_' + e.menuId,
							'class' : 'menu-title'
						});

						// 拖拽背景样式
						f.initResize(menu_title, e);

						// root菜单鼠标悬停样式
						f.menuHover(menu_title);

						// 菜单单击事件
						f.menuClick(menu_title, e);

						// 菜单双击事件
						f.menuDBlClick(menu_title, e);

						// children菜单层
						var menu_div = $('<div>', {
							id : 'menu_' + e.menuId,
							name : 'menu_div'
						});

						// 如果子节点不为空，初始化子节点
						if (e.children.length > 0) {
							f.initMenu(e.children, e.menuId, menu_div);
						}

						var separate_div = $('<div>', {
							'class' : 'separate-line'
						})

						menu_div.css('display', 'none');
						category_div.append(menu_title);
						category_div.append(menu_div);
						category_div.append(separate_div);
						menu_level.append(category_div);
					});
					$(t).append(menu_level);
					$(t).addClass('noSelect');

					// 是否展开
					if (c.spread) {
						$("div[name='menu_div']").each(function() {
							// 如果子节点有数据
							if ($(this).find('.menu-li').length) {
								var mid = $(this).attr('id').substring(5);
								var sim = $('#shrink_icon_' + mid);
								sim.removeClass('keep');
								sim.addClass('spread');
								$(this).show();
							}
						});
					}
					// 默认选中
					if (c.selected != '') {
						var menu = null;
						if (c.selected == '0') {
							menu = $('#menu_title_' + c.selected);
						} else {
							$('.menu-li').each(function() {
								var menuid = $(this).attr('id').substring(8);
								if (menuid == c.selected) {
									menu = $(this);
								}
							});
						}
						f.dynamic(c.selected, menu);
						// 移除深蓝色焦点效果
						menu.find('.menu-title-left').removeClass('menu-title-left-hover-blue');
						menu.find('.menu-title-bg').removeClass('menu-title-bg-hover-blue');
						menu.find('.menu-title-right').removeClass('menu-title-right-hover-blue');
						// 添加白色焦点效果
						menu.find('.menu-title-left').addClass('menu-title-left-hover-choose');
						menu.find('.menu-title-bg').addClass('menu-title-bg-hover-choose');
						menu.find('.menu-title-right').addClass('menu-title-right-hover-choose');
					}
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
								$.menu.datastores.data = d;
								callback.call(null, true);
							},
							error : function(xhttp, textStatus, errorThrown) {
								callback.call(null, false);
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