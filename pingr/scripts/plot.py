import matplotlib.pyplot as plt
import pandas as pd


class MeasureDataFrames:

    def __init__(self):
        self.df_sync = pd.read_csv("./measures-sync.csv")
        self.df_async = pd.read_csv("./measures-async.csv")
        self.df_sync_docker = pd.read_csv("./measures-sync-docker.csv")
        self.df_async_docker = pd.read_csv("./measures-async-docker.csv")

    def plot(self):
        axes = plt.gca()

        self.plot_sync(axes)
        self.plot_async(axes)

    def plot_sync(self, axes=plt.gca()):
        self.df_sync.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            label='sync-local',
            ax=axes
        )

        self.df_sync_docker.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            ax=axes,
            label='sync-docker',
            title='Total request time to complete',
            ylabel='total time (ms)',
        )

    def plot_async(self, axes=plt.gca()):
        self.df_async.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            label='async-local',
            ax=axes
        )

        self.df_async_docker.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            ax=axes,
            label='async-docker',
            title='Total request time to complete',
            ylabel='total time (ms)',
        )

    def plot_async_gaussian(self, axes=plt.gca()):
        self.df_async.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            label='async-local',
            ax=axes
        )

        self.df_async_docker.rolling(3, win_type='gaussian').mean(std=1).plot(
            kind='line',
            y='elapsed_time',
            ax=axes,
            label='async-docker',
            title='Total request time to complete',
            ylabel='total time (ms)',
        )


if __name__ == "__main__":
    df = MeasureDataFrames()

    df.plot()

    plt.show()
