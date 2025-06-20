# 1. Используем официальный Maven-образ для сборки
FROM maven:3.9.6-eclipse-temurin-21 AS build

# 2. Копируем весь проект в контейнер
COPY . /app
WORKDIR /app

# 3. Сборка проекта (скачает зависимости, соберёт JAR)
RUN mvn clean package -DskipTests

# 4. Берем минимальный Java-образ для запуска
FROM eclipse-temurin:21-jre

# 5. Копируем JAR из предыдущего слоя
COPY --from=build /app/target/*.jar app.jar

# 6. Указываем порт (если, например, 8080)
EXPOSE 8080

# 7. Команда запуска
ENTRYPOINT ["java", "-jar", "app.jar"]
