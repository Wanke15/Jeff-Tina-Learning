import time

import psycopg2
from psycopg2 import pool


def connection_pool_test(test_num):
    start = time.time()
    all_tbl_name = []
    postgreSQL_pool = psycopg2.pool.SimpleConnectionPool(1, 2000, user="postgres",
                                                         password="password",
                                                         host="host.ip",
                                                         port="5432",
                                                         database="postgres")

    for idx, _ in enumerate(range(test_num)):
        ps_connection = postgreSQL_pool.getconn()
        cursor = ps_connection.cursor()
        cursor.execute("SELECT table_name FROM information_schema.tables WHERE table_schema='public' limit 5")
        for name in cursor.fetchall():
            all_tbl_name.append(name)
        postgreSQL_pool.putconn(ps_connection)
    print("Connection pool time consumed: ", time.time() - start)
    return all_tbl_name


def simple_db_connection_test(test_num):
    start = time.time()
    all_tbl_name = []
    for idx, _ in enumerate(range(test_num)):
        ps_connection = psycopg2.connect(user="postgres",
                                         password="zzc709394",
                                         host="10.2.113.110",
                                         port="5432",
                                         database="postgres")
        cursor = ps_connection.cursor()
        cursor.execute("SELECT table_name FROM information_schema.tables WHERE table_schema='public' limit 5")
        for name in cursor.fetchall():
            all_tbl_name.append(name)

    print("Simple time consumed: ", time.time() - start)
    return all_tbl_name


num = 20
res1 = connection_pool_test(num)
res2 = simple_db_connection_test(num)

assert res1 == res2

""" For my test database
Connection pool time consumed:  1.8410780429840088
Simple time consumed:  5.46538519859314
"""
