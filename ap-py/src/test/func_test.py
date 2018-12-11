import os
import sys

sys.path.append(os.path.abspath('../main'))

import func

with open("testdata.csv") as datas:
    jsonstr = func.csv_to_json(datas)

print(jsonstr)

b64 = func.str_to_gzip(jsonstr)
print(b64)