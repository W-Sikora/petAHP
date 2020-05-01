<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl-PL">
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
            <a class="nav-item nav-link active" href="/panel">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>pomoc</strong></a>
        </div>
    </nav>
    <hr>

    <div class="col-lg-12 text-center">
        <h2>O co chodzi?</h2>
    </div>
    <div class="col-lg-12">
        <p>
            Wybierz możliwie najlepszego zwierzęcia domowego uwzględniając
            preferencję poszczególnych domowników/współlokatorów itd.
        </p>
    </div>
    <div class="col-lg-12 text-center">
        <h2>Jak zacząć</h2>
    </div>
    <div class="col-lg-12">
        <h4>1</h4>
        <p>Zarejestruj się w naszej aplikacji</p>
        <h4>2</h4>
        <p>Zaloguj się</p>
        <h4>3</h4>
        <p>Utwórz formularz ankiety</p>
        <h4>4</h4>
        <p>Wybierz akceptowalne dla Ciebie zwierzęta</p>
        <h4>5</h4>
        <p>Wybierz kryteria według których będą ocenia zwierzęta</p>
        <h4>6</h4>
        <p>Udostępnij ankietę wybranym przez siebie osobą (pamiętaj, że sam też możesz ją wypełnić)</p>
        <h4>7</h4>
        <p>Udostępnij ranking końcowy</p>
        <h4>8</h4>
        <p>Wybierz nowego pupila bez kłótni</p>
    </div>
</div>


<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
