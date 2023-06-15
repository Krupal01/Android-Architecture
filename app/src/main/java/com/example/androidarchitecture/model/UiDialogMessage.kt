package com.example.androidarchitecture.model

data class UiDialogMessage(
    val message: String? = null,
    val messageRes: Int? = null,
    val params: Array<String>? = null,
    val okAction: (()->Unit)? = null,
    val cancelAction: (()->Unit)? = null,
    val uiDismissAction: (() -> Unit)? = null,

    /* how we can use params
    in XML :
    String.format(getString(R.string.please_check_internet_connection), arrayOf("John", "25", "New York"))

    in Compose :
    stringResource(id = it.resMessage,*(it.params))
    */
)
