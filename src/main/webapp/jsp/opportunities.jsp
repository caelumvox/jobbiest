<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/resources/html/includes.html" %>
  <title>jobbiest :: track your next move</title>
</head>
<body>
<%@include file="/jsp/navbar.jsp" %>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 main">
        <div class="col-md-7">
          <table id="opp_table" class="table table-striped">
            <thead>
              <tr>
                <th>name</th>
                <th>status</th>
                <th>industry</th>
                <th>location</th>
              </tr>
            </thead>
          </table>
        </div>
      </div>
    </div>
  </div>
<%@include file="/resources/html/footer_includes.html" %>
  <script type="text/javascript">
  $(document).ready(function(){
	  var request_map = {};
	  var seeker_id = ${seeker_id};
	  $.ajax({
           method:"GET",
           url:"/rest/opportunities?seekerId=" + seeker_id,
           dataType:"json",
           contentType:"application/json"
      }).done(function(opp_list){
    	  opp_table = $("#opp_table");
    	  
    	  opp_list.sort(function(a, b) {
    		 return a.name.localeCompare(b.name); 
    	  });
    	  
          $.each(opp_list, function(index, opportunity) {
        	  var opp_id = opportunity['opp_id'];
       	      var linkpath = "/web/opportunities/" + opp_id;
       	      var link = $("<a></a>").attr("href", linkpath).text(opportunity['name']);
       	      
        	  var name_col = $("<td></td>").append(link);
        	  var industry_col = $("<td></td>").text(opportunity['industry']);
              var location_col = $("<td></td>").text(opportunity['city'] + ", " + opportunity['state']);
        	  
              var status_val = opportunity['status'];
              var status_label = $("<span></span>");
              status_label.addClass("label");
              if (status_val == "INCLUDED") {
            	  status_label.addClass("label-success").text("Included");
              } else if (status_val == "EXCLUDED") {
            	  status_label.addClass("label-danger").text("Excluded");
              } else if (status_val == "ACTIVE"){
            	  status_label.addClass("label-primary").text("Active");
              }
        	  var status_col = $("<td></td>").append(status_label);
        	  
       	      var row = $("<tr id=\"opp_" + opp_id + "\"></tr>");
       	      row.append(name_col);
       	      row.append(status_col);
       	      row.append(industry_col);
       	      row.append(location_col);
       	      
              opp_table.append(row);
          });
      }).fail(function(err){
           console.log(err);
      });

    });
  </script>
</body>
</html>