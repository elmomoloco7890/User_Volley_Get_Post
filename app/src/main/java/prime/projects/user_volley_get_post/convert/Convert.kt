package prime.projects.user_volley_get_post.convert




/*
data class Movie(
    var title: String? = null,
    var rating: Int = 0,
    var year: Int = 0
)*/

/*
private fun getData() {

    val jsonArrayRequest = JsonArrayRequest(url, object : Listener<JSONArray?>() {
        fun onResponse(response: JSONArray) {
            for (i in 0 until response.length()) {
                try {
                    val jsonObject: JSONObject = response.getJSONObject(i)
                    val movie = Movie()
                    movie.setTitle(jsonObject.getString("title"))
                    movie.setRating(jsonObject.getInt("rating"))
                    movie.setYear(jsonObject.getInt("releaseYear"))
                    movieList.add(movie)
                } catch (e: JSONException) {
                    e.printStackTrace()
                    progressDialog.dismiss()
                }
            }
            adapter.notifyDataSetChanged()
            progressDialog.dismiss()
        }
    }, object : ErrorListener() {
        fun onErrorResponse(error: VolleyError) {
            Log.e("Volley", error.toString())
            progressDialog.dismiss()
        }
    })
    val requestQueue = Volley.newRequestQueue(this)
    requestQueue.add<Any>(jsonArrayRequest)
}*/
