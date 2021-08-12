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
            <a class="nav-item nav-link active"><strong>logowanie</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-7 margin-auto">
            <c:if test="${errorMsg != null}">
                <div class="text-center">
                    <h3><mark>${errorMsg}</mark></h3>
                </div>
            </c:if>
            <h2>Logowanie</h2>
            <form action="<c:url value="/logowanie"/>" method="post">
                <div class="form-group">
                    <label>adres e-mail
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="np. jan.kowalski@gmail.com" required></label>
                    <label>hasło
                        <input type="password" class="form-control" id="password" name="password" placeholder="********"
                               required minlength="8" maxlength="30"></label>
                </div>
                <div class="text-center mtb17-7">
                    <button type="submit" class="btn btn-outline-dark">zaloguj</button>
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
