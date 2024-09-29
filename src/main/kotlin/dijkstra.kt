package org.jang

fun dijkstra(graph: Map<String, List<Pair<String, Int>>>, start: String): Map<String, Int> {
    val dist = mutableMapOf<String, Int>() // 거리 배열
    val prev = mutableMapOf<String, String?>() // 선택 경로 배열
    val visited = mutableSetOf<String>() // 방문한 노드

    // 초기화
    for (node in graph.keys) {
        dist[node] = Int.MAX_VALUE // 무한대로 초기화
        prev[node] = null // 이전 노드 없음
    }
    dist[start] = 0 // 시작 노드의 거리 0

    while (visited.size < graph.size) {
        // 가장 짧은 거리의 노드를 선택
        val current = dist.filterKeys { it !in visited } // key 만 뽑아낸다. / visited 에없는 / 그 key 리스트의 값(거리)중에서 min값의 key 가져오기. / 없으면 break. ( 여기서 null 이라는건 방문안한 노드가 없다는 뜻 )
            .minByOrNull { it.value }?.key ?: break
        // current = "A" 처럼 노드이름. Key.

        visited.add(current) // 현재 노드 방문

        // 연결된 노드에 대해 거리 업데이트
        for ((neighbor, weight) in graph[current] ?: emptyList()) { // graph[Key] = "A", 3 노드이름, 거리
            val newDist = dist[current]!! + weight // dist[n] 의 값이 nullable 이기 때문에 !!
            if (newDist < dist[neighbor]!!) { // 새 거리가 기존 dist[목적지] 보다 작다면 최단거리 최신화
                dist[neighbor] = newDist
                prev[neighbor] = current // 이전 노드 업데이트
            }
        }
    }

    return dist // 최단 거리 결과 반환
}

