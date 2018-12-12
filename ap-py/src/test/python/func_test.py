import os
import sys
import io
import unittest

sys.path.append(os.path.abspath('../../main/python'))

import func

class FuncTest(unittest.TestCase):
    def test_csv_to_json(self):
        test = "aaa,bbb,ccc\n111,222,333"
        expected = '[{"aaa": "111", "bbb": "222", "ccc": "333"}]'
        actual = func.csv_to_json(io.StringIO(test))

        self.assertEqual(expected, actual)

        expected = '[{}]'
        with open("testdata.csv") as datas:
            actual = func.csv_to_json(datas)

        self.assertEqual(expected, actual)

if __name__ == "__main__":
    unittest.main()
