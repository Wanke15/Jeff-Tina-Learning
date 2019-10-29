### Pyspark 提交到 YARN 上时的一些注意事项

先说一下在我的某个应用上创建sparkContext 和sqlContext 最终的比较好的配置：
```python
import pyspark
conf = pyspark.SparkConf().setAll([('spark.executor.memory', '6g'), ('spark.executor.cores', '10'), ('spark.cores.max', '15'), ('spark.driver.memory','64g'),
                                   ('spark.app.name', 'Night spots mining new'),
                                   ('spark.dynamicAllocation.enabled', 'true'), ('spark.dynamicAllocation.minExecutors', '4'), ('spark.dynamicAllocation.maxExecutors', '20')])
sc = pyspark.SparkContext(conf=conf)
sqlContext = pyspark.sql.SQLContext(sc)

```
##### 注意事项：
- Pyspark 应用提交时如果按照如下的默认配置（不显式声明）：
```python
import pyspark
sc = pyspark.SparkContext()
sqlContext = pyspark.sql.SQLContext(sc)
```
这时默认是开启了动态资源分配**spark.dynamicAllocation.enabled => 'true'**的(可以通过**print(sc.getConf().getAll())**查看)，而且又因为没有声明**spark.dynamicAllocation.minExecutors**和**spark.dynamicAllocation.maxExecutors**，因此在数据量比较大的情况下可能造成YARN集群的资源几乎被全部占用，其他新的Job就没有资源可供分配。

另一方面在实践中如果手动设置executors的数量，太少的话又不能充分利用集群的资源，因此可能比较好的方式是设置动态分配的同时，再设置一下最小和最大的executors数量，也就是最上边的推荐设置。
