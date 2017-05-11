package vec2d4s.apps

import vec2d4s.sparse.SparseVector

/**
  * LoamStream - Language for Omics Analysis Management
  * Created by oruebenacker on 5/10/17.
  */
object SparseApp extends App {

  val vec1 = SparseVector.from("a" -> 1, "b" -> 2, "c" -> 3)

  println(vec1.normalized.len)

}
