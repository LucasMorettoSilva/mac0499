################################################################################
#                                  BASE IMAGE                                  #
################################################################################

# Use Gradle image to run Gradle commands
FROM gradle:7.5-jdk8 AS base

ARG APP_DIR=/home/gradle/app

# Change default workdir
WORKDIR ${APP_DIR}

# Set environment variable to disable Gradle Daemon
ENV GRADLE_OPTS -Dorg.gradle.daemon=false

# Give the ownership of workdir to the Gradle user
RUN chown -R gradle:gradle .

# Change to non-priviled user (PID 1000)
USER gradle

# NOTE: This repo has gradle wrappers to ensure developers use the same version
# of gradle in every environment. However, using gradle's docker image already
# guarantees that. Therefore, commands below can use the `gradle` CLI directly.

# Copy source code to workdir
COPY --chown=gradle:gradle . .

# NOTE: Gradle download dependencies only when they are necessary, which slows
# tasks the first time they execute. Running them here will allow docker to
# cache these dependencies to speed up containers that execute gradle tasks.

# Force gradle to download dependencies to build, lint and test code
RUN gradle ${GRADLE_OPTS} --no-build-cache clean -x test

################################################################################
#                                BUILDER IMAGE                                 #
################################################################################

# Use base image to build the project
FROM base AS builder

# Use gradle wrapper to generate an uber JAR
RUN gradle ${GRADLE_OPTS} bootJar

################################################################################
#                               PRODUCTION IMAGE                               #
################################################################################

FROM eclipse-temurin:8-jre-alpine as runner

ARG APP_DIR=/home/gradle/app

ARG RUN_DIR=/app

ENV PROFILE docker

ENV JAVA_OPTS -Dspring.profiles.active=${PROFILE}

# Creating user
ENV GID 1033
ENV UID 1033

WORKDIR ${RUN_DIR}

RUN addgroup -S $GID && adduser -S $UID -G $GID

COPY --from=builder --chown=$UID:$GID ${APP_DIR}/build/libs/*.jar \
                    ${RUN_DIR}/app.jar

# Setting user to use when running the image
USER $UID

ENTRYPOINT java ${JAVA_OPTS} -jar ./app.jar
