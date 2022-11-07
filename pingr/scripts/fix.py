import math

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

class Measure:

    def __init__(self, line):
        s = line.split(",")
        self.i = int(s[0])
        self.mean = float(s[1])
        self.std = float(s[2])
        self.ci = 1.96 * (self.std / np.sqrt(30))

    def __str__(self):
        return f"{self.i},{self.mean},{self.std},{self.ci}"


def read_file(filename):
    measures = []
    first_line = True
    with open(filename) as file:
        for line in file:
            if first_line:
                first_line = False
                continue
            measures.append(Measure(line))
    return measures


def write_file(filename, measures):
    with open(filename, "w") as file:
        file.write("i,mean,std,ci\n")
        for m in measures:
            file.write(f"{m}\n")


def pa():
    df = pd.read_csv("./results-2/final-measures.csv")
    stats = df.groupby("experiment-type")["elapsed_time"].agg(["count", "mean", "std"])

    ci95_hi = []
    ci95_lo = []

    for i in stats.index:
        c, m, s = stats.loc[i]
        ci95_hi.append(m + 1.96 * s / math.sqrt(c))
        ci95_lo.append(m - 1.96 * s / math.sqrt(c))

    stats['ci95_lo'] = ci95_lo
    stats['ci95_hi'] = ci95_hi
    print(stats)


if __name__ == "__main__":
    pa()
