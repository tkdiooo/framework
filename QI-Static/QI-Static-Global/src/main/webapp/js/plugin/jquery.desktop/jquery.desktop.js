//
// Namespace - Module Pattern.
//
var mask_status = false;
var JQD = (function($, window, document, undefined) {
	// Expose innards of JQD.
	return {
		go : function() {
			for ( var i in JQD.init) {
				JQD.init[i]();
			}
		},
		init : {
			frame_breaker : function() {
				if (window.location !== window.top.location) {
					window.top.location = window.location;
				}
			},
			//
			// Initialize the clock.
			//
			clock : function() {
				var clock = $('#clock');

				if (!clock.length) {
					return;
				}

				// Date variables.
				var date_obj = new Date();
				var hour = date_obj.getHours();
				var minute = date_obj.getMinutes();
				var day = date_obj.getDate();
				var year = date_obj.getFullYear();
				var seconds = date_obj.getSeconds();

				// Array for weekday.
				var weekday = [ '星期天', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六' ];

				// Array for month.
				var month = [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月' ];

				// Assign weekday, month, date, year.
				weekday = weekday[date_obj.getDay()];
				month = month[date_obj.getMonth()];

				// Leading zero, if needed.
				if (minute < 10) {
					minute = '0' + minute;
				}

				if (seconds < 10) {
					seconds = '0' + seconds;
				}

				// Build two HTML strings.
				var clock_time = weekday + ' ' + hour + ':' + minute + ':' + seconds;
				var clock_date = year + '年' + month + day + '号';

				// Shove in the HTML.
				clock.html(clock_time).attr('title', clock_date);

				// Update every 60 seconds.
				setTimeout(JQD.init.clock, 1000);
			},
			//
			// Initialize the desktop.
			//
			desktop : function() {
				// Alias to document.
				var d = $(document);

				// Cancel mousedown.
				d.mousedown(function(ev) {
					var tags = [ 'a', 'button', 'input', 'select', 'textarea', 'tr' ];

					if (!$(ev.target).closest(tags).length) {
						JQD.util.clear_active();
						ev.preventDefault();
						ev.stopPropagation();
					}
				});

				// Cancel right-click. // 屏蔽上下文菜单
				d.on('contextmenu', function() {
					return false;
				});

				// Relative or remote links? //
				d.on('click', 'a', function(ev) {
					var url = $(this).attr('href');
					this.blur();

					if (url.match(/^#/)) {
						ev.preventDefault();
						ev.stopPropagation();
					} else {
						$(this).attr('target', '_blank');
					}
				});

				// Make top menus active.
				d.on('mousedown', 'a.menu_trigger', function() {
					if ($(this).next('ul.menu').is(':hidden')) {
						JQD.util.clear_active();
						$(this).addClass('active').next('ul.menu').show();
					} else {
						JQD.util.clear_active();
					}
				});

				// Transfer focus, if already open.
				d.on('mouseenter', 'a.menu_trigger', function() {
					if ($('ul.menu').is(':visible')) {
						JQD.util.clear_active();
						$(this).addClass('active').next('ul.menu').show();
					}
				});

				// Cancel single-click. // 桌面图标鼠标点下
				d.on('mousedown', 'a.icon', function() {
					// Highlight the icon.
					JQD.util.clear_active();
					$(this).addClass('active');
				});

				// Respond to double-click. // 桌面图标鼠标双击，弹出windows窗口
				d.on('dblclick', 'a.icon', function() {
					var diid = $(this).attr('id');
					var src = $(this).attr('src');
					var title = $(this).attr('title');
					var imgPath = $(this).find('img').attr('src');
					var menuid = $(this).attr('menuid');
					JQD.util.windows_open(diid, src, title, imgPath, menuid);
				});

				// Make icons draggable. //鼠标悬停至桌面图标
				d.on('mouseenter', 'a.icon', function() {
					$(this).off('mouseenter').draggable({
						revert : true,
						containment : 'parent'
					});
				});

				// Taskbar buttons. //任务栏图标点击
				d.on('click', '#dock a', function() {
					// Get the link's target.
					var x = $($(this).attr('href'));

					if ($(this).parent().hasClass('dock-focus')) {
						x.hide();
						$(this).parent().removeClass('dock-focus');
					} else {
						JQD.util.focus_set($(this).parent());
						JQD.util.window_flat();
						x.show().addClass('window_stack');
					}
				});

				// Focus active window. //鼠标点击windows窗口，窗口前置。
				d.on('mousedown', 'div.window', function() {
					JQD.util.focus_set($($(this).find('a.window_close').attr('href')));
					// Bring window to front.
					JQD.util.window_flat();
					$(this).addClass('window_stack');
				});

				// //鼠标移动至windows窗口内.
				d.on('mouseenter', 'div.window', function() {
					$(this).off('mouseenter').draggable({
						// Confine to desktop.
						// Movable via top bar only.
						cancel : 'a',
						containment : 'parent',
						handle : 'div.window_top'
					}).resizable({
						containment : 'parent',
						minWidth : 400,
						minHeight : 200,
						handles : 'n,w,e,s,se,ne,nw,sw'
					});
				});

				// Double-click top bar to resize, ala Windows OS.
				// //双击windows窗口标题，最大化windows窗口或还原windows窗口
				d.on('dblclick', 'div.window_top', function() {
					JQD.util.window_resize(this);
				});

				// Double click top bar icon to close, ala Windows OS. 双击windows窗口左上图标，关闭windows窗口。
				d.on('dblclick', 'div.window_top img',
						function() {
							// Traverse to the close button, and hide its taskbar button.
							$($(this).closest('div.window_top').find('a.window_close').attr('href')).remove();

							// Close the window itself.
							var win = $(this).closest('div.window');
							if (win.hasClass('window_full')) {
								JQD.util.cookie_set(win.attr('id'), "0px,0px,100%,100%");
							} else {
								JQD.util.cookie_set(win.attr('id'), win.css('top') + "," + win.css('left') + "," + win.css('width') + "," + win.css('height'));
							}
							win.remove();
							// Stop propagation to window's top bar.
							return false;
						});

				// Minimize the window. //鼠标点击windows窗口最小化按钮
				d.on('click', 'a.window_min', function() {
					$($(this).closest('div.window_top').find('a.window_close').attr('href')).attr('focus', 'dock-focus');
					$($(this).closest('div.window_top').find('a.window_close').attr('href')).removeClass('dock-focus');
					$(this).closest('div.window').hide();
				});

				// Maximize or restore the window. //鼠标点击windows窗口最大化\还原按钮
				d.on('click', 'a.window_resize', function() {
					JQD.util.window_resize(this);
				});

				// Close the window. //鼠标点击windows窗口关闭按钮
				d.on('click', 'a.window_close',
						function() {
							// 记录窗口位置cookie
							var win = $(this).closest('div.window');
							if (win.hasClass('window_full')) {
								JQD.util.cookie_set(win.attr('id'),
										"0px,0px,100%,100%");
							} else {
								JQD.util.cookie_set(win.attr('id'), win.css('top') + "," + win.css('left') + "," + win.css('width') + "," + win.css('height'));
							}

							win.remove();
							$($(this).attr('href')).remove();
						});

				// Show desktop button, ala Windows OS. // 显示桌面
				d.on('mousedown', '#show_desktop', function() {
					// If any windows are visible, hide all.
					if ($('div.window:visible').length) {
						$('div.window').hide();
						$('#dock').find('li').each(function() {
							if ($(this).hasClass('dock-focus')) {
								$(this).attr('focus', 'dock-focus');
								$(this).removeClass('dock-focus');
							}
						});
					} else {
						// Otherwise, reveal hidden windows that are open.
						$('#dock li:visible a').each(function() {
							if (null != $(this).parent().attr('focus'))
								$(this).parent().addClass('dock-focus');
							$($(this).attr('href')).show();
						});
					}
				});

				$('table.data').each(function() {
					// Add zebra striping, ala Mac OS X.
					$(this).find('tbody tr:odd').addClass('zebra');
				});

				d.on('mousedown', 'table.data tr', function() {
					// Clear active state.
					JQD.util.clear_active();

					// Highlight row, ala Mac OS X.
					$(this).closest('tr').addClass('active');
				});

				d.on('mousedown', 'span.ui-resizable-handle', function() {
					mask_status = true;
					$('div.iframe-mask').show();
				});

				d.on('mouseup', function() {
					if (mask_status) {
						$('div.iframe-mask').hide();
						mask_status = false;
					}
				});
			},
			wallpaper : function() {
			}
		},
		util : {
			/**
			 * 创建任务栏图标
			 * 
			 * icon_dock：任务栏图标ID window：Windows弹出窗口ID title：窗口标题 imgPath：图标路径
			 */
			dockicon_create : function(icon_dock, window, title, imgPath) {
				var img = $('<img>', {
					'width' : 28,
					'height' : 28,
					'src' : imgPath
				});

				var dockIcon = $('<a>', {
					'href' : '#' + window, // 弹出窗口ID
					'title' : title
				});
				dockIcon.append(img);

				var li = $('<li>', {
					id : icon_dock
				// 任务栏图标ID
				});
				li.append(dockIcon);

				$('#dock').append(li);
			},
			/*
			 * 创建桌面图标
			 */
			desktopicon_create : function() {
				// 桌面图片
				var img = $('<img>', {
					'width' : 32,
					'height' : 32,
					'src' : webroot + '/framework/images/menuIcon/network.png'
				});
				// 桌面图标
				var deskIcon = $('<a>', {
					id : 'menu_lIcon', // 桌面图标ID
					'class' : 'abs icon',
					'href' : '#icon_dock_test'// 任务栏图标ID
				});

				deskIcon.css('left', '0');
				deskIcon.css('top', '180px');
				deskIcon.css('z-index', '5');
				deskIcon.append(img);
				deskIcon.append("Test");

				$('#desktop').append(deskIcon);
			},
			/**
			 * 创建windows窗口
			 * 
			 * icon_dock：任务栏图标ID 
			 * window：Windows弹出窗口ID 
			 * diid：模块ID 
			 * src：模块访问路径
			 * title：窗口标题 
			 * imgPath：图标路径
			 */
			windows_create : function(icon_dock, window, diid, src, title, imgPath, menuid) {
				// *************** Top ***************//
				var window_min = $('<a>', {
					'class' : 'window_min',
					'title' : '最小化',
 					'href' : '#'
				});

				var window_resize = $('<a>', {
					'class' : 'window_resize',
					'title' : '全屏',
					'href' : '#'
				});

				var window_close = $('<a>', {
					'class' : 'window_close',
					'title' : '关闭',
					'href' : '#' + icon_dock// 任务栏图标ID
				});

				var float_right = $('<span>', {
					'class' : 'float_right'
				});

				float_right.append(window_min);
				float_right.append(window_resize);
				float_right.append(window_close);

				var img = $('<img>', {
					'width' : 16,
					'height' : 16,
					'src' : imgPath
				});

				var float_left = $('<span>', {
					'class' : 'float_left'
				});

				float_left.append(img);
				float_left.append(title);

				var window_top = $('<div>', {
					'class' : 'abs window_top window_top_empty'
				});

				window_top.append(float_left);
				window_top.append(float_right);

				// *************** Top ***************//

				// *************** content ***************//
				var window_main = $('<div>', {
					'class' : 'abs window_main'
				});

				window_main.html('<iframe src="' + webroot + '/basic/windows!toLayout.action?id=' + icon_dock + '_frameset&src=' + src + '&menuid=' + menuid + '&title=' + title + '" frameborder="0" marginwidth="0" marginheight="0" style="width:100%;min-height:100%;"></iframe>');

				var window_content = $('<div>', {
					'class' : 'abs window_content window_content_empty'
				});

				var iframe_mask = $('<div>', {
					'class' : 'abs iframe-mask'
				});

				window_content.append(window_main);

				window_content.append(iframe_mask);

				// *************** content ***************//
				// *************** resizable ***************//
				// 顶部拖动
				var ui_resizable_n = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-n'
				});

				var north_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs north-resize'
				});

				north_resize.append(ui_resizable_n);

				// 左侧拖动
				var ui_resizable_w = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-w'
				});

				var west_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs west-resize'
				});

				west_resize.append(ui_resizable_w);

				// 右侧拖动
				var ui_resizable_e = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-e'
				});

				var east_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs east-resize'
				});

				east_resize.append(ui_resizable_e);

				// 底部拖动
				var ui_resizable_s = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-s'
				});

				var south_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs south-resize'
				});

				south_resize.append(ui_resizable_s);

				// 右下拖动
				var ui_resizable_se = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-se'
				});

				var se_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs se-resize'
				});

				se_resize.append(ui_resizable_se);

				// 右上拖动
				var ui_resizable_ne = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-ne'
				});

				var ne_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs ne-resize'
				});

				ne_resize.append(ui_resizable_ne);

				// 左上拖动
				var ui_resizable_nw = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-nw'
				});

				var nw_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs nw-resize'
				});

				nw_resize.append(ui_resizable_nw);

				// 左下拖动
				var ui_resizable_sw = $('<span>', {
					'class' : 'abs ui-resizable-handle ui-resizable-sw'
				});

				var sw_resize = $('<div>', {
					name : window + '_resize',
					'class' : 'abs sw-resize'
				});

				sw_resize.append(ui_resizable_sw);

				// *************** resizable ***************//

				var window = $('<div>', {
					id : window,// Windows弹出窗口ID
					name : diid,
					'class' : 'abs window'
				});

				var window_inner = $('<div>', {
					'class' : 'abs window_inner'
				});

				window_inner.append(window_top);
				window_inner.append(window_content);
				window_inner.append(north_resize);
				window_inner.append(west_resize);
				window_inner.append(east_resize);
				window_inner.append(south_resize);
				window_inner.append(se_resize);
				window_inner.append(ne_resize);
				window_inner.append(nw_resize);
				window_inner.append(sw_resize);

				window.append(window_inner);
				$('#desktop').append(window);
			},
			/**
			 * 显示Windows窗口 
			 * diid：模块ID 
			 * src：模块访问路径 
			 * title：窗口标题 
			 * imgPath：图标路径
			 */
			windows_open : function(diid, src, title, imgPath, menuid) {
				var timestamp = diid + '_' + new Date().getMilliseconds();

				var icon_dock = 'icon_dock_' + timestamp;
				var window = 'window_' + timestamp;
				// 创建Windows窗口
				JQD.util.windows_create(icon_dock, window, diid, src, title, imgPath, menuid);
				// 创建任务栏图标
				JQD.util.dockicon_create(icon_dock, window, title, imgPath);

				// Get the link's target. 获取任务栏图标ID
				var x = '#' + icon_dock;// $(this).attr('href');

				// 获取Windows窗口ID
				var y = $(x).find('a').attr('href');

				// Show the taskbar button.
				if ($(x).is(':hidden')) {
					$(x).remove().appendTo('#dock');
					$(x).show('fast');
				}

				// Bring window to front.
				JQD.util.window_flat();

				// 通过cookie设定宽、高以及位置
				if (null != $.cookie(diid)) {
					var position = $.cookie(diid);
					var top = position.split(',')[0];
					var left = position.split(',')[1];
					var width = position.split(',')[2];
					var height = position.split(',')[3];
					// 如果top坐标不为空，并且是数字
					if (null != top && top.indexOf('px') > -1) {
						// 判断是否是全屏显示
						if (width == '100%' && height == '100%') {
							$(y).addClass('window_full').css({
								'top' : '0px',
								'left' : '0px',
								'right' : '0px',
								'bottom' : '0px',
								'width' : '100%',
								'height' : '100%'
							});
							JQD.util.window_resize_css(true, $('#' + window));
						} else {
							// 当前已有同样窗口打开
							if ($('div[name=' + diid + ']').length > 1) {
								var n = $('div[name=' + diid + ']')[$('div[name=' + diid + ']').length - 2];
								JQD.util.position_set($(y), (parseInt($(n).css('top')) + 35) + 'px', (parseInt($(n).css('left')) + 20) + 'px', width, height);
							} else {
								JQD.util.position_set($(y), top, left, width, height);
							}
						}
					} else {
						if ($('div[name=' + diid + ']').length > 1) {
							var n = $('div[name=' + diid + ']')[$('div[name=' + diid + ']').length - 2];
							JQD.util.position_set($(y), (parseInt($(n).css('top')) + 35) + 'px', (parseInt($(n).css('left')) + 20) + 'px', parseInt($(n).css('width')) + 'px', parseInt($(n).css('height')) + 'px');
						} else {
							JQD.util.position_set($(y), '30px', '120px', '900px', '500px');
						}
					}
				} else {
					if ($('div[name=' + diid + ']').length > 1) {
						var n = $('div[name=' + diid + ']')[$('div[name=' + diid + ']').length - 2];
						JQD.util.position_set($(y), (parseInt($(n).css('top')) + 35) + 'px', (parseInt($(n).css('left')) + 20) + 'px', parseInt($(n).css('width')) + 'px', parseInt($(n).css('height')) + 'px');
					} else {
						JQD.util.position_set($(y), '30px', '120px', '900px', '500px');
					}
				}
				$(y).addClass('window_stack').show();

				JQD.util.focus_set($(x));

				// 任务栏图标右击关闭窗口
				$(x).mousedown(function(e) {
					if (e.which == 3) {
						// 关闭Windows窗口
						var win = $($(this).find('a').attr('href'));
						// 记录窗口位置cookie
						if (win.hasClass('window_full')) {
							JQD.util.cookie_set(win.attr('id'), "0px,0px,100%,100%");
						} else {
							JQD.util.cookie_set(win.attr('id'), win.css('top') + "," + win.css('left') + "," + win.css('width') + "," + win.css('height'));
						}
						win.remove();
						$(this).remove();
						return false;// 阻止链接跳转
					}
				});
			},
			/**
			 * 窗口全屏样式设置
			 * resize : true 全屏、false 窗口
			 * name : 拖动边框name
			 */
			window_resize_css : function(resize, win){
				var name = $(win).attr('id') + '_resize';
				if (resize) {
					$('div[name=' + name + ']').hide();
					$(win).find('.window_top').removeClass('window_top_empty').addClass('window_top_full');
					$(win).find('.window_content').removeClass('window_content_empty').addClass('window_content_full');
					$(win).find('.window_resize').attr('title', '向下还原');
				} else {
					$('div[name=' + name + ']').show();
					$(win).find('.window_top').removeClass('window_top_full').addClass('window_top_empty');
					$(win).find('.window_content').removeClass('window_content_full').addClass('window_content_empty');
					$(win).find('.window_resize').attr('title', '全屏');
				}
			},
			/*
			 * 焦点设置
			 */
			focus_set : function(dock) {
				$('#dock').find('li').each(function() {
					if ($(this).hasClass('dock-focus')) {
						$(this).removeClass('dock-focus');
					}
				});
				if (null != dock) {
					dock.addClass('dock-focus');
				}
			},
			/*
			 * 坐标设置
			 */
			position_set : function(window, top, left, width, height) {
				window.css({
					'top' : top,
					'left' : left,
					'width' : width,
					'height' : height
				});
			},
			/*
			 * cookie设置
			 */
			cookie_set : function(id, value) {
				var id = id.substring(id.indexOf('_') + 1, id.lastIndexOf('_'));
				$.cookie(id, value, {
					expires : 180
				});
			},
			//
			// Clear active states, hide menus.
			//
			clear_active : function() {
				$('a.active, tr.active').removeClass('active');
				$('ul.menu').hide();
			},
			//
			// Zero out window z-index.
			//
			window_flat : function() {
				$('div.window').removeClass('window_stack');
			},
			//
			// Resize modal window.
			//
			window_resize : function(el) {
				// Nearest parent window.
				var win = $(el).closest('div.window');

				// Is it maximized already?
				if (win.hasClass('window_full')) {
					JQD.util.window_resize_css(false, win);
					if (null != win.attr('data-t')) {
						// Restore window position.
						win.removeClass('window_full').css({
							'top' : win.attr('data-t'),
							'left' : win.attr('data-l'),
							'right' : win.attr('data-r'),
							'bottom' : win.attr('data-b'),
							'width' : win.attr('data-w'),
							'height' : win.attr('data-h')
						});
					} else {
						win.removeClass('window_full').css({
							'top' : '30px',
							'left' : '120px',
							'width' : '900px',
							'height' : '500px'
						});
					}
				} else {
					JQD.util.window_resize_css(true, win);
					// 窗口全屏操作
					win.attr({
						// Save window position.
						'data-t' : win.css('top'),
						'data-l' : win.css('left'),
						'data-r' : win.css('right'),
						'data-b' : win.css('bottom'),
						'data-w' : win.css('width'),
						'data-h' : win.css('height')
					}).addClass('window_full').css({
						// Maximize dimensions.
						'top' : '0px',
						'left' : '0px',
						'right' : '0px',
						'bottom' : '0px',
						'width' : '100%',
						'height' : '100%'
					});
				}

				// Bring window to front.
				JQD.util.window_flat();
				win.addClass('window_stack');
			}
		}
	};
	// Pass in jQuery.
})(jQuery, this, this.document);

//
// Kick things off.
//
jQuery(document).ready(function() {
	JQD.go();
});