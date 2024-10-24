<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Student Login</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #001f3f; 
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        form {
            background: linear-gradient(to bottom right, #004080, #0066cc);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            animation: fadeIn 0.5s;
            width: 100%;
            max-width: 400px; 
            box-sizing: border-box;
        }

        input[type="text"], input[type="password"] {
            width: calc(100% - 20px);
            padding: 15px;
            margin: 15px 0;
            border: 1px solid #d4af37;
            border-radius: 5px;
            transition: border-color 0.3s, box-shadow 0.3s;
            font-size: 16px;
            background-color: #fff; 
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: #d4af37; 
            outline: none;
            box-shadow: 0 0 5px rgba(212, 175, 55, 0.5);
        }

        input[type="submit"], input[type="reset"] {
            background-color: #d4af37; 
            color: white;
            border: none;
            padding: 15px;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            width: calc(48% - 10px); 
            margin: 10px 1%;
            font-size: 16px; 
        }

        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #b89b30; 
            transform: translateY(-2px);
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        @media (max-width: 500px) {
            form {
                padding: 10px;
                margin: 10px;
            }
            input[type="text"], input[type="password"], input[type="submit"], input[type="reset"] {
                font-size: 14px; 
                padding: 12px;
            }
        }

        h2 {
            text-align: center; 
            color: #fff; 
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<form action="StudentLogins" method="post">
    <h2>Student Login</h2>
    <input type="text" name="RoomNumber" placeholder="Enter your room number..." required>
    <input type="password" name="Password" placeholder="Enter your password..." required>
    <div style="display: flex; justify-content: space-between;">
        <input type="submit" value="Login">
        <input type="reset" value="Cancel">
    </div>
</form>

</body>
</html>
