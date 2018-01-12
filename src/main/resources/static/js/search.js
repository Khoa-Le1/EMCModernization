function fetchSearchRecords(form){
    var data = form.serializeArray();
    var path = form.attr('action');
    var jsDataTable
    var dataTable;
    var idColumn = -1;
    console.log(data);

    //form validation logic

    //get the page number, size per page and other query variables

    showLoading();
    setTimeout(function () {

        try {
            //use proper ajax functions instead of .load()
            $("#res-tab").load("table.html", function (responseText, textStatus, req) {
                if(textStatus == "error"){
                    Materialize.toast("<i class='material-icons left'>close</i>&nbsp;Oops! An error occurred",2000);
                }
                dataTable = new MaterialDataTable(document.getElementById("result-table"));
                jsDataTable = $("#result-table").DataTable({
                    "paging": false,
                    //                        "searching" : false,
                    "info": false
                });
                $("#result-table th").each(function (index) {
                    if (index > 0) {
                        $(this).append("<i class='material-icons'>arrow_upward</i>");
                    }
                });
                $("#result-table_filter").addClass('col s6').css({
                    padding: '20px 20px 0px 20px'
                });
                $("#result-table th").each(function(index){
                    if($(this).attr('data-id-field')){
                        idColumn = index;
                    }
                });
                $("#result-table tr td:nth-child("+(idColumn+1)+")")
                    .addClass('id-column')
                    .click(function () {
                        openMessageModal($(this).text(),$(this).parent());
                    });
                jsDataTable.on('draw', function () {
                    var body = $(jsDataTable.table().body());
                    body.unhighlight();
                    body.highlight(jsDataTable.search());
                });
            });

            $("#res-tab,#ser-res,#toggle-action-footer,#sticky-action-footer").removeClass("hiddendiv");
        }catch (err){
            console.log("[ERROR]"+err);
        }finally {
            hideLoading();
        }

    },2000);
}

function clearPage(){
    $("#res-tab,#ser-res,#toggle-action-footer,#sticky-action-footer").addClass("hiddendiv");
}