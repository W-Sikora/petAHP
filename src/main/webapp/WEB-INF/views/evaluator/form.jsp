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
                <ol id="ol">
                </ol>
                <div class="text-center">
                    <button type="button" id="addCriterion" class="btn btn-outline-success">dodaj kryterium</button>
                    <button type="button" id="removeCriterion" class="btn btn-outline-danger m-left">usuń kryterium
                    </button>
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
    $(document).ready(function () {

        const add = $("#addCriterion");
        const remove = $("#removeCriterion");
        let iter = 0;
        add.click(() => {
            iter++;
            $("#ol").append(
                "<li>" +
                "<div class='form-row'>" +
                "<div class='col col-lg-7'>" +
                "<input type='text' class='form-control' placeholder='nazwa kryterium' name='"+ "criterionName" + iter +"'>" +
                "</div>" +
                "<div class='col col-lg-3'>" +
                "<input type='number' class='form-control' placeholder='nadrzędne kryterium' min='0' name='"+ "parentCriterion" + iter +"'>" +
                "</div>" +
                "</div>" +
                "</li>"
            );
        });
        remove.click(() => {
            iter--;
            $("#ol").find("li:last-child").remove();
        })

    });

</script>

<script>

    // const inputNumber = document.getElementById("nb");
    // const div = document.getElementById("sub");
    // div.style.marginLeft = "15px";
    // let iter;
    // let subCriteria;
    // let divs;
    //

    // inputNumber.addEventListener("change", () => {
    //     iter = 0;
    //     addCriteria(iter, div, inputNumber, false);
    //     subCriteria = find("input", "subCriteria");
    //     divs = find("div", "divSubCriteria");
    //     iter++;
    //     for (let j = 0; j < subCriteria.length; j++) {
    //         subCriteria[j].addEventListener("change", () => {
    //             addCriteria(iter, divs[j], subCriteria[j], false);
    //             subCriteria = find("input", "subCriteria");
    //             divs = find("div", "divSubCriteria");
    //             iter++;
    //             for (let j = 0; j < subCriteria.length; j++) {
    //                 subCriteria[j].addEventListener("change", () => {
    //                     addCriteria(iter, divs[j], subCriteria[j], false);
    //                     subCriteria = find("input", "subCriteria");
    //                     divs = find("div", "divSubCriteria");
    //                     iter++;
    //                     for (let j = 0; j < subCriteria.length; j++) {
    //                         subCriteria[j].addEventListener("change", () => {
    //                             addCriteria(iter, divs[j], subCriteria[j], true);
    //                         });
    //                     }
    //                 });
    //             }
    //         });
    //     }
    // });


    // function addCriteria(iter, div, parentInput, last) {
    //     removeAllRelated(div);
    //     for (let i = 0; i < parentInput.value; i++) {
    //         let elements = [];
    //
    //         elements[0] = document.createElement("br");
    //
    //         elements[1] = document.createElement("input");
    //         elements[1].placeholder = "nazwa kryterium";
    //         elements[1].className = "form-control";
    //         elements[1].name = "criterionName" + iter + "_" + i;
    //
    //         if (!last) {
    //             elements[2] = document.createElement("input");
    //             elements[2].placeholder = "liczba podkryteriów";
    //             elements[2].className = "form-control";
    //             elements[2].id = "subCriteriaNumber" + iter + "_" + i;
    //
    //             elements[3] = document.createElement(("div"));
    //             elements[3].id = iter + "divSubCriteria" + i;
    //             elements[3].style.marginLeft = 30 + "px";
    //         }
    //
    //         elements.forEach(el => {
    //             div.appendChild(el)
    //         });
    //     }
    // }
    //
    // function removeAllRelated(core) {
    //     while (core.firstChild) {
    //         core.lastChild.remove();
    //     }
    // }
    //
    // function find(tag, idContains) {
    //     let array = [];
    //     Array.from(document.getElementsByTagName(tag)).forEach(el => {
    //         if (el.id.includes(idContains)) {
    //             array.push(el);
    //         }
    //     });
    //     return array;
    // }

</script>
</body>
</html>
