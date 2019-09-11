// import required spark classes
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

import java.lang.Double.isNaN

// define main method (Spark entry point)
object linkageAnalysis {
  def isHeader(l: String): Boolean = {
    l.contains("id_1")
  }

  def toDouble(s: String): Double = {
    if ("?".equals(s)) Double.NaN else s.toDouble
  }

  case class MatchData(id1: Int, id2: Int, scores: Array[Double], matched: Boolean)

  def parse(line: String): MatchData = {
    val pieces = line.split(",")

    val id1 = pieces(0).toInt
    val id2 = pieces(1).toInt

    val scores = pieces.slice(2, 11).map(toDouble)

    val matched = pieces(11).toBoolean

    // (id1, id2, scores, matched)
    MatchData(id1, id2, scores, matched)
  }

  def main(args: Array[String]) {

    // initialise spark context
    val conf = new SparkConf().setAppName("test").setMaster("local[*]").set("spark.executor.memory","6g")
    val spark: SparkSession = SparkSession.builder.config(conf).getOrCreate()
    val sc = spark.sparkContext

    val rawblocks = sc.textFile("data/linkage")

    val head = rawblocks.take(10)

    val line = head(5)

    val tup = parse(line)

    // println(tup._1, tup._2, tup._4)
    // tup._3.foreach(println)
    // println(tup.id1, tup.id2, tup.matched)
    // tup.scores.foreach(println)

    val mds = head.filter(x => !isHeader(x)).map(parse)


    val noheader = rawblocks.filter(x => !isHeader(x))
    val parsed = noheader.map(line => parse(line))
//    val grouped = parsed.groupBy(x => x.matched)
//    grouped.mapValues(x => x.size).foreach(println)

    // grouped.foreach(println)

    // 创建直方图
    val matchCounts = parsed.map(x => x.matched).countByValue()

    matchCounts.foreach(println)

    matchCounts.toSeq.sortBy(_._1).foreach(println)
    matchCounts.toSeq.sortBy(_._2).foreach(println)

    matchCounts.toSeq.sortBy(_._2).reverse.foreach(println)

    // 连续变量的概要统计
    println(parsed.map(x => x.scores(0)).stats())

    println(parsed.map(x => x.scores(0)).filter(!isNaN(_)).stats())

    // terminate spark context
    spark.stop()
  }
}
