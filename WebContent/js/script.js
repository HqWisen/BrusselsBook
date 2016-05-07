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


