var App = function () {
    var currentPage = "";
    var collapsed = false;
    var is_mobile = false;
    var is_mini_menu = false;
    var is_fixed_header = false;
    var responsiveFunctions = [];
    var runResponsiveFunctions = function () {
        for (var i in responsiveFunctions) {
            var each = responsiveFunctions[i];
            each.call()
        }
    };
    var getViewPort = function () {
        var e = window, a = "inner";
        if (!("innerWidth" in window)) {
            a = "client";
            e = document.documentElement || document.body
        }
        return {width: e[a + "Width"], height: e[a + "Height"]}
    };
    var checkLayout = function () {
        is_mini_menu = $("#sidebar").hasClass("mini-menu");
        is_fixed_header = $("#header").hasClass("navbar-fixed-top")
    };
    var handleSidebarAndContentHeight = function () {
        var content = $("#content");
        var sidebar = $("#sidebar");
        var body = $("body");
        var height;
        if (body.hasClass("sidebar-fixed")) {
            height = $(window).height() - $("#header").height() + 1
        } else {
            height = sidebar.height() + 20
        }
        if (height >= content.height()) {
            content.attr("style", "min-height:" + height + "px !important")
        }
    };
    var handleSidebar = function () {
        jQuery(".sidebar-menu .has-sub > a").click(function () {
            var last = jQuery(".has-sub.open", $(".sidebar-menu"));
            last.removeClass("open");
            jQuery(".arrow", last).removeClass("open");
            jQuery(".sub", last).slideUp(200);
            var thisElement = $(this);
            var slideOffeset = -200;
            var slideSpeed = 200;
            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery(".arrow", jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(slideSpeed, function () {
                    if ($("#sidebar").hasClass("sidebar-fixed") == false) {
                        App.scrollTo(thisElement, slideOffeset)
                    }
                    handleSidebarAndContentHeight()
                })
            } else {
                jQuery(".arrow", jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(slideSpeed, function () {
                    if ($("#sidebar").hasClass("sidebar-fixed") == false) {
                        App.scrollTo(thisElement, slideOffeset)
                    }
                    handleSidebarAndContentHeight()
                })
            }
        });
        jQuery(".sidebar-menu .has-sub .sub .has-sub-sub > a").click(function () {
            var last = jQuery(".has-sub-sub.open", $(".sidebar-menu"));
            last.removeClass("open");
            jQuery(".arrow", last).removeClass("open");
            jQuery(".sub", last).slideUp(200);
            var sub = jQuery(this).next();
            if (sub.is(":visible")) {
                jQuery(".arrow", jQuery(this)).removeClass("open");
                jQuery(this).parent().removeClass("open");
                sub.slideUp(200)
            } else {
                jQuery(".arrow", jQuery(this)).addClass("open");
                jQuery(this).parent().addClass("open");
                sub.slideDown(200)
            }
        })
    };
    var collapseSidebar = function () {
        var iconElem = document.getElementById("sidebar-collapse").querySelector('[class*="fa-"]');
        var iconLeft = iconElem.getAttribute("data-icon1");
        var iconRight = iconElem.getAttribute("data-icon2");
        jQuery(".navbar-brand").addClass("mini-menu");
        jQuery("#sidebar").addClass("mini-menu");
        jQuery("#main-content").addClass("margin-left-50");
        jQuery(".sidebar-collapse i").removeClass(iconLeft);
        jQuery(".sidebar-collapse i").addClass(iconRight);
        jQuery(".search").attr("placeholder", "");
        collapsed = true;
        $.cookie("mini_sidebar", "1")
    };
    var responsiveSidebar = function () {
        var width = $(window).width();
        if (width < 768) {
            is_mobile = true;
            jQuery("#main-content").addClass("margin-left-0")
        } else {
            is_mobile = false;
            jQuery("#main-content").removeClass("margin-left-0");
            var menu = $(".sidebar");
            if (menu.parent(".slimScrollDiv").size() === 1) {
                menu.slimScroll({destroy: true});
                menu.removeAttr("style");
                $("#sidebar").removeAttr("style")
            }
        }
    };
    var handleSidebarCollapse = function () {
        var viewport = getViewPort();
        if ($.cookie("mini_sidebar") === "1") {
            jQuery(".navbar-brand").addClass("mini-menu");
            jQuery("#sidebar").addClass("mini-menu");
            jQuery("#main-content").addClass("margin-left-50");
            collapsed = true
        }
        jQuery(".sidebar-collapse").click(function () {
            if (is_mobile && !(is_mini_menu)) {
                if (collapsed) {
                    jQuery("body").removeClass("slidebar");
                    jQuery(".sidebar").removeClass("sidebar-fixed");
                    if (is_fixed_header) {
                        jQuery("#header").addClass("navbar-fixed-top");
                        jQuery("#main-content").addClass("margin-top-100")
                    }
                    collapsed = false;
                    $.cookie("mini_sidebar", "0")
                } else {
                    jQuery("body").addClass("slidebar");
                    jQuery(".sidebar").addClass("sidebar-fixed");
                    if (is_fixed_header) {
                        jQuery("#header").removeClass("navbar-fixed-top");
                        jQuery("#main-content").removeClass("margin-top-100")
                    }
                    collapsed = true;
                    $.cookie("mini_sidebar", "1");
                    handleMobileSidebar()
                }
            } else {
                var iconElem = document.getElementById("sidebar-collapse").querySelector('[class*="fa-"]');
                var iconLeft = iconElem.getAttribute("data-icon1");
                var iconRight = iconElem.getAttribute("data-icon2");
                if (collapsed) {
                    jQuery(".navbar-brand").removeClass("mini-menu");
                    jQuery("#sidebar").removeClass("mini-menu");
                    jQuery("#main-content").removeClass("margin-left-50");
                    jQuery(".sidebar-collapse i").removeClass(iconRight);
                    jQuery(".sidebar-collapse i").addClass(iconLeft);
                    jQuery(".search").attr("placeholder", "Search");
                    collapsed = false;
                    $.cookie("mini_sidebar", "0")
                } else {
                    jQuery(".navbar-brand").addClass("mini-menu");
                    jQuery("#sidebar").addClass("mini-menu");
                    jQuery("#main-content").addClass("margin-left-50");
                    jQuery(".sidebar-collapse i").removeClass(iconLeft);
                    jQuery(".sidebar-collapse i").addClass(iconRight);
                    jQuery(".search").attr("placeholder", "");
                    collapsed = true;
                    $.cookie("mini_sidebar", "1")
                }
                $("#main-content").on("resize", function (e) {
                    e.stopPropagation()
                })
            }
        })
    };
    var handleMobileSidebar = function () {
        var menu = $(".sidebar");
        if (menu.parent(".slimScrollDiv").size() === 1) {
            menu.slimScroll({destroy: true});
            menu.removeAttr("style");
            $("#sidebar").removeAttr("style")
        }
        menu.slimScroll({
            size: "7px",
            color: "#a1b2bd",
            opacity: 0.3,
            height: "100%",
            allowPageScroll: false,
            disableFadeOut: false
        })
    };
    var handleFixedSidebar = function () {
        var menu = $(".sidebar-menu");
        if (menu.parent(".slimScrollDiv").size() === 1) {
            menu.slimScroll({destroy: true});
            menu.removeAttr("style");
            $("#sidebar").removeAttr("style")
        }
        if ($(".sidebar-fixed").size() === 0) {
            handleSidebarAndContentHeight();
            return
        }
        var viewport = getViewPort();
        if (viewport.width >= 992) {
            var sidebarHeight = $(window).height() - $("#header").height() + 1;
            menu.slimScroll({
                size: "7px",
                color: "#a1b2bd",
                opacity: 0.3,
                height: sidebarHeight,
                allowPageScroll: false,
                disableFadeOut: false
            });
            handleSidebarAndContentHeight()
        }
    };
    jQuery(window).resize(function () {
        setTimeout(function () {
            checkLayout();
            handleSidebarAndContentHeight();
            responsiveSidebar();
            handleFixedSidebar();
            handleNavbarFixedTop();
            runResponsiveFunctions()
        }, 50)
    });
    var handleHomePageTooltips = function () {
        $(".tip").tooltip();
        $(".tip-bottom").tooltip({placement: "bottom"});
        $(".tip-left").tooltip({placement: "left"});
        $(".tip-right").tooltip({placement: "right"});
        $(".tip-focus").tooltip({trigger: "focus"})
    };
    var handleBoxTools = function () {
        jQuery(".box .tools .collapse, .box .tools .expand").click(function () {
            var el = jQuery(this).parents(".box").children(".box-body");
            if (jQuery(this).hasClass("collapse")) {
                jQuery(this).removeClass("collapse").addClass("expand");
                var i = jQuery(this).children(".fa-chevron-up");
                i.removeClass("fa-chevron-up").addClass("fa-chevron-down");
                el.slideUp(200)
            } else {
                jQuery(this).removeClass("expand").addClass("collapse");
                var i = jQuery(this).children(".fa-chevron-down");
                i.removeClass("fa-chevron-down").addClass("fa-chevron-up");
                el.slideDown(200)
            }
        });
        jQuery(".box .tools a.remove").click(function () {
            var removable = jQuery(this).parents(".box");
            if (removable.next().hasClass("box") || removable.prev().hasClass("box")) {
                jQuery(this).parents(".box").remove()
            } else {
                jQuery(this).parents(".box").parent().remove()
            }
        });
        jQuery(".box .tools a.reload").click(function () {
            var el = jQuery(this).parents(".box");
            App.blockUI(el);
            window.setTimeout(function () {
                App.unblockUI(el)
            }, 1000)
        })
    };
    var handleSlimScrolls = function () {
        if (!jQuery().slimScroll) {
            return
        }
        $(".scroller").each(function () {
            $(this).slimScroll({
                size: "7px",
                color: "#a1b2bd",
                height: $(this).attr("data-height"),
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                railOpacity: 0.1,
                disableFadeOut: true
            })
        })
    };
    var handlePopovers = function () {
        $(".pop").popover();
        $(".pop-bottom").popover({placement: "bottom"});
        $(".pop-left").popover({placement: "left"});
        $(".pop-top").popover({placement: "top"});
        $(".pop-hover").popover({trigger: "hover"})
    };
    var handleAlerts = function () {
        $(".alert").alert()
    };
    var initTimeAgo = function () {
        jQuery("abbr.timeago").timeago()
    };
    var handleStatefulButtons = function () {
        $(document).ready(function () {
            $("#btn-load").on("click", function () {
                var a = $(this);
                a.button("loading");
                setTimeout(function () {
                    a.button("reset")
                }, 1500)
            });
            $("#btn-load-complete").on("click", function () {
                var a = $(this);
                a.button("loading");
                setTimeout(function () {
                    a.button("complete")
                }, 1500)
            })
        })
    };
    var handleToggle = function () {
        $(".radio1").on("switch-change", function () {
            $(".radio1").bootstrapSwitch("toggleRadioState")
        });
        $(".radio1").on("switch-change", function () {
            $(".radio1").bootstrapSwitch("toggleRadioStateAllowUncheck")
        });
        $(".radio1").on("switch-change", function () {
            $(".radio1").bootstrapSwitch("toggleRadioStateAllowUncheck", false)
        })
    };
    var handleSliders = function () {
        function repositionTooltip(e, ui) {
            $;
            var div = $(ui.handle).data("bs.tooltip").$tip[0];
            var pos = $.extend({}, $(ui.handle).offset(), {
                width: $(ui.handle).get(0).offsetWidth,
                height: $(ui.handle).get(0).offsetHeight
            });
            var actualWidth = div.offsetWidth;
            tp = {left: pos.left + pos.width / 2 - actualWidth / 2};
            $(div).offset(tp);
            $(div).find(".tooltip-inner").text(ui.value)
        }

        $("#slider").slider({value: 15, slide: repositionTooltip, stop: repositionTooltip});
        $("#slider .ui-slider-handle:first").tooltip({
            title: $("#slider").slider("value"),
            trigger: "manual"
        }).tooltip("show");
        $("#slider-default").slider();
        $("#slider-range").slider({range: true, min: 0, max: 500, values: [75, 300]});
        $("#slider-range-min").slider({
            range: "min", value: 37, min: 1, max: 700, slide: function (a, b) {
                $("#slider-range-min-amount").text("$" + b.value)
            }
        });
        $("#slider-range-max").slider({
            range: "max", min: 1, max: 700, value: 300, slide: function (a, b) {
                $("#slider-range-max-amount").text("$" + b.value)
            }
        });
        $("#slider-vertical-multiple > span").each(function () {
            var a = parseInt($(this).text(), 10);
            $(this).empty().slider({value: a, range: "min", animate: true, orientation: "vertical"})
        });
        $("#slider-vertical-range-min").slider({range: "min", value: 400, min: 1, max: 600, orientation: "vertical"});
        $("#slider-horizontal-range-min").slider({range: "min", value: 600, min: 1, max: 1000})
    };
    var handleKnobs = function () {
        $(".knob").knob({
            change: function (value) {
            }, release: function (value) {
                console.log("release : " + value)
            }, cancel: function () {
                console.log("cancel : ", this)
            }, draw: function () {
                if (this.$.data("skin") == "tron") {
                    var a = this.angle(this.cv), sa = this.startAngle, sat = this.startAngle, ea, eat = sat + a, r = 1;
                    this.g.lineWidth = this.lineWidth;
                    this.o.cursor && (sat = eat - 0.3) && (eat = eat + 0.3);
                    if (this.o.displayPrevious) {
                        ea = this.startAngle + this.angle(this.v);
                        this.o.cursor && (sa = ea - 0.3) && (ea = ea + 0.3);
                        this.g.beginPath();
                        this.g.strokeStyle = this.pColor;
                        this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sa, ea, false);
                        this.g.stroke()
                    }
                    this.g.beginPath();
                    this.g.strokeStyle = r ? this.o.fgColor : this.fgColor;
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth, sat, eat, false);
                    this.g.stroke();
                    this.g.lineWidth = 2;
                    this.g.beginPath();
                    this.g.strokeStyle = this.o.fgColor;
                    this.g.arc(this.xy, this.xy, this.radius - this.lineWidth + 1 + this.lineWidth * 2 / 3, 0, 2 * Math.PI, false);
                    this.g.stroke();
                    return false
                }
            }
        })
    };
    var handleCustomTabs = function () {
        var adjustMinHeight = function (y) {
            $(y).each(function () {
                var A = $($($(this).attr("href")));
                var z = $(this).parent().parent();
                if (z.height() > A.height()) {
                    A.css("min-height", z.height())
                }
            })
        };
        $("body").on("click", '.nav.nav-tabs.tabs-left a[data-toggle="tab"], .nav.nav-tabs.tabs-right a[data-toggle="tab"]', function () {
            adjustMinHeight($(this))
        });
        adjustMinHeight('.nav.nav-tabs.tabs-left > li.active > a[data-toggle="tab"], .nav.nav-tabs.tabs-right > li.active > a[data-toggle="tab"]');
        if (location.hash) {
            var w = location.hash.substr(1);
            $('a[href="#' + w + '"]').click()
        }
    };
    var handleTablecloth = function () {
        $("#example-dark").tablecloth({theme: "dark"});
        $("#example-paper").tablecloth({theme: "paper", striped: true});
        $("#example-stats").tablecloth({theme: "stats", sortable: true, condensed: true, striped: true, clean: true})
    };
    var handleTypeahead = function () {
        $("#autocomplete-example").typeahead({
            name: "countries",
            local: ["red", "blue", "green", "yellow", "brown", "black"]
        })
    };
    var handleAutosize = function () {
        $("textarea.autosize").autosize();
        $("textarea.autosize").addClass("textarea-transition")
    };
    var handleCountable = function () {
        $(".countable").simplyCountable()
    };
    var handleUniform = function () {
        $(".uniform").uniform()
    };
    var handleAllUniform = function () {
        $("select, input[type='checkbox']").uniform()
    };
    var handleDropzone = function () {
        try {
            $(".dropzone").dropzone({
                paramName: "file",
                maxFilesize: 0.5,
                addRemoveLinks: true,
                dictResponseError: "Error while uploading file!",
                previewTemplate: '<div class="dz-preview dz-file-preview">\n  <div class="dz-details">\n    <div class="dz-filename"><span data-dz-name></span></div>\n    <div class="dz-size" data-dz-size></div>\n    <img data-dz-thumbnail />\n  </div>\n  <div class="progress progress-sm progress-striped active"><div class="progress-bar progress-bar-success" data-dz-uploadprogress></div></div>\n  <div class="dz-success-mark"><span></span></div>\n  <div class="dz-error-mark"><span></span></div>\n  <div class="dz-error-message"><span data-dz-errormessage></span></div>\n</div>'
            })
        } catch (e) {
            alert("Dropzone.js does not support older browsers!")
        }
    };
    var handleSparkline = function () {
        $(".sparkline").each(function () {
            var barSpacing, barWidth, color, height;
            color = $(this).attr("data-color") || "red";
            height = "18px";
            if ($(this).hasClass("big")) {
                barWidth = "5px";
                barSpacing = "2px";
                height = "40px"
            }
            return $(this).sparkline("html", {
                type: "bar",
                barColor: Theme.colors[color],
                height: height,
                barWidth: barWidth,
                barSpacing: barSpacing,
                zeroAxis: false
            })
        });
        $(".sparklinepie").each(function () {
            var height;
            height = "50px";
            if ($(this).hasClass("big")) {
                height = "70px"
            }
            return $(this).sparkline("html", {
                type: "pie",
                height: height,
                sliceColors: [Theme.colors.blue, Theme.colors.red, Theme.colors.green, Theme.colors.orange]
            })
        });
        $(".linechart").each(function () {
            var height;
            height = "18px";
            if ($(this).hasClass("linechart-lg")) {
                height = "30px"
            }
            return $(this).sparkline("html", {
                type: "line",
                height: height,
                width: "150px",
                minSpotColor: Theme.colors.red,
                maxSpotColor: Theme.colors.green,
                spotRadius: 3,
                lineColor: Theme.colors.primary,
                fillColor: "rgba(94,135,176,0.1)",
                lineWidth: 1.2,
                highlightLineColor: Theme.colors.red,
                highlightSpotColor: Theme.colors.yellow
            })
        })
    };
    var handleCalendar = function () {
        var initDrag = function (el) {
            var eventObject = {title: $.trim(el.text())};
            el.data("eventObject", eventObject);
            el.draggable({zIndex: 999, revert: true, revertDuration: 0})
        };
        var addEvent = function (title) {
            title = title.length == 0 ? "Untitled Event" : title;
            var html = $('<div class="external-event">' + title + "</div>");
            jQuery("#event-box").append(html);
            initDrag(html)
        };
        $("#external-events div.external-event").each(function () {
            initDrag($(this))
        });
        $("#add-event").unbind("click").click(function () {
            var title = $("#event-title").val();
            addEvent(title)
        });
        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        var calendar = $("#calendar").fullCalendar({
            header: {left: "prev,next today", center: "title", right: "month,agendaWeek,agendaDay"},
            selectable: true,
            selectHelper: true,
            select: function (start, end, allDay) {
                var title = prompt("Event Title:");
                if (title) {
                    calendar.fullCalendar("renderEvent", {title: title, start: start, end: end, allDay: allDay}, true)
                }
                calendar.fullCalendar("unselect")
            },
            editable: true,
            editable: true,
            droppable: true,
            drop: function (date, allDay) {
                var originalEventObject = $(this).data("eventObject");
                var copiedEventObject = $.extend({}, originalEventObject);
                copiedEventObject.start = date;
                copiedEventObject.allDay = allDay;
                $("#calendar").fullCalendar("renderEvent", copiedEventObject, true);
                if ($("#drop-remove").is(":checked")) {
                    $(this).remove()
                }
            }
        })
    };
    var handleBackstretch = function () {
        $.backstretch(["img/login/1.jpg", "img/login/2.jpg", "img/login/3.jpg", "img/login/4.jpg"], {
            duration: 3000,
            fade: 750
        })
    };
    var handleSliderNav = function () {
        $("#address-book").sliderNav();
        $("#address-book .slider-content ul li ul li a").click(function (e) {
            e.preventDefault();
            var contact_card = $("#contact-card");
            var name = $(this).text();
            $("#contact-card .panel-title").html(name);
            $("#contact-card #card-name").html(name);
            var img_id = Math.floor(Math.random() * (5 - 1 + 1)) + 1;
            $("#contact-card .headshot img").attr("src", "img/addressbook/" + img_id + ".jpg");
            contact_card.removeClass("animated fadeInUp").addClass("animated fadeInUp");
            var wait = window.setTimeout(function () {
                contact_card.removeClass("animated fadeInUp")
            }, 1300)
        })
    };
    var handleActiveToggle = function () {
        $("#list-toggle .list-group a").click(function () {
            $("#list-toggle .list-group > a.active").removeClass("active");
            $(this).addClass("active")
        })
    };
    var handleBoxSortable = function () {
        $(".box-container").sortable({
            connectWith: ".box-container",
            items: "> .box",
            opacity: 0.8,
            revert: true,
            forceHelperSize: true,
            placeholder: "box-placeholder",
            forcePlaceholderSize: true,
            tolerance: "pointer"
        })
    };
    var handleGoToTop = function () {
        $(".footer-tools").on("click", ".go-top", function (e) {
            App.scrollTo();
            e.preventDefault()
        })
    };
    var handleNavbarFixedTop = function () {
        if (is_mobile && is_fixed_header) {
            $("#main-content").addClass("margin-top-100")
        }
        if (!(is_mobile) && is_fixed_header) {
            $("#main-content").removeClass("margin-top-100").addClass("margin-top-50")
        }
    };
    var handleDashFlotCharts;
    handleDashFlotCharts = function () {
    };
    var handleVerticalChart = function () {
        if ($(".verticalChart")) {
            $(".singleBar").each(function () {
                var percent = $(this).find(".value span").html();
                $(this).find(".value").animate({height: percent}, 2000, function () {
                    $(this).find("span").fadeIn()
                })
            })
        }
    };
    var handleThemeSkins = function () {
        var setSkin = function (color) {
            $("#skin-switcher").attr("href", "css/themes/" + color + ".css");
            $.cookie("skin_color", color)
        };
        $("ul.skins > li a").click(function () {
            var color = $(this).data("skin");
            setSkin(color)
        });
        if ($.cookie("skin_color")) {
            setSkin($.cookie("skin_color"))
        }
    };
    var handleGritter = function () {
        if ($.cookie("gritter_show")) {
            return
        }
        $.cookie("gritter_show", 1);
        setTimeout(function () {
            var unique_id = $.gritter.add({
                title: "欢迎登录设备维修系统!",
                text: "北京地铁运营三分公司版权所有!",
                image: "img/logo/logo_sm.png",
                sticky: true,
                time: "",
                class_name: "my-sticky-class"
            });
            setTimeout(function () {
                $.gritter.remove(unique_id, {fade: true, speed: "slow"})
            }, 12000)
        }, 2000)
    };
    var handleProfileEdit = function () {
    };
    return {
        init: function () {
            if (App.isPage("workOrder")) {
                handleCalendar();
                handleGritter()
            }
            if (App.isPage("equipments")) {
               // handleRaty()
            }
            if (App.isPage("portal")) {
                handleCalendar();
                handleGritter()
            }
            if (App.isPage("index")) {
                handleSparkline();
                handleCalendar();
                handleGritter()
            }
            if (App.isPage("simple_table")) {
                handleTablecloth()
            }
            if (App.isPage("dynamic_table")) {
                handleCalendar();
                handleGritter()
            }
            if (App.isPage("wizards_validations")) {
                handleUniform()
            }
            if (App.isPage("login_bg")) {
                handleUniform();
                handleBackstretch()
            }
            if (App.isPage("mini_sidebar")) {
                collapseSidebar()
            }
            if (App.isPage("fixed_header_sidebar")) {
                handleFixedSidebar()
            }
            checkLayout();
            handleSidebar();
            handleSidebarCollapse();
            handleSidebarAndContentHeight();
            responsiveSidebar();
            handleHomePageTooltips();
            handleBoxTools();
            handleSlimScrolls();
            handleCustomTabs();
            handleGoToTop();
            handleNavbarFixedTop();
            handleThemeSkins()
        }, setPage: function (name) {
            currentPage = name
        }, isPage: function (name) {
            return currentPage == name ? true : false
        }, addResponsiveFunction: function (func) {
            responsiveFunctions.push(func)
        }, scrollTo: function (el, offeset) {
            pos = (el && el.size() > 0) ? el.offset().top : 0;
            jQuery("html,body").animate({scrollTop: pos + (offeset ? offeset : 0)}, "slow")
        }, scrollTop: function () {
            App.scrollTo()
        }, initUniform: function (els) {
            if (els) {
                jQuery(els).each(function () {
                    if ($(this).parents(".checker").size() == 0) {
                        $(this).show();
                        $(this).uniform()
                    }
                })
            } else {
                handleAllUniform()
            }
        }, blockUI: function (el, loaderOnTop) {
            lastBlockedUI = el;
            jQuery(el).block({
                message: '<img src="./img/loaders/12.gif" align="absmiddle">',
                css: {border: "none", padding: "2px", backgroundColor: "none"},
                overlayCSS: {backgroundColor: "#000", opacity: 0.05, cursor: "wait"}
            })
        }, unblockUI: function (el) {
            jQuery(el).unblock({
                onUnblock: function () {
                    jQuery(el).removeAttr("style")
                }
            })
        }
    }
}();
(function (a, b) {
    a.fn.admin_tree = function (d) {
        var c = {
            "open-icon": "fa fa-folder-open",
            "close-icon": "fa fa-folder",
            selectable: true,
            "selected-icon": "fa fa-check",
            "unselected-icon": "tree-dot"
        };
        c = a.extend({}, c, d);
        this.each(function () {
            var e = a(this);
            e.html('<div class = "tree-folder" style="display:none;">				<div class="tree-folder-header">					<i class="' + c["close-icon"] + '"></i>					<div class="tree-folder-name"></div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display:none"></div>			</div>			<div class="tree-item" style="display:none;">				' + (c["unselected-icon"] == null ? "" : '<i class="' + c["unselected-icon"] + '"></i>') + '				<div class="tree-item-name"></div>			</div>');
            e.addClass(c.selectable == true ? "tree-selectable" : "tree-unselectable");
            e.tree(c)
        });
        return this
    }
})(window.jQuery);
(function () {
    this.Theme = (function () {
        function Theme() {
        }

        Theme.colors = {
            white: "#FFFFFF",
            primary: "#5E87B0",
            red: "#D9534F",
            green: "#A8BC7B",
            blue: "#70AFC4",
            orange: "#F0AD4E",
            yellow: "#FCD76A",
            gray: "#6B787F",
            lightBlue: "#D4E5DE",
            purple: "#A696CE",
            pink: "#DB5E8C",
            dark_orange: "#F38630"
        };
        return Theme
    })()
})(window.jQuery);