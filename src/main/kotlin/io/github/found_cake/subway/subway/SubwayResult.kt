package io.github.found_cake.subway.subway

import java.time.Instant

class SubwayResult {

	var wait: Boolean = true;
	var searchTime: Int = 0

	fun isWait() : Boolean {
		return wait
	}

	fun isNeedAgain() : Boolean {
		val now: Long = Instant.now().epochSecond
		return now - searchTime > 150;
	}


}