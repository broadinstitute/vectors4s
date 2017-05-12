package vectors4s.methods

import vectors4s.base.AbstractVector

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
case class RoundRobinClusterer(config: Clusterer.Config = Clusterer.Config.default) extends Clusterer {
  override def cluster[I, V <: AbstractVector[I, V], K](items: Map[K, V]): Clustering[K] =
    Clustering(config.nClusters, items.keys.zipWithIndex.map {
      case (item, index) => (item, index % config.nClusters)
    }.toMap)
}
