<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>PetAHP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style>
        <c:import url="/WEB-INF/views/panel/form/style/style.jsp"/>
    </style>
</head>
<body>

<header>
    <c:import url="/WEB-INF/views/header&footer/header.jsp"/>
</header>

<div class="container">
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<c:url value="/panel"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active">nowa ankieta</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active">ustawienia zwierząt</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>ustawienia kryteriów</strong></a>
        </div>
    </nav>
    <hr>

    <div class="row">
        <div class="col-lg-12 text-center">
            <h3>Tworzenie nowej ankiety - krok 3 z 3</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-3"></div>

        <div class="col-lg-6">
            <div class="form-group">
                <form action="<c:url value="/panel/tworzenie-nowej-ankiety/krok-5"/>" method="post">

                    <div class="form-part">
                        <strong>Ustawienia kryteriów głównych</strong>
                        <hr>
                    </div>

                    <c:forEach begin="0" end="${noOfCriteria - 1}" varStatus="i">
                        <label>Nazwa ${i.count}-ego kryterium</label>
                        <input name="name${i.index}" class="form-control" placeholder="np. koszty"
                               type="text" minlength="1" required>
                        <label>Liczba podkryteriów</label>
                        <input onchange="addSubCriteria(${i.index})" name="noOfSubCriteria${i.index}" id="noOfSubCriteria${i.index}"
                               class="form-control" placeholder="od 0 do 6" type="number" min="0" max="6" required>
                        <div id="divSubCriteria${i.index}">

                        </div>
                    </c:forEach>

                    <div class="form-part">
                        <button type="submit" class="btn btn-dark">dalej</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="col-lg-3"></div>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script>
    function addSubCriteria(index) {
        const div = document.getElementById("divSubCriteria" + index);
        let input = document.getElementById("noOfSubCriteria" + index);
        if(input.value > -1 && input.value <7) {
            let subCriteria = Array.from(document.getElementsByClassName("" + index));
            for (let i = 0; i < subCriteria.length ; i++) {
                div.removeChild(subCriteria[i])
            }
            for (let i = 0; i < input.value; i++) {
                let newInput = document.createElement("input");
                newInput.type = "text";
                newInput.className = "form-control " + index ;
                newInput.name = index + "subCriterionName" + i;
                newInput.placeholder = "nazwa " + (i + 1) + "-ego podkryterium";
                div.appendChild(newInput);
            }
        }
    }
</script>

</body>
</html>
