import csv
import json
import gzip
import base64

def csv_to_json(datas):
    result = [json.dumps(l, ensure_ascii=False) for l in csv.DictReader(datas)]
    return "[" + ",".join(result)+"]"

def str_to_gzip(datas):
    gz = gzip.compress(datas.encode())
    return base64.b64encode(gz).decode()