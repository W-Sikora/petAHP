if (window.location.pathname === "/panel/tworzenie-nowej-ankiety") {
    const form = document.getElementById("form");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    const formParts = Array.from(document.getElementsByClassName("part"));
    const inputsMinNb = [3, 2, 2];
    let current = 0;

    const evaluatorNumber = document.getElementById("evaluatorNumber");
    const errorEvaluatorNumber = document.getElementById("errorEvaluatorNumber");
    const endDate = document.getElementById("endDate");
    const errorEndDate = document.getElementById("errorEndDate");
    let correctNb, correctDate;

    const animalsDiv = document.getElementById("animals");
    const addAnimal = document.getElementById("addAnimal");
    const animalsMaxNb = 9;
    let animalIter = 0;

    const criteriaDiv = document.getElementById("criteria");
    const criteriaMaxNb = 25;

    display(current, form, prevBtn, formParts);

    evaluatorNumber.addEventListener("change", () => {
        correctNb = isIntegerGreaterThan(evaluatorNumber, 1);
        if (!correctNb) {
            errorEvaluatorNumber.style.display = "inline";
        } else {
            errorEvaluatorNumber.style.display = "none";
        }
    });

    endDate.addEventListener("change", () => {
        correctDate = isDateCorrect(endDate);
        if (!correctDate) {
            errorEndDate.style.display = "inline";
        } else {
            errorEndDate.style.display = "none";
        }
    });

    nextBtn.addEventListener("click", () => {
        let inputs = $($(document).find(".part").get(current)).find("input").toArray();
        if (current === 0) {
            if (isNotEmpty(inputs) && correctNb && correctDate) {
                current++;
                display(current, form, prevBtn, formParts);
            }
        } else {
            if (isNotEmpty(inputs) && inputs.length >= inputsMinNb[current]) {
                if (current === 2) {
                    let inputsPart2 = $($(document).find(".part").get(2)).find("input").toArray();
                    for (let i = 0; i < inputsPart2.length; i++) {
                        inputsPart2[i].name += "_" + i;
                    }
                }
                current++;
                display(current, form, prevBtn, formParts);
            }
        }
    });

    prevBtn.addEventListener("click", () => {
        if (current >= 1) {
            current--;
            display(current, form, prevBtn, formParts);
        }
    });

    addAnimal.addEventListener("click", () => {
        let inputs = $($(document).find(".part").get(current)).find("input").toArray();
        if (inputs.length < animalsMaxNb) {
            animalIter++;
            createAnimal(animalsDiv, animalIter);
        }
    });

    createCriterion(criteriaDiv, 0, criteriaMaxNb);

}

if (window.location.pathname === "/panel/ankieta-podsumowanie") {
    new ClipboardJS('.copy');
}

if (window.location.pathname === "/panel") {
    new ClipboardJS('.copy');

    const logOut = document.getElementById("logOut");
    const newForm = document.getElementById("newForm");

    changeInputClassWhen(logOut, "mouseover", "btn btn-outline-danger");
    changeInputClassWhen(logOut, "mouseout", "btn btn-outline-dark");
    changeInputClassWhen(newForm, "mouseover", "btn btn-outline-success");
    changeInputClassWhen(newForm, "mouseout", "btn btn-outline-dark");
}

if (window.location.pathname.includes("/ankieta=")) {

}

if (window.location.pathname === "ankieta/podsumowanie") {
    setTimeout(() => {
        window.location.href = "/";
    }, 3000);
}

function display(index, form, prevBtn, parts) {
    if (index === 0) {
        parts[0].style.display = "block";
        parts[1].style.display = "none";
        parts[2].style.display = "none";
        prevBtn.style.display = "none";
    } else if (index === 1) {
        parts[0].style.display = "none";
        parts[1].style.display = "block";
        parts[2].style.display = "none";
        prevBtn.style.display = "inline";
    } else if (index === 2) {
        parts[0].style.display = "none";
        parts[1].style.display = "none";
        parts[2].style.display = "block";
        prevBtn.style.display = "inline";
    } else {
        form.submit();
    }
}

function isNotEmpty(inputs) {
    let notEmpty = true;
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            notEmpty = false;
        }
    }
    return notEmpty;
}

function isIntegerGreaterThan(input, number) {
    return Number.isInteger(+input.value) && +input.value > number;
}

function isDateCorrect(input) {
    return new Date(input.value) instanceof Date && new Date(input.value) >= new Date(input.min);
}

function createAnimal(animalsDiv, iter) {
    let div = document.createElement("div");
    div.className = "input-group mb-3";
    div.id = "animal" + iter;
    animalsDiv.appendChild(div);

    let input = document.createElement("input");
    input.className = "form-control";
    input.type = "text";
    input.placeholder = "nazwa/gatunek zwierzęcia";
    input.name = "animalName" + iter;
    div.appendChild(input);

    let inputGroup = document.createElement("div");
    inputGroup.className = "input-group-append";
    div.appendChild(inputGroup);

    let button = document.createElement("button");
    button.className = "btn btn-outline-danger";
    button.type = "button";
    button.innerText = "usuń";
    button.addEventListener("click", function () {
        $(this).closest("div[id^=animal]").remove();
    });
    inputGroup.appendChild(button);
}

function getRndInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1) ) + min;
}

function createCriterion(criteriaDiv, level, criteriaMaxNb) {
    let inputs = $($(document).find(".part").get(2)).find("input[type=text]").toArray();
    if (inputs.length < criteriaMaxNb) {
        let div = document.createElement("div");
        div.className = "form-inline";
        criteriaDiv.appendChild(div);

        let newDiv = document.createElement("div");
        newDiv.className = "level-" + level;
        div.appendChild(newDiv);

        if (level > 0) {
            let input = document.createElement("input");
            input.type = "text";
            input.className = "form-control rm15";
            input.style = "width: 350px";
            input.placeholder = "nazwa";
            input.name = "criterionName" + getRndInteger(100000, 999999);
            newDiv.appendChild(input);

            let inputLevel = document.createElement("input");
            inputLevel.type = "hidden";
            inputLevel.value = level;
            inputLevel.name = "criterionLevel";
            newDiv.appendChild(inputLevel);

            let inputParent = document.createElement("input");
            inputParent.type = "hidden";
            inputParent.name = "criterionParent";
            if (level > 1) {
                input.addEventListener("change", function () {
                    inputParent.value = $(this).parents()[3].getElementsByTagName("input")[0].name;
                });
            } else {
                inputParent.value = "x";
            }

            newDiv.appendChild(inputParent);

            let remove = document.createElement("button");
            remove.type = "button";
            remove.innerText = "usuń";
            remove.className = "btn btn-outline-danger rm15 margin-top-sm";
            remove.addEventListener("click", function () {
                $(this).closest("div").parent().remove();
            });
            newDiv.appendChild(remove);
        }

        if (level < 4) {
            let add = document.createElement("button");
            add.type = "button";
            add.innerText = "dodaj";
            add.className = "btn btn-outline-success margin-top-sm";
            add.addEventListener("click", function (event) {
                createCriterion(event.target.parentNode, level + 1, criteriaMaxNb);
            });
            newDiv.appendChild(add);
        }
    }
}

function changeInputClassWhen(input, event, className) {
    input.addEventListener(event, () => {
        input.className = className;
    });
}


