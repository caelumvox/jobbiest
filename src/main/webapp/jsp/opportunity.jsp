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
        <div id="summary" class="col-md-3">
          <div class="panel panel-default">
            <div class="panel-body">
              <h1 id="name">${name}</h1>
              <dl>
                <dt>URL</dt>
                <dd id="url"><a href="${url}">${url}</a>&nbsp;</dd>
              </dl>
              <dl>
                <dt>Address</dt>
                <dd id="address">${address} &nbsp;</dd>
                <dd id="citystatezip">${city}, ${state} ${zip} &nbsp;</dd>
              </dl>
              <dl>
                <dt>Industry</dt>
                <dd id="industry">${industry} &nbsp;</dd>
              </dl>
              <dl>
                <dt>Status</dt>
                <dd id="status"></dd>
              </dl>
            </div>
          </div>
        </div>
        <div class="col-md-9">
          <div class="panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">Event Log</h3>
            </div>
            <div id="events_body" class="panel-body">
              <div id="events_form_panel" class="panel panel-success">
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
    var edit_state_map = {};
    var seeker_id = ${seeker_id};
    var opp_id = ${opp_id};
    var status = "${status}";
    
    var event_map = {};
    
    // List of all states for the state drop down
    var states_list = null;
    
    var status_enum_to_text_map =  {
        "INCLUDED" : {
            "label_type":"label-success", 
            "label_string":"Included",
        },
        "ACTIVE" : {
            "label_type":"label-primary", 
            "label_string":"Active"
        },
        "EXCLUDED" : {
            "label_type":"label-danger", 
            "label_string":"Excluded"
        }
    };

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
    
    function open_inline_edit(event, name, dd_field_name) {
        edit_state_map[name]["editing"] = true;
        $(dd_field_name).empty();
        
        var form_inline = $("<form></form>").addClass("form-inline");

        var valid_func_map = {};
        if (name == "citystatezip") {
            var edit_city_box = $("<input></input").addClass("form-control").attr("type","text").attr("id","value_city");
            edit_city_box.attr("name","value_city").attr("value", edit_state_map[name]["orig_val"]["city"]);
            valid_func_map["city"] = function() {
                return true;
            }
            form_inline.append(edit_city_box);
            
            var edit_state_box = $("<select></select").addClass("form-control").attr("id","value_state");
            $.each(states_list, function(index, state) {
                var option = $("<option>" + state.abbreviation + "</option>");
                if (state.abbreviation == edit_state_map[name]["orig_val"]["state"]) {
                    option.attr("selected","true");
                }
                edit_state_box.append(option);
            });
            valid_func_map["state"] = function() {
                return true;
            }
            form_inline.append(edit_state_box);        
            
            var edit_zip_box = $("<input></input").addClass("form-control").attr("type","text").attr("id","value_zip");
            edit_zip_box.attr("name","value_zip").attr("value", edit_state_map[name]["orig_val"]["zip"]);
            valid_func_map["zip"] = function() {
                return validate_zip($("#value_zip").val());
            }
            form_inline.append(edit_zip_box);
        } else if (name == "status") {
            var select_box = $("<select></select>").attr("id","value_status");
            for (var key in status_enum_to_text_map) {
                if (status_enum_to_text_map.hasOwnProperty(key)) {
                    var option = $("<option></option>").attr("value",key);
                    option.text(status_enum_to_text_map[key].label_string);
                    if (edit_state_map[name]["orig_val"] == key) {
                        option.attr("selected", "true");
                    }
                    select_box.append(option);
                }
            }
            form_inline.append(select_box);
        } else {
            var edit_box = $("<input></input").addClass("form-control").attr("type","text").attr("id","value_" + name);
            var text = edit_state_map[name]["orig_val"];
            if (text == null) {
                text = "";
            }
            edit_box.attr("name","value_" + name).attr("value", text);
            form_inline.append(edit_box);
        }
        
        var ok_btn = $("<span></span>").attr("type","submit").addClass("glyphicon").addClass("glyphicon-ok-circle");
        ok_btn.attr("aria-hidden","true").attr("style", "float:right");
        ok_btn.click(function(event) {
            var value = null;
            if (name == "citystatezip") {
                value = {
                    "city" : $("#value_city").val(),
                    "state" : $("#value_state").val(),
                    "zip" : $("#value_zip").val()
                };
            } else {
                value = $("#value_" + name).val();
            }
            ok_inline_edit(event, name, dd_field_name, value);
            return false;
        });
        
        var cancel_btn = $("<span></span>").attr("type","submit").addClass("glyphicon").addClass("glyphicon-remove-circle");
        cancel_btn.attr("aria-hidden","true").attr("style", "float:right; margin-left: 2px");
        cancel_btn.click(function(event) {
            close_inline_edit(event, name, dd_field_name);
            return false;
        });
        
        form_inline.append(cancel_btn);
        form_inline.append(ok_btn);
        
        $(dd_field_name).append(form_inline);
    }
    
    function ok_inline_edit(event, name, dd_field_name, value) {
        var data_string = "";
        if (name == "citystatezip") {
            data_string += ("city=" + value["city"] + "&");
            data_string += ("state=" + value["state"] + "&");
            data_string += ("zip=" + value["zip"]);
        } else {
            data_string = name + "=" + value;
        }
        $.ajax({
            method : "POST",
            url : "/jobbiest/rest/opportunity/" + opp_id,
            // FIXME: For some reason, jquery is sending 'name' literal
            //data : {name : value},
            data : data_string,
            mimeType : "application/x-www-form-urlencoded",
        }).fail(function(err) {
            console.log(err);
        });
        
        if (name == "citystatezip") {
            edit_state_map[name]["orig_val"]["city"] = value["city"];
            edit_state_map[name]["orig_val"]["state"] = value["state"];
            edit_state_map[name]["orig_val"]["zip"] = value["zip"];
        } else {
            edit_state_map[name]["orig_val"] = value;
       }
 
        close_inline_edit(event, name, dd_field_name);
    }
    
    function close_inline_edit(event, name, dd_field_name) {
        edit_state_map[name]["editing"] = false;
        $(dd_field_name).empty();
        
        if (name == "url") {
            var anchor = $("<a></a>");
            var link_string = edit_state_map[name]["orig_val"];
            anchor.attr("href",link_string).text(link_string);
            $(dd_field_name).append(anchor);
        } else if (name == "status") {
            set_status_label(edit_state_map[name]["orig_val"]);
        } else if (name == "citystatezip") {
            $(dd_field_name).text(edit_state_map[name]["orig_val"]["city"] + ", " +
                edit_state_map[name]["orig_val"]["state"] + " " +
                edit_state_map[name]["orig_val"]["zip"]);
        } else {
            $(dd_field_name).text(edit_state_map[name]["orig_val"]);
        }
    }

    function register_opp_edit_behaviors(name, value) {
        var dd_field_name = "#" + name;

        edit_state_map[name] = {
            "orig_val" : value,
            "editing" : false
        };
        
        $(dd_field_name).mouseenter(function() {
            if (edit_state_map[name]["editing"] == false) {
                var edit_industry = $("<span></span>").addClass("glyphicon").addClass("glyphicon-pencil");
                edit_industry.attr("id","edit_" + name).attr("aria-hidden","true").attr("style","float:right");
                edit_industry.click(function(event) {
                    open_inline_edit(event, name, dd_field_name);
                });
                $(dd_field_name).append(edit_industry);
            }
        });

        $(dd_field_name).mouseleave(function() {
            $("#edit_" + name).remove();
        });        
    }
    
    function set_status_label(status) {
        var status_label = $("<span></span>").addClass("label");
        status_label.addClass(status_enum_to_text_map[status].label_type);
        status_label.text(status_enum_to_text_map[status].label_string);
        $("#status").append(status_label);
    }
    
    function build_event_panel(event) {
        var event_id = event['event_id'];
        var event_date = new Date(event['date']);
        var date_str = dateToString(event_date);
            
        var event_title = $("<h3></h3>").append(date_str + " - " + event['type']);
        var event_heading = $("<div></div>").addClass("panel-heading").append(event_title);
        var html_text = nl2br(event['text']);
        var event_body = $("<div></div>").addClass("panel-body").append(html_text);
        var event_panel = $("<div></div>").attr("id",event["eventId"]).addClass("panel").addClass("panel-success");
        event_panel.append(event_heading).append(event_body);
        
        return event_panel;
    }
    
    $(document).ready(function() {
        var cur_datetime_str = dateToString(new Date());
        $("#add_event_datetime").val(cur_datetime_str);

        set_status_label(status);

        // Get all events associated with this opportunity.
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
                event_map[event.eventId] = event;
                var event_panel = build_event_panel(event);

                events_body.append(event_panel);
            });
        }).fail(function(err) {
            console.log(err);
        });
        
        // Add Event button handler
        $("#add_event_btn").click(function(event){
            // Dispatch event to REST interface
            var posting = $.post("/jobbiest/rest/events/" + seeker_id + "/" + opp_id,
                $("#events_form").serialize());
            posting.done(function(data){
                // Receive acknowledgement
                var eventId = data.eventId;
                
                var event = {};
                event["eventId"] = data.eventId;
                event["date"] = $("#add_event_datetime").val();
                event["type"] = $("#add_event_title").val();
                event["text"] = $("#add_event_description").val();
                
                // Add to existing events.
                var event_panel = build_event_panel(event);
                var after_panel = $("#events_form_panel");
                $("#events_body").children("div").each(function() {
                    if (this.id != "events_form_panel") {
                        if (data.date > event_map[this.id].date) {
                            return false;
                        }
                        after_panel = this;
                    }
                });
                after_panel.after(event_panel);
                
                // Clear out existing form.
                $("#add_event_title").val("");
                $("#add_event_description").val("");
            });
            
        });
        
        // Register icon callback.
        var citystatezip_map = {
            "city" : "${city}",
            "state" : "${state}",
            "zip" : "${zip}"
        }
        register_opp_edit_behaviors("name", "${name}");
        register_opp_edit_behaviors("url", "${url}");
        register_opp_edit_behaviors("address", "${address}");
        register_opp_edit_behaviors("citystatezip", citystatezip_map);
        register_opp_edit_behaviors("industry", "${industry}");
        register_opp_edit_behaviors("status", "${status}");
        
        $.get("/jobbiest/rest/states", function(data) {
            states_list = data;
        }, "json");
    });
        </script>
</body>
</html>