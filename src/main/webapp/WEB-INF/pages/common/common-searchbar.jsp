<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="js/searchbar/css/style.css"/>
<%@include file="common-head.jsp"%>
<div id="searchBar" style="border: 1px">
    <ul class="select" style="padding-left: 10px">
        <li class="select-list">
            <dl id="select1">
                <dt>地铁线：</dt>
                <dd class="select-all selected"><a>全部</a></dd>
                <dd><a>2号线</a></dd>
                <dd><a>8号线</a></dd>
                <dd><a>10号线</a></dd>
                <dd><a>13号线</a></dd>
            </dl>
        </li>
        <li class="select-list">
            <dl id="select2">
                <dt>地铁站：</dt>
                <dd class="select-all selected"><a>全部</a></dd>
                <dd><a>西直门</a></dd>
                <dd><a>积水潭</a></dd>
                <dd><a>阜成门</a></dd>
                <dd><a>建国门</a></dd>
                <dd><a>朝阳门</a></dd>
            </dl>
        </li>
        <li class="select-result">
            <dl>
                <dt>已选条件：</dt>
                <%--<dd class="select-no">暂时没有选择过滤条件</dd>--%>
            </dl>
        </li>
    </ul>
</div>
<%@include file="common-foot.jsp"%>
<script type="text/javascript">
    $(document).ready(function () {
        $("#select1 dd").click(function () {
            $(this).addClass("selected").siblings().removeClass("selected");
            if ($(this).hasClass("select-all")) {
                $("#selectA").remove();
            } else {
                var copyThisA = $(this).clone();
                if ($("#selectA").length > 0) {
                    $("#selectA a").html($(this).text());
                } else {
                    $(".select-result dl").append(copyThisA.attr("id", "selectA"));
                }
            }
        });

        $("#select2 dd").click(function () {
            $(this).addClass("selected").siblings().removeClass("selected");
            if ($(this).hasClass("select-all")) {
                $("#selectB").remove();
            } else {
                var copyThisB = $(this).clone();
                if ($("#selectB").length > 0) {
                    $("#selectB a").html($(this).text());
                }
                /*else {
                    $(".select-result dl").append(copyThisB.attr("id", "selectB"));
                }*/
            }
        });

        $("#select3 dd").click(function () {
            $(this).addClass("selected").siblings().removeClass("selected");
            if ($(this).hasClass("select-all")) {
                $("#selectC").remove();
            } else {
                var copyThisC = $(this).clone();
                if ($("#selectC").length > 0) {
                    $("#selectC a").html($(this).text());
                }
               /* else {
                    $(".select-result dl").append(copyThisC.attr("id", "selectC"));
                }*/
            }
        });

        $("#selectA").on("click", function () {
            $(this).remove();
            $("#select1 .select-all").addClass("selected").siblings().removeClass("selected");
        });

        $("#selectB").on("click", function () {
            $(this).remove();
            $("#select2 .select-all").addClass("selected").siblings().removeClass("selected");
        });

        $("#selectC").on("click", function () {
            $(this).remove();
            $("#select3 .select-all").addClass("selected").siblings().removeClass("selected");
        });

        $(".select dd").on("click", function () {
            if ($(".select-result dd").length > 1) {
                $(".select-no").hide();
            } else {
                $(".select-no").show();
            }
        });

    });
</script>