const form = document.getElementById("newForm");
const partsOfForm = Array.from(document.getElementsByClassName("part"));
const prevBtn = document.getElementById("prevBtn");
const nextBtn = document.getElementById("nextBtn");
const noOfAnimals = document.getElementById("noOfAnimals");
const newAnimalParent = document.getElementById("animalsDiv");
const noOfCriteria = document.getElementById("noOfCriteria");
const subCriteriaDivs = find("div", "subCriteriaDiv");
const noOfSubCriteria = find("input", "noOfSubCriteria");

let current = 0;

display(current, form, prevBtn, partsOfForm);
//&& validate(current, partsOfForm)
nextBtn.onclick = () => {
    current >= 0  ? current++ : current;
    display(current, form, prevBtn, partsOfForm);
};

prevBtn.onclick = () => {
    current >= 1 ? current-- : current;
    display(current, form, prevBtn, partsOfForm);
};

noOfAnimals.addEventListener("change", () => {
    createNew(noOfAnimals, newAnimalParent, "text", "animal", "form-control", true, "nazwa/gatunek zwierzęcia")
});

noOfCriteria.addEventListener("change", () => {
    const criteria = document.getElementsByClassName("hide");
    show(noOfCriteria, criteria);
});

noOfSubCriteria.forEach((el, i) => {
    el.addEventListener("change", () => {
        createNew(el, subCriteriaDivs[i], "text", i +"subCriterion", "form-control", true, "nazwa podkryterium")
    })
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
