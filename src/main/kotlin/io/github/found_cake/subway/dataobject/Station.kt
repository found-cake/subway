package io.github.found_cake.subway.dataobject

data class Station (
	val name: String,
	val subways: MutableList<Subway>
)