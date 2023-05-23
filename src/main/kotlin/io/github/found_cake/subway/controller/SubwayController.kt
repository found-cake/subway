package io.github.found_cake.subway.controller

import io.github.found_cake.subway.subway.SubwayAPI
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/subway")
class SubwayController {

	@GetMapping("/check")
	fun check(): String {
		return "This is working"
	}

	@GetMapping("/info/{station}")
	fun getSubwayInfo(@PathVariable station: String): String {
		return SubwayAPI.getSubwayByStation(station).result
	}
}