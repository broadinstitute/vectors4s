package vectors4s.sparse

import vectors4s.base.AbstractVector

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 5/10/17.
  */
case class SparseVector[I](coeffs: Map[I, Double]) extends AbstractVector[I, SparseVector[I]] {

  override def indices: Iterable[I] = coeffs.keys
  override def apply(index: I): Double = coeffs.getOrElse(index, 0.0)

  override def *(a: Double): SparseVector[I] = SparseVector(coeffs.mapValues(_*a).view.force)
  override def /(a: Double): SparseVector[I] = SparseVector(coeffs.mapValues(_/a).view.force)

  override def unary_- : SparseVector[I] = SparseVector(coeffs.mapValues(-_).view.force)

  override def +(o: SparseVector[I]): SparseVector[I] = {
    val allKeys = coeffs.keySet ++ o.coeffs.keySet
    SparseVector(allKeys.map(key => (key, coeffs.getOrElse(key, 0.0) + o.coeffs.getOrElse(key, 0.0))).toMap)
  }

  override def -(o: SparseVector[I]): SparseVector[I] = {
    val allKeys = coeffs.keySet ++ o.coeffs.keySet
    SparseVector(allKeys.map(key => (key, coeffs.getOrElse(key, 0.0) - o.coeffs.getOrElse(key, 0.0))).toMap)
  }

  override def lenSquared: Double = coeffs.mapValues(coeff => coeff*coeff).values.sum

  override def normalized: SparseVector[I] = super.normalized

  override def zero: SparseVector[I] = SparseVector.zero
}

object SparseVector {
  def from[I](coeffs: (I, Double)*): SparseVector[I] = SparseVector[I](coeffs.toMap)
  def zero[I]: SparseVector[I] = SparseVector[I](Map.empty)
}
