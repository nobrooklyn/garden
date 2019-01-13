import csv
import io

from memory_profiler import profile

class Data:
    def __init__(self, file):
        self.count = sum(1 for l in file)
        size = file.tell()
        print(size)
        file.seek(0)
        if size < 1_000_000:
            str = file.read()
            self.reader = csv.DictReader(io.StringIO(str))
        else:
            file.seek(0)
            self.reader = csv.DictReader(file)

    def read(self):
        l = []
        for d in self.reader:
            l.append(d)
            if (self.reader.line_num - 1) % 10001 == 0:
                yield l
                l.clear()
        yield l

@profile
def main():
    print("start")
    with open('test.csv') as f:
        data = Data(f)
        counter = 0
        rows = 0
        for d in data.read():
            rows += len(d)
            print(counter, len(d), d[0]['id'], rows)
            counter += 1

    print("done")

if __name__ == '__main__':
    main()