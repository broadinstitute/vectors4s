package vectors4s.util

import vectors4s.AbstractVector

/**
  * vectors4s
  * Created by oruebenacker on 5/11/17.
  */
object TestUtil {

  case class Measure(epsilon: Double, scale: Double) {
    override def toString: String = s"(epsilon=$epsilon, scale=$scale)"
  }

  val defaultMeasure = Measure(1e-12, 1.0)

  def assertDoubleAlmostZero(x: Double)(implicit measure: Measure = defaultMeasure): Unit = {
    assert(Math.abs(x / measure.scale) < measure.epsilon, s"$x is not approximately zero with $measure.")
  }

  def assertVectorAlmostZero[I, V <: AbstractVector[I, V]]
  (vector: V)(implicit measure: Measure = defaultMeasure): Unit = {
    vector.indices.foreach { index =>
      assertDoubleAlmostZero(vector.apply(index))
    }
  }

  def assertDoublesAlmostEqual(x: Double, y: Double)(implicit measure: Measure = defaultMeasure): Unit = {
    assert(Math.abs((x - y) / measure.scale) < measure.epsilon,
      s"$x and $y are not approximately equal with $measure.")
  }

  def assertVectorsAlmostEqual[I, V <: AbstractVector[I, V]]
  (vector1: V, vector2: V)(implicit measure: Measure = defaultMeasure): Unit = {
    assertVectorAlmostZero[I, V](vector1 - vector2)
  }

  def assertVector[I, V <: AbstractVector[I, V]](v: V): Unit = {
    assertVectorsAlmostEqual[I, V](v + v, v * 2.0)
    assertVectorsAlmostEqual[I, V](v + v + v, v * 3.0)
    assertVectorsAlmostEqual[I, V](v * 2.0 + v * 3.0, v * 5.0)
    assertVectorsAlmostEqual[I, V](v * 2.0 + v * 3.0, v * 5.0)
    assertVectorsAlmostEqual[I, V](v + (-v), v - v)
    assertVectorsAlmostEqual[I, V](v * (-1.0), -v)
    assertVectorsAlmostEqual[I, V](v * 0.0, v.zero)
    assertVectorsAlmostEqual[I, V](v - v, v.zero)
    assertDoublesAlmostEqual(v.len * v.len, v.lenSquared)
    assertDoubleAlmostZero((v * 0.0).len)
    assertDoubleAlmostZero(v.zero.len)
    assertDoublesAlmostEqual((v * 5.0).len, 5.0 * v.len)
    assertDoublesAlmostEqual(v.normalized.len, 1.0)
  }

  def assertVectors[I, V <: AbstractVector[I, V]](v1: V, v2: V, vSum: V): Unit = {
    Seq(v1, v2, vSum).foreach(assertVector[I, V])
    assertVectorsAlmostEqual[I, V](v1 + v2, vSum)
    assertVectorsAlmostEqual[I, V](v2 + v1, vSum)
    assertVectorsAlmostEqual[I, V](vSum - v1, v2)
    assertVectorsAlmostEqual[I, V](vSum - v2, v1)
    assertVectorsAlmostEqual[I, V](-v1 + vSum, v2)
    assertVectorsAlmostEqual[I, V](-v2 + vSum, v1)
  }


}
