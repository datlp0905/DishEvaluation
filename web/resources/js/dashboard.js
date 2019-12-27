/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var nutritionComboBox;

function matchingIngrdientToServer(ingredientHash, nutritionHash, mappingNutrition) {
    var url = "http://localhost:8084/DishEvaluation/webresources/ingredient/matching-ingredient?" +
            "txtIngredientHashName=" + ingredientHash + "&" +
            "txtNutritionHashName=" + nutritionHash + "&" +
            "mappingNutrition=" + mappingNutrition;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS MATCHING INGREDIENT: ingreHash "
                    + ingredientHash + " nutriHash " + nutritionHash);
            renderMatchingIngredientTable(xmlHttp.responseXML);
        } else {
            console.log("ERROR MATCHING INGREDIENT: " + xmlHttp.status);
        }
    };

    xmlHttp.send();
}

function normalizeUnitToServer(ingredientId, equalTo, isIndependent) {
    var url = "http://localhost:8084/DishEvaluation/webresources/ingredient/normalize-unit?" +
            "txtIngredientId=" + ingredientId + "&txtEqualTo=" + equalTo +
            "&cbIndependent=" + isIndependent;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS NORMALIZE UNIT: ingreId "
                    + ingredientId + " qualTo " + equalTo
                    + " isIndependent " + isIndependent);
            renderNormalizeUnitTable(xmlHttp.responseXML);
        } else {
            console.log("ERROR NORMALIZE UNIT: " + xmlHttp.status);
        }
    };

    xmlHttp.send();
}

function getLishNutritionFromServer() {
    var url = "http://localhost:8084/DishEvaluation/webresources/nutrition";
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS GET LISH NUTRITION: " + xmlHttp.responseXML);
            createNutritionComboBox(xmlHttp.responseXML);
        } else {
            console.log("ERROR GET LISH NUTRITION: " + xmlHttp.status);
        }
    };

    xmlHttp.send();
}

function createNutritionComboBox(xmlDoc) {
    var path = "//nutrition";
    var nutritionList = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    nutritionComboBox = document.createElement("select");
    var defaultOption = document.createElement("option");
    defaultOption.value = "-1";
    defaultOption.innerHTML = "--Custom--";
    nutritionComboBox.appendChild(defaultOption);

    for (var i = 0; i < nutritionList.snapshotLength; i++) {
        var option = document.createElement("option");
        option.value = nutritionList.snapshotItem(i).getElementsByTagName("hashName")[1].textContent.toString();
        option.innerHTML = nutritionList.snapshotItem(i).getElementsByTagName("name")[1].textContent.toString();
        nutritionComboBox.appendChild(option);
    }
}

function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function matchingIngredient(ingredientHash, nutritionHash, mappingId) {
    var mappingNutrition;
    if ((typeof mappingId) === "string") {
        if (document.getElementById(mappingId) !== null) {
            mappingNutrition = document.getElementById(mappingId).value;
        } else
            return;
    } else {
        mappingNutrition = mappingId.value;
    }
    matchingIngrdientToServer(ingredientHash, nutritionHash, mappingNutrition);
}

function renderMatchingIngredientTable(xmlDoc) {
    var path = "//unmappingIngredientDTO";
    var dto = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    var ingredientTableBody = document.getElementById("table-matching-ingredient-body");
    ingredientTableBody.innerHTML = "";
    var count = 0;

    for (var i = 0; i < dto.snapshotLength; i++) {
        var tr = document.createElement("tr");

        var tdNo = document.createElement("td");
        tdNo.innerHTML = i + 1;

        var tdIngerdientName = document.createElement("td");
        var ingredientName = dto.snapshotItem(i).getElementsByTagName("ingredient")[0]
                .getElementsByTagName("name")[0].textContent.toString();
        tdIngerdientName.innerHTML = ingredientName;

        var tdNutritionName = document.createElement("td");
        var nutritionName = dto.snapshotItem(i).getElementsByTagName("recommendedNutrition")[0]
                .getElementsByTagName("name")[1].textContent.toString();
        tdNutritionName.innerHTML = nutritionName;

        var ingredientHash = dto.snapshotItem(i).getElementsByTagName("ingredient")[0]
                .getElementsByTagName("hashName")[0].textContent.toString();
        var nutritionHash = dto.snapshotItem(i).getElementsByTagName("recommendedNutrition")[0]
                .getElementsByTagName("hashName")[1].textContent.toString();

        var tdComboBoxNutrition = document.createElement("td");
        var comboBoxNutrition = nutritionComboBox.cloneNode(true);
        var comboBoxId = "mapping" + ingredientHash;
        comboBoxNutrition.id = comboBoxId;
        tdComboBoxNutrition.appendChild(comboBoxNutrition);

        var tdButton = document.createElement("td");
        tdButton.innerHTML = "<button onClick=\"matchingIngredient(" +
                ingredientHash + "," + nutritionHash + "," + comboBoxId + ")\" id=\"" +
                ingredientHash + "\">Matching Ingredient</button>";

        tr.appendChild(tdNo);
        tr.appendChild(tdIngerdientName);
        tr.appendChild(tdNutritionName);
        tr.appendChild(tdComboBoxNutrition);
        tr.appendChild(tdButton);

        ingredientTableBody.appendChild(tr);
        count++;
    }

    document.getElementById("no-matching").innerHTML = count;
}

function normalizeUnit(ingredientId, txtEqualToId, cbIndependentId) {
    var equalTo, isIndependent;
    if ((typeof txtEqualToId) === "string") {
        equalTo = document.getElementById(txtEqualToId).value;
    } else {
        equalTo = txtEqualToId.value;
    }

    if ((typeof cbIndependentId) === "string") {
        isIndependent = document.getElementById(cbIndependentId).checked;
    } else {
        isIndependent = cbIndependentId.checked;
    }
    console.log("id " + ingredientId + " euqal " + equalTo + " isindependent " + isIndependent);
    normalizeUnitToServer(ingredientId, equalTo, isIndependent);
}

function renderNormalizeUnitTable(xmlDoc) {
    var path = "//ingredient";
    var ingredients = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    var tableNormalize = document.getElementById("table-normalize-unit-body");
    tableNormalize.innerHTML = "";
    var count = 0;

    for (var i = 0; i < ingredients.snapshotLength; i++) {
        var item = ingredients.snapshotItem(i);

        var tdNo = document.createElement("td");
        tdNo.innerHTML = i + 1;

        var tdUnit = document.createElement("td");
        var unit = item.getElementsByTagName("unit")[0].textContent.toString();
        tdUnit.innerHTML = "Mỗi " + unit;

        var tdName = document.createElement("td");
        tdName.innerHTML = item.getElementsByTagName("name")[0].textContent.toString();

        var ingredientId = item.getElementsByTagName("id")[0].textContent.toString();
        var btnId = "btn" + ingredientId;

        var tdEqualTo = document.createElement("td");
        var inputEqualTo = document.createElement("input");
        inputEqualTo.type = "text";
        inputEqualTo.name = "txtEqualTo";
        inputEqualTo.id = "txt" + item.getElementsByTagName("hashName")[0].textContent.toString();
        inputEqualTo.onkeyup = function () {
            catchEnterKey(event, btnId);
        };
        tdEqualTo.innerHTML = "tương ứng với " + inputEqualTo.outerHTML + " " + standardUnit;

        var tdCheckbox = document.createElement("td");
        tdCheckbox.style = "text-align: center";
        var chechbox = document.createElement("input");
        chechbox.type = "checkbox";
        chechbox.id = "cb" + item.getElementsByTagName("hashName")[0].textContent.toString();
        tdCheckbox.appendChild(chechbox);

        var tdButton = document.createElement("td");
        tdButton.innerHTML = "<button onclick='normalizeUnit(" + ingredientId +
                "," + inputEqualTo.id + "," + chechbox.id + ")' id='" + btnId + "'>" +
                "Normalize unit</button>";

        var tr = document.createElement("tr");
        tr.appendChild(tdNo);
        tr.appendChild(tdUnit);
        tr.appendChild(tdName);
        tr.appendChild(tdEqualTo);
        tr.appendChild(tdCheckbox);
        tr.appendChild(tdButton);

        tableNormalize.appendChild(tr);
        count++;
    }
    document.getElementById("no-normalize").innerHTML = count;
}

function catchEnterKey(event, btnId) {
    if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById(btnId).click();
    }
}

//function autoMatching(xmlDoc) {
//    var path = "//unmappingIngredientDTO";
//    var dto = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
//
//    for (var i = 0; i < dto.snapshotLength; i += 3) {
//        var ingredientHash = dto.snapshotItem(i).getElementsByTagName("ingredient")[0]
//                .getElementsByTagName("hashName")[0].textContent.toString();
//        var nutritionHash = dto.snapshotItem(i).getElementsByTagName("recommendedNutrition")[0]
//                .getElementsByTagName("hashName")[1].textContent.toString();
//        var comboBoxId = "mapping" + ingredientHash;
//        var mappingNutrition = document.getElementById(comboBoxId).value;
//
//        var url = "http://localhost:8084/DishEvaluation/webresources/ingredient/matching-ingredient?" +
//                "txtIngredientHashName=" + ingredientHash + "&" +
//                "txtNutritionHashName=" + nutritionHash + "&" +
//                "mappingNutrition=" + mappingNutrition;
//        var xmlHttp = new XMLHttpRequest();
//        xmlHttp.open("GET", url, true);
//
//        xmlHttp.onreadystatechange = function () {
//            if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
//                console.log("SUCCESS MATCHING INGREDIENT: ingreHash "
//                        + ingredientHash + " nutriHash " + nutritionHash);
//            } else {
//                console.log("ERROR MATCHING INGREDIENT: " + xmlHttp.status);
//            }
//        };
//
//        xmlHttp.send();
//    }
//}