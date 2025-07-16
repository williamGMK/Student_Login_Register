

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to our application</title>
        <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f7f8;
            color: #333;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
        }

        h1 {
            color: #000000;
            font-size: 2.5rem;
            margin-bottom: 10px;
        }

        h2 {
            color: #000000;
            margin-bottom: 30px;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 10px;
            width: 250px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 1rem;
        }

        input[type="submit"] {
            padding: 10px 20px;
            background-color: #9900cc;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #000000;
        }

        a {
            display: inline-block;
            margin: 5px;
            color: #9900cc;
            text-decoration: none;
            font-weight: bold;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
    </head>
    <body>
        <h1>welcome to the application!</h1>
        <h2>Please stay tuned we will add more features!</h2>
        
        <form method="get" action="/error">
            <input type="submit" value="Navigate to the main page">
        </form>
        <a href="/register">Sign Up</a>
        <a href="/login">Login In</a>
    </body>
</html>
