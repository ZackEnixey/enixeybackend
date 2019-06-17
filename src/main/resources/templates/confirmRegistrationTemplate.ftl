<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body style="text-align:center">
<div class="bordered" style=" width: 350px;
            height: 350px;
            padding: 30px;
            border: 1px solid #91917f;
            border-radius: 8px;
            color: black;">
    <img id="donation" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-8q-41FjVUs1oYZka_8ithg3ZB1XHo36eDHUvnzDMP_hKBWogdA"
         width="120" height="80">
    <br>
    <h2>WebShop</h2>
    <p>Hello ${name},</p>
    <p style="color: black">You have invited to join WebApp as CompanyAdmin!</p>
    <p>Your temporary password is: ${password}</p>
    <p>Please click on the button to verify your email.</p>
    <br>

        <a href="http://localhost:3000/invite?token=${token}"><button style=" background-color: #555555;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;">Confirm!
        </button></a>

</div>
</body>

</html>