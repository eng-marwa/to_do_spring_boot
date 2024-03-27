FROM openjdk:17
LABEL authors="Marwa Talaat"
COPY ./target/todo-0.0.1-SNAPSHOT.jar todo-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "todo-0.0.1-SNAPSHOT.jar"]