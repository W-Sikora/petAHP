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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .level-1 {
            padding-left: 45px;
        }

        .level-2 {
            padding-left: 45px;
        }

        .level-3 {
            padding-left: 45px;
        }

        .level-4 {
            padding-left: 45px;
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
            <a class="nav-item nav-link active" href="<c:url value="/"/>">strona główna</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>ankieta: ${survey.name}</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-10 margin-auto">
            <form>
                <div id="criteria">
                </div>

            </form>
        </div>
    </div>
</div>


<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<%--<script src="<c:url value="/static/js/index.js"/>"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    function setName(level) {
        return "criterion_l=" + level;
    }

    function appendInnerCategory(level, element) {
        let div = document.createElement("div");
        div.className = "form-inline";

        let newDiv = document.createElement("div");
        newDiv.className = "level-" + level;

        if (level > 0) {
            let name = document.createElement("input");
            name.type = "text";
            name.className = "form-control rm15";
            name.style = "width: 350px";
            name.placeholder = "nazwa";
            name.name = setName(level);
            newDiv.appendChild(name);

            let remove = document.createElement("input");
            remove.type = "button";
            remove.value = "usuń";
            remove.className = "btn btn-outline-danger rm15";
            remove.addEventListener("click", function () {
                this.closest("div").parentElement.remove();
            });
            newDiv.appendChild(remove);
        }

        if (level < 4) {
            let add = document.createElement("input");
            add.type = "button";
            add.value = "dodaj";
            add.className = "btn btn-outline-success";
            add.addEventListener("click", function(event) {
                appendInnerCategory(level + 1, event.target.parentNode);
            });
            newDiv.appendChild(add);
        }

        div.appendChild(newDiv);
        element.appendChild(div);
    }

    let i = 0;
    appendInnerCategory(0, document.querySelector("#criteria"), i);

</script>

</body>
</html>
