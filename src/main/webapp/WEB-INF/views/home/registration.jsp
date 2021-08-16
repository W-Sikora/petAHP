<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta lang="pl-PL">
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet"
          href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"/>"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/style/style.css"/>"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container min-height">

    <section>
        <nav class="breadcrumb transparent-background">
            <a class="breadcrumb-item text-dark" href="<c:url value="/"/>">strona główna</a>
            <span class="breadcrumb-item"><b>rejestracja</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-100">
        <div class="col-7 mx-auto my-auto">
            <div class="text-center mb-4">
                <h3>Rejestracja</h3>
            </div>

            <div class="form-group">
                <form action="<c:url value="/rejestracja"/>" method="post">
                    <div class="form-group">
                        <label for="name">Imię</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="np. Jan" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Adres e-mail</label>
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="np. jan.kowalski@gmail.com" required>
                        <small class="form-text text-muted">nie wysyłamy spamu :)</small>
                    </div>

                    <div class="form-group">
                        <label for="password">Hasło</label>
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="********" minlength="8" maxlength="30" required>
                        <small class="form-text text-muted">od 8 do 30 znaków</small>
                    </div>

                    <div class="text-center my-4">
                        <button type="submit" class="btn btn-outline-dark">zarejestruj</button>
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

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
