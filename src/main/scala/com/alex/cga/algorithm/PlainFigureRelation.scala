package com.alex.cga
package algorithm

import figure.PlainFigure
import com.alex.cga.figure.plain.{Segment, Point}
import language.implicitConversions

class PlainFigureRelation[A <: PlainFigure](figure: A) {

  def R[B](figure2: B)(implicit algo: (A, B) => A#Relation) = algo(figure, figure2)
}

object PlainFigureRelation {
  implicit def pointToPlainFigureRelation(p: Point): PlainFigureRelation[Point] =
    new PlainFigureRelation(p)

  implicit def segmentToPlainFigureRelation(segment: Segment): PlainFigureRelation[Segment] =
    new PlainFigureRelation(segment)
}