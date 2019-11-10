import time

import asyncio
import requests

from tqdm import tqdm


async def async_request(test_url, test_num):
    start = time.time()
    loop = asyncio.get_event_loop()
    # loop.run_in_executor(None, requests.get, test_url) returns a 'future' object
    futus = [loop.run_in_executor(None, requests.get, test_url) for _ in range(test_num)]

    # await 'future' object created last step
    resps = [await _fu for _fu in tqdm(futus)]
    end = time.time()
    for _ in resps:
        assert _.status_code == 200, "Request error!"
    print("Async time: ", end - start)


def naive_request(test_url, test_num):
    start = time.time()
    resps = []
    for i in tqdm(range(test_num)):
        _resp = requests.get(test_url)
        resps.append(_resp)
    end = time.time()
    for _ in resps:
        assert _.status_code == 200, "Request error!"
    print("Naive time: ", end - start)


test_num = 1000
test_get_url = 'https://www.baidu.com/'


loop = asyncio.get_event_loop()
loop.run_until_complete(async_request(test_url=test_get_url, test_num=test_num))

naive_request(test_url=test_get_url, test_num=test_num)
