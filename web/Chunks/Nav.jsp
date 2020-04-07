
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
            <a class="navbar-brand" href="<%= request.getContextPath() %>/Home?action=home">
                <img src="${pageContext.request.contextPath}/static/images/LuvrLogo_dhrough.png" alt="luvrlogo" id="logo">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <label for="zip"></label>
                    <input type="text" class="form-control" id="zip" name="zip" placeholder="Search for Date Locations"
                           pattern="(^\d{5}([ \-]\d{4})?$)">
                </div>
                <button type="submit" class="btn btn-default"><i class="fas fa-search fa-fw"></i> Find Date</button>
            </form>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="<%= request.getContextPath() %>/Home?action=dates">Dates</a></li>
                <li class="dropdown">
                    <c:choose>
                    <c:when test="${user == null}">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                            aria-expanded="false"> Not signed in <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<%= request.getContextPath() %>/Home?action=login"><i class="fas fa-sign-in-alt fa-fw"></i> Login</a></li>
                        <li><a href="<%= request.getContextPath() %>/Home?action=register"><i class="fas fa-user-plus fa-fw"></i> Register</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="#"><i class="fas fa-file fa-fw"></i> Privacy Policy</a></li>
                    </ul></c:when>
                    <c:otherwise>
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                           aria-expanded="false"> ${user.uName} <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="<%= request.getContextPath() %>/Home?action=userdates"><i class="fas fa-user fa-fw"></i> My Dates</a></li>
                            <li><a href="<%= request.getContextPath() %>/Home?action=logout"><i class="fas fa-sign-out-alt fa-fw"></i> Logout</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#"><i class="fas fa-file fa-fw"></i> Privacy Policy</a></li>
                        </ul>
                    </c:otherwise>
                </c:choose></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
