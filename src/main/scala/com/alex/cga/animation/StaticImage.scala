package com.alex.cga.animation

import com.alex.cga.figure.plain.{ConcavePolygon, ConvexPolygon}


case class StaticImage(
    convexPolygon: ConvexPolygon,
    concavePolygon: ConcavePolygon)
