var dataOnClient;

function initLoad() {
    loadDish();
    loadCategory();
}

function loadDish() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8084/DishEvaluation/webresources/dish", true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS LOAD DISH: " + xmlHttp.responseXML);
            dataOnClient = xmlHttp.responseXML;
            document.getElementById("loading").style.display = "none";
            showDishes(xmlHttp.responseXML);
        } else {
            console.log("ERROR LOAD DISH: " + xmlHttp.status);
        }
    };

    xmlHttp.send();
}

function loadCategory() {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", "http://localhost:8084/DishEvaluation/webresources/maincategory", true);

    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS LOAD CATEGORY: " + xmlHttp.responseXML);
            showCategories(xmlHttp.responseXML);
        } else {
            console.log("ERROR LOAD CATEGORY: " + xmlHttp.status);
        }
    };

    xmlHttp.send();
}

function loadDishByCategoryIds(ids) {
    var xmlHttp = new XMLHttpRequest();
    var url = "http://localhost:8084/DishEvaluation/webresources/dish/category?";
    for(var i = 0; i < ids.length; i++) {
        url += "categoryIds=" + ids[i] + "&";
    }
    xmlHttp.open("GET", url, true);
    
    xmlHttp.onreadystatechange = function () {
        if(xmlHttp.status === 200 && xmlHttp.readyState === 4) {
            console.log("SUCCESS LOAD DISH BY CATEGORY: " + xmlHttp.responseXML);
            showDishes(xmlHttp.responseXML);
        } else {
            console.log("ERROR LOAD DISH BY CATEGORY: " + xmlHttp.status);
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
    divEnergy.innerHTML = computeSumEnergy(dish.getElementsByTagName("ingredientList")) + " calo";

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

function showCategories(xmlDoc) {
    var path = "//mainCategory";
    var categories = xmlDoc.evaluate(path, xmlDoc, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
    var divCategorySearch = document.getElementById("category-search");

    for (var i = 0; i < categories.snapshotLength; i++) {
        var divCategory = document.createElement("div");
        divCategory.className = "category";

        var labelCategoryName = document.createElement("label");
        labelCategoryName.className = "category-name";
        var snapshotItem = categories.snapshotItem(i);
        labelCategoryName.innerHTML = snapshotItem.getElementsByTagName("name")[0].textContent.toString() + " >> ";

        divCategory.appendChild(labelCategoryName);

        var subCategories = categories.snapshotItem(i).getElementsByTagName("subCategoryList");
        for (var j = 0; j < subCategories.length; j++) {
            var subCategory = subCategories[j];

            var inputSubCategory = document.createElement("input");
            inputSubCategory.type = "checkbox";
            inputSubCategory.name = "cbCategory";
            inputSubCategory.value = subCategory.getElementsByTagName("id")[0].textContent.toString();

            var labelSubCateName = document.createElement("label");
            labelSubCateName.innerHTML = subCategory.getElementsByTagName("name")[0].textContent.toString();

            divCategory.appendChild(inputSubCategory);
            divCategory.appendChild(labelSubCateName);
        }

        divCategorySearch.appendChild(divCategory);
        divCategorySearch.appendChild(document.createElement("br"));
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

function showAdvanceSearch() {
    var divAdvanceSearch = document.getElementById("category-search");
    var btnAdvanceSearch = document.getElementById("btn-advance-search");
    if (divAdvanceSearch.style.display === "none") {
        divAdvanceSearch.style.display = "block";
        btnAdvanceSearch.style.display = "block";
    } else {
        divAdvanceSearch.style.display = "none";
        btnAdvanceSearch.style.display = "none";
    }
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

function search() {
    var txtSearch = document.getElementById("txtSearch").value;
    if (txtSearch.length > 0) {
        var path = "//dish[contains(name,'" + txtSearch + "')]";
        var searchResult = dataOnClient.evaluate(path, dataOnClient, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);

        var containerDish = document.getElementById("dishes");
        containerDish.innerHTML = "";

        for (var i = 0; i < searchResult.snapshotLength; i++) {
            var dish = searchResult.snapshotItem(i);
            containerDish.appendChild(showDish(dish));
        }

        var divClear = document.createElement("div");
        divClear.className = "clear";

        containerDish.appendChild(divClear);
    }
}

function catchEnterKey(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById("btnSearch").click();
    }
}

function searchByCategory() {
    var cbCategories = document.getElementsByName("cbCategory");
    var categoryIds = new Array();
    for(var i = 0; i < cbCategories.length; i++) {
        var cbCategory = cbCategories[i];
        if(cbCategory.checked) {
            var id = parseInt(cbCategory.value);
            categoryIds.push(id);
        }
    }
    
    loadDishByCategoryIds(categoryIds);
}
