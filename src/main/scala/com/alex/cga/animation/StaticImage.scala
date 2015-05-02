package com.alex.cga.animation

import com.alex.cga.geometry.plain.{ConcavePolygon, ConvexPolygon}


case class StaticImage(
    convexPolygon: ConvexPolygon,
    concavePolygon: ConcavePolygon)
