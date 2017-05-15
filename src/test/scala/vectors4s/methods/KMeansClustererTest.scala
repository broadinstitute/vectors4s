package vectors4s.methods

import org.scalatest.FunSuite
import vectors4s.sparse.SparseVector

import scala.util.Random

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
final class KMeansClustererTest extends FunSuite {
  val random = new Random

  def nextRand(min: Double, max: Double): Double = min + (max - min) * random.nextDouble()

  test("clustering some sparse vectors") {
    val nGroups = 3
    val itemsGroupA =
      (0 to 9).map(i => ("A" + i, SparseVector.from("a" -> nextRand(1.0, 2.0), "b" -> nextRand(1.0, 2.0))))
    val itemsGroupB =
      (0 to 9).map(i => ("B" + i, SparseVector.from("a" -> nextRand(1.0, 2.0), "c" -> nextRand(1.0, 2.0))))
    val itemsGroupC =
      (0 to 9).map(i => ("C" + i, SparseVector.from("b" -> nextRand(1.0, 2.0), "c" -> nextRand(1.0, 2.0))))
    val items = (itemsGroupA ++ itemsGroupB ++ itemsGroupC).toMap
    for (nClusters <- 1 to 32) {
      val clusterer = KMeansClusterer(Clusterer.Config.default.copy(nClusters = nClusters))
      val clustering = clusterer.cluster[String, SparseVector[String], String](items)
      assert(clustering.asSetsOfSets.size >= Math.min(nClusters, nGroups))
      assert(clustering.asSetsOfSets.size <= Math.min(nClusters, items.size))
    }
  }

}
