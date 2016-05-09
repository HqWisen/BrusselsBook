<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Book of Brussels Horeca !">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="icon" href="image/logoULB.png" />
<title>BrusselsBook</title>
</head>

<body>
	<c:import url="tabs.jsp">
		<c:param name="search" value="true"/>
	</c:import>
  <div id="container">
    <div class="estapage">
      <div class="estapage-name"><c:out value="${establishment.name}"/></div>
      <%-- TODO Check if image picture  --%>
      <c:if test="${null}">
      	<div class="estapage-picture"><img src="couverture.png" /></div>
      </c:if>
      <div class="estapage-coord">
        <div class="estapage-contact">
          <div class="estapage-address"><c:out value="${establishmentAddress}"/></div>
          <div class="estapage-number"><c:out value="${establishment.phoneNumber}"/></div>
          <div class="estapage-site"><a href=""><c:out value="${establishment.formattedUrl}"/></a></div>
        </div>
        <div id="map" class="map"></div>
      </div>
      <div class="estapage-tags">
        <div class="estapage-tag">
          <div class="estapage-tagname">Super</div>
          <div class="estapage-tagcounter estapage-tagcounterclick" onclick="addOneTag(this)">1</div>
        </div>
        <div class="estapage-tag">
          <div class="estapage-tagname">Trop bon</div>
          <div class="estapage-tagcounter">1</div>
        </div>
        <br/>
        <form class="estapage-tag-add" method="post">
          <input class="estapage-tag-addtext" type="text" />
          <input class="estapage-tag-addbutton" type="submit" value="+" />
        </form>
      </div>
      <div class="estapage-comments">
         <c:forEach items="${requestScope.comments}" var="comment">
           <div class="estapage-comment">
             <div class="estapage-commenttext"><c:out value="${comment.text}"/></div>
             <div class="estapage-commentauthor"><c:out value="${commentAuthors[comment.did]}"/></div>
             <div class="estapage-commentdate"><c:out value="${comment.creationDate}"/></div>
             <div class="estapage-commentscore">
		       <c:forEach var="i" begin="1" end="${comment.score}" step="1">
			     <img src="image/star.png" />
			   </c:forEach>
		       <c:forEach var="i" begin="1" end="${5 - comment.score}" step="1">
			     <img src="image/star-off.png" />
		       </c:forEach>
              </div>
           </div>
      	 </c:forEach>
       </div>
     <c:choose>
     	<c:when  test="${sessionScope.connected}">
     	<div class="estapage-commentform">
        <form id="form" method="post" action="comment">
          <textarea name="text" class="estapage-commentformtext"></textarea>
          <input type="hidden" name="score" id="comment-score" value="0"/>
          <input type="hidden" name="eid" value="${establishment.eid}"/>
          <input class="estapage-commentformsubmit" id="comment-submit" type="submit" value="Leave a comment" />
        </form>

        <div class="estapage-commentformscore">
          <img id="score1" src="image/star-off.png">
          <img id="score2" src="image/star-off.png">
          <img id="score3" src="image/star-off.png">
          <img id="score4" src="image/star-off.png">
          <img id="score5" src="image/star-off.png">
        </div>
      </div>
     		
     	</c:when>
    	<c:otherwise>
      <a href="login?from=establishment?eid=${establishment.eid}&elem=form" class="estapage-notconnected">
        Log in to leave a comment !
      </a>
 
    	</c:otherwise>
     </c:choose>
      
    </div>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script>
    var estaLat = ${establishmentAddress.latitude};
    var estaLng = ${establishmentAddress.longitude};
    var commentScore = 0;
    var map;

    for (var i = 1; i <= 5; i++) {
      $("#score" + i).hover(hoverScore);
    }

    $("#comment-submit").click(function(){
    	$("#comment-score").val(commentScore);
    });
    
    function getScoreFromId(id) {
      var score = id.charAt(id.length - 1);
      return parseInt(score, 10);
    }

    function changeScore(score) {
      commentScore = score;
      for (var i = 1; i <= score; i++) {
        $("#score" + i).attr("src", "image/star.png");
      }
      for (var i = score + 1; i <= 5; i++) {
        $("#score" + i).attr("src", "image/star-off.png");
      }
    }

    function hoverScore(e) {
      var score = getScoreFromId($(this).attr('id'));
      changeScore(score);
    }

    function addOneTag(element) {
      var value = parseInt(element.innerHTML, 10);
      $(element).text(value + 1);
      element.onclick = undefined;
      $(element).removeClass("estapage-tagcounterclick");
    }

    function initMap() {
      map = new google.maps.Map(document.getElementById('map'), {
        center: {
          lat: estaLat,
          lng: estaLng
        },
        zoom: 16
      });
      new google.maps.Marker({
        position: new google.maps.LatLng(estaLat, estaLng),
        map: map
      });
    }
  </script>
  <script src="https://maps.googleapis.com/maps/api/js?callback=initMap" async defer></script>


</body>

</html>