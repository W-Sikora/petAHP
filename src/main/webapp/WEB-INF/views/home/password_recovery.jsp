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
        <c:set var="currentPageName" scope="session" value="odzyskiwanie hasła"/>
        <c:import url="/WEB-INF/views/header&footer/nav.jsp"/>
        <nav class="breadcrumb transparent-background">
            <a class="breadcrumb-item text-dark" href="<c:url value="/"/>">strona główna</a>
            <span class="breadcrumb-item"><b>odzyskiwanie hasła</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-100">
        <div class="col-md-7 mx-auto">
            <div class="text-center mt-5 mb-4">
                <h3>Strona w budowie</h3>
            </div>

        </div>
    </section>

</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

</body>
</html>
