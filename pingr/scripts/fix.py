import numpy as np


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


if __name__ == "__main__":
    measures = read_file("./new-measures-sync.csv")

    write_file("new-measures-sync.csv", measures)
