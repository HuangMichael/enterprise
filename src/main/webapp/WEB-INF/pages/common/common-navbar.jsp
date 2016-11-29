<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header class="navbar clearfix" id="header">
    <div class="container">
        <div class="navbar-brand">
            <!-- COMPANY LOGO -->
            <a>
                <img src="${org.logoUrl}" alt="${org.sysName}" class="img-responsive" height="30" width="120">
            </a>

            <div class="visible-xs">
                <a onclick="javascript:void(0)" class="team-status-toggle switcher btn dropdown-toggle">
                    <i class="fa fa-users"></i>
                </a>
            </div>
            <div id="sidebar-collapse" class="sidebar-collapse btn">
                <i class="fa fa-bars"
                   data-icon1="fa fa-bars"
                   data-icon2="fa fa-bars"></i>
            </div>
            <!-- /SIDEBAR COLLAPSE -->
        </div>
        <ul class="nav navbar-nav pull-right">

            <li class="dropdown" id="header-notification">
                <a class="dropdown-toggle" data-toggle="dropdown" id="reportOrder">
                    <i class="fa fa-bell"></i>
                    <span class="badge" id="reportOrderSize"></span>

                </a>
                <ul class="dropdown-menu notification">
                    <li class="dropdown-title">
                        <span id="orderMsgCnt"><i class="fa fa-bell"></i></span>
                    </li>
                    <div id="orderBox"></div>
                </ul>
            </li>
            <li class="dropdown user" id="header-user">
                <a onclick="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">
                    <img alt="" src="img/avatars/avatar3.jpg"/>
                    <span class="username">${currentUser.person.personName}(${currentUser.userName})</span>
                    <i class="fa fa-angle-down"></i>
                </a>
                <ul class="dropdown-menu" id="dropdown">
                    <li><a onclick="reload()"><i class="fa fa-download"></i>重新加载</a></li>
                    <li><a onclick="showUser()"><i class="fa fa-lock"></i>修改密码</a></li>
                    <li><a href="/"><i class="fa fa-power-off"></i>退出登录</a></li>
                </ul>
            </li>
        </ul>
    </div>
</header>


<div class="modal fade" id="user_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">我的信息</h4>
            </div>
            <div class="modal-body" id="profileView">

            </div>
        </div>
    </div>
</div>
<script src="js/jquery/jquery-2.0.3.min.js"></script>
<script>
    function showUser() {
        var url = "/user/profile";
        $("#profileView").load(url, function () {
            $("#user_modal").modal("show");
        });
    }


    /**
     * 重载session数据
     */
    function reload() {
        var url = "/commonData/reload";
        $.getJSON(url, function (data) {
            showMessageBox("info", "数据加载成功");
            console.log("data-----------" + JSON.stringify(data));
        });
    }

    $(function () {
        $("#reportOrder").on("click", function () {
            var num = $("#reportOrderSize").html();
            var url = "/workOrderReportCart/findMyCart";
            if (!isNaN(num)) {
                url += "/" + num; //查询最近N条
            } else {
                return;
            }
            $.getJSON(url, function (data) {
                var html = "";
                for (var x = 0; x < data.length; x++) {
                    html += "<li>";
                    html += "<a javascript:void(0)>";
                    html += '<span class="label label-success">' + (x + 1) + "</span>";
                    html += '<span class="body">';
                    html += '<span class="message">' + data[x]["vlocations"]["locName"] + "--" + data[x]["equipmentsClassification"]["description"] + "</span>";
                    html += '<span class="time">';
                    html += "<span></span>";
                    html += "</span>";
                    html += "</span>";
                    html += "</a>";
                    html += " </li>"
                }
                $("#orderMsgCnt").html(data.length + "个报修信息");
                $("#orderBox").html(html)
            })
        });
    });


</script>