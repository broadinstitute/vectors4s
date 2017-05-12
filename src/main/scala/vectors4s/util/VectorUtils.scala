package vectors4s.util

import vectors4s.base.AbstractVector

/**
  * vectors4s
  * Created by oliverr on 5/12/2017.
  */
object VectorUtils {

  def average[I, V <: AbstractVector[I, V]](vectors: Iterable[V]): V =
    vectors.fold(vectors.head.zero)(_ + _) / vectors.size

}
