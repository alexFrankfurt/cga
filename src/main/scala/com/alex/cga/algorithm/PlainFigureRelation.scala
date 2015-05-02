package com.alex.cga
package algorithm

import geometry.plain.Figure

import scala.language.implicitConversions

class PlainFigureRelation[A <: Figure](figure: A) {

  def R[B](figure2: B)(implicit algorithm: (A, B) => A#Relation) = algorithm(figure, figure2)
}

object PlainFigureRelation {
  implicit def plainFigureToPlainFigureRelation[A <: Figure](f: A): PlainFigureRelation[A] =
    new PlainFigureRelation[A](f)
}
