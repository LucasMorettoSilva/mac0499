import pandas as pd
import matplotlib.pyplot as plt


def main():
    plt.close("all")

    measures = pd.read_csv("./results/sync-measures.csv")

    measures.plot(x="msgSize", y='totalTime', kind='line')
    print(measures.groupby("msgSize")["totalTime"].mean())

    # measures.groupby("msgSize")["totalTime"].plot()

    plt.show()


if __name__ == "__main__":
    main()
