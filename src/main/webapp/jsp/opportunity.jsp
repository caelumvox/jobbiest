<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<%@include file="/html/includes.html"%>
<title>jobbiest :: opportunity ${name}</title>
</head>
<body>
  <%@include file="/html/head.html"%>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 main">
        <div id="summary" class="col-md-4">
          <div class="panel panel-default">
            <div class="panel-body">
              <h1>${name}</h1>
              <table class="border: 0px; padding: 5px">
                <tr>
                  <td style="padding: 5px">URL:</td>
                  <td id="url" style="padding: 5px">
                    <a href="${url}">${url}</a>
                  </td>
                </tr>
                <tr>
                  <td style="padding: 5px; vertical-align: top">Address:</td>
                  <td style="padding: 5px">
                    <p>${address}</p>
                    <p>${city}, ${state}</p>
                  </td>
                </tr>
                <tr>
                  <td style="padding: 5px">Industry:</td>
                  <td style="padding: 5px">${industry}</td>
                </tr>
                <tr>
                  <td style="padding: 5px">Status:</td>
                  <td id="status" style="padding: 5px"></td>
                </tr>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-8">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Event Log</h3>
            </div>
            <div id="events_body" class="panel-body">
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@include file="/html/footer_includes.html"%>
  <script type="text/javascript">
  $(document).ready(function(){
      var seeker_id = ${seeker_id};
	  var opp_id = ${opp_id};
      var status = "${status}";
      var status_label = $("<span></span>");
      
      if (status == "INCLUDED") {
    	  status_label.addClass("label").addClass("label-success").text("Included");
      } else if (status == "EXCLUDED") {
    	  status_label.addClass("label").addClass("label-danger").text("Excluded");
      }
	  $("#status").append(status_label);
	  
      $.ajax({
          method:"GET",
          url:"/jobbiest/rest/events/" + seeker_id + "/" + opp_id,
          dataType:"json",
          contentType:"application/json"
     }).done(function(event_list){
         events_body = $("#events_body");
         
         event_list.sort(function(a, b) {
             // Subtract epoch dates, descending.
             return b.date - a.date; 
         });
         
         $.each(event_list, function(index, event) {
             var event_id = event['event_id'];
             var event_date = new Date(event['date']);
             var date_str = event_date.getFullYear() + '-' +
                 ("0" + (event_date.getMonth()+1)).slice(-2) + '-' + 
                 ("0" + event_date.getDate()).slice(-2) + ' ' +
                 ("0" + event_date.getHours()).slice(-2) + ':' +
                 ("0" + event_date.getMinutes()).slice(-2) + ':' +
                 ("0" + event_date.getSeconds()).slice(-2);
             
             var event_title = $("<h3></h3>").append(date_str + " - " + event['type']);
             var event_heading = $("<div></div>").addClass("panel-heading").append(event_title);
             var event_body = $("<div></div>").addClass("panel-body").text(event['text']);
             var event_panel = $("<div></div>").addClass("panel").addClass("panel-success");
             event_panel.append(event_heading).append(event_body);
           
             events_body.append(event_panel);
         });
     }).fail(function(err){
          console.log(err);
     });
  });
  </script>
</body>
</html>