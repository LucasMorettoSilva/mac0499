#! /bin/bash

set -o xtrace

MEASUREMENTS=100
PRODUCER_URL='http://localhost:8080/ping'
QUEUE_NAME='\"default_queue\"'
MESSAGE_SIZE=0.1
REQUEST_BODY="{\"queueName\": ${QUEUE_NAME}, \"sizeOfMessage\": ${MESSAGE_SIZE} }"



for ((I=1; I<=$MEASUREMENTS; I+=1)); do
    curl --location --request POST ${PRODUCER_URL} \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "queueName": "default_queue",
        "sizeOfMessage": 0.1
    }'
done
