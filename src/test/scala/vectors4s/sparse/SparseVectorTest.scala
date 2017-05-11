package vectors4s.sparse

import org.scalatest.FunSuite
import vectors4s.util.TestUtil.assertVectors

/**
  * vectors4s
  * Created by oruebenacker on 5/11/17.
  */
final class SparseVectorTest extends FunSuite {

  private val v1 = SparseVector.from("a" -> 1.0, "b" -> 2.0)
  private val v2 = SparseVector.from("a" -> 3.0, "c" -> 4.0)
  private val vSum = SparseVector.from("a" -> 4.0, "b" -> 2.0, "c" -> 4.0)

  test("v1, v2, vSum") {
    assertVectors[String, SparseVector[String]](v1, v2, vSum)
  }
}
