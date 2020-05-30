if (window.location.pathname === "/panel/tworzenie-nowej-ankiety") {
    const form = document.getElementById("form");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    const formParts = Array.from(document.getElementsByClassName("part"));
    let current = 0;

    const animalsDiv = document.getElementById("animals");
    const addAnimal = document.getElementById("addAnimal");
    const animalsMaxQty = 9;
    let animalIter = 0;

    const criteriaDiv = document.getElementById("criteria");
    const criteriaMaxQty = 40;
    let criterionIter = 0;

    let inputs = Array.from(formParts[current].getElementsByTagName("input"));

    display(current, form, prevBtn, formParts);

    nextBtn.addEventListener("click", () => {
        if (current === 0) {
            if (validate(current, formParts)) {
                current++;
            }
        } else {
            if (validate(current, formParts) && $(document).find("input[type=text]").length > 2) {
                current++
            } else {
                alert("muisz wprowadzić przynajmniej 2");
            }
        }
        display(current, form, prevBtn, formParts);
    });

    prevBtn.addEventListener("click", () => {
        if (current >= 1) {
            current--;
        }
        display(current, form, prevBtn, formParts);
    });

    inputs.forEach(el => {
        el.addEventListener("change", () => {
            if (el.value !== "") {
                el.className = "form-control";
            } else {
                el.className = "form-control invalid";
            }
        })
    });

    addAnimal.addEventListener("click", () => {
        if ($(document).find("input[name^=animal]").length < animalsMaxQty) {
            animalIter++;
            createAnimal(animalsDiv, animalIter);
        } else {
            alert("maksymalna liczba zwierząt to " + animalsMaxQty);
        }
    });

    createCriterion(criteriaDiv, criterionIter, 0, criteriaMaxQty);
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

function validate(index, parts) {
    const inputs = parts[index].getElementsByTagName("input");
    let valid = true;
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            inputs[i].className += " invalid";
            valid = false;
        }
    }
    return valid;
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

function createCriterion(criteriaDiv, iter, level, criteriaMaxQty) {
    if ($(document).find("input[name^=criterion]").length < criteriaMaxQty) {
        let div = document.createElement("div");
        div.className = "form-inline";
        div.id = "criterion" + iter;
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
            input.name = "criterionName" + iter;
            newDiv.appendChild(input);

            let inputLevel = document.createElement("input");
            inputLevel.type = "hidden";
            inputLevel.value = level;
            inputLevel.name = "level" + iter;
            newDiv.appendChild(inputLevel);

            let inputParent = document.createElement("input");
            inputParent.type = "hidden";
            if (level > 1) {
                input.addEventListener("change", function () {
                    inputParent.value = $(this).parents()[3].getElementsByTagName("input")[0].name;
                });
            }
            inputParent.name = "inputParent" + iter;
            newDiv.appendChild(inputParent);

            let remove = document.createElement("button");
            remove.type = "button";
            remove.innerText = "usuń";
            remove.className = "btn btn-outline-danger rm15 margin-top-sm";
            remove.addEventListener("click", function () {
                $(this).closest("div[id^=criterion]").remove();
            });
            newDiv.appendChild(remove);
        }

        if (level < 4) {
            let add = document.createElement("button");
            add.type = "button";
            add.innerText = "dodaj";
            add.className = "btn btn-outline-success margin-top-sm";
            add.addEventListener("click", function (event) {
                iter++;
                createCriterion(event.target.parentNode, iter, level + 1, criteriaMaxQty);
            });
            newDiv.appendChild(add);
        }
    } else {
        alert("maksymalna liczba kryteriów to " + criteriaMaxQty);
    }
}

function appendInnerCategory(level, element, inputsMaxNb) {
    let inputsLen = $("input[type=text]").length - 1;
    if (inputsLen < inputsMaxNb) {
        let div = document.createElement("div");
        div.className = "form-inline";

        let newDiv = document.createElement("div");
        newDiv.className = "level-" + level;

        if (level > 0) {
            console.log()
            let name = document.createElement("input");
            name.type = "text";
            name.className = "form-control rm15";
            name.style = "width: 350px";
            name.placeholder = "nazwa";
            if (level === 1) {
                name.name = "criterion_lev=" + level + "_par=_id=" + inputsLen;
            } else {
                let parentName = $(element).find("input[type=text]")[0].name;
                name.name = "criterion_lev=" + level + "_par=" + parentName + "_id=" + inputsLen;
            }
            newDiv.appendChild(name);

            let remove = document.createElement("button");
            remove.type = "button";
            remove.innerText = "usuń";
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
            add.addEventListener("click", function (event) {
                appendInnerCategory(level + 1, event.target.parentNode, inputsMaxNb);
            });
            newDiv.appendChild(add);
        }
        div.appendChild(newDiv);
        element.appendChild(div);
    } else {
        alert("Osiągnięto maksymalną liczbę kryteriów");
    }
}

function changeInputClassWhen(input, event, className) {
    input.addEventListener(event, () => {
        input.className = className;
    });
}


