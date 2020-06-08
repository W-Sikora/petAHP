<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<c:url value="/"/>">strona główna</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>przejdź do ankiety</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-7 margin-auto">
            <c:if test="${error != null}">
                <div class="text-center">
                    <h3>
                        <mark>${error}</mark>
                    </h3>
                </div>
            </c:if>
            <h2>Przejdź do wypełnienia / wyniku ankiety</h2>
            <form action="<c:url value="/ankieta/"/>" method="post">
                <div class="form-group">
                    <label>wpisz/wklej poniżej link
                        <input type="text" class="form-control" name="link" required></label>
                </div>
                <div class="text-center mtb17-7">
                    <button type="submit" class="btn btn-outline-dark">przejdź</button>
                </div>
            </form>
        </div>
    </div>
</div>

<footer class="footer-sm-page">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
