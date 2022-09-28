import matplotlib.pyplot as plt
import pandas as pd


def main():
    df_sync = pd.read_csv("./measures-sync.csv")
    df_sync_docker = pd.read_csv("./measures-sync-docker.csv")

    axes = df_sync.plot(kind='line', y='elapsed_time', label='local')
    df_sync_docker.plot(
        kind='line',
        y='elapsed_time',
        ax=axes,
        label='docker',
        title='Total request time to complete',
        ylabel='total time (ms)',
    )

    plt.show()


if __name__ == "__main__":
    main()
