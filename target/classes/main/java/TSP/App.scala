package TSP

import java.io
import java.nio.file._
import java.nio.charset._

/**
 * Run the solver on a particular graph for exemplary purposes.
 */
object App {
  val ant = new Ant with Ant.Defaults
  val colony = new Colony(ant) with Colony.Defaults

  /**
   * The scale factor is used to scale the location coordinates of points to
   * produce a reasonably-sized image.
   */
  def nodeAttr(
    scaleFactor: Double, lookup: ant.Node => Point
  )(node: ant.Node)
      : Map[String, String] =
  {
    val p = lookup(node)
    val sx = p.x / scaleFactor
    val sy = p.y / scaleFactor
    Map("pos" -> s"""\"$sx,$sy!\"""")
  }

  /**
   * The solver progress is recorded in CSV format with a single header row.
   *
   * Columns are:
   *
   * {{{
   *   iteration index, mean cost per iteration, best solution cost so far
   * }}}
   */
  def prepareLog(fileName: String): io.BufferedWriter = {
    val log = Files.newBufferedWriter(Paths.get(fileName), Charset.forName("UTF-8"))
    log.write("iteration,iteration-mean-cost,best-cost\n")
    log
  }

  /**
   * Log solver progress to stderr and to the log.
   *
   * The format matches that described in [[prepareLog]].
   */
  def loggingListener(log: io.BufferedWriter)(progress: colony.Progress): Unit = {
    def formatCost(c: Double): String =
      "%.1f".format(c)

    import progress._
    val iterationMean = costMeans(iterationCount)

    Console.err.println(
      s"#$iterationCount: ${formatCost(bestSolution.cost)}/${formatCost(iterationMean)}")

    log.write(
      s"$iterationCount,${formatCost(iterationMean)},${formatCost(bestSolution.cost)}\n")
  }

  def main(args: Array[String]): Unit = {
    val log = prepareLog("travelling-ants.log")

    val lookup = {
      val nodeMap = Nodes.toMap
      (n: Int) => nodeMap.apply(n)
    }

    val graph = ConnectedGraph(Nodes.map(_._1)) { (a, b) =>
      Point.distance(lookup(a), lookup(b))
    }

    val solution = colony.solve(
      graph,
      iterations = 100,
      Some(loggingListener(log))).run

    val output = DirectedGraph.formatAsDot(
      solution.steps.toSet,
      Some(nodeAttr(scaleFactor = 280.0, lookup) _))

    println(output)
    log.flush()
  }

  /**
   * Sample problem. The solver can be invoked with an arbitrary set of nodes.
   */
  final val Nodes = Seq(
    1-> Point(2.0, 0.0),
    2-> Point(1.0, 0.0),
    3-> Point(0.0, 0.0),
    4-> Point(3.0, 0.0),
    5-> Point(4.0, 0.0))
}
