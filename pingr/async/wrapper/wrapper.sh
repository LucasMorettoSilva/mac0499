#!/bin/bash

set -x

JAVA_OPTS=$1
APP1=$2
APP2=$3

# Start the first process
java "${JAVA_OPTS}" -jar "${APP1}" &

# Start the second process
java "${JAVA_OPTS}" -jar "${APP2}" &

# Wait for any process to exit
wait -n

# Exit with status of process that exited first
exit $?
