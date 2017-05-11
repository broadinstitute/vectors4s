package vectors4s

/**
  * vectors4s
  * Created by oruebenacker on 5/11/17.
  */
trait AbstractVector[I, V <: AbstractVector[I, V]] {

  def indices: Iterable[I]

  def apply(index: I): Double

  def *(a: Double): V

  def /(a: Double): V = *(1.0 / a)

  def unary_- : V = *(-1.0)

  def +(o: V): V

  def -(o: V): V = this.+(-o)

  def lenSquared: Double = indices.map(index => apply(index)).map(x => x * x).sum

  def len: Double = Math.sqrt(lenSquared)

  def normalized: V = /(len)

  def zero: V
}
