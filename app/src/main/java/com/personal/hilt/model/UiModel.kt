package com.personal.hilt.model

sealed class UiModel {
    data class Response (val catsDataResponseItem : CatsDataResponseItem) : UiModel ()
    data class ItemSeparator (val description : String) : UiModel ()
}