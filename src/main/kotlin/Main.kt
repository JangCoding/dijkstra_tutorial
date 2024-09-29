package org.jang

fun my_dijkstra(graph : Map<String, List<Pair<String, Int>>>, start : String) : Map<String, Int>{
    val dist = mutableMapOf<String, Int>()
    val prev = mutableMapOf<String, String?>() // 시작점은 null 이니까
    val visited = mutableSetOf<String>()

    for(node in graph.keys){
        dist[node] = Int.MAX_VALUE
        prev[node] = null
    }

    dist[start] = 0

    while( visited.size < graph.size){
        val current = dist.filterKeys{it !in visited}.minByOrNull{it.value}?.key ?: break

        for((neighbor, weight) in graph[current]?: emptyList())
        {
            val newDist = dist[current]!! + weight
            if( newDist < dist[neighbor]!!)
            {
                dist[neighbor] = newDist
                prev[neighbor] = current
            }
        }
        visited.add(current)
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
