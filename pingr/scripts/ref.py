import math

import pandas as pd
import requests
from bs4 import BeautifulSoup


class Ref:

    def __init__(self, url, index):
        self.config = """
            @online{ref,
             title       = {title-text},
             urldate     = {2022-10-31},
             url         = {url-text},
            }
        """

        print(f"requesting url: {url}")

        try:
            reqs = requests.get(url, timeout=5)

            soup = BeautifulSoup(reqs.text, features="html.parser")

            self.config = self.config \
                .replace("ref", f"ref{index}") \
                .replace("url-text", url) \
                .replace("title-text", soup.title.get_text().strip())
        except:
            self.config = self.config \
                .replace("ref", f"ref{index}") \
                .replace("url-text", url) \
                .replace("title-text", "none")

    def __str__(self):
        return self.config


class RefGenerator:

    def __init__(self, filename):
        self.df = pd.read_csv(filename)
        self.refs = []
        self.index = 76

        for url in self.df["http-lib"]:
            if type(url) is float and math.isnan(url):
                continue
            self.refs.append(Ref(url, self.index))
            self.index += 1

    def write_file(self):
        with open("refs-http.txt", "w", encoding="utf-8") as file:
            for ref in self.refs:
                file.write(f"{ref}\n\n")


def main():
    RefGenerator("../../analysis/lang.txt").write_file()


if __name__ == "__main__":
    main()
