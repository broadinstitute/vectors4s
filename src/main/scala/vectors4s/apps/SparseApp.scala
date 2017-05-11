package vectors4s.apps

import vectors4s.sparse.SparseVector

/**
  * vectors4s
  * Created by oruebenacker on 5/10/17.
  */
object SparseApp extends App {

  val vec1 = SparseVector.from("a" -> 1, "b" -> 2, "c" -> 3)

  println(vec1.normalized.len)

}
