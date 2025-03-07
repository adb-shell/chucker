@file:JvmName("OkHttpUtils")

package com.chuckerteam.chucker.internal.support

import okhttp3.Headers
import okhttp3.Request
import okhttp3.Response

internal val Response.contentLength: Long
    get() {
        return this.header("Content-Length")?.toLongOrNull() ?: -1
    }

internal val Response.isChunked: Boolean
    get() {
        return this.header("Transfer-Encoding").equals("chunked", ignoreCase = true)
    }

internal val Response.contentType: String?
    get() {
        return this.header("Content-Type")
    }

/** Checks if the OkHttp response uses gzip encoding. */
internal val Response.isGzipped: Boolean
    get() {
        return this.headers().containsGzip
    }

/** Checks if the OkHttp request uses gzip encoding. */
internal val Request.isGzipped: Boolean
    get() {
        return this.headers().containsGzip
    }

private val Headers.containsGzip: Boolean
    get() {
        return this["Content-Encoding"].equals("gzip", ignoreCase = true)
    }
