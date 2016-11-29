$(document).ready(function() {
    $('#fixListTable').bootgrid({

        columnSelection: 1,
        rowCount: [10, 20, 25, -1]
    });

    $("#myTab a").on("click", function(e) {
        e.preventDefault();
        preview(1);
        $(this).tab('show');
    })

});