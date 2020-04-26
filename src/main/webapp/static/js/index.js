document.addEventListener("DOMContentLoaded", function () {

    const form = document.getElementById("form");
    const prevBtn = document.getElementById("prevBtn");
    const nextBtn = document.getElementById("nextBtn");
    const parts = Array.from(document.getElementsByClassName("part"));
    const animalsDiv = document.getElementById("animalsDiv");
    const criteriaDiv = document.getElementById("criteriaDiv");

    let noOfAnimals = document.getElementById("noOfAnimals");
    let noOfCriteria = document.getElementById("noOfCriteria");
    // let noOfSubCriteria = [];
    // let subCriteriaDivs = [];

    let current = 0;

    display(current, form, prevBtn, parts);

    nextBtn.onclick = () => {
        current >= 0 && validate(current, parts) ? current++ : current;
        display(current, form, prevBtn, parts);
    };

    prevBtn.onclick = () => {
        current >= 1 ? current-- : current;
        display(current, form, prevBtn, parts);
    };


    if (noOfAnimals) {
        noOfAnimals.onchange = () => {
            addAnimals(animalsDiv, noOfAnimals, "animal", "zwierzęcia");
        };
    }


    if (noOfCriteria) {
        noOfCriteria.onchange = () => {
            addCriteria(criteriaDiv, noOfCriteria, "criterion", "kryterium");
        };
    }



    // console.log(noOfSubCriteria);
    // if (noOfSubCriteria.length !== 0) {
    //     for (let i = 0; i < noOfSubCriteria.length; i++) {
    //         subCriteriaDivs.push(document.getElementById("subCriteriaDiv" + i));
    //         noOfSubCriteria[i].onchange = () => {
    //             addSubCriteria(subCriteriaDivs[i], noOfSubCriteria[i], "subCriterion" + i, "podkryterium")
    //         };
    //     }
    // }

});

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

function addAnimals(parent, input, name, placeholder) {
    if (input.value >= input.min && input.value <= input.max) {
        while (parent.firstChild) {
            parent.removeChild(parent.lastChild)
        }
        for (let i = 0; i < input.value; i++) {
            let newInput = document.createElement("input");
            newInput.type = "text";
            newInput.className = "form-control";
            newInput.name = "" + name + i;
            newInput.placeholder = "nazwa " + (i + 1) + "-ego " + placeholder;
            parent.appendChild(newInput);
        }
    }
}

function addCriteria(parent, input, name, placeholder) {
    if (input.value >= input.min && input.value <= input.max) {
        while (parent.firstChild) {
            parent.removeChild(parent.lastChild)
        }
        for (let i = 0; i < input.value; i++) {
            let criterionName = document.createElement("input");
            criterionName.type = "text";
            criterionName.className = "form-control";
            criterionName.name = "" + name + i;
            criterionName.placeholder = "nazwa " + (i + 1) + "-ego " + placeholder;
            parent.appendChild(criterionName);
            let noOfSubCriteria = document.createElement("input");
            noOfSubCriteria.type = "number";
            noOfSubCriteria.className = "form-control noOfSubCriteria";
            noOfSubCriteria.name = "noOfSubCriteria" + i;
            noOfSubCriteria.id = "noOfSubCriteria" + i;
            noOfSubCriteria.placeholder = "liczba podkryteriów (od 0 do 6)";
            parent.appendChild(noOfSubCriteria);
            let subCriteriaDiv = document.createElement("div");
            subCriteriaDiv.id = "subCriteriaDiv" + i;
            parent.appendChild(subCriteriaDiv);
        }
    }
}

// function addSubCriteria(parent, input, name, placeholder) {
//     if (input.value >= input.min && input.value <= input.max) {
//         while (parent.firstChild) {
//             parent.removeChild(parent.lastChild)
//         }
//         for (let i = 0; i < input.value; i++) {
//             let newInput = document.createElement("input");
//             newInput.type = "text";
//             newInput.className = "form-control";
//             newInput.name = "" + name + i;
//             newInput.placeholder = "nazwa " + (i + 1) + "-ego " + placeholder;
//             parent.appendChild(newInput);
//         }
//     }
// }

