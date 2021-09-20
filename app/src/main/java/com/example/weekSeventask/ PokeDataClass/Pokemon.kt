package com.example.weekSeventask. PokeDataClass

import com.example.weekSeventask.PokeDataClass.Result

// this is the data class for pokemon API
data class Pokemon(
    val count: Int,
    val next: String,
    val previous: String,
    var results: ArrayList<Result>

)





















/**
 *      {
 *      count: 5,
 *      next: "yes",
 *      previous: "no",
 *      results: [{
 *          name: "MQ",
 *          url: "https://ghfjjfkf"
 *      }]
 *      }
 */