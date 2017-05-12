package vectors4s.methods

import vectors4s.base.AbstractVector

import scala.util.Random

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
case class RandomClusterer(config: Clusterer.Config) extends Clusterer {
  val random = new Random

  override def cluster[I, V <: AbstractVector[I, V], K](items: Map[K, V]): Clustering[K] =
    Clustering(config.nClusters, items.keys.map(key => (key, random.nextInt(config.nClusters))).toMap)
}
