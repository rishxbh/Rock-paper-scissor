Rock Paper Scissor (Swagger UI) Game Documentation 
Overview :
Remember a childhood game "Rock, Paper and Scissors". It is a two-player game in which each person simultaneously chooses either rock, paper, or scissors.
Rules : Rock wins overs scissor, scissor wins over paper and paper wins over rock.
This application allows you to play this game with my custom Boombot, so you need start the game by hitting start Api, and the first one to score 3 points wins the Game.

Prerequisite :

-> Java 17

-> Maven

-> Spring Boot


This application is featured with Swagger-UI 


How to Run Locally :

-> Clone the repository:

    git clone https://github.com/rishxbh/Rock-paper-scissor.git

-> Navigate to the project directory:

    cd Rock-paper-scissor

-> Build the project:

    mvnw clean package

-> Run the application:

    java -jar target/Rock-paper-scissor-0.0.1-SNAPSHOT.jar

-> The application will start, and you can access the API endpoints using swagger or using a tool like Postman or curl.

The Swagger URL is : http://localhost:8081/swagger-ui/index.html