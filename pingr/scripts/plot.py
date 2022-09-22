import pandas as pd
import matplotlib.pyplot as plt


def main():
    plt.close("all")

    measures = pd.read_csv("./measures-sync.csv")

    measures.plot(y='elapsed_time')
    print(measures.mean())
    print(measures.std())

    # print(measures.groupby("msgSize")["totalTime"].mean())

    # measures.groupby("msgSize")["totalTime"].plot()

    plt.show()


if __name__ == "__main__":
    main()
