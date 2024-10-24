<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background: linear-gradient(135deg, #003366, #00509e); /* Dark blue gradient */
            margin: 0;
            font-family: Arial, sans-serif;
        }

        form {
            background: #ffffff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.2);
            animation: fadeIn 0.5s;
            width: 400px;
            box-sizing: border-box; 
        }

        h2 {
            text-align: center;
            color: #FFD700; /* Dark golden */
            margin-bottom: 20px;
        }

        input[type="text"], input[type="password"] {
            width: calc(100% - 20px);
            padding: 15px;
            margin: 15px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            transition: border-color 0.3s, box-shadow 0.3s;
            font-size: 16px; 
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #FFD700; /* Dark golden */
            outline: none;
            box-shadow: 0 0 5px rgba(255, 215, 0, 0.5);
        }

        input[type="submit"],
        input[type="reset"] {
            background-color: #003366; /* Dark blue */
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

        input[type="submit"]:hover,
        input[type="reset"]:hover {
            background-color: #00509e; /* Lighter blue */
            transform: translateY(-2px);
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        .note {
            font-size: 14px;
            margin-top: -10px; 
            margin-bottom: 15px;
            color: #555; 
        }

        @media (max-width: 500px) {
            form {
                width: 90%;
                padding: 20px;
            }
        }
    </style>
</head>
<body>

<form action="Logins" method="post">
    <h2>Admin Login</h2>
    <input type="text" name="AdminName" placeholder="Enter your name..." required>
    <div class="note">Note: Please use only lowercase letters without spaces.</div>
    <input type="password" name="AdminPass" placeholder="Enter your Password..." required>
    <div style="display: flex; justify-content: space-between;">
        <input type="submit" value="Login">
        <input type="reset" value="Cancel">
    </div>
</form>

</body>
</html>
