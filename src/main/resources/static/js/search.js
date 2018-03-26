$(document).ready(function () {

    $("#page-back").click(function(){
        if($(this).hasClass("disabled"))
            return;
        clearPage();
        fetchSearchRecords($("#msg-search-form"), $(this).attr("page-value"));
    });
    $("#page-forward").click(function(){
        clearPage();
        fetchSearchRecords($("#msg-search-form"), $(this).attr("page-value"));
    });
});

function fetchSearchRecords(form, page){
    var data = form.serialize();
    if (page) {
        data += "&page=" + page;
    }
    var path = form.attr('action');
    var jsDataTable
    var dataTable;
    var idColumn = -1;
    var responseText;
    console.log(data);

    //form validation logic

    //get the page number, size per page and other query variables

    showLoading();
    setTimeout(function () {

        try {
            //use proper ajax functions instead of .load()
            var resp;
            $("#res-tab").load("/messages?"+data, function (responseText, textStatus, response) {
                resp = response;
                responseText  = response["responseText"];
                if (responseText.indexOf("ERR001") >= 0) {
                    //return error toast if no fields are entered
                    Materialize.toast(
                        "<i class='material-icons left'>close</i>" +
                        "&nbsp;Oops! Enter at least one of: Message Control ID, Order Number, From Date, To Date.",2500)
                } else if (responseText.indexOf("ERR002") >=0) {
                    //return error toast if times are selected without their corresponding dates
                    Materialize.toast(
                        "<i class='material-icons left'>close</i>" +
                        "&nbsp;Oops! Dates are required with time selections.",2500)
                }
                else if(textStatus == "error"){
                    //warn for generic error
                    console.log(response);
                    Materialize.toast("<i class='material-icons left'>close</i>&nbsp;Oops! An error occurred",2000);
                }

                //prepare datatable to be populated
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
                        openMessageModal($(this).text(),$(this).attr("message-correlation-uuid"),$(this).parent());
                    });
                jsDataTable.on('draw', function () {
                    var body = $(jsDataTable.table().body());
                    body.unhighlight();
                    body.highlight(jsDataTable.search());
                });

                //sticky activate
                $("#res-tab,#ser-res,#toggle-action-footer,#sticky-action-footer").removeClass("hiddendiv");

                var pageHeader =  parseInt(response.getResponseHeader("page"));
                $("#page-back").attr("page-value",(pageHeader-1)+"");
                if (pageHeader > 0) {
                    $("#page-back").removeClass("disabled");
                } else {
                    $("#page-back").addClass("disabled");
                }
                $("#page-current a").text(pageHeader+1);
                $("#page-forward").attr("page-value",(pageHeader+1)+"");
            });
        }catch (err){
            console.log("[ERROR]"+err);
        }finally {
            hideLoading();
        }

    },2000);
}

function clearPage(){
    $("#page-current a").text("");
    $("#res-tab,#ser-res,#toggle-action-footer,#sticky-action-footer").addClass("hiddendiv");
}