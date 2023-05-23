package io.github.found_cake.subway.subway

import java.time.Instant

class SubwayResult {

	private var wait: Boolean = true
	private var searchTime: Long = 0
	var result: String = ""
		set(value) {
			wait = false
			searchTime = Instant.now().epochSecond
			field = value
		}
		get() = field

	fun needWait() : Boolean {
		return wait
	}

	fun isNeedAgain() : Boolean {
		val now: Long = Instant.now().epochSecond
		return now - searchTime >= 100
	}

}