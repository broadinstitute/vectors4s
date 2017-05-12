package vectors4s.methods

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
case class Clustering[D](nClusters: Int, clusters: Map[D, Int]) {
  def asSetsOfSets: Set[Set[D]] = clusters.groupBy(_._2).map(_._2.keySet).toSet

  def indexToMembers: Map[Int, Set[D]] = clusters.groupBy(_._2).mapValues(_.keySet).view.force
}
