package io.github.found_cake.subway.dataobject

data class Subway(
	val name: String,
	val line: Int,
	val type: String,
	val lastStation: String,
	val message: String
)