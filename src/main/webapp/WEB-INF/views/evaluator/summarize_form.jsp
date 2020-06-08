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
          crossorigin="anonymous">
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
        <div class="col-lg-12">

            <div class="text-center">
                <h3>Pomyślnie zakończoną ankietę</h3>
                <h4 class="mtb37-17">strona główna</h4>
                <a role="button" class="btn btn-outline-dark mtb7-17" href="<c:url value="/"/>">przejdź</a>
            </div>

            <div class="col-lg-10 margin-auto">
                <h5>Ostateczny wynik:</h5>
                <hr>
                <div class="input-group mb-3">
                    <div class="input-group-prepend" style="margin-top:15px">
                        <span class="input-group-text">link do&nbsp;<b>wyniku</b>&nbsp;ankiety</span>
                    </div>
                    <input readonly class="form-control" style="background-color: white"
                           value="http://localhost:8080/ankieta/wynik=${survey.resultLink}"/>
                    <div class="input-group-append" style="margin-top:15px">
                        <button class="btn btn-outline-primary copy" id="resultLink${survey.id}"
                                data-clipboard-text="http://localhost:8080/ankieta/wynik=${survey.resultLink}">skopiuj
                        </button>
                    </div>
                </div>
                <h5 class="mtb37-7">Preferencje wg twoich odpowiedzi</h5>
                <hr>
                <c:forEach items="${names}" var="n">
                    <input type="hidden" name="name" value="${n}"/>
                </c:forEach>
                <c:forEach items="${values}" var="v">
                    <input type="hidden" name="value" value="${v}"/>
                </c:forEach>

                <canvas id="myChart" width="auto" height="100px"></canvas>
            </div>
        </div>
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
    for (let i = 0; i < names.length ; i++) {
        namesArray.push(names[i].value);
        valuesArray.push(values[i].value);
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
            legend: { display: false },
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
