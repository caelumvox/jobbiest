<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<%@include file="/html/includes.html" %>
  <title>jobbiest :: track your next move</title>
</head>
<body>
<%@include file="/html/head.html" %>
  <div class="container-fluid">
    <div class="row">
      <div id="summary" class="col-md-3" style="margin: 5px">
          <div class="panel panel-default">
            <div class="panel-body">
              <h1>${name}</h1>
              <table class="border: 0px; padding: 5px">
                <tr>
                  <td style="padding: 5px">Address:</td>
                  <td style="padding: 5px">${address}<br>${city}, ${state}</td>
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
      <div id="events" class="col-md-5" style="margin: 5px">
        <div class="panel panel-primary">
          <div class="panel-heading">
              <h3 class="panel-title">Event Log</h3>
          </div>
          <div class="panel-body">
            This is where the events happened.
          </div>
        </div>
        <div id="geo_map" class="col-md-4">
        </div>
      </div>
    </div>
  </div>
<%@include file="/html/footer_includes.html" %>
  <script type="text/javascript">
  $(document).ready(function(){
	  var opp_id = ${opp_id};
      var status = "${status}";
      var status_label = $("<span></span>");
      
      if (status == "INCLUDED") {
    	  status_label.addClass("label-success").text("Included");
      } else if (status == "EXCLUDED") {
    	  status_label.addClass("label-danger").text("Excluded");
      }
	  $("#status").append(status_label);
  });
  </script>
</body>
</html>