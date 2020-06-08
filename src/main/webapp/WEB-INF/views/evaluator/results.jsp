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
    <div class="row">
        <div class="col-lg-8 margin-auto text-center">
            <h2>Wyniki</h2>
            <hr>
            <h4 class="mtb7-17">strona główna</h4>
            <a role="button" class="btn btn-outline-dark mtb7-27" href="/">przejdź</a>
        </div>
    </div>
</div>

<footer class="footer-sm-page">
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

</body>
</html>
