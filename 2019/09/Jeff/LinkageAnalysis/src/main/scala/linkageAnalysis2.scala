
object linkageAnalysis2 {
  def main(args: Array[String]): Unit = {
    val nas1 = NAStatCounter(10.0)
    nas1.add(2.1)
    println(nas1.toString())

    val nas2 = NAStatCounter(Double.NaN)
    nas2.add(2.1)
    println(nas2.toString())

  }
}
