/**
 * Created by Administrator on 2016/9/29.
 */






$(function () {

    var locs = getMyLocs();
    var eqClasses = findEqClass();

    var searchVue = new Vue({
        el: "#searchBox",
        data: {
            locs: locs,
            eqClasses: eqClasses
        }
    });


    dataTableName = "#matCostDataTable";
    mainObject = "matCost";
    docName = "物料消耗信息";
    searchModel = [
        {"param": "locName", "paramDesc": "位置"},
        {"param": "eqClass", "paramDesc": "设备分类"},
        {"param": "ecName", "paramDesc": "耗材名称"},
        {"param": "ecType", "paramDesc": "物资类型"}
    ];


    initSelect();
    initBootGrid(dataTableName);
    initSearchDate();
    search();


});






