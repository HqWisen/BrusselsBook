NOTIFTIME = 2000


function notif(message){
  $("#notifmessage").text(message);
  showNotif();
  setTimeout(unshowNotif, NOTIFTIME);
}

function showNotif(){
  $("#notif").removeClass("unshow");
  $("#notif").addClass("show");      
}

function unshowNotif(){
  $("#notif").removeClass("show");
  $("#notif").addClass("unshow");      
}

function inputFocus(e) {
    var val = $(this).val();
    var defaulttext = e.data.defaulttext;
    if (val == defaulttext) {
      e.target.selectionStart = 0;
      e.target.selectionEnd = 0;
    }
  }

  function inputKeyDown(e) {
    var val = $(this).val();
    var defaulttext = e.data.defaulttext;
    if (val == defaulttext) {
      $(this).val("");
      $(this).removeClass("input-default");
    }
  }

  function inputKeyUp(e) {
    var val = $(this).val();
    var defaulttext = e.data.defaulttext;
    if (val.length == 0) {
      $(this).val(defaulttext);
      e.target.selectionStart = 0;
      e.target.selectionEnd = 0;
      $(this).addClass("input-default");
    }
  }
  
  function initClass(id, defaulttext) {
    if ($(id).val() == defaulttext) {
      $(id).addClass("input-default");
    }
  }

  function buildInputDefault(id, defaulttext){
    $(id).focus({'defaulttext':defaulttext}, inputFocus);
    $(id).keydown({'defaulttext':defaulttext}, inputKeyDown);
    $(id).keyup({'defaulttext':defaulttext}, inputKeyUp);      
    initClass(id, defaulttext);
  }


