package com.shahin.deezer.data.models.album

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.shahin.deezer.data.models.artist.Artist
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(

    @field:SerializedName("md5_image")
    val md5Image: String,

    /**
     * @param nbTracks this parameter is visible through general search
     * @see generalSearch
     * however it's not being received though Albums query
     * @see queryForAlbums
     * which is unfortunate!?
     */
    @field:SerializedName("nb_tracks")
    val nbTracks: Int,

    @field:SerializedName("tracklist")
    val tracklist: String,

    /**
     * @param artist this parameter is visible through general search
     * @see generalSearch
     * however it's not being received though Albums query
     * @see queryForAlbums
     * which is unfortunate!?
     */
    @field:SerializedName("artist")
    val artist: Artist? = null,

    @field:SerializedName("link")
    val link: String,

    @field:SerializedName("cover_small")
    val coverSmall: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("genre_id")
    val genreId: Int,

    @field:SerializedName("record_type")
    val recordType: String,

    @field:SerializedName("cover")
    val cover: String,

    @field:SerializedName("cover_xl")
    val coverXl: String,

    @field:SerializedName("cover_medium")
    val coverMedium: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("cover_big")
    val coverBig: String,

    @field:SerializedName("fans")
    val fans: Int? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,
) : Parcelable