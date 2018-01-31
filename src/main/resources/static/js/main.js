function showLoading(){
    $("#loading-screen").modal('open');
}
function hideLoading(){
    $("#loading-screen").modal('close');
}
var stickyHeader;
var stickyPos;
$(document).ready(function() {
    $("#loading-screen").modal({
        dismissible:false,
        endingTop: '0',
        inDuration: 0,
        outDuration: 0
    });
    $('.tooltipped').tooltip({delay: 50});
    $('.form-row select,.action-modal select').material_select();
    $(".button-collapse").sideNav();
    $(".action-modal").modal({
        endingTop: '25%'
    });
    $(".message-modal").modal({
        endingTop:'4%',
        ready : function () {
            $("#message-content-modal input.truncate").each(function () {
                if($(this)[0].scrollWidth > ($(this).innerWidth()+2)){
                    $(this).attr('data-tooltip',$(this).val());
                    $(this).tooltip({delay: 50});
                }
            });
            $("#message-content-modal select").material_select();
        }
    });
    $(".collapsible").collapsible();
    $('.dropdown-button').dropdown();
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15, // Creates a dropdown of 15 years to control year,
        today: 'Today',
        clear: 'Clear',
        close: 'Ok',
        max : new Date(),
        closeOnSelect: false // Close upon selecting a date,
    })
    stickyHeader = document.getElementById("sticky-page-header");
    stickyPos = stickyHeader.offsetTop;
    window.onscroll = function() {
        if (window.pageYOffset >= stickyPos) {
            stickyHeader.classList.add("sticky");
        } else {
            stickyHeader.classList.remove("sticky");
        }
    };

    $(".form-row button[type=reset]").click(function (event) {
        $(".form-row input[type=text]").val("");
        Materialize.updateTextFields();
    });
    //hide all advanced search fields
    $(".adv-search-field").hide();
    $(".adv-search button").click(function () {
        var state = $(this).find("i").text();
        if(state == "add") {
            $(this).parent().parent().find(".adv-search-field").show();
            $(this).find("i").text("remove");
        }else{
            $(this).parent().parent().find(".adv-search-field").hide();
            $(this).find("i").text("add");
        }
    });

    // openMessageModal("12706580",null);
    //Auto scroll on arrow keys
    $(document).keydown(function (event) {
        if(event.key == "ArrowRight"){
            console.log(event);
        }else if(event.key == "ArrowLeft"){
            console.log(event);
        }
    });
});

function toggleToolbar(toolbar,button){
    if(toolbar.hasClass("active")){
        toolbar.removeClass("active");
        toolbar.fadeOut();
        button.find("i").attr('Show Toolbar');
        button.find("i").addClass("rotate");
    }else{
        toolbar.addClass("active");
        toolbar.fadeIn();
        button.find("i").attr('Hide Toolbar');
        button.find("i").removeClass("rotate");
    }
}

function openMessageAction(action) {
    if($("tr.is-selected").length > 0){
        $(".action-modal[data-action="+action+"]").modal('open');
    }
}
function closeMessages() {
    $("#act-close-messages .progress").removeClass("hiddendiv");
    var rows = $("tr.is-selected");
    //close logic
    setTimeout(function () {
        msgSuccess('Messages closed',2000);
        $("#act-close-messages .progress").addClass("hiddendiv");
        $('#act-close-messages').modal('close');
    },1500);
}
function assignTo() {
    $("#act-assign-to .progress").removeClass("hiddendiv");
    var rows = $("tr.is-selected");
    //close logic
    setTimeout(function () {
        msgSuccess('Assigned to '+$("#sel-assign-to").val(),2000);
        $("#act-assign-to .progress").addClass("hiddendiv");
        $('#act-assign-to').modal('close');
    },1500);
}
function openMessageModal(msgid,row) {
    $("#message-content-modal span[data-attribute=message-control-id]").html(msgid);
    //set all the other field values
    $("#view-msg-content textarea,#view-trns-msg textarea").val("");
    $("#view-msg-content .msg-content-action,#view-trns-msg .msg-content-action").show();
    $("#view-msg-content .err-text,#view-trns-msg .err-text").detach();
    $("#message-content-modal").modal('open');
}
function showMessageContent(msgid,panelid,path){
    $(panelid+" .msg-content-action .progress").css({
        opacity : 1
    });
    $(panelid+" .msg-content-action .btn").addClass("disabled");
    setTimeout(function () {
        $.get(path,function (data) {
           $(panelid+" textarea").val(data);
           //  $(panelid+" textarea").trigger('autoresize');
        }).fail(function () {
            msgError("Failed to get message content",2000);
            $(panelid).prepend('<span class="err-text" style="display: block">' +
                '<i class="material-icons">highlight_off</i><br>An error occurred while fetching' +
                ' the message contents</span>');
        });
        $(panelid+" .msg-content-action").hide();
        $(panelid+" .msg-content-action .progress").css({
            opacity : 0
        });
        $(panelid+" .msg-content-action .btn").removeClass("disabled");
    },1500);
}
function msgSuccess(msg,duration) {
    Materialize.toast("<i class='material-icons left'>check</i>&nbsp;"+msg,duration);
}
function msgError(msg,duration) {
    Materialize.toast("<i class='material-icons left'>error_outline</i>&nbsp;"+msg,duration);
}

function switchForm(){
    if ($("#domain").val() == "CDM"){
        $("#formType").html("<h5 class=\"title\">Chronic Disease Management Message Inquiry</h5>");
        $("#source").hide();
        $("#element1").html("  <div class=\"col s7 input-field\" id=\"docSetID\">\n" +
            "                    <label>Document Set ID</label>\n" +
            "                    <input type=\"text\" name=\"document-setID\" url-updater>\n" +
            "                </div>");
    }
    else if($("#domain").val() == "CD"){
        $("#formType").html("<h5 class=\"title\">Clinical Document Message Inquiry</h5>");
        $("#source").hide();
        $("#element1").html("  <div class=\"col s7 input-field\" id=\"docID\">\n" +
            "                    <label>Document ID</label>\n" +
            "                    <input type=\"text\" name=\"doc-ID\" url-updater>\n" +
            "                </div>");
    }
    else if($("#domain").val() == "CE"){
        $("#formType").html("<h5 class=\"title\">Clinical Encounter Message Inquiry</h5>");
        $("#source").hide();
        $("#element1").html("  <div class=\"col s7 input-field\" id=\"visitNumber\">\n" +
            "                    <label>Visit Number</label>\n" +
            "                    <input type=\"text\" name=\"visit-number\" url-updater>\n" +
            "                </div>");
    }
    else if($("#domain").val() == "LAB"){
        $("#formType").html("<h5 class=\"title\">Lab Message Inquiry</h5>");
        $("#source").hide();
        $("#element1").html("  <div class=\"col s7 input-field\" id=\"orderNumber\">\n" +
            "                    <label>Order Number</label>\n" +
            "                    <input type=\"text\" name=\"order-number\" url-updater>\n" +
            "                </div>");
    }
    else if($("#domain").val() == "MI"){
        $("#formType").html("<h5 class=\"title\">Medical Imaging Reports Message Inquiry</h5>");
        $("#source").show();
        $("#element1").html("  <div class=\"col s7 input-field\" id=\"docID\">\n" +
            "                    <label>Document ID</label>\n" +
            "                    <input type=\"text\" name=\"doc-ID\" url-updater>\n" +
            "                </div>");

    }else{
        $("#source").hide();
    }
}
//EXTRA FUNCTIONALITY
// var pageURL = new URL(location.href);
// function addURLSearchParam(param,value) {
//     if(!pageURL.searchParams.has("autosearch"))
//         pageURL.searchParams.set("autosearch","true");
//     pageURL.searchParams.set(param,value);
//     history.pushState({},null,pageURL.pathname+pageURL.search);
// }
// function clearURLSearchParams(){
//     for(var param of pageURL.searchParams.keys()){
//         pageURL.searchParams.delete(param);
//     }
//     history.pushState({},null,pageURL.pathname+pageURL.search);
// }
// $(document).ready(function () {
//    $("*[url-updater]").change(function (event) {
//        var param = $(this).attr('name');
//        var value = $(this).val().toString();
//        if(param && value)
//            addURLSearchParam(param,value);
//    }) ;
//     $(".form-row button[type=reset]").click(function (event) {
//        clearURLSearchParams();
//     });
// });
