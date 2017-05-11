package vectors4s.sparse

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 5/10/17.
  */
case class SparseVector[T](coeffs: Map[T, Double]) {

  def *(a: Double): SparseVector[T] = SparseVector(coeffs.mapValues(_*a).view.force)
  def /(a: Double): SparseVector[T] = SparseVector(coeffs.mapValues(_/a).view.force)

  def unary_- : SparseVector[T] = SparseVector(coeffs.mapValues(-_).view.force)

  def +(o: SparseVector[T]): SparseVector[T] = {
    val allKeys = coeffs.keySet ++ o.coeffs.keySet
    SparseVector(allKeys.map(key => (key, coeffs.getOrElse(key, 0.0) + o.coeffs.getOrElse(key, 0.0))).toMap)
  }

  def -(o: SparseVector[T]): SparseVector[T] = {
    val allKeys = coeffs.keySet ++ o.coeffs.keySet
    SparseVector(allKeys.map(key => (key, coeffs.getOrElse(key, 0.0) - o.coeffs.getOrElse(key, 0.0))).toMap)
  }

  def lenSquared: Double = coeffs.mapValues(coeff => coeff*coeff).values.sum

  def len: Double = Math.sqrt(lenSquared)

  def normalized: SparseVector[T] = /(len)

}

object SparseVector {
  def from[T](coeffs: (T, Double)*): SparseVector[T] = SparseVector[T](coeffs.toMap)
  def zero[T]: SparseVector[T] = SparseVector[T](Map.empty)
}
