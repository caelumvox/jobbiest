<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/html/includes.html" %>
  <link href="/jobbiest/css/carousel.css" rel="stylesheet">
  <title>jobbiest :: track your next move</title>
</head>
<body>
<%@include file="/jsp/navbar.jsp" %>
  <!-- Carousel
  ================================================== -->
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img class="first-slide" src="/jobbiest/img/list.jpg" alt="First slide">
        <div class="container">
          <div class="carousel-caption">
            <h1>List your opportunities.</h1>
            <p>Get a top-level view of all your opportunities and observe the current status of interactions with them.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Registration Soon!</a></p>
          </div>
        </div>
      </div>
      <div class="item">
        <img class="second-slide" src="/jobbiest/img/track.jpg" alt="Second slide">
        <div class="container">
          <div class="carousel-caption">
            <h1>Evaluate your search.</h1>
            <p>Use specialized tools to determine which companies provide a better fit than others, and begin narrowing down to the best options.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Registration Soon!</a></p>
          </div>
        </div>
      </div>
      <div class="item">
        <img class="third-slide" src="/jobbiest/img/opp.jpg" alt="Third slide">
        <div class="container">
          <div class="carousel-caption">
            <h1>Select your best option.</h1>
            <p>Identify which opportunities presented themselves best, and pull the trigger.</p>
            <p><a class="btn btn-lg btn-primary" href="#" role="button">Registration Soon!</a></p>
          </div>
        </div>
      </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div><!-- /.carousel -->


  <div class="container marketing">

    <!-- Three columns of text below the carousel -->
    <div class="row">
      <div class="col-lg-4">
        <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
        <h2>List</h2>
        <p>Enter new potential opportunities using the creation form, listing them with the other opportunities.  Enter in details of the company, including location, industry, and job description links.  Add events as a running log as more interactions are made with the company.</p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
        <h2>Track</h2>
        <p>Track overall progress using analytics. Observe the search radius from your residence using maps.</p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
        <h2>Select</h2>
        <p>Use additional analytics to weigh in on certain opportunities.</p>
        <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
  </div><!-- /.container -->
<%@include file="/html/footer_includes.html" %>

</body>
</html>