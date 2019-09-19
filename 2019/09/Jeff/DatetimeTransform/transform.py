import time
from datetime import datetime


def stamp2date(stamp):
    if not isinstance(stamp, int):
        stamp = int(stamp)
    _date = datetime.fromtimestamp(stamp).strftime("%Y-%m-%d %H:%M:%S")
    return _date


def date2stamp(date):
    _stamp = time.mktime(time.strptime(date, '%Y-%m-%d %H:%M:%S'))
    return _stamp


print(stamp2date(1561415716))
print(date2stamp(stamp2date(1561415716)))
