package vectors4s.methods

import vectors4s.base.AbstractVector

/**
  * vectors4s
  * Created by oruebenacker on 5/11/17.
  */
trait Clusterer {
  def cluster[I, V <: AbstractVector[I, V], K](items: Map[K, V]): Clustering[K]
}

object Clusterer {

  case class Config(nClusters: Int, nIterations: Int) {
    def withNClusters(nClusters: Int): Config = copy(nClusters = nClusters)

    def withNIterations(nNIterations: Int): Config = copy(nIterations = nIterations)
  }

  object Config {
    val default = Config(5, 10)
  }

}
