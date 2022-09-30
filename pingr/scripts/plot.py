import matplotlib.pyplot as plt
import pandas as pd


class MeasureDataFrames:

    def __init__(self):
        self.df_sync = pd.read_csv("./measures-sync.csv")
        self.df_async = pd.read_csv("./measures-async.csv")
        self.df_sync_docker = pd.read_csv("./measures-sync-docker.csv")
        self.df_async_docker = pd.read_csv("./measures-async-docker.csv")
        self.new_sync = pd.read_csv("./new-measures-sync.csv")

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

        # self.df_sync_docker.rolling(3, win_type='gaussian').mean(std=1).plot(
        #     kind='line',
        #     y='elapsed_time',
        #     ax=axes,
        #     label='sync-docker',
        #     title='Total request time to complete',
        #     ylabel='total time (ms)',
        # )

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

    def plot_new_sync(self, axes=plt.gca(), plot_ci=False):
        self.new_sync.plot(
            kind='line',
            x='i',
            y='mean',
            label='async-local',
            ax=axes
        )

        if plot_ci:
            x = self.new_sync["i"].tolist()
            y = self.new_sync["mean"].tolist()
            ci = self.new_sync["ci"].tolist()

            y_plus = []
            y_minus = []
            for i in range(len(y)):
                y_plus.append(y[i] + ci[i])
                y_minus.append(y[i] - ci[i])

            axes.fill_between(x, y_minus, y_plus, color='b', alpha=.1)


if __name__ == "__main__":
    df = MeasureDataFrames()

    df.plot_sync()

    plt.show()
