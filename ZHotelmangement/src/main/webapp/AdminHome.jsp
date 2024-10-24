<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            background-color: #f5f5f5;
        }
        .sidebar {
            width: 250px;
            background-color: #003366; /* Dark blue */
            padding: 20px;
            color: white;
            height: 100vh;
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.2);
            transition: width 0.3s;
        }
        .sidebar h2 {
            margin: 0;
            padding-bottom: 20px;
            font-size: 1.5em;
            border-bottom: 2px solid #FFD700; /* Dark golden */
            animation: fadeIn 0.5s;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            padding: 10px;
            transition: background-color 0.3s, padding-left 0.3s;
            animation: fadeIn 0.5s;
        }
        .sidebar a:hover {
            background-color: #FFD700; /* Dark golden */
            padding-left: 15px;
        }
        .content {
            flex-grow: 1;
            padding: 20px;
            text-align: center;
            background-color: #ffffff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            animation: fadeIn 0.5s;
        }
        .content h1 {
            color: #003366; /* Dark blue */
            margin-bottom: 20px;
            font-size: 2em;
            border-bottom: 3px solid #FFD700; /* Dark golden */
            display: inline-block;
            padding-bottom: 10px;
        }
        .content img {
            max-width: 70%;
            height: auto;
            margin-top: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.3);
            animation: slideIn 0.5s;
        }

        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }

        @keyframes slideIn {
            from { transform: translateY(-20px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }

        @media (max-width: 800px) {
            .sidebar {
                width: 200px;
            }
            .content {
                padding: 15px;
            }
        }

        @media (max-width: 500px) {
            .sidebar {
                width: 100%;
                height: auto;
                padding: 10px; /* Adjusted padding */
            }
            .content {
                padding: 10px;
            }
            .content img {
                max-width: 90%;
            }
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>Admin Options</h2>
    <a href="${pageContext.request.contextPath}/StudRoom">Rooms</a>
    <a href="./EmployeeAdd.jsp">Employee Add</a>
    <a href="${pageContext.request.contextPath}/StudInfo">Student Info</a>
    <a href="${pageContext.request.contextPath}/EmpInfo">Employee Info</a>
    <a href="${pageContext.request.contextPath}/Reciepe">Hostel Food</a>
    <a href="${pageContext.request.contextPath}/AdminComplainStatus">Complaints</a> 
</div>

<div class="content">
    <h1>Welcome to Koustuv Residency</h1>
    <br>
    <img src="Logo.png" alt="Koustuv Residency" />
</div>

</body>
</html>
