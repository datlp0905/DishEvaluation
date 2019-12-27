/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var calo;
var maleWeightAmount, maleHeightAmount, maleAgeAmount, maleEpsilon;
var femaleWeightAmount, femaleHeightAmount, femaleEpsilon, femaleAgeAmount;
var numberOfDish;
var dataOnClient;

function getSuggestionFromServer(energyNeed, numberOfDish) {
    var url = "http://localhost:8084/DishEvaluation/webresources/dish/suggest-menu?" +
            "energyNeed=" + energyNeed + "&numberOfDish=" + numberOfDish;
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS GET SUGGESTION: " + energyNeed + " num " + numberOfDish);
            dataOnClient = xmlHttp.responseXML;
            showDishes(xmlHttp.responseXML);
        } else {
            console.log("ERROR GET SUGGESTION: " + energyNeed + " num " + numberOfDish);
        }
    };

    xmlHttp.send();
}

function getConstanstFromServer() {
    var url = "http://localhost:8084/DishEvaluation/webresources/bmi-constanst/lastest";
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", url, true);
    
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS GET CONSTANST");
            initConstantValue(xmlHttp.responseXML);
        } else {
            console.log("ERROR GET CONSTANST");
        }
    };
    
    xmlHttp.send();
}

function loadNutritionValue(id) {
    var xmlHttp = new XMLHttpRequest();
    var url = "http://localhost:8084/DishEvaluation/webresources/dish/" + id + "/nutrition-value";
    xmlHttp.open("GET", url, true);
    
    xmlHttp.onreadystatechange = function () {
        if(xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS LOAD NUTRITION VALUE OF DISH ID: " + id);
            showNutritionValue(xmlHttp.responseXML);
        } else {
            console.log("ERROR LOAD NUTRITION VALUE OF DISH ID: " + id);
        }
    };
    
    xmlHttp.send();
}

function initConstantValue(xmlDoc) {
    maleWeightAmount = xmlDoc.getElementsByTagName("maleWeightAmount")[0].textContent.toString();
    maleWeightAmount = parseFloat(maleWeightAmount);
    
    maleHeightAmount = xmlDoc.getElementsByTagName("maleHeightAmount")[0].textContent.toString();
    maleHeightAmount = parseFloat(maleHeightAmount);
    
    maleAgeAmount = xmlDoc.getElementsByTagName("maleAgeAmount")[0].textContent.toString();
    maleAgeAmount = parseFloat(maleAgeAmount);
    
    maleEpsilon = xmlDoc.getElementsByTagName("maleEpsilon")[0].textContent.toString();
    maleEpsilon = parseFloat(maleEpsilon);
    
    femaleWeightAmount = xmlDoc.getElementsByTagName("femaleWeightAmount")[0].textContent.toString();
    femaleWeightAmount = parseFloat(femaleWeightAmount);
    
    femaleHeightAmount = xmlDoc.getElementsByTagName("femaleHeightAmount")[0].textContent.toString();
    femaleHeightAmount = parseFloat(femaleHeightAmount);
    
    femaleAgeAmount = xmlDoc.getElementsByTagName("femaleAgeAmount")[0].textContent.toString();
    femaleAgeAmount = parseFloat(femaleAgeAmount);
    
    femaleEpsilon = xmlDoc.getElementsByTagName("femaleEpsilon")[0].textContent.toString();
    femaleEpsilon = parseFloat(femaleEpsilon);
    
    var levelOfWorks = xmlDoc.getElementsByTagName("BMILevelOfWorkList");
    var cbLevelOfWork = document.getElementById("cbLevelOfWork");
    cbLevelOfWork.innerHTML = "";
    for(var i = 0; i < levelOfWorks.length; i++) {
        var option = document.createElement("option");
        option.value = levelOfWorks[i].getElementsByTagName("value")[0].textContent.toString();
        option.innerHTML = levelOfWorks[i].getElementsByTagName("info")[0].textContent.toString();
        cbLevelOfWork.appendChild(option);
    }
    
    var needs = xmlDoc.getElementsByTagName("BMINeedList");
    var cbNeed = document.getElementById("need");
    cbNeed.innerHTML = "";
    for(var i = 0; i < needs.length; i++) {
        var option = document.createElement("option");
        option.value = needs[i].getElementsByTagName("value")[0].textContent.toString();
        option.innerHTML = needs[i].getElementsByTagName("info")[0].textContent.toString();
        cbNeed.appendChild(option);
    }
}

function showDishes(xmlDoc) {
    var dishes = xmlDoc.getElementsByTagName("dishes")[0];
    var containerDish = document.getElementById("dishes");
    containerDish.innerHTML = "";

    for (var i = 0; i < dishes.childNodes.length; i++) {
        var dish = dishes.children[i];
        containerDish.appendChild(showDish(dish));
    }
    var divClear = document.createElement("div");
    divClear.className = "clear";

    containerDish.appendChild(divClear);
}

function showDish(dish) {
    var divDish = document.createElement("div");
    divDish.className = "dish";
    divDish.onclick = function () {
        showDetail(dish.getElementsByTagName("id")[0].textContent.toString());
    };

    var divImg = document.createElement("div");
    divImg.className = "dish-img";
    var imgTag = document.createElement("img");
    imgTag.src = dish.getElementsByTagName("img")[0].textContent.toString();
    imgTag.alt = dish.getElementsByTagName("name")[0].textContent.toString();

    divImg.appendChild(imgTag);

    var divName = document.createElement("div");
    divName.className = "dish-name";
    divName.innerHTML = dish.getElementsByTagName("name")[0].textContent.toString();

    var divEnergy = document.createElement("div");
    divEnergy.className = "dish-energy";
    divEnergy.innerHTML = computeSumEnergyÒDish(dish.getElementsByTagName("ingredientList")) + " calo";

    var divLevel = document.createElement("div");
    divLevel.className = "dish-level";
    divLevel.innerHTML = dish.getElementsByTagName("level")[0].textContent.toString();

    var divCookTime = document.createElement("div");
    divCookTime.className = "dish-cook-time";
    divCookTime.innerHTML = dish.getElementsByTagName("cookTime")[0].textContent.toString();

    divDish.appendChild(divImg);
    divDish.appendChild(divName);
    divDish.appendChild(divEnergy);
    divDish.appendChild(divLevel);
    divDish.appendChild(divCookTime);

    return divDish;
}

function computeEnergy() {
    var weight = document.getElementById("txtWeight").value;
    var height = document.getElementById("txtHeight").value;
    var age = document.getElementById("txtAge").value;
    var levelOfWork = document.getElementById("cbLevelOfWork").value;
    var rdGender = document.getElementsByName("rdGender");
    var gender;
    if (rdGender[0].checked) {
        gender = "male";
    } else {
        gender = "female";
    }

    weight = parseFloat(weight);
    height = parseInt(height);
    age = parseInt(age);
    levelOfWork = parseFloat(levelOfWork);

    if (gender === "male") {
        calo = weight * maleWeightAmount + height * maleHeightAmount - maleAgeAmount * age + maleEpsilon;
    } else {
        calo = weight * femaleWeightAmount + height * femaleHeightAmount - femaleAgeAmount * age + femaleEpsilon;
    }
    calo = Math.round(calo * levelOfWork);
    document.getElementsByClassName("more-calo-info")[0].style.display = "block";
    document.getElementById("calo-need").innerHTML = calo;
}

function getSuggestion() {
    var cbNeed = document.getElementById("need");
    var need = cbNeed.value;
    numberOfDish = document.getElementById("numberOfDishes").value;
    var needEnergy = Math.round(calo * need);
    
    getSuggestionFromServer(needEnergy, numberOfDish);
    
    document.getElementById("user-need").innerHTML = cbNeed.options[cbNeed.selectedIndex].text;
    document.getElementById("user-need-calo").innerHTML = needEnergy + " calo ";
    document.getElementById("h1-dish-info").style.display = "block";
    document.getElementById("span-number-of-dish").innerHTML = numberOfDish;
    document.getElementById("span-number-of-dish").style.display = "block";
}

function computeSumEnergyÒDish(ingredients) {
    var sumEnergy = 0.0;
    for (var i = 0; i < ingredients.length; i++) {
        var nodeEnergy = ingredients[i].getElementsByTagName("energy");
        if (nodeEnergy.length !== 0) {
            var energy = nodeEnergy[0].textContent.toString();
            if (energy) {
                sumEnergy += parseFloat(energy);
            }
        }
    }
    return Math.round(sumEnergy * 100) / 100;
}

function showDetail(id) {
    var path = "//dish[id=" + id + "]";
    if (dataOnClient.evaluate) {
        var nodeDetail = dataOnClient.evaluate(path, dataOnClient, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);

        var modelDetail = document.getElementById("dish-detail");
        showModel(modelDetail);

        var img = document.getElementById("detail-img").childNodes[1];
        img.src = nodeDetail.snapshotItem(0).getElementsByTagName("img")[0].textContent.toString();

        img.alt = nodeDetail.snapshotItem(0).getElementsByTagName("name")[0].textContent.toString();

        var level = document.getElementById("detail-icon").childNodes[2];
        level.innerHTML = nodeDetail.snapshotItem(0).getElementsByTagName("level")[0].textContent.toString();

        var recipe = document.getElementById("detail-icon").childNodes[5];
        recipe.innerHTML = nodeDetail.snapshotItem(0).getElementsByTagName("recipeYield")[0].textContent.toString();

        var cookTime = document.getElementById("detail-icon").childNodes[8];
        cookTime.innerHTML = nodeDetail.snapshotItem(0).getElementsByTagName("cookTime")[0].textContent.toString();

        var name = document.getElementById("detail-name").childNodes[1];
        name.innerHTML = nodeDetail.snapshotItem(0).getElementsByTagName("name")[0].textContent.toString();

        var description = document.getElementById("detail-description");
        description.innerHTML = nodeDetail.snapshotItem(0).getElementsByTagName("description")[0].textContent.toString();

        var link = document.getElementById("detail-link");
        link.href = nodeDetail.snapshotItem(0).getElementsByTagName("link")[0].textContent.toString();
        link.target = "_blank";

//        var sumEnergy = document.getElementById("detail-energy");
//        sumEnergy.innerHTML = "";
//        var ingredients = nodeDetail.snapshotItem(0).getElementsByTagName("ingredientList");
//        sumEnergy.innerHTML = computeSumEnergy(ingredients);
        
        loadNutritionValue(id);
        showIngredients(id);
    }
}

function showModel(model) {
    model.style.display = "block";
    var span = document.getElementsByClassName("close")[0];
    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        model.style.display = "none";
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === model) {
            model.style.display = "none";
        }
    };
}

function showIngredients(id) {
    var path = "//dish[id=" + id + "]/ingredientList";
    var ingredients = dataOnClient.evaluate(path, dataOnClient, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    var tableIngredient = document.getElementById("table-ingredient");
    tableIngredient.innerHTML = "";

    for (var i = 0; i < ingredients.snapshotLength; i++) {
        var tr = document.createElement("tr");
        var tdName = document.createElement("td");
        var tdAmount = document.createElement("td");

        var ingredient = ingredients.snapshotItem(i);
        tdName.innerHTML = ingredient.getElementsByTagName("name")[0].textContent.toString();

        var amount = "", unit = "";
        if (ingredient.getElementsByTagName("amount")[0]) {
            amount = ingredient.getElementsByTagName("amount")[0].textContent.toString();
            amount = parseInt(amount);
        }
        if (ingredient.getElementsByTagName("unit")[0]) {
            unit = ingredient.getElementsByTagName("unit")[0].textContent.toString();
        }
        tdAmount.innerHTML = amount + " " + unit;

        tr.appendChild(tdName);
        tr.appendChild(tdAmount);
        tableIngredient.appendChild(tr);
    }
}

function computeSumEnergy(ingredients) {
    var sumEnergy = 0.0;
    for (var i = 0; i < ingredients.length; i++) {
        var nodeEnergy = ingredients[i].getElementsByTagName("energy");
        if (nodeEnergy.length !== 0) {
            var energy = nodeEnergy[0].textContent.toString();
            if (energy) {
                sumEnergy += parseFloat(energy);
            }
        }
    }
    return Math.round(sumEnergy * 100) / 100;
}

function showNutritionValue(xmlDoc) {
    var path = "//nutritionValue";
    var values = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    var tableNutrition = document.getElementById("table-nutrition");
    tableNutrition.innerHTML = "";
    
    for(var i = 0; i < values.snapshotLength; i++) {
        var item = values.snapshotItem(i);
        var tr = document.createElement("tr");
        var tdName = document.createElement("td");
        var tdAmount = document.createElement("td");
        
        tdName.innerHTML = item.getElementsByTagName("name")[0].textContent.toString();
        var amount = item.getElementsByTagName("amount")[0].textContent.toString();
        tdAmount.innerHTML = (Math.round(amount * 1000) / 1000) + " " +
                            item.getElementsByTagName("unit")[0].textContent.toString();
                    
        tr.appendChild(tdName);
        tr.appendChild(tdAmount);
        tableNutrition.appendChild(tr);
    }
}