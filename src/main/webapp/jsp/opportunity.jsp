<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
              <div class="panel panel-success">
                <form id="events_form" class="form-horizontal" method="POST">
                  <div class="form-group">
                    <div class="col-md-offset-2 col-md-6">
                      <h3>Add Event</h3>
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEventDateTime" class="col-md-2 control-label">Date/Time</label>
                    <div class="col-md-6">
                      <input id="add_event_datetime" name="date" type="datetime-local" class="form-control" value="1970-01-01T00:00:00">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEventTitle" class="col-md-2 control-label">Title</label>
                    <div class="col-md-6">
                      <input  id="add_event_title" type="text" name="type" class="form-control" placeholder="OnSite interview - Initech">
                    </div>
                  </div>
                  <div class="form-group">
                    <label for="inputEventDescription" class="col-md-2 control-label">Description</label>
                    <div class="col-md-6">
                      <textarea id="add_event_description" name="text" class="form-control" rows="3" placeholder="Met with Bill Lumburgh, VP.  Interesting conversation."></textarea>
                    </div>
                  </div>
                  <div class="form-group">
                    <div class="col-md-offset-2 col-md-6">
                      <button id="add_event_btn" type="button" class="btn btn-primary">Add Event</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@include file="/html/footer_includes.html"%>
  <script type="text/javascript">

    function leftPad(val, chr, len) {
        var result = val;
        if (val.length < len) {
            result = Array(len - val.length + 1).join(chr) + val;
        }
        return result;
    }
    
    function dateToString(date) {
        return String(date.getFullYear()) + "-" + 
            leftPad(String(date.getMonth()+1), "0", 2) + "-" + 
            leftPad(String(date.getDate()), "0", 2) + "T" + 
            leftPad(String(date.getHours()), "0", 2) + ":" + 
            leftPad(String(date.getMinutes()), "0", 2);
    }
  
    $(document).ready(function() {
        var seeker_id = ${seeker_id};
        var opp_id = ${opp_id};
        var status = "${status}";
        var status_label = $("<span></span>");

        var cur_datetime_str = dateToString(new Date());
        $("#add_event_datetime").val(cur_datetime_str);

        if (status == "INCLUDED") {
            status_label.addClass("label").addClass(
                    "label-success").text("Included");
        } else if (status == "EXCLUDED") {
            status_label.addClass("label").addClass(
                    "label-danger").text("Excluded");
        } else if (status == "ACTIVE") {
            status_label.addClass("label").addClass(
                    "label-primary").text("Active");
        }
        $("#status").append(status_label);

        $.ajax({
            method : "GET",
            url : "/jobbiest/rest/events/" + seeker_id + "/" + opp_id,
            dataType : "json",
            contentType : "application/json"
        }).done(function(event_list) {
            events_body = $("#events_body");
            event_list.sort(function(a, b) {
                // Subtract epoch dates, descending.
                return b.date - a.date;
            });
            $.each(event_list, function(index, event) {
                var event_id = event['event_id'];
                var event_date = new Date(event['date']);
                var date_str = dateToString(event_date);
                    
                var event_title = $("<h3></h3>").append(date_str + " - " + event['type']);
                var event_heading = $("<div></div>").addClass("panel-heading").append(event_title);
                var event_body = $("<div></div>").addClass("panel-body").text(event['text']);
                var event_panel = $("<div></div>").addClass("panel").addClass("panel-success");
                event_panel.append(event_heading).append(event_body);

                events_body.append(event_panel);
            });
        }).fail(function(err) {
            console.log(err);
        });
        
        // Add Event button handler
        $("#add_event_btn").click(function(event){
            // Dispatch event to REST interface
            var posting = $.post("/jobbiest/rest/event/" + seeker_id + "/" + opp_id,
                $("#events_form").serialize());
            posting.done(function(data){
                // Receive acknowledgement
                var eventId = data.eventId;  
                // Append to existing events.
                
                // Clear out existing form.
                $("#add_event_title").val("");
                $("#add_event_description").val("");
            });
            
        });
    });
        </script>
</body>
</html>