<jsp:include page="/Chunks/links.jsp"></jsp:include>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="/../images/LuvrLogo_dhrough.png" alt="luvrlogo" id="logo" width="30" height="30">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#"> Home <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>
            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search for Date Locations">
                </div>
                <button type="submit" class="btn btn-default">Find Date</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Click Here if You Hate Cybersecurity</a></li>
                <li><a href="#">Dates</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false"> Me <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/login">Login</a></li>
                        <li><a href="/signup">Register</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#">Privacy Policy</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>