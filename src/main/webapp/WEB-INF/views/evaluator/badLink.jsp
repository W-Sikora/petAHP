<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
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
        .row {
            min-height: 500px;
        }
    </style>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <div class="row">
        <div class="col-lg-2"></div>
        <div class="col-lg-8">
            <h2></h2>
            <h3>Co robić?</h3>
        </div>
        <div class="col-lg-2"></div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
