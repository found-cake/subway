package io.github.found_cake.subway.subway

import io.github.found_cake.subway.config.Config
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

object SubwayAPI {

	private val subwayInfos: MutableMap<String, SubwayResult> = mutableMapOf()

	private const val URL = "http://swopenapi.seoul.go.kr/api/subway/${Config.KEY}/json/realtimeStationArrival/0/${Config.MAX_INDEX}/"

	fun getSubwayByStation(station: String): SubwayResult{
		if(subwayInfos.containsKey(station)){
			val result: SubwayResult = subwayInfos[station]!!
			if(result.needWait()){
				runBlocking {
					while(result.needWait()){
						Thread.sleep(1000)
					}
				}
				return result
			}
			if(!result.isNeedAgain()){
				return result
			}
		}
		val result = SubwayResult()
		subwayInfos[station] = result
		runBlocking {
			val response:ResponseEntity<String> = RestTemplate().exchange(URL + station, HttpMethod.GET, null, String::class.java)
			if(response.body == null) {
				result.result = "NULL!!!"
			}else if(response.statusCode.value() != 200) {
				result.result = "error code : ${response.statusCode.value()}"
			}else {
				result.result = response.body!!
			}
		}
		return result
	}
}