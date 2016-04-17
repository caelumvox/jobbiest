<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<%@include file="/html/includes.html"%>
<title>jobbiest :: profile</title>
</head>
<body>
  <%@include file="/html/head.html"%>
  <div class="container-fluid">
    <div class="row">
      <div class="col-md-12 main">
        <form id="profile" class="form-horizontal">
          <div class="form-group">
            <label for="inputFirstname" class="col-md-2 control-label">First Name</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputFirstname" name="firstname" value="${firstname}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputLastname" class="col-md-2 control-label">Last Name</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputLastname" name="lastname" value="${lastname}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputUsername" class="col-md-2 control-label">Username</label>
            <div class="col-md-3">
              <input type="text" class="form-control" id="inputUsername" name="username" value="${username}">
            </div>
          </div>
          <div class="form-group">
            <label for="inputEmail" class="col-md-2 control-label">Email</label>
            <div class="col-md-3">
              <input type="email" class="form-control" id="inputEmail" name="email" value="${email}">
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
              <input type="text" class="form-control" id="inputState" name="state" value="${state}">
            </div>
          </div>
          <div class="form-group">
            <div class="col-md-offset-2 col-md-10">
              <button type="submit" class="btn">Update</button>
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
      $("#profile").submit(function(event) {
          $.post("/jobbiest/rest/seeker/" + ${seeker_id},
                  $("#profile").serialize());
          return false;
      });
  });
  </script>
</body>
</html>