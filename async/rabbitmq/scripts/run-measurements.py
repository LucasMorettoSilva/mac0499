import requests
import time


producerUrl = "http://localhost:8080/ping"
rabbitQueueInfoUrl = "http://localhost:15672/api/queues/%2F/"

rabbitmq_user = "guest"
rabbitmq_pass = "guest"


def call_producer(message_size, runs, queue):
    response = requests.post(
        producerUrl,
        json={
            "messageSize": message_size,
            "queueName": queue,
            "times": runs
        }
    )

    if response.status_code == 200:
        print(f"runs for message size {message_size} MB finished successfully")
    else:
        print(f"runs for message size {message_size} MB failed : {response.text}")


def call_rabbit_queue_info(queue):
    response = requests.get(
        rabbitQueueInfoUrl + queue,
        params={
            "msg_rates_age": 60,
            "msg_rates_incr": 60
        },
        auth=(rabbitmq_user, rabbitmq_pass)
    )

    stats = response.json()
    print(stats)
    return stats['message_stats']['publish_details']['rate'], stats['message_stats']['deliver_get_details']['rate']


def run_experiments(message_sizes, runs=1000, queue="default_queue"):
    for size in message_sizes:
        call_producer(size, runs, queue)
        call_rabbit_queue_info(queue)


def main():
    message_sizes = [0.1, 0.2, 0.4, 0.8, 1.6]
    run_experiments(message_sizes)


if __name__ == '__main__':
    main()
