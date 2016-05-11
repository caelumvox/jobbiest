    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="/">jobbiest</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">opportunities<span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="/web/opportunities">view all</a></li>
                <li><a href="/web/opportunities/create">create</a></li>
              </ul>
            </li>
          </ul>        
          <ul class="nav navbar-nav navbar-right">
          <% if (request.getRemoteUser() == null) { %>
            <li><a href="/web/login">login</a></li>
          <% } else { %>
            <li><a href="/web/profile"><%= request.getRemoteUser() %></a></li>
          <% } %>
            <li><a href="/web/about">about</a></li>
          </ul>
        </div>        
      </div>
    </nav>
