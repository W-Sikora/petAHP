<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        hr {
            margin-top: 15px;
            margin-bottom: 15px;
        }

        p {
            text-align: justify;
            margin-top: 20px;
            margin-bottom: 20px;
        }

        h2 {
            margin-top: 10px;
            margin-bottom: 25px;
        }

        h3 {
            margin-top: 10px;
            margin-bottom: 17px;

        }

        h5 {
            margin-top: 20px;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 30px;
        }

        footer {
            margin-top: 40px;
        }
    </style>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>


<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="/">strona główna</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>rejestracja</strong></a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
            <h2>Rejestracja</h2>
            <c:if test="${registerError != null}">
                <p>${registerError}</p>
            </c:if>
            <form action="/zarejestrowano" method="post">
                <div class="form-group">
                    <label>imię</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="np. Jan" required>
                </div>
                <div class="form-group">
                    <label>adres e-mail</label>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="np. jan.kowalski@gmail.com" required>
                    <small id="emailHelp" class="form-text text-muted">nie wysyłamy spamu :)</small>
                </div>
                <div class="form-group">
                    <label>hasło</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="********"
                                minlength="8" maxlength="30" required>
                    <small id="passwordHelp" class="form-text text-muted">od 8 do 30 znaków</small>
                </div>
                <button type="submit" class="btn btn-dark">dalej</button>
            </form>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>