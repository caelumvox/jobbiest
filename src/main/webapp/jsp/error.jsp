<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/resources/html/includes.html" %>
  <title>jobbiest :: track your next move</title>
</head>
<body>
  <div class="container">
    <div class="header clearfix">
      <nav>
        <ul class="nav nav-pills pull-right">
          <li role="presentation"><a href="/web/about">about</a></li>
        </ul>
      </nav>
      <h3 class="text-muted"><a href="/">jobbiest</a></h3>
    </div>

    <div class="jumbotron">
      <h1>${requestScope['javax.servlet.error.status_code']} Error</h1>
      <p class="lead">An error occurred while attempting to access this page.  Please contact support if you think this message was received in error.</p>
      <p>Message: ${requestScope['javax.servlet.error.message']}</p>
    </div>
  </div>
<%@include file="/resources/html/footer_includes.html" %>

</body>
</html>
