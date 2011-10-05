window.onload = function() {
  console.log('here');
  var editor = ace.edit("editor");
  editor.setTheme("ace/theme/twilight");
  var ClojureMode = require("ace/mode/clojure").Mode;
  editor.getSession().setMode(new ClojureMode());
};