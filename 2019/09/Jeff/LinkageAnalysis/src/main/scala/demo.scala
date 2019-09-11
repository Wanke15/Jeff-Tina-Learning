import org.apache.spark.util.StatCounter

object demo {
  def main(args: Array[String]): Unit = {
    val statCounter = new StatCounter()
    println(statCounter.toString)

    statCounter.merge(1)
    statCounter.merge(5)
    println(statCounter.toString)
  }
}
