<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
            <span class="breadcrumb-item"><b>przejdź do ankiety</b></span>
        </nav>
        <hr>
    </section>

    <section class="row h-50">
        <div class="col-7 mx-auto my-auto">
            <div class="text-center mb-4">
                <h3>Przejdź do wypełnienia / wyniku ankiety</h3>
            </div>

            <div class="form-group">
                <form id="form" action="<c:url value="/"/>" method="post">
                    <label for="link">Wklej poniżej link</label>
                    <div class="input-group">
                        <input id="link" name="link" type="text" class="form-control">
                        <div class="input-group-append">
                            <button class="btn btn-outline-dark" type="submit">przejdź</button>
                        </div>
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
