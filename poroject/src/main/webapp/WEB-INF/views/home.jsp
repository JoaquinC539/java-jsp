<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="bg">
    <h1>Hello from jsp ${name}</h1>
    <hr>
    <h2>Names:</h2> 
    <p>${names.getClass()}</p>   
    <ul>
        <c:forEach var="nam" items="${names}">
            <li>${nam}</li>
        </c:forEach>
       


        
    </ul>
    <hr>
    <h2>Person</h2>
    <p>Name: ${person.name}</p>
    <p>Age: ${person.age}</p>
    <h2>bool</h2>
    <p>${bool ? "Is true":"Not True "}</p>
</body>
</html>