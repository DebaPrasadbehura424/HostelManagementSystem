<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Home</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            background-color: #e7f0f9;
            overflow: hidden;
        }
        .sidebar {
            width: 250px;
            background-color: #003366; /* Dark blue */
            padding: 20px;
            color: #FFD700; /* Light golden */
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
            transition: width 0.3s, transform 0.3s;
            position: relative;
        }
        .sidebar h2 {
            margin-top: 0;
            font-size: 24px;
            animation: fadeIn 0.5s;
        }
        .sidebar a {
            color: #FFD700; /* Light golden */
            text-decoration: none;
            display: block;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 10px;
            transition: background-color 0.3s, transform 0.2s;
            position: relative;
        }
        .sidebar a:hover {
            background-color: #0056b3;
            transform: translateX(5px);
            animation: pulse 0.2s;
        }
        .content {
            flex-grow: 1;
            padding: 30px;
            text-align: center;
            background-color: #f2f2f2;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            margin: 20px;
            transition: transform 0.3s;
        }
        .content h1 {
            color: #003366; /* Dark blue */
            font-size: 36px;
            margin-bottom: 20px;
            animation: fadeIn 0.5s;
        }
        .content p {
            font-size: 18px;
            color: #333;
            animation: fadeIn 0.5s;
        }
        .notification {
            background-color: #FFD700; /* Light golden */
            padding: 15px;
            border-radius: 8px;
            margin: 20px 0;
            animation: slideIn 0.5s;
            display: none;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
        @keyframes slideIn {
            from { transform: translateY(-20px); opacity: 0; }
            to { transform: translateY(0); opacity: 1; }
        }
        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }
        @media (max-width: 600px) {
            .sidebar {
                width: 100%;
                height: auto;
                box-shadow: none;
            }
            .content {
                padding: 15px;
                margin: 10px;
            }
        }
    </style>
</head>
<body>

<div class="sidebar">
    <h2>Student Options</h2>
    <% String roomNumber = (String) request.getAttribute("roomNumber"); %>
    <a href="${pageContext.request.contextPath}/OwnRoom?roomNumber=<%=roomNumber %>">Own Room</a>
    <a href="${pageContext.request.contextPath}/Reciepe">Recipes</a>
    <a href="${pageContext.request.contextPath}/Complain?roomNumber=<%= roomNumber %>">Complaints</a>
    <a href="${pageContext.request.contextPath}/ComplainStatus">Complaint Status</a>

</div>

<div class="content">
    <h1>Welcome to koustuv Residency</h1>
    <p>Explore your options on the left and make the most of your stay!</p>
    <div class="notification" id="notification">
        You have new updates! Check your notifications regularly.
    </div>
    <button onclick="showNotification()">Show Notification</button>
</div>

<script>
    function showNotification() {
        const notification = document.getElementById('notification');
        notification.style.display = 'block';
        setTimeout(() => {
            notification.style.display = 'none';
        }, 3000);
    }
</script>

</body>
</html>
