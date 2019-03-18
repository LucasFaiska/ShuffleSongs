package com.lfaiska.shufflesongs.network

import android.os.AsyncTask
import com.lfaiska.shufflesongs.network.entity.PlaylistResponse
import com.lfaiska.shufflesongs.network.entity.SongResponse
import java.io.BufferedReader
import java.net.HttpURLConnection
import java.net.URL
import java.io.InputStreamReader
import org.json.JSONObject

class PlaylistService(val handler: (playlistResponse: PlaylistResponse) -> Unit) : AsyncTask<String, Void, PlaylistResponse>() {

    var baseUrl = "https://us-central1-tw-exercicio-mobile.cloudfunctions.net/lookup?id={artistId}&limit5"

    override fun doInBackground(vararg artistId: String): PlaylistResponse? {
        val requestUrl = buildRequestUrl(artistId)

        try {
            val connection = performRequest(requestUrl)
            if (connection.responseCode != HttpURLConnection.HTTP_OK) {
                return null
            }
            val response = readResponse(connection)
            return deserializeResponse(response)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun buildRequestUrl(artistId: Array<out String>): String {
        val requestParameters = artistId.joinToString()
        return baseUrl.replace("{artistId}", requestParameters).removeWhiteSpaces()
    }

    fun String.removeWhiteSpaces() = this.replace("\\s".toRegex(), "")

    private fun performRequest(requestUrl: String): HttpURLConnection {
        val url = URL(requestUrl)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.connect()
        return connection
    }

    private fun readResponse(connection: HttpURLConnection): String? {
        val reader: BufferedReader?
        val inputStream = connection.inputStream ?: return null
        reader = BufferedReader(InputStreamReader(inputStream))
        return reader.readLines().joinToString("\n")
    }

    fun deserializeResponse(response: String?): PlaylistResponse {
        val songResponseList = mutableListOf<SongResponse>()
        val responseJson = JSONObject(response)
        val resultsJson = responseJson.getJSONArray("results")

        for (i in 0..(resultsJson.length() - 1)) {
            val item = resultsJson.getJSONObject(i)

            if (item.isSongResponse()) {
                songResponseList.add(item.toSongResponse())
            }
        }

        return PlaylistResponse(songResponseList)
    }

    private fun JSONObject.isSongResponse() = this.getString("wrapperType") == "track"

    private fun JSONObject.toSongResponse() = SongResponse(
            this.getString("artworkUrl"),
            this.getString("artistId"),
            this.getString("artistName"),
            this.getString("trackName"),
            this.getString("primaryGenreName"))

    override fun onPostExecute(result: PlaylistResponse) {
        handler(result)
    }
}