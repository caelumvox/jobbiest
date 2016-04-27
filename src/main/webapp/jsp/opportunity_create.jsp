<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/html/includes.html"%>
<title>jobbiest :: creating opportunity</title>
</head>
<body>
  <%@include file="/html/head.html"%>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 main">
        <form id="opportunity" class="form-horizontal" action="/jobbiest/view/opportunities">
          <!-- TODO: Static referenced! Please correct. -->
          <input type="hidden" name="seeker_id" value="1">
          <div class="form-group">
            <div class="col-md-2"></div>
            <div class="col-md-3">
                <h1>new opportunity</h1>
            </div>
          </div>
          
          <div class="form-group">
            <label for="inputName" class="col-md-2 control-label">Name</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputName" name="name" value="${name}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputLastname" class="col-md-2 control-label">Industry</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputLastname" name="industry" value="${industry}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputAddress" class="col-md-2 control-label">Address</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputAddress" name="address" value="${address}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputCity" class="col-md-2 control-label">City</label>
            <div class="col-md-2">
              <input type="text" class="form-control" id="inputCity" name="city" value="${city}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputState" class="col-md-2 control-label">State</label>
            <div class="col-md-1">
             <select class="form-control" name="state" id="inputState">
             </select>
            </div>
          </div>
          <div class="form-group">
            <label for="inputZip" class="col-md-2 control-label">Zip</label>
            <div class="col-md-1">
              <input type="text" class="form-control" id="inputZip" name="zip" value="${zip}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputUrl" class="col-md-2 control-label">URL</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputUrl" name="url" value="${url}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputStatus" class="col-md-2 control-label">Status</label>
            <div class="col-md-3">
             <select class="form-control" name="status" id="inputStatus">
               <option>Included</option>
               <option>Active</option>
               <option>Excluded</option>
             </select>
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
              <button type="submit" class="btn">Create</button>
              <button class="btn btn-default">Cancel</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <%@include file="/html/footer_includes.html"%>
  <script type="text/javascript">
  $(document).ready(function(){
      // Fill in states form.
      $.get("/jobbiest/rest/states", function(states_list) {
          $.each(states_list, function(index, state) {
              $("#inputState").append($("<option>" + state.abbreviation + "</option>"));
          });
      }, "json");
      
      $("#opportunity").submit(function(event) {
          var posting = $.post("/jobbiest/rest/opportunity",
              $("#opportunity").serialize());
          posting.done(function(data){
              var opportunity_id = data.opportunityId;  
              window.location.replace("/jobbiest/view/opportunity/" + opportunity_id);
          });
          return false;
      });
  });
  </script>
</body>
</html>