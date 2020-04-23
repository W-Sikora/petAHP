// function addSubCriteria(index) {
//     const div = document.getElementById("divSubCriteria" + index);
//     let input = document.getElementById("noOfSubCriteria" + index);
//     if(input.value > -1 && input.value <7) {
//         let subCriteria = Array.from(document.getElementsByClassName("" + index));
//         for (let i = 0; i < subCriteria.length ; i++) {
//             div.removeChild(subCriteria[i])
//         }
//         for (let i = 0; i < input.value; i++) {
//             let newInput = document.createElement("input");
//             newInput.type = "text";
//             newInput.className = "form-control " + index ;
//             newInput.name = index + "subCriterionName" + i;
//             newInput.placeholder = "nazwa " + (i + 1) + "-ego podkryterium";
//             div.appendChild(newInput);
//         }
//     }
// }

// function addAnimal() {
//     const div = document.getElementById("divAnimals");
//     let input = document.getElementById("noOfAnimals");
//     if (input.value > -1 && input.value < 7) {
//         let subCriteria = Array.from(document.getElementsByClassName("" + index));
//         for (let i = 0; i < subCriteria.length; i++) {
//             div.removeChild(subCriteria[i])
//         }
//         for (let i = 0; i < input.value; i++) {
//             let newInput = document.createElement("input");
//             newInput.type = "text";
//             newInput.className = "form-control " + index;
//             newInput.name = index + "subCriterionName" + i;
//             newInput.placeholder = "nazwa " + (i + 1) + "-ego podkryterium";
//             div.appendChild(newInput);
//         }
//     }
// }

function add(index, name, divId, inputId, min, max, className, placeholder) {
    const div = document.getElementById("" + divId + index);
    const input = document.getElementById("" + inputId + index);
    if (input.value > (min - 1) && input.value < (max + 1)) {
        let newInputs = Array.from(document.getElementsByClassName("" + className + index));
        console.log(newInputs.length);
        for (let i = 0; i <newInputs.length; i++) {
            div.removeChild(newInputs[i]);
        }
        for (let i = 0; i < input.value; i++) {
            let newInput = document.createElement("input");
            newInput.type = "text";
            newInput.className = "" + className + index;
            newInput.name = "" + index + name + i;
            newInput.placeholder = "nazwa " + (i + 1) + "-ego " + placeholder;
            div.appendChild(newInput);
        }
    }
}