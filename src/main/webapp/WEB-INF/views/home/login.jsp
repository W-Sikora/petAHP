<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pl-PL">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container min-height">

    <section>
        <c:set var="previousPageURL" scope="session" value="/"/>
        <c:set var="previousPageName" scope="session" value="strona główna"/>
        <c:set var="currentPageName" scope="session" value="logowanie"/>
        <c:import url="/WEB-INF/views/header&footer/nav.jsp"/>
    </section>

    <section class="row h-100">
        <div class="col-md-7 mx-auto">
            <div class="text-center mt-5 mb-4">
                <h3>Logowanie</h3>
            </div>

            <div class="form-group">
                <form action="<c:url value="/logowanie"/>" method="post">

                    <div class="form-group">
                        <label for="email">Adres e-mail</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="np. jan.kowalski@gmail.com" required>
                    </div>

                    <div class="form-group">
                        <label for="password">Hasło</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="********" minlength="8" maxlength="30" required>
                    </div>

                    <c:if test="${error != null}">
                        <div class="alert alert-danger">${error}</div>
                    </c:if>

                    <ul class="nav nav-pills nav-fill mt-4">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Odzyskaj hasło</a>
                        </li>
                        <li class="nav-item">
                            <button type="submit" class="btn btn-outline-dark">zaloguj</button>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Załóż konto</a>
                        </li>
                    </ul>

                </form>
            </div>

        </div>
    </section>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
