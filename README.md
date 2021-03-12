# Tic-Tac-Toe 

### A simple proof of concept of the tic-tac-toe algorithm and game result validator

### Open API

API documentation is available in url: http://localhost:8080/swagger-ui.html or in json format to client generation in the file "target/openapi.json" 

### Docker

A docker image of project could be generated running commands: 

docker build -t tic-tac-toe:latest .

docker run --name tic-tac-toe-pod -p 8080:8080 -d tic-tac-toe:latest  

Application will be available at url: http://localhost:8080
