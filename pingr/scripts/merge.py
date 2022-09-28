import sys


class FileConverter:

    def __init__(self):
        self.files = []
        self.read_files()
        self.write_file()

    def read_files(self):
        print("reading files...")

        first_arg = True
        for arg in sys.argv:
            if first_arg:
                first_arg = False
                continue
            self.files.append(FileArgs(arg))

    def write_file(self):
        print("printing final file...")

        with open('final-measures.csv', 'w') as file:
            file.write('type,env,start_time,end_time,elapsed_time\n')
            for f in self.files:
                f.write_to_file(file)


class FileArgs:

    def __init__(self, filename):
        self.filename = filename
        self.type = self.__get_experiment_type(filename)
        self.env = self.__get_experiment_env(filename)
        self.lines = []
        self.read_file(filename)

    def read_file(self, filename):
        print(f"reading file: {filename}")

        with open(filename) as file:
            lines = file.readlines()[2:]
            for line in lines:
                self.lines.append(f"{self.type},{self.env},{line}")

    def write_to_file(self, file):
        for line in self.lines:
            file.write(line)

    @staticmethod
    def __get_experiment_type(filename):
        if 'async' in filename.lower():
            return 'async'
        return 'sync'

    @staticmethod
    def __get_experiment_env(filename):
        if 'docker' in filename.lower():
            return 'docker'
        return 'local'


def main():
    FileConverter()


if __name__ == "__main__":
    main()
