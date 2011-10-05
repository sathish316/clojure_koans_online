var editor;
$(function() {
  editor = ace.edit("editor");
  editor.setTheme("ace/theme/twilight");
  var ClojureMode = require("ace/mode/clojure").Mode;
  editor.getSession().setMode(new ClojureMode());

  $('.run-koan').click(function(){
    var code = editor.getSession().getValue();
    $.ajax({
      type: "POST",
      url: "./eval",
      data: {code: code},
      success: function(data){
        console.log(data);
        $('#status').html(data.status);
        $('#status').removeClass().addClass(data.status);
      },
      error: function(data){
        console.log("failed");
      }
    })
  });
});