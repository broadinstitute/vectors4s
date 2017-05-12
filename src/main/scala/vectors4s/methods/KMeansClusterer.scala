package vectors4s.methods

import vectors4s.base.AbstractVector
import vectors4s.util.VectorUtils

/**
  * vectors4s
  * Created by oruebenacker on 5/11/17.
  */
case class KMeansClusterer(config: Clusterer.Config = Clusterer.Config.default) extends Clusterer {
  val initialClusterer = RoundRobinClusterer(config)

  override def cluster[I, V <: AbstractVector[I, V], K](items: Map[K, V]): Clustering[K] = {
    var clustering = initialClusterer.cluster[I, V, K](items)
    if (items.size > config.nClusters) {
      for (_ <- 0 until config.nIterations) {
        val centroids = clustering.indexToMembers.mapValues(_.map(items)).mapValues(VectorUtils.average[I, V])
        val newMemberships = items.map { case (item, vItem) =>
          val iCentroidClosest =
            centroids.minBy { case (_, vCentroid) => (vItem - vCentroid).lenSquared }._1
          (item, iCentroidClosest)
        }
        clustering = Clustering(clustering.nClusters, newMemberships)
      }
    }
    clustering
  }

}
