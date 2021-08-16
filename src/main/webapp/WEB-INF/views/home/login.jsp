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

<div class="container min-height">

    <section>
        <nav class="breadcrumb transparent-background">
            <a class="breadcrumb-item text-dark" href="<c:url value="/"/>">strona główna</a>
            <span class="breadcrumb-item"><b>logowanie</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-75">
        <div class="col-7 mx-auto my-auto">
            <div class="text-center">
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

                    <div class="text-center mtb17-7">
                        <button type="submit" class="btn btn-outline-dark">zaloguj</button>
                    </div>
                </form>
            </div>

            <div class="text-center mt-5">
                <c:if test="${error.length() > 0}">
                    <div class="text-center">
                        <p>
                            <mark>${error}</mark>
                        </p>
                    </div>
                </c:if>
            </div>
        </div>
    </section>

</div>

<footer class="footer-sm-page">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
