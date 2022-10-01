package com.example.fragmentsnew.data.retrofit.chuckList

data class JokesListDataClass (
    val type: String,
    val value: List<JokesRandomMulti>)


data class JokesRandomMulti(
    val id:Int,
    val joke:String
)
data class JokeGik(
    val joke:String
)

/*
{ "type": "success",
 "value":
 [
 { "id": 449,
 "joke": "All arrays Chuck Norris declares are of infinite size, because Chuck Norris knows no bounds.",
  "categories": ["nerdy"] },
  { "id": 193,
  "joke": "Most boots are made for walkin'. Chuck Norris' boots ain't that merciful.",
  "categories": [] },
  { "id": 171,
  "joke": "Chuck Norris can set ants on fire with a magnifying glass. At night.",
   "categories": [] }
   ]

   }

 */