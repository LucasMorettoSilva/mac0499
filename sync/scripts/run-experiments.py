import argparse
import requests
import time


producerUrl = "http://localhost:8080/ping"
rabbitQueueInfoUrl = "http://localhost:15672/api/queues/%2F/"

rabbitmq_user = "guest"
rabbitmq_pass = "guest"


def call_api(api_url, message_size, times):
    print(f"[+] Starting experiments for message size:  {message_size} MB")

    response = requests.post(
        api_url,
        json={
            "messageSize": message_size,
            "times": times
        }
    )

    if response.status_code == 200:
        print(f"[+][+] Call for message size {message_size} MB finished successfully")
    else:
        print(f"[-][-] Call for message size {message_size} MB failed : {response}")


def run_experiments(api_url, message_sizes, runs):
    for size in message_sizes:
        call_api(api_url, size, runs)
        time.sleep(60)


def main():
    parser = argparse.ArgumentParser(description='Run Experiments')

    parser.add_argument('--url', help='target url', required=True)

    parser.add_argument(
        '--size',
        help='comma separated values for message sizes (in MB) to run experiments',
        required=True
    )

    parser.add_argument(
        '--runs',
        help='how many times should each experiment run',
        required=True
    )

    url_arg = parser.parse_args().url
    sizes = parser.parse_args().size
    runs = parser.parse_args().runs

    if url_arg is None:
        print("Must pass an option for --url")
        return

    if sizes is None:
        print("Must pass an option for --size")
        return

    if runs is None:
        print("Must pass an option for --runs")
        return

    message_sizes = [float(i) for i in sizes.split(',')]

    try:
        run_experiments(url_arg, message_sizes, runs)
        print("[+] Process completed")
    except Exception as e:
        print(e)


if __name__ == '__main__':
    main()
