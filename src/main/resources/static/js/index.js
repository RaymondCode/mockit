$(function () {
    $("#result-url").hide();

    $("#send-btn").click(function () {
        $("#send-btn").attr("disabled", true);
        $.post("/api/put",
            $("#create-form").serializeArray().concat({name: "body", value: editor.getSession().getValue()}),
            function (data, status) {
                $("#result-url").html("<b>Success!  Better Call: </b>" + '<a href="' + data + '" target="blank">' + data + '</a>');
                $("#result-url").show();
                $("#send-btn").attr("disabled", false);
            });
    });

    $('.js-example-basic-single').select2();

    $('#statusCode').select2().val(200);
    $('#statusCode').change();

    var editor = ace.edit("editor");
    editor.setOptions({
        autoScrollEditorIntoView: true,
        minLines: 25,
        maxLines: 25,
        showLineNumbers: false,
        showGutter: false
    });

    editor.getSession().setMode("ace/mode/json");
    editor.renderer.setScrollMargin(10, 10, 10, 10);
});