/**
 * Created by Administrator on 2016/9/27.
 */

$(function () {
    //先查询出所有的模块
    var userName = "";
    $.ajaxSettings.async = false;
    $.getJSON("/getCurrentUser", function (data) {
        userName = data.id;
    });
    var modules = getAllModules(userName);
    var html = '';
    var moduleId = null;
    var apps = [];
    for (var x in modules) {
        if (modules[x]["resourceDesc"]) {
            html += '<li class="has-sub">';
            html += '   <a><i class="' + modules[x]["iconClass"] + '"></i> <span class="menu-text">' + modules[x]["resourceDesc"] + '</span><span class="arrow"></span></a>';
            html += '     <ul class="sub" id="sub' + moduleId + '">';
            moduleId = modules[x]["id"];
            apps = getAppByModule(userName, moduleId);
            for (var i in apps) {
                if (apps[i]["resourceDesc"]) {
                    html += '       <li><a ' + apps[i]["resourceUrl"] + '><span class="sub-menu-text">' + apps[i]["resourceDesc"] + '</span></a></li>';
                }
            }
            html += '     </ul>';
            html += '</li>';
        }
    }
    $("#menuL1").append(html);
    $(".sub-menu-text").parent().on("click", function () {
        $(this).css("cursor", "hand");
        var url = $(this).data("url");
        if (url) {
            $("#main-content").load(url, function () {
                $(this).removeData("url");
            });
        }
    });
})


/**
 *
 * @returns {Array} 查询所有的一级模块
 */
function getAllModules(userName) {
    var modules = [];
    $.ajaxSettings.async = false;
    var url = "authority/loadModule/" + userName;
    $.getJSON(url, function (data) {
        modules = data;
    });
    return modules;
}

/**
 *
 * @returns {Array} 查询所有的一级模块
 */
function getAppByModule(userName, moduleId) {
    var modules = [];
    $.ajaxSettings.async = false;
    var url = "authority/loadApp/" + moduleId + "/" + userName;
    $.getJSON(url, function (data) {
        modules = data;
    });
    return modules;
}