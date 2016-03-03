Lab2 submission - Ruchi

After starting the tomcat server execute the following commands from url.

1. POST [id] [todo message]
http://localhost:8080/Lab2/TodoServlet?operation=POST [1] [message1]
http://localhost:8080/Lab2/TodoServlet?operation=POST [2] [message2]

2. GET [id]
http://localhost:8080/Lab2/TodoServlet?operation=GET [1]

3. GET
http://localhost:8080/Lab2/TodoServlet?operation=GET

4. DELETE [id]
http://localhost:8080/Lab2/TodoServlet?operation=DELETE [1]

5. PUT [id] [todo message]
http://localhost:8080/Lab2/TodoServlet?operation=PUT [2] [messagemodified]