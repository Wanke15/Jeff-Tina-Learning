// import required spark classes
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

// define main method (Spark entry point)
object helloWorld {
  def main(args: Array[String]) {

    // initialise spark context
    val conf = new SparkConf().setAppName("test").setMaster("local[*]").set("spark.executor.memory","2g")
    val spark: SparkSession = SparkSession.builder.config(conf).getOrCreate()
    val sc = spark.sparkContext

    val rawblocks = sc.textFile("data/linkage")

    val head = rawblocks.take(10)

    head.foreach(println)

    // terminate spark context
    spark.stop()

  }
}
