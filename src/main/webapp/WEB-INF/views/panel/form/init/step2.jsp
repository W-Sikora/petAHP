<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<form id="stepped" action="#" method="post">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12">
                <h1 class="text-center py-3">Multi step form with vanilla JS in Bootstrap 4</h1>
            </div>
            <div class="col-md-10">
                <div class="indicators d-flex justify-content-around py-4 text-light">
                    <div class="rounded-circle bg-secondary px-2 shadow-sm mr-2">1</div>
                    <div class="rounded-circle bg-secondary px-2 shadow-sm mr-2">2</div>
                    <div class="rounded-circle bg-secondary px-2 shadow-sm mr-2">3</div>
                    <div class="rounded-circle bg-secondary px-2 shadow-sm">4</div>
                </div>
            </div>
            <div class="col-md-8">
                <div class="fix-height position-relative">
                    <div class="steps">
                        <div class="row justify-content-center">
                            <div class="col">
                                <div class="form-group">
                                    <label for="inp1">Label 1-1</label>
                                    <input type="text" id="inp1" class="form-control">
                                    <label for="inp1">Label 1-1</label>
                                    <input type="text" id="inpa2" class="form-control">
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <label for="inp12">Label 1-2</label>
                                    <input type="text" id="inp12" class="form-control">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="steps">
                        <div class="form-group">
                            <label for="inp2">Label 2</label>
                            <input type="text" id="inp2" class="form-control">
                        </div>
                    </div>
                    <div class="steps">
                        <div class="form-group">
                            <label for="inp3">Label 3</label>
                            <input type="text" id="inp3" class="form-control">
                        </div>
                    </div>
                    <div class="steps">
                        <div class="form-group">
                            <label for="inp4">Label 4</label>
                            <input type="text" id="inp4" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-8 text-right">
                <button type="button" class="btnPrev btn btn-outline-success">Prev</button>
                <button type="button" class="btnNext btn btn-outline-success">Next</button>
            </div>
        </div>
    </div>
</form>

<footer>
    <c:import url="/WEB-INF/views/header&footer/footer.jsp"/>
</footer>
<script>
    document.addEventListener("DOMContentLoaded", () => {
        class MultiStep {
            constructor(formId) {
                let myForm = document.querySelector(formId),
                    steps = myForm.querySelectorAll(".steps"),
                    btnPrev = myForm.querySelector(".btnPrev"),
                    btnNext = myForm.querySelector(".btnNext"),
                    indicators = myForm.querySelectorAll(".rounded-circle"),
                    inputClasses = ".form-control",
                    currentTab = 0;

                // we'll need 4 different functions to do this
                showTab(currentTab);

                function showTab(n) {
                    steps[n].classList.add("active");
                    if (n == 0) {
                        btnPrev.classList.add("hidden");
                        btnPrev.classList.remove("show");
                    } else {
                        btnPrev.classList.add("show");
                        btnPrev.classList.remove("hidden");
                    }
                    if (n == steps.length - 1) {
                        btnNext.textContent = "Submit";
                    } else {
                        btnNext.textContent = "Next";
                    }
                    showIndicators(n);
                }

                function showIndicators(n) {
                    for (let i = 0; i < indicators.length; i++) {
                        indicators[i].classList.replace("bg-warning", "bg-success");
                    }
                    indicators[n].className += " bg-warning";
                }

                function clickerBtn(n) {
                    if (n == 1 && !validateForm()) return false;
                    steps[currentTab].classList.remove("active");
                    currentTab = currentTab + n;
                    if (currentTab >= steps.length) {
                        myForm.submit();
                        return false;
                    }
                    showTab(currentTab);
                }
// Do whatever validation you want
                function validateForm() {
                    let input = steps[currentTab].querySelectorAll(inputClasses),
                        valid = true;
                    for (let i = 0; i < input.length; i++) {
                        if (input[i].value == "") {
                            if (!input[i].classList.contains("invalid")) {
                                input[i].classList.add("invalid");
                            }
                            valid = false;
                        }
                        if (!input[i].value == "") {
                            if (input[i].classList.contains("invalid")) {
                                input[i].classList.remove("invalid");
                            }
                        }
                    }
                    return valid;
                }
                btnPrev.addEventListener("click", () => {
                    clickerBtn(-1);
                });
                btnNext.addEventListener("click", () => {
                    clickerBtn(1);
                });
            }
        }
        let MS = new MultiStep("#stepped");
    });

</script>
</body>
</html>