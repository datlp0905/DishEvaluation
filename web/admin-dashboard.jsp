<%-- 
    Document   : admin-crawl
    Created on : Nov 30, 2019, 2:41:05 PM
    Author     : DATLPSE62823
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/dashboard.css"/>
        <script src="resources/js/dashboard.js"></script>
        <title>Admin - Dashboard</title>
        <script>
            var standardUnit = '${requestScope.STANDARD_UNIT}';
        </script>
    </head>
    <body onload="getLishNutritionFromServer()">
        <h1>Welcome Admin</h1>
        <form action="ProcessServlet" method="POST">
            <input type="submit" name="btAction" value="Crawl Data"/>
        </form>

        <div class="tab">
            <button class="tablinks" onclick="openCity(event, 'matching-ingredient')">
                Custom matching ingredient (<span id="no-matching">${requestScope.UNMAPPING_NUTRITION.size()}</span>)
            </button>
            <button class="tablinks" onclick="openCity(event, 'normalize-unit')">
                Custom normalize unit (<span id="no-normalize">${requestScope.UNNORMALIZED_UNIT.size()}</span>)
            </button>
        </div>

        <div id="matching-ingredient" class="tabcontent">
            <table id="table-matching-ingredient">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Ingredient Name</th>
                        <th>Recommended Nutrition</th>
                        <th>Custom Nutrition</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody id="table-matching-ingredient-body">
                    <c:set var="nutritions" value="${requestScope.NUTRITION}"/>
                    <c:forEach var="dto" items="${requestScope.UNMAPPING_NUTRITION}" varStatus="counter">
                        <!--<form action="ProcessServlet" method="POST">-->
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.ingredient.name}</td>
                            <td>${dto.recommendedNutrition.name}</td>
                            <td>
                                <select name="mappingNutrition" id="mapping${dto.ingredient.hashName}">
                                    <option value="-1">--Custom--</option>
                                    <c:forEach var="nutri" items="${nutritions}">
                                        <option value="${nutri.hashName}">${nutri.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <button onclick="matchingIngredient(${dto.ingredient.hashName},${dto.recommendedNutrition.hashName}, mapping${dto.ingredient.hashName})">
                                    Matching ingredient
                                </button>
                            </td>
                        </tr>
                        <!--                    </form>-->
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="normalize-unit" class="tabcontent">
            <table>
                <thead>
                    <tr>
                        <th>No.</th>
                        <th></th>
                        <th></th>
                        <th></th>
                        <th>Is independent unit?</th>
                    </tr>
                </thead>
                <tbody id="table-normalize-unit-body">
                    <c:forEach var="ingredient" items="${requestScope.UNNORMALIZED_UNIT}" varStatus="counter">
                        <!--<form action="ProcessServlet" method="POST">-->
                        <tr>
                            <td>${counter.count}</td>
                            <td>Mỗi ${ingredient.unit}</td>
                            <td>${ingredient.name}</td>
                            <td>tương ứng với <input type="text" name="txtEqualTo" id="txt${ingredient.hashName}"/> g </td>
                            <td style="text-align: center">
                                <input type="checkbox" name="cbIndependent" id="cb${ingredient.hashName}"/>
                            </td>
                            <td>
                                <button onclick="normalizeUnit(${ingredient.id}, txt${ingredient.hashName}, cb${ingredient.hashName})">
                                    Normalize unit
                                </button>
<!--                                <input type="hidden" name="txtIngredientId" value="${ingredient.id}"/>
                                <input type="submit" name="btAction" value="Normalize unit"/>-->
                            </td>
                        </tr>
                        <!--</form>-->
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
