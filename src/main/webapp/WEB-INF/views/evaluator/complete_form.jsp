<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../static/style/style.css"/>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active">ankieta ${name}</a>
        </div>
    </nav>
    <hr>
    <div class="row">
        <div class="col-lg-6 margin-auto">
            <h2>Ankieta</h2>
            <form action="" method="post">
                <div class="form-group">
                    <label>imię wypełniającego
                    <input type="text" class="form-control" name="evaluatorName" placeholder="np. Jan" minlength="3" maxlength="30" required></label>
                </div>
                <button type="submit" class="btn btn-dark">zaloguj</button>
            </form>
        </div>

    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
