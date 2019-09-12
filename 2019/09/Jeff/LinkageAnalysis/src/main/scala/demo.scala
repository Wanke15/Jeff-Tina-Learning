import org.apache.spark.util.StatCounter

object demo {
  def main(args: Array[String]): Unit = {
    val statCounter = new StatCounter()
    //println(statCounter.toString)

    statCounter.merge(1)
    statCounter.merge(5)
    //println(statCounter.toString)

    val nas3 = Array(1.0, Double.NaN).map(d => NAStatCounter(d))
    val nas4 = Array(Double.NaN, 2.0).map(d => NAStatCounter(d))

    nas3.foreach(println)
    println("*************")
    nas4.foreach(println)

    nas3.zip(nas4).foreach(println)
    println("##############")
    val merged = nas3.zip(nas4).map(p => p._1.merge(p._2))
    merged.foreach(println)
    println("@@@@@@@@@@@@@@@")
    val merged2 = nas3.zip(nas4).map {case (a, b) => a.toString + "; " + b.toString }
    merged2.foreach(println)
  }
}
