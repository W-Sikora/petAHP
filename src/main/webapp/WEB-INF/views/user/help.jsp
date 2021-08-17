<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
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
            <a class="breadcrumb-item text-dark" href="<c:url value="/panel"/>">panel użytkownika</a>
            <span class="breadcrumb-item"><b>pomoc</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-75">
        <div class="col-12 my-auto text-center">
            <h2>Strona w budowie</h2>
        </div>
    </section>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
