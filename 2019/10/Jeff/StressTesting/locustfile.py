# locustfile.py

from locust import HttpLocust, TaskSet, task


class UserBehavior(TaskSet):
    @task
    def sleep(self):
        self.client.get("/sleep")

    @task
    def sleep2(self):
        self.client.get("/sleep2")


class WebsiteUser(HttpLocust):
    task_set = UserBehavior
    min_wait = 0  # ms
    max_wait = 500


# locust --host=http://localhost:8888