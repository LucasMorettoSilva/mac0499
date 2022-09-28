import argparse
import requests
import time

default_output_file = "measures.csv"
default_api_url = "http://localhost:9086/api/chats/messages"
default_runs = 50
default_interval = 2


class Stopwatch:

    def __init__(self):
        self.start_time = time.time()
        self.end_time = 0.0
        self.elapsed_time = 0.0

    def stop(self):
        self.end_time = time.time()
        self.elapsed_time = self.end_time - self.start_time

    def __str__(self):
        return f"{self.start_time},{self.end_time},{self.elapsed_time}"


def call_api(api_url):
    stopwatch = Stopwatch()

    response = requests.post(
        api_url,
        json={
            "senderEmail": "user2@email.com",
            "recipientEmail": "user1@email.com",
            "message": "hey, how are you?"
        }
    )

    stopwatch.stop()

    if 299 < response.status_code < 200:
        print(f"[-][-] call failed : {response}")
        exit(1)

    return stopwatch


def run_experiments(api_url, runs):
    measures = []

    for i in range(runs):
        print(f"running experiment {i} of {runs}...")
        measures.append(call_api(api_url))
        time.sleep(default_interval)

    return measures


def save_measures(filename, measures):
    print(f"writing measures in file: {filename}")

    with open(filename, 'w') as file:
        file.write("start_time,end_time,elapsed_time\n")

        for m in measures:
            file.write(f"{m}\n")

    print("finished writing file")


def main():
    parser = argparse.ArgumentParser(description='Run Experiments')

    parser.add_argument(
        '--url',
        help='target url',
        default=default_api_url
    )

    parser.add_argument(
        '--runs',
        help='how many measurements should be done',
        default=default_runs
    )

    parser.add_argument(
        '--output',
        help='output file path',
        default=default_output_file
    )

    url_arg = parser.parse_args().url
    output_file = parser.parse_args().output
    runs = parser.parse_args().runs

    try:
        measures = run_experiments(url_arg, runs)
        save_measures(output_file, measures)

        print("Process completed")
    except Exception as e:
        print(e)


if __name__ == '__main__':
    main()
