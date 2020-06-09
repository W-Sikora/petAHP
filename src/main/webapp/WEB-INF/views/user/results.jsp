<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

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
    <nav class="navbar navbar-expand-sm navbar-light">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="<c:url value="/panel/"/>">panel użytkownika</a>
            <a class="nav-item nav-link active">/</a>
            <a class="nav-item nav-link active"><strong>wynik</strong></a>
        </div>
    </nav>
    <hr>
        <h2 class="text-center">Wynik: ${survey.name}</h2>
        <hr>
    <div class="col-lg-12">
        <c:forEach items="${results}" var="result" varStatus="i">
            <input name="name" type="hidden" value="${result.animal.name}"/>
            <input name="value" type="hidden" value="${result.value}"/>
        </c:forEach>
        <ol>
            <c:forEach items="${results}" var="result" varStatus="i">
                <li class="mtb37-7"><strong>${result.animal.name}</strong> :
                    <mark>
                        <c:set var="resultValue" value="${result.value}"/>
                        <fmt:formatNumber type="percent" minFractionDigits="2" value="${resultValue}"/>
                    </mark>
                </li>
                <c:forEach items="${evaluators}" var="evaluator" varStatus="j">
                    <ul>
                        <li class="mb10">
                                ${evaluator.name}
                            <c:set var="evaluatorValue" value="${evaluatorResults.get(j.index).get(i.index)}"/>
                            <fmt:formatNumber type="percent" minFractionDigits="2" value="${evaluatorValue}"/>
                        </li>
                    </ul>
                </c:forEach>
            </c:forEach>
        </ol>
        <canvas id="myChart" width="auto" height="100px"></canvas>
    </div>
</div>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>

<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"/>"></script>
<script src="<c:url value="https://cdn.jsdelivr.net/npm/clipboard@2/dist/clipboard.min.js"/>"></script>
<script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"/>"></script>
<script src="<c:url value="/static/js/index.js"/>"></script>

<script>
    let namesArray = [];
    let valuesArray = [];
    let names = document.getElementsByName("name");
    let values = document.getElementsByName("value");
    for (let i = 0; i < names.length; i++) {
        namesArray.push(names[i].value);
        valuesArray.push(+values[i].value);
    }

    new Chart(document.getElementById("myChart"), {
        type: 'horizontalBar',
        data: {
            labels: namesArray,
            datasets: [
                {
                    backgroundColor: 'rgba(0, 0, 0, 0.5)',
                    data: valuesArray
                }
            ]
        },
        options: {
            legend: {display: false},
            scales: {
                yAxes: [{
                    ticks: {
                        fontSize: 15,
                        fontColor: "black"
                    }
                }],
                xAxes: [{
                    ticks: {
                        fontSize: 15,
                        fontColor: "black",
                        suggestedMin: 0,
                        suggestedMax: 1
                    }
                }]
            }
        }
    });
</script>
</body>
</html>
