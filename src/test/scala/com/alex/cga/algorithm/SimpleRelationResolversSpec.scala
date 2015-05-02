package com.alex.cga
package algorithm

import SimpleRelationResolvers._
import Container.{cp, seg}
import org.scalatest.{Matchers, FlatSpec}
import geometry.plain.Segment

class SimpleRelationResolversSpec extends  FlatSpec with Matchers{
  "segment" should "intersect polygon" in {
    val a = (seg intersect cp).orNull
    a.x should be (3)
  }
}
