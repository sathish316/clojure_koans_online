var editor;
$(function() {
  editor = ace.edit("editor");
  editor.setTheme("ace/theme/twilight");
  var ClojureMode = require("ace/mode/clojure").Mode;
  editor.getSession().setMode(new ClojureMode());

  function findErrorLine(assertionError){
    var line = assertionError.split("\n")[0];
    line = line.substr(line.lastIndexOf(": ") + 2);
    var lineNumber = -1
    $(editor.getSession().getValue().split("\n")).each(function(index, currentLine){
      if(currentLine.indexOf(line) >= 0){
        lineNumber = index;
        return false;
      }
     });
    return lineNumber;
  }

  $('.run-koan').click(function(){
    var code = editor.getSession().getValue();
    $.ajax({
      type: "POST",
      url: "./eval",
      data: {code: code},
      success: function(data){
        $('#status').html(data.status);
        $('#status').removeClass().addClass(data.status);
        if (data.status == 'FAIL'){
          var lineNumber = findErrorLine(data.message);
          editor.getSession().setAnnotations([{ row: lineNumber, column: 0, text: "Assertion error", type: "error"}]);
        } else {
          $('#next_koan').removeClass('hidden');
        }
      },
      error: function(data){
        console.log("failed");
      }
    })
  });
});