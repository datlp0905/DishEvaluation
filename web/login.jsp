<%-- 
    Document   : login
    Created on : Dec 8, 2019, 3:53:11 PM
    Author     : DATLPSE62823
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/login.css" />
        <title>Login</title>
    </head>
    <body>
        <div id="container">
            <div id="login">
                <img src="resources/img/logo.png" alt="Dish Evaluation"/>
                <h1>Welcom Admin</h1>
                <form action="ProcessServlet" method="POST">
                    <table>
                        <tr>
                            <td>Username</td>
                            <td><input type="text" name="txtUsername" value="" /></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><input type="password" name="txtPassword" value="" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button name="btAction" value="Login" id="btnLogin">Login</button></td>
                        </tr>
                    </table>
                </form>
                <font color="red">${requestScope.ERROR}</font>
            </div>
        </div>
    </body>
</html>
