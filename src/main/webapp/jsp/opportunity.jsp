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
      <div id="summary" class="col-md-3">
          <div class="panel panel-default">
            <div class="panel-body">
              <p id="opp_name"></p>
              <table class="border: 0px">
                <tr>
                  <td>Address:</td>
                  <td id="opp_address"></td>
                </tr>
                <tr>
                  <td>Industry:</td>
                  <td id="opp_industry"></td>
                </tr>
                <tr>
                  <td>Status:</td>
                  <td id="opp_status"></td>
                </tr>
              </table>
            </div>
          </div>
      </div>
      <div id="events" class="col-md-5">
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

  });
  </script>
</body>
</html>