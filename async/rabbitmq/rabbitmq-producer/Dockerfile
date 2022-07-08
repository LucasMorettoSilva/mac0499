FROM adoptopenjdk/openjdk8:alpine AS build

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

FROM adoptopenjdk/openjdk8:alpine-jre

# Creating user
ENV APP_GROUP 1033
ENV APP_USER 1033

RUN groupadd -r $APP_GROUP && adduser -g $APP_GROUP $APP_USER

WORKDIR /app

# Copying needed files
COPY --from=build --chown=$APP_USER:$APP_GROUP /app/build/libs/*all.jar /app/app.jar

# Setting user to use when running the image
USER $APP_USER

# Entrypoint definition
CMD ["java", "-jar", "./app.jar"]
