if (window.location.pathname === "/panel/tworzenie-nowej-ankiety") {

    const form = document.getElementById("form");
    const partsOfForm = Array.from(document.getElementsByClassName("part"));
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    let current = 0;

    display(current, form, prevBtn, partsOfForm);
//&& validate(current, partsOfForm)
    nextBtn.onclick = () => {
        current >= 0 ? current++ : current;

        display(current, form, prevBtn, partsOfForm);

    };

    prevBtn.onclick = () => {
        current >= 1 ? current-- : current;
        display(current, form, prevBtn, partsOfForm);
    };

    const animalsOrderedList = $("#animals");
    const addAnimal = $("#addAnimal");
    let animalIter = 0;

    $(addAnimal).click(() => {
        if (animalIter < 9) {
            animalIter++;
            animalsOrderedList.append(
                "<li id='animalRow'>" +
                "<div class='input-group mb-3'>" +
                "<input class='form-control' type='text' placeholder='nazwa/gatunek zwierzęcia'" +
                "name='animalName" + animalIter + "'/>" +
                "<div class='input-group-append' style='margin-top:15px'>" +
                "<button class='btn btn-outline-danger' type='button' id='removeAnimal'>usuń</button>" +
                "</div>" +
                "</div>" +
                "</li>");
        }
    });

    $(document).on("click", "#removeAnimal", function () {
        $(this).closest("#animalRow").remove();
        animalIter--;
    });

    function setName(level, index) {
        return "criterion_l=" + level + "_id=" + index;
    }

    function appendInnerCategory(level, element, inputsMaxNb) {
        console.log($("input[type=text]"));
        let inputsLen = $("input[type=text]").length - 1;

        if (inputsLen < inputsMaxNb) {
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
                if (level === 1) {
                    name.name = "criterion_id=" + inputsLen  + "_lev=" + level;
                } else {
                    let parentName = $(element).find("input[type=text]")[0].name;
                    name.name = "criterion_id=" + inputsLen  + "_lev=" + level + "_par=" + parentName;
                }

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

    function validateCriteria() {
        let inputs = $("input[type=text]");
        let error = 0;
        inputs.forEach(el => {
            el.value !== "" ? error++ : error += 0;
        });
        return error <= 0;
    }

    let inputsMaxNb = 30;
    appendInnerCategory(0, document.querySelector("#criteria"), inputsMaxNb);

}

if (window.location.pathname === "/panel/ankieta-podsumowanie") {
    new ClipboardJS('.copy');
}

if (window.location.pathname === "/panel") {

    document.getElementById("logOut").addEventListener("mouseover", () => {
        document.getElementById("logOut").className = "btn btn-outline-danger";
    });

    document.getElementById("logOut").addEventListener("mouseout", () => {
        document.getElementById("logOut").className = "btn btn-outline-dark";
    });

    document.getElementById("newForm").addEventListener("mouseover", () => {
        document.getElementById("newForm").className = "btn btn-outline-success";
    });

    document.getElementById("newForm").addEventListener("mouseout", () => {
        document.getElementById("newForm").className = "btn btn-outline-dark";
    });

    new ClipboardJS('.copy');

}

if (window.location.pathname.includes("/ankieta=")) {
    const form = document.getElementById("completeForm");
    const partsOfForm = Array.from(document.getElementsByClassName("part")).forEach(el => {
        el.style.display = "block";
    });
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");

    nextBtn.addEventListener("click", () => {
        form.submit();
    });

    // let current = 0;
    // display(current, form, prevBtn, partsOfForm);
    // nextBtn.onclick = () => {
    //     current >= 0 ? current++ : current;
    //     display(current, form, prevBtn, partsOfForm);
    // };
    //
    // prevBtn.onclick = () => {
    //     current >= 1 ? current-- : current;
    //     display(current, form, prevBtn, partsOfForm);
    // };

    const ratings = Array.from(document.getElementsByClassName("ratings"));
    const ratingValues = Array.from(document.getElementsByClassName("rating-value"));
    for (let i = 0; i < ratings.length; i++) {
        const stars = ratings[i].children;
        toInput(stars, ratingValues[i])
    }

    function toInput(stars, ratingValue) {
        let index;
        for (let i = 0; i < stars.length; i++) {
            stars[i].addEventListener("mouseover", function () {
                for (let j = 0; j < stars.length; j++) {
                    stars[j].classList.remove("fa-star");
                    stars[j].classList.add("fa-star-o");
                }
                for (let j = 0; j <= i; j++) {
                    stars[j].classList.remove("fa-star-o");
                    stars[j].classList.add("fa-star");
                }
            });
            stars[i].addEventListener("click", function () {
                ratingValue.value = i + 1;
                index = i;
            });
            stars[i].addEventListener("mouseout", function () {
                for (let j = 0; j < stars.length; j++) {
                    stars[j].classList.remove("fa-star");
                    stars[j].classList.add("fa-star-o");
                }
                for (let j = 0; j <= index; j++) {
                    stars[j].classList.remove("fa-star-o");
                    stars[j].classList.add("fa-star");
                }
            })
        }
    }

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
    let inputs = parts[index].getElementsByTagName("input");
    let valid = true;
    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].value === "") {
            inputs[i].className += " invalid";
            valid = false;
        } else {
            inputs[i].className = "form-control";
        }
    }
    return valid;
}

function createNew(base, parent, type, name, className, required, placeholder) {
    if (base.value >= base.min && base.value <= base.max) {
        removeAllRelated(parent);
        for (let i = 0; i < base.value; i++) {
            let newInput = document.createElement("input");
            setInput(parent, newInput, type, name + i, className, required, (i + 1) + ". " + placeholder);
        }
    }
}

function show(base, elements) {
    if (base.value >= base.min && base.value <= base.max) {
        hideAll(elements);
        for (let i = 0; i < base.value; i++) {
            elements[i].style.display = "block";
        }
    }
}

function hideAll(elements) {
    for (let i = 0; i < elements.length; i++) {
        elements[i].style.display = "none";
    }
}

function removeAllRelated(core) {
    while (core.firstChild) {
        core.lastChild.remove();
    }
}

function setInput(parent, base, type, name, className, required, placeholder) {
    base.type = type;
    base.name = name;
    base.className = className;
    base.required = required;
    base.placeholder = placeholder;
    parent.appendChild(base);
}

function find(tag, nameLike) {
    const allTag = Array.from(document.getElementsByTagName(tag));
    let matching = [];
    if (allTag.length > 0) {
        for (let i = 0; i < allTag.length; i++) {
            if (allTag[i].hasAttribute("name")) {
                if (allTag[i].name.includes(nameLike)) {
                    matching.push(allTag[i]);
                }
            } else {
                if (allTag[i].id.includes(nameLike)) {
                    matching.push(allTag[i]);
                }
            }
        }
    }
    return matching;
}
