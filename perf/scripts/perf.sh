#!/bin/bash

# set -o xtrace

APP_MODE="$1"
DOCKER_CONTAINER_ID="$2"

if [ "${APP_MODE}" = "sync" ]
then
    WIN_PATH="C:/Users/Lucas_da_Silva1/Documents/app/usp/mac0499/perf/sync/sync-ms-request/logs/perf.log"
    LINUX_PATH="/home/lucas/app/usp/mac0499/perf/sync/sync-ms-request/logs/perf.log"
else
    WIN_PATH="C:/Users/Lucas_da_Silva1/Documents/app/usp/mac0499/perf/async/async-ms-request/logs/perf.log"
    LINUX_PATH="/home/lucas/app/usp/mac0499/perf/async/async-ms-request/logs/perf.log"
fi

DOCKER_PATH="/app/logs/perf.log"
EXPATH=${WIN_PATH}

case "$OSTYPE" in
  solaris*) EXPATH=${LINUX_PATH} ;;
  darwin*)  EXPATH=${LINUX_PATH} ;; 
  linux*)   EXPATH=${LINUX_PATH} ;;
  bsd*)     EXPATH=${LINUX_PATH} ;;
  msys*)    EXPATH=${WIN_PATH} ;;
  cygwin*)  EXPATH=${WIN_PATH} ;;
  *)        echo "unknown: $OSTYPE" ;;
esac

MSG_SIZES=('0.5' '1' '2')
MEASUREMENTS=30

RESULTS='./results'

mkdir -p $RESULTS

for MSG_SIZE in ${MSG_SIZES[@]}; do
    curl "http://localhost:8080/api/run/${MSG_SIZE}/${MEASUREMENTS}"
done

if [ -z "${DOCKER_CONTAINER_ID}" ]
then
    echo "copying measures from local folder..."
    cp ${EXPATH} ${RESULTS}/measures.csv
else
    echo "copying measures from container..."
    docker cp ${DOCKER_CONTAINER_ID}:${DOCKER_PATH} ${RESULTS}/measures.csv
fi
