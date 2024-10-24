<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(to right, #0062E6, #33AEFF);
            margin: 0;
            padding: 50px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
            width: 400px;
            transition: transform 0.3s;
        }
        .card:hover {
            transform: translateY(-5px);
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
            font-weight: 700;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }
        input[type='text'], select {
            width: 100%;
            padding: 12px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 8px;
            transition: border-color 0.3s;
            font-size: 16px;
        }
        input[type='text']:focus, select:focus {
            border-color: #0062E6;
            outline: none;
        }
        input[type='submit'], input[type='reset'] {
            background-color: #0062E6;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 8px;
            cursor: pointer;
            width: 48%;
            font-size: 16px;
            transition: background-color 0.3s, transform 0.3s;
            margin-top: 10px; 
        }
        input[type='submit']:hover, input[type='reset']:hover {
            background-color: #004BB5;
            transform: scale(1.05);
        }
        .button-container {
            display: flex;
            justify-content: space-between;
        }
        .footer {
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            color: #777;
        }
        @media (max-width: 500px) {
            .card {
                width: 90%;
                padding: 20px;
            }
        }
    </style>
</head>
<body>
    <div class="card">
        <h1>Add Employee</h1>
        <form action="${pageContext.request.contextPath}/EmpAdd" method="post">    
        
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
            
            <label for="workingStatus">Working Status:</label>
            <select id="workingStatus" name="workingStatus" required>
                <option value="" disabled selected>Select Working Status</option>
                <option value="security">Security</option>
                <option value="sweeper">Sweeper</option>
                <option value="cook">Cook</option>
                <option value="plumber">Plumber</option>
            </select>
            
            <label for="dutyTime">Duty Time:</label>
            <select id="dutyTime" name="dutyTime" required>
                <option value="" disabled selected>Select Duty Time</option>
                <option value="morning">Morning</option>
                <option value="afternoon">Afternoon</option>
                <option value="evening">Evening</option>
                <option value="night">Night</option>
            </select>
            
            <div class="button-container">
                <input type="submit" value="Submit">
                <input type="reset" value="Cancel">
            </div>
        </form>
        <div class="footer">
            &copy; 2024 Koustuv Residency
        </div>
    </div>
</body>
</html>
