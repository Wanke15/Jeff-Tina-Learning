import linkageAnalysis.{isHeader, parse}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object linkageAnalysis2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("test").setMaster("local[*]").set("spark.executor.memory","6g")
    val spark: SparkSession = SparkSession.builder.config(conf).getOrCreate()
    val sc = spark.sparkContext

    val rawblocks = sc.textFile("data/linkage")
    val noheader = rawblocks.filter(x => !isHeader(x))
    val parsed = noheader.map(line => parse(line))

    val nas1 = NAStatCounter(10.0)
    nas1.add(2.1)
    // println(nas1.toString())

    val nas2 = NAStatCounter(Double.NaN)
    nas2.add(2.1)
    // println(nas2.toString())

    val arr = Array(1.0, Double.NaN, 17.29)
    val nas = arr.map(n => NAStatCounter(n))

    //nas.foreach(println)

    val nasRDD = parsed.map(md => {md.scores.map(d => NAStatCounter(d))})

    nasRDD.take(2).foreach(x => x.foreach(println))

    val reduced = nasRDD.reduce((n1, n2) => {n1.zip(n2).map { case (a, b) => a.merge(b)}})
    reduced.take(2).foreach(println)

  }
}
