package org.jang

fun my_dijkstra(graph : Map<String, List<Pair<String, Int>>>, start : String) : Map<String, Int>{
    val dist = mutableMapOf<String, Int>() // 각 노드, 최단거리 저장
    val prev = mutableMapOf<String, String?>() // 각 노드의 이전 노드 저장. 시작점은 null 이니까 String?
    val visited = mutableSetOf<String>() // 방문 완료한 노드 저장

    // 초기화 과정.
    for(node in graph.keys){
        dist[node] = Int.MAX_VALUE // 각 노드의 최단거리 max 값으로 초기화.
        prev[node] = null          // 각 노드의 이전 노드는 null
    }

    dist[start] = 0 // 시작 노드는 거리가 0. 이전은 null 그대로.

    while( visited.size < graph.size){  // 방문 노드가 graph 노드 길이와 같아질 때 까지 (즉 전부방문)

        val current = dist.filterKeys{it !in visited}.minByOrNull{it.value}?.key ?: break
        // dist 에서 key만 가져오기.
        // + visited 에속하지 않는
        // + 그 값이 min 최소값인.
        // 만약 null 이면 break. 즉 방문 안한 노드가 없을 때.

        for((neighbor, weight) in graph[current]?: emptyList()) // 인접노드명, 거리 가져옴. 없으면(독립노드) 빈 리스트 반환, 종료
        {
            val newDist = dist[current]!! + weight // 현재 거리에 거리 더해서 총 거리 계산
            if( newDist < dist[neighbor]!!)     // 기존 최단 거리보다 빠를 시 업데이트
            {
                dist[neighbor] = newDist
                prev[neighbor] = current    // current 노드를 통해 neighbor 에 도착한다는 뜻
            }
        }
        visited.add(current)    // 방문 완료 노드 기록
    }
    return dist
}

fun main() {
    // 그래프 생성 (노드 A, B, C, D, E)
    val graph = mapOf(
        "A" to listOf("B" to 4, "C" to 2),
        "B" to listOf("C" to 5, "D" to 10),
        "C" to listOf("E" to 3),
        "D" to listOf("E" to 4),
        "E" to listOf("A" to 7)
    )

    // 다익스트라 알고리즘 실행
    val startNode = "A"
    val result = my_dijkstra(graph, startNode)

    // 결과 출력
    println("시작 노드 $startNode 에서 각 노드까지의 최단 거리:")
    for ((node, distance) in result) {
        println("노드 $node: $distance")
    }
}
