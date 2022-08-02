FROM adoptopenjdk/openjdk8:alpine AS build

WORKDIR /app

COPY . .

RUN chmod +x ./gradlew && ./gradlew clean build -x test

FROM adoptopenjdk/openjdk8:alpine-jre

ENV PORT 9086

# Creating user
ENV GID 1033
ENV UID 1033

RUN addgroup -S $GID && adduser -S $UID -G $GID

WORKDIR /app

# Copying needed files
COPY --from=build --chown=$UID:$GID /app/build/libs/*all.jar /app/app.jar

# Setting user to use when running the image
USER $UID

EXPOSE $PORT

# Entrypoint definition
ENTRYPOINT ["java", "-jar", "./app.jar"]
