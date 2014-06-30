<%@page import="java.util.Random"%>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<script language="JavaScript" type="text/JavaScript" 
src="validate.js"></script>

<style type="text/css">
h1 {
font-weight: bold;
font-size: 18pt
}
p {
font-weight: bold;
font-size:50px;
}

INPUT {
background-color: #99ccff;
color: black;
font-family: arial, verdana, ms sans serif;
font-weight: bold;
font-size: 12pt;
height:40px;
width:500px;
}

FORM{
background-color:#C0C0C0;

}

TEXTAREA{
background-color:#C0C0C0;
height:100px;

width:500px;
}

</style>


</script>
</HEAD>
<body>
<form action="/EducationGamesApp/ContentServ"  method="post" onSubmit="return validate(this)">
    <table border = "0">
        <tr align="left" valign="top">
            <td><p>User Name:</p></td>
           
        </tr>
        
        <tr align="left" valign="top">
         <td><input type="text" name ="user" class="inputbox"/></td>
        </tr>
        
        
        <tr align="left" valign="top">
            <td ><p >Request:</p></td>
        </tr>
         <tr align="left" valign="top">
         <td><TEXTAREA style="background-color:#C0C0C0;" name="request" ROWS="5" COLS="40" >
</TEXTAREA></td>
        </tr>
        <tr align="left" valign="top">
            <td><input type="submit" name="submit" style="font-size:30px;"
                        value="SUBMIT" class="submitButton"/>
                       </td>
                       <p>Enter your request below!</p>
        </tr>
    </table>
</form>
</body>
</html>