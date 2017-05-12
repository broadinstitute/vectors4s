package vectors4s.methods

import org.scalatest.FunSuite
import vectors4s.sparse.SparseVector

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
final class KMeansClustererTest extends FunSuite {
  test("clustering some sparse vectors") {
    val itemsGroupA = (0 to 9).map(i => ("A" + i, SparseVector.from("a" -> (1.0 + i / 10.0), "b" -> (2.0 - i / 10.0))))
    val itemsGroupB = (0 to 9).map(i => ("B" + i, SparseVector.from("a" -> (2.0 - i / 10.0), "c" -> (1.0 + i / 10.0))))
    val itemsGroupC = (0 to 9).map(i => ("C" + i, SparseVector.from("b" -> (1.0 + i / 10.0), "c" -> (2.0 - i / 10.0))))
    val items = (itemsGroupA ++ itemsGroupB ++ itemsGroupC).toMap
    for (nClusters <- 1 to 32) {
      val clusterer = KMeansClusterer(Clusterer.Config.default.copy(nClusters = nClusters))
      val clustering = clusterer.cluster[String, SparseVector[String], String](items)
      assert(clustering.asSetsOfSets.size == Math.min(nClusters, items.size))
    }
  }

}
