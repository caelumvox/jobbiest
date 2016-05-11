<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">  
  <link href="/resources/css/bootstrap-editable.css" rel="stylesheet">
  <link href="/resources/css/signin.css" rel="stylesheet">
  <title>jobbiest :: track your next move</title>
</head>
<body>
  <div class="container">

    <form class="form-signin" method="post" action="/web/login">
      <h2 class="form-signin-heading">Please sign in</h2>
      <label for="inputUsername" class="sr-only">Email address</label>
      <input id="inputUsername" name="username" class="form-control" placeholder="Username" required autofocus></input>
      <label for="inputPassword" class="sr-only">Password</label>
      <input id="inputPassword" name="password" class="form-control" placeholder="Password" type="password" required></input>
      <div class="checkbox">
        <label>
          <input type="checkbox" value="remember-me"> Remember me
        </label>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      <input type="hidden" name="${_csrf.parameterName}"   value="${_csrf.token}" />
    </form>

  </div> <!-- /container -->
<%@include file="/resources/html/footer_includes.html" %>

</body>
</html>