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
              <option>AL</option>
              <option>AK</option>
              <option>AS</option>
              <option>AZ</option>
              <option>AR</option>
              <option>CA</option>
              <option>CO</option>
              <option>CT</option>
              <option>DE</option>
              <option>DC</option>
              <option>FM</option>
              <option>FL</option>
              <option>GA</option>
              <option>GU</option>
              <option>HI</option>
              <option>ID</option>
              <option>IL</option>
              <option>IN</option>
              <option>IA</option>
              <option>KS</option>
              <option>KY</option>
              <option>LA</option>
              <option>MA</option>
              <option>MD</option>
              <option>ME</option>
              <option>MH</option>
              <option>MI</option>
              <option>MN</option>
              <option>MS</option>
              <option>MO</option>
              <option>MT</option>
              <option>NE</option>
              <option>NV</option>
              <option>NH</option>
              <option>NJ</option>
              <option>NM</option>
              <option>NY</option>
              <option>NC</option>
              <option>ND</option>
              <option>MP</option>
              <option>OH</option>
              <option>OK</option>
              <option>OR</option>
              <option>PW</option>
              <option>PA</option>
              <option>PR</option>
              <option>RI</option>
              <option>SC</option>
              <option>SD</option>
              <option>TN</option>
              <option>TX</option>
              <option>UT</option>
              <option>VT</option>
              <option>VI</option>
              <option>VA</option>
              <option>WA</option>
              <option>WV</option>
              <option>WI</option>
              <option>WY</option>
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